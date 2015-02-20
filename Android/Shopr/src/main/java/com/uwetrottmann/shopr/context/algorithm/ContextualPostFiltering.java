package com.uwetrottmann.shopr.context.algorithm;

import android.database.Cursor;
import android.util.Log;

import com.uwetrottmann.shopr.ShoprApp;
import com.uwetrottmann.shopr.algorithm.model.Item;
import com.uwetrottmann.shopr.context.model.Company;
import com.uwetrottmann.shopr.context.model.DayOfTheWeek;
import com.uwetrottmann.shopr.context.model.DistanceMetric;
import com.uwetrottmann.shopr.context.model.ItemSelectedContext;
import com.uwetrottmann.shopr.context.model.ScenarioContext;
import com.uwetrottmann.shopr.context.model.Temperature;
import com.uwetrottmann.shopr.context.model.TimeOfTheDay;
import com.uwetrottmann.shopr.context.model.Weather;
import com.uwetrottmann.shopr.provider.ShoprContract;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by yannick on 17.02.15.
 *
 * In this class the post-filtering of the recommended cases is done. We will retrieve several different
 * items from the recommendation algorithm. Based on the current active context and the contexts in which these items
 * were selected, there is going to be a re-ordered list of the top x items.
 */
public class ContextualPostFiltering {

    private static String[] sSelectionColumns = new String[]{ShoprContract.ContextItemRelation.REF_ITEM_ID, ShoprContract.ContextItemRelation.CONTEXT_TIME, ShoprContract.ContextItemRelation.CONTEXT_DAY, ShoprContract.ContextItemRelation.CONTEXT_TEMPERATURE, ShoprContract.ContextItemRelation.CONTEXT_HUMIDITY, ShoprContract.ContextItemRelation.CONTEXT_COMPANY};

    /**
     * This method shall postFilter the provided item set of the case base in order to return better recommendations.
     * Therefore it takes a list of current preferred items by the algorithm and searches for contexts in which this item
     * was selected. It then checks these contexts with the current context and compares them based on the context's distance metric.
     *
     * @param currentRecommendation the current recommendations as set by the algorithm
     * @param numberOfRecommendations the number of recommendations that should be outputted
     * @return an updated list with only the number of items that should be outputted.
     */
    public static List<Item> postFilterItems(List<Item> currentRecommendation, int numberOfRecommendations){
        List<Item> updatedRecommendation = new ArrayList<Item>();

        //Updates the items, such that we can see in which contexts these items were selected
        retrieveContextInformationForItems(currentRecommendation);

        ScenarioContext scenarioContext = ScenarioContext.getInstance();

        //Only if we have a scenario context, we are allowed to check whether the scenario matches the item contexts.
        if (scenarioContext.isSet()) {
            //Calculate the distances
            for (Item item : currentRecommendation) {
                ItemSelectedContext itemContext = item.getItemContext();
                Map<DistanceMetric, Integer> distanceMetrics = itemContext.getContextsForItem();
                for (DistanceMetric metric : distanceMetrics.keySet()) {
                    double distance;
                    int times = distanceMetrics.get(metric);
                    if (metric.isMetricWithEuclideanDistance()){
                        distance = times * getAdaptedEuclideanDistance(scenarioContext.getDayOfTheWeek().ordinal(), metric.currentOrdinal(), metric.numberOfItems());
                    } else {
                        distance = times * metric.distanceToContext(scenarioContext);
                    }
                    Log.d("Distance", "" + distance);
                }
            }
        }

        for (Item item : currentRecommendation){
            if (updatedRecommendation.size() < numberOfRecommendations) {
                updatedRecommendation.add(item);
            } else {
                break;
            }
        }

        return updatedRecommendation;
    }

    /**
     * Updates the items and sets the context scenarios in which these items have been selected.
     * @param currentRecommendation A list of items, that shall be part of the current recommendation cycle.
     */
    private static void retrieveContextInformationForItems(List<Item> currentRecommendation){
        for (Item item : currentRecommendation){

            //Set a new context for the item(s)
            ItemSelectedContext itemContext = new ItemSelectedContext();
            item.setItemContext(itemContext);

            //Query the database
            String selectionString = ShoprContract.ContextItemRelation.REF_ITEM_ID + " = " + item.id();
            Cursor query = ShoprApp.getContext().getContentResolver().query(ShoprContract.ContextItemRelation.CONTENT_URI,
                    sSelectionColumns, selectionString, null, null);

            //Move the information from the database into the data structure
            if (query != null){
                while (query.moveToNext()){
                    itemContext.increaseDistanceMetric(TimeOfTheDay.getTimeOfTheDay(query.getInt(1)));
                    itemContext.increaseDistanceMetric(DayOfTheWeek.getDayOfTheWeek(query.getInt(2)));
                    itemContext.increaseDistanceMetric(Temperature.getTemperature(query.getInt(3)));
                    itemContext.increaseDistanceMetric(Weather.getWeather(query.getInt(4)));
                    itemContext.increaseDistanceMetric(Company.getCompany(query.getInt(5)));
                }

                query.close();
            }

//            Log.d("Item Context: ", ""+ itemContext);
        }
    }

    /**
     * This method returns the adapted euclidean distance as described in the HEOM
     * @param p the value for the first coordinate
     * @param q the value for the second coordinate
     * @param range the range in which the values are distributed
     * @return a double with the distance
     */
    private static double getAdaptedEuclideanDistance(double p, double q, double range){
        double result = ( p - q ) / range;
        result = Math.pow(result, 2); // square it
        return Math.sqrt(result);
    }
}