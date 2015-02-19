package com.uwetrottmann.shopr.context.algorithm;

import android.database.Cursor;
import android.util.Log;

import com.uwetrottmann.shopr.ShoprApp;
import com.uwetrottmann.shopr.algorithm.model.Item;
import com.uwetrottmann.shopr.context.model.Company;
import com.uwetrottmann.shopr.context.model.DayOfTheWeek;
import com.uwetrottmann.shopr.context.model.ItemSelectedContext;
import com.uwetrottmann.shopr.context.model.Temperature;
import com.uwetrottmann.shopr.context.model.TimeOfTheDay;
import com.uwetrottmann.shopr.context.model.Weather;
import com.uwetrottmann.shopr.provider.ShoprContract;

import java.util.ArrayList;
import java.util.List;

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
        retrieveSelectedInTheseContexts(currentRecommendation);

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
    private static void retrieveSelectedInTheseContexts(List<Item> currentRecommendation){
        for (Item item : currentRecommendation){

            ItemSelectedContext itemContext = new ItemSelectedContext();

            // Reset the context maps
            item.setItemContext(itemContext);

            //Query the database
            String selectionString = ShoprContract.ContextItemRelation.REF_ITEM_ID + " = " + item.id();
            Cursor query = ShoprApp.getContext().getContentResolver().query(ShoprContract.ContextItemRelation.CONTENT_URI,
                    sSelectionColumns, selectionString, null, null);

            if (query != null){
                while (query.moveToNext()){
                    itemContext.increaseTimeOfTheDay(TimeOfTheDay.getTimeOfTheDay(query.getInt(1)));
                    itemContext.increaseDayOfTheWeek(DayOfTheWeek.getDayOfTheWeek(query.getInt(2)));
                    itemContext.increaseTemperature(Temperature.getTemperature(query.getInt(3)));
                    itemContext.increaseWeather(Weather.getWeather(query.getInt(4)));
                    itemContext.increaseCompany(Company.getCompany(query.getInt(5)));
                }

                query.close();
            }

            Log.d("Item Context: ", ""+ itemContext);
        }
    }
}