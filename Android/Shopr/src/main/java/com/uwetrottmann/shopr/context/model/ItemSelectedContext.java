package com.uwetrottmann.shopr.context.model;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Yannick on 19.02.15.
 *
 * This class stores the information on the contexts in which the {@link com.uwetrottmann.shopr.algorithm.model.Item} has been selected.
 * It should therefore be attached to an item.
 */
public class ItemSelectedContext {

    private Map<DistanceMetric, Integer> mDistanceMetrics;

    private static final int DIFFERENT_CONTEXT_FACTORS = 5;

    /**
     * Initializes the local data stores.
     */
    public ItemSelectedContext(){
        mDistanceMetrics = new HashMap<DistanceMetric, Integer>();
        for (TimeOfTheDay t : TimeOfTheDay.values()){
            mDistanceMetrics.put(t, 0);
        }

        for (DayOfTheWeek d : DayOfTheWeek.values()){
            mDistanceMetrics.put(d, 0);
        }

        for (Temperature t : Temperature.values()){
            mDistanceMetrics.put(t, 0);
        }

        for (Weather w : Weather.values()){
            mDistanceMetrics.put(w, 0);
        }

        for (Company c : Company.values()){
            mDistanceMetrics.put(c, 0);
        }
    }

    /**
     * Increases the counter for the times of the day in which this item has been selected.
     * @param d the TimeOfTheDay to increase.
     */
    public void increaseDistanceMetric(DistanceMetric d){
        mDistanceMetrics.put(d, mDistanceMetrics.get(d) + 1);
    }

    /**
     * Returns all the different distance metrics that we do have here.
     * @return a map with all the distance metrics possible
     */
    public Map<DistanceMetric, Integer> getContextsForItem(){
        return mDistanceMetrics;
    }

    /**
     * Returns the number of different context factors that are used in this calculation. This is necessary
     * in order to calculate averages from the different distances.
     * @return number of different context factors (for postfiltering) in use.
     */
    public static int getNumberOfDifferentContextFactors(){
        return DIFFERENT_CONTEXT_FACTORS;
    }

    @Override
    public String toString() {
        return "ItemSelectedContext{" +
                "mDistanceMetrics=" + mDistanceMetrics +
                '}';
    }
}
