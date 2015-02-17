package com.uwetrottmann.shopr.context.algorithm;

import com.uwetrottmann.shopr.algorithm.model.Item;

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

        for (Item item : currentRecommendation){
            if (updatedRecommendation.size() < numberOfRecommendations) {
                updatedRecommendation.add(item);
            } else {
                break;
            }
        }

        return updatedRecommendation;
    }
}