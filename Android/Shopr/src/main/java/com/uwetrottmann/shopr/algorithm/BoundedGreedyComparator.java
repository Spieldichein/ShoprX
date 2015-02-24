package com.uwetrottmann.shopr.algorithm;

import com.uwetrottmann.shopr.algorithm.model.Item;

import java.util.Comparator;

/**
 * Created by Yannick on 24.02.15.
 *
 * This comparator is used to compare items based on their quality in the case of a negative critique.
 */
public class BoundedGreedyComparator implements Comparator<Item> {

    @Override
    public int compare(Item o1, Item o2) {
        // is o2 smaller?
        if (o1.quality() > o2.quality()) {
            return -1;
        }
        // is o2 bigger?
        if (o1.quality() < o2.quality()) {
           return 1;
        }

        if (o1.getProximityToStereotype() > o2.getProximityToStereotype()){
            return -1;
        }

        if (o1.getProximityToStereotype() < o2.getProximityToStereotype()){
            return 1;
        }

        // they are equal!
        return 0;
    }
}
