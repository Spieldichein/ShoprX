package de.tum.in.schlichter.shoprx.context.algorithm;

import java.util.Comparator;

import de.tum.in.schlichter.shoprx.algorithm.model.Item;

/**
 * Created by Yannick on 23.02.15.
 *
 * This comparator compares two items based on their distance to the current context
 * The items must hold the distance they currently have to the context.
 */
public class ContextualDistanceComparator implements Comparator<Item> {

    @Override
    public int compare(Item item, Item item2) {
        if (item.getDistanceToContext() > item2.getDistanceToContext()){
            return 1;
        } else if (item.getDistanceToContext() < item2.getDistanceToContext()){
            return -1;
        }

        if (item.querySimilarity() > item2.querySimilarity()){
            return 1;
        } else if (item.querySimilarity() < item2.querySimilarity()){
            return -1;
        }

        if (item.getProximityToStereotype() > item2.getProximityToStereotype()){
            return 1;
        } else if (item.getProximityToStereotype() < item2.getProximityToStereotype()){
            return -1;
        }

        return 0;
    }

    @Override
    public boolean equals(Object o) {
        return o.getClass() == ContextualDistanceComparator.class;
    }
}
