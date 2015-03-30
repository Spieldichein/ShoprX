package de.tum.in.schlichter.shoprx.context.model;

import java.util.HashMap;
import java.util.Map;

import de.tum.in.schlichter.shoprx.context.model.interfaces.DistanceMetric;

/**
 * Created by Yannick on 19.02.15.
 *
 * This class stores the information on the contexts in which the {@link de.tum.in.schlichter.shoprx.algorithm.model.Item} has been selected.
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
    }

    /**
     * Increases the counter for the distance metric in which this item has been selected.
     * @param d the DistanceMetric to increase.
     */
    public void increaseDistanceMetric(DistanceMetric d){
        Integer times = mDistanceMetrics.get(d);
        if (times == null){
            times = 0;
        }
        mDistanceMetrics.put(d, times + 1);
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
     * @return number of different context factors (for post-filtering) in use.
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
