package de.tum.in.schlichter.shoprx.context.algorithm;

import android.database.Cursor;
import android.util.Log;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import de.tum.in.schlichter.shoprx.Explanations.Model.SimpleExplanation;
import de.tum.in.schlichter.shoprx.ShoprApp;
import de.tum.in.schlichter.shoprx.algorithm.AdaptiveSelection;
import de.tum.in.schlichter.shoprx.algorithm.model.ClothingType;
import de.tum.in.schlichter.shoprx.algorithm.model.Item;
import de.tum.in.schlichter.shoprx.context.model.Company;
import de.tum.in.schlichter.shoprx.context.model.DayOfTheWeek;
import de.tum.in.schlichter.shoprx.context.model.interfaces.DistanceMetric;
import de.tum.in.schlichter.shoprx.context.model.ItemSelectedContext;
import de.tum.in.schlichter.shoprx.context.model.ScenarioContext;
import de.tum.in.schlichter.shoprx.context.model.Temperature;
import de.tum.in.schlichter.shoprx.context.model.TimeOfTheDay;
import de.tum.in.schlichter.shoprx.context.model.Weather;
import de.tum.in.schlichter.shoprx.provider.ShoprContract;

/**
 * Created by yannick on 17.02.15.
 *
 * In this class the post-filtering of the recommended cases is done. We will retrieve several different
 * items from the recommendation algorithm. Based on the current active context and the contexts in which these items
 * were selected, there is going to be a re-ordered list of the top x items.
 */
public class ContextualPostFiltering {

    /*
     * This variable can be between 0 and 1.
     * It states the distance of items, that were not selected in any context, to the currently
     * active context. Meaning that if you set 0.5 here, all items that were not selected yet, are
     * going to get a distance of 0.5 Therefore you can influence the exploration/exploitation parameter.
     */
    private static final double DISTANCE_OF_NON_SELECTED_ITEMS = 0.6;
    private static final double THRESHOLD_FREQUENTLY_USED_ITEMS = 0.3; // Items that are selected in more than this numbers times of cases
    private static final double IMPROVEMENT_FREQUENTLY_USED_ITEMS = 0.2; // will get a bonus in distance of this

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
        Log.d("CONTEXTSTUFF","enter method!!!");

        //Updates the items, such that we can see in which contexts these items were selected
        retrieveContextInformationForItems(currentRecommendation);

        ScenarioContext scenarioContext = ScenarioContext.getInstance();

        //Check that we have a scenario (real test)
        if (scenarioContext.isSet()) {
            Log.d("CONTEXTSTUFF","scenario isset");

            int overallNumberOfContextsSetForAllItems = 0;

            List<Item> newCurrentRec = new ArrayList<Item>();
            //For each item: Calculate the distances
            for (Item item : currentRecommendation) {


                Log.d("CONTEXTSTUFF","item in currentrec");

                //Get the context for the item
                ItemSelectedContext itemContext = item.getItemContext();
                Map<DistanceMetric, Integer> distanceMetrics = itemContext.getContextsForItem(); //Get the contexts for the item




                int overallWeather =0;
                int overallTemperature =0;
                int overallDay=0;
                int overallTime =0;
                int overallCompany=0;
                double overallWeatherDistance =0;
                double overallTemperatureDistance =0;
                double overallDayDistance=0;
                double overallTimeDistance =0;
                double overallCompanyDistance=0;



                int overallContextFactorsSet = 0; // The number of context factors which are set for this item
                double overallItemDistance = 0.0; // The overall distance after summation of all factors without dividing
                double weight;
                for (DistanceMetric metric : distanceMetrics.keySet()) { //For each metric for the item
                    int times = distanceMetrics.get(metric);
                    overallContextFactorsSet += times;
                    weight = metric.getWeight((ClothingType) item.attributes().getAttributeById(ClothingType.ID));
                    double distanceHolder =  getDistance(times, metric, scenarioContext) * weight;
                    Log.d("CONTEXTSTUFF","a context");

                    //**TODO** gather information for all 5 dimension seperatly for explanations, count factorset for weight too
                    if (metric instanceof Company){
                        overallCompany++;
                        overallCompanyDistance+=distanceHolder;
                        Log.d("CONTEXTSTUFF","company");
                    }
                    else if (metric instanceof DayOfTheWeek){
                        overallDay++;
                        overallDayDistance+=distanceHolder;
                        Log.d("CONTEXTSTUFF","DayOfTheWeek");
                    }
                    else if (metric instanceof Temperature){
                        Log.d("CONTEXTSTUFF","Temperature");
                        overallTemperature++;
                        overallTemperatureDistance+=distanceHolder;
                    }
                    else if (metric instanceof TimeOfTheDay){
                        Log.d("CONTEXTSTUFF","TimeOfTheDay");
                        overallTime++;
                        overallTimeDistance+=distanceHolder;
                    }
                    else if (metric instanceof Weather){
                        Log.d("CONTEXTSTUFF","Weather");
                        overallWeather++;
                        overallWeatherDistance+=distanceHolder;
                    }
                    //Multiply the distance with its weight, as they should not have their full weight
//                    Log.d("Weight", "" + weight);
                    overallItemDistance = overallItemDistance +distanceHolder;
//                    Log.d("Found Context", ""+ metric +" selected "+ times +" times " +overallItemDistance);

                } // End for each metric within the contexts of a item


                if (overallCompany !=0 && overallCompanyDistance !=0)overallCompanyDistance = overallCompanyDistance/overallCompany;
                if (overallWeather!=0 && overallWeatherDistance!=0)overallWeatherDistance = overallWeatherDistance/overallWeather;
                if (overallDay!=0&&overallDayDistance!=0)overallDayDistance = overallDayDistance/overallDay;
                if (overallTemperature!=0 &&overallTemperatureDistance!=0)overallTemperatureDistance = overallTemperatureDistance/overallTemperature;
                if (overallTime!=0 &&overallTimeDistance !=0)overallTimeDistance = overallTimeDistance / overallTime;

                // Set the distance to the current context
                // Items that were not selected in any context are very far away and will first of all be attached to the end, afterwards they will get the median.
                if (overallItemDistance != 0 && overallContextFactorsSet != 0){
//                    Log.d("Found", "Dist: " + overallItemDistance + " Factors: " + overallContextFactorsSet);
                    overallItemDistance = overallItemDistance / overallContextFactorsSet; //have an average weight
                }

//TODO make more reasonable ^^
                if (overallTemperatureDistance < overallItemDistance / 8){
                    item.getExplanation().getSimpleExplanations().add(new SimpleExplanation("The current temperature fits this item", SimpleExplanation.IconType.TEMPERATURE));
                    Log.d("CONTEXTSTUFF","ADDED temp");

                }
                if (overallWeatherDistance < overallItemDistance /8){
                    item.getExplanation().getSimpleExplanations().add(new SimpleExplanation("The current weather fits this item", SimpleExplanation.IconType.WEATHER));
                    Log.d("CONTEXTSTUFF","ADDED Weather");

                }

                newCurrentRec.add(item);

                //scale from 0 to 1 to be able to compare different items
                double maxContextWeights = ((ClothingType) item.attributes().getAttributeById(ClothingType.ID)).getContextWeightsSum();

                item.setDistanceToContext(overallItemDistance / maxContextWeights * ItemSelectedContext.getNumberOfDifferentContextFactors()); //divided by contextWeights multiplied by 5 (because of 5 context factors)

//                Log.d("Distance compare", "Before: " + overallItemDistance + " After: " + item.getDistanceToContext());
                //The variable times selected states how often this item was (overall) selected. This is necessary,
                // because we might want to improve the scores for products that are very frequently selected.
                item.setTimesSelected(overallContextFactorsSet / ItemSelectedContext.getNumberOfDifferentContextFactors());

                overallNumberOfContextsSetForAllItems = overallNumberOfContextsSetForAllItems + item.getTimesSelected();

            } // End for each item

            AdaptiveSelection.get().setCurrentRecommendations(newCurrentRec);
            currentRecommendation = newCurrentRec;
            adjustDistances(overallNumberOfContextsSetForAllItems, currentRecommendation);

            //The maximum distance for a product is 1 and the minimum 0 (closest).
            //We want the closest items to be at the top of the list (screen)
            Collections.sort(currentRecommendation, new ContextualDistanceComparator());
        } //End check for scenario

