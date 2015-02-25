package de.tum.in.schlichter.shoprx.algorithm;

import java.util.Comparator;

import de.tum.in.schlichter.shoprx.algorithm.model.Item;

/**
 * Created by Yannick on 23.02.15.
 *
 * Is the comparator for Adaptive Selection. It compares items based on their query similarity.
 */
public class AdaptiveSelectionComparator implements Comparator<Item> {

    @Override
    public int compare(Item item1, Item item2) {
        // is item2 smaller?
        if (item1.querySimilarity() > item2.querySimilarity()) {
            return -1;
        }
        // is item2 bigger?
        if (item1.querySimilarity() < item2.querySimilarity()) {
            return 1;
        }

        //On equal chances for both items, we check the stereotype settings
        // they are equal!
        if (item1.getProximityToStereotype() > item2.getProximityToStereotype()){
            return -1;
        } else if (item1.getProximityToStereotype() < item2.getProximityToStereotype()) {
            return 1;
        }

        return 0;
    }

    @Override
    public boolean equals(Object o) {
        return o.getClass() == AdaptiveSelectionComparator.class;
    }
}