        logItems(currentRecommendation);

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
     * This method returns the distance of this scenario context compared to the context in which the
     * item has been selected.
     * @param timesSelected the times in which the item has been selected in this context
     * @param metric the distance metric to check
     * @param scenarioContext the current scenario context
     * @return the distance of the metric to the scenario multiplied by the times it was selected
     */
    private static double getDistance(int timesSelected, DistanceMetric metric, ScenarioContext scenarioContext){
        double distance;
        if (metric.isMetricWithEuclideanDistance()){
            // The -1 makes sure that we can have 1 as a distance, as when min is 0 and max 5, the number of items is 6, but should be 5 for the maximum distance.
            distance = getAdaptedEuclideanDistance(scenarioContext.getMetric(metric).currentOrdinal(), metric.currentOrdinal(), metric.numberOfItems() - 1.0);
            distance = timesSelected * distance;
        } else {
            distance = timesSelected * metric.distanceToContext(scenarioContext);
        }

        return distance;
    }

    /**
     * This method adjusts the distances for over-performing articles as well as for articles
     * that do not sell at all.
     * @param contexts the number of times an item was (successfully) recommended
     * @param currentRecommendation the list of current recommendations.
     */
    private static void adjustDistances(int contexts, List<Item> currentRecommendation){
        if (contexts > 0) {
            for (Item item : currentRecommendation) {
                //Improve items that are very frequently selected
                if ( (item.getTimesSelected() * 1.0 / contexts * 1.0) > THRESHOLD_FREQUENTLY_USED_ITEMS) {
                    Log.d("ContextDistanceImprove", ""+ item);
                    item.setDistanceToContext(item.getDistanceToContext() * (1 - IMPROVEMENT_FREQUENTLY_USED_ITEMS));
                }

                //Items that were never selected.
                if (item.getTimesSelected() == 0){
                    item.setDistanceToContext(DISTANCE_OF_NON_SELECTED_ITEMS); // Set the distance for items that were not selected in any context
                }
            }
        }
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
     * @param p the value for the coordinate of the first object
     * @param q the value for the coordinate of the second object
     * @param range the difference between the highest and the lowest number in the range
     * @return a double with the distance
     */
    private static double getAdaptedEuclideanDistance(double p, double q, double range){
//        Log.d("Euclide", "p " + p + " q " + q + " range " + range);
        double result = ( p - q ) / range;
        result = Math.sqrt(Math.pow(result, 2)); // square it
//        Log.d("Result" , ""+result);
        return result;
    }

    private static void logItems(List<Item> items){
        for (Item item : items) {
            Log.d("ContextDistance", "" + item.getDistanceToContext() + " " + item);
        }
    }
}