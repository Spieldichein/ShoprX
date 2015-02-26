package de.tum.in.schlichter.shoprx.stereotype.algorithm;

import android.util.Log;

import java.util.Comparator;
import java.util.List;
import java.util.Map;

import de.tum.in.schlichter.shoprx.algorithm.model.Color;
import de.tum.in.schlichter.shoprx.algorithm.model.Item;
import de.tum.in.schlichter.shoprx.algorithm.model.Label;
import de.tum.in.schlichter.shoprx.stereotype.stereotypes.AbstractStereotype;

/**
 * Created by Yannick on 09.02.15.
 *
 * This class does the Stereotype based filtering. It searches within the stereotype settings for
 * colors and brands that this person might like. Brands are more important than colours in this
 * classification.
 * The user's stereotype was determined via {@link de.tum.in.schlichter.shoprx.stereotype.controller.StereotypeDeterminator}
 * and is now exploited in order to pre-filter the case base.
 */
public class StereotypeFiltering {

    /**
     * Computes the proximity of a list of clothing items for a certain
     * stereotype and sorts them in descending order.
     *
     * @param stereotype
     *            the stereotype to which the proximity is to be computed
     * @param clothingItems
     *            the list of clothing items for which the proximity is to be
     *            computed
     * @return a sorted list of clothing items in descending order of their
     *         proximity to a stereotype
     */
    public List<Item> computeStereotypeProximity(AbstractStereotype stereotype, List<Item> clothingItems) {
        for (Item item : clothingItems) {
            item.setProximityToStereotype(computeStereotypeProximity(stereotype, item));
            //Log.i("Putting proximity " + item.getProximityToStereotype(), item.toString());
        }

        //Do not have to sort here, as the proximity is part of the adaptive selection algorithm
        // sort the entries in descending order
        //ProximityComparator proximityComparator = new ProximityComparator();
        //Collections.sort(clothingItems, proximityComparator);


        //logTop10Items(clothingItems);
        return clothingItems;
    }

    /**
     * Logs the top 10 items onto the console.
     *
     * @param sortedClothingItems the sorted list with the top 10 clothing items.
     */
    private void logTop10Items(List<Item> sortedClothingItems) {
        int i = 1;

        for (Item item : sortedClothingItems) {
            if (i > 10)
                break;
            Log.i(i + ": " + item.toString(), " Proximity: " + item.getProximityToStereotype());
            i++;
        }
    }

    /**
     * Compute the proximity of a clothing item to a stereotype.
     *
     * @param stereotype
     *            the stereotype to which the proximity is to be computed
     * @param item
     *            the item of interest
     * @return the proximity on a scale of 0 to 10;
     */
    public double computeStereotypeProximity(AbstractStereotype stereotype, Item item) {
        double proximity = 0.0;
        int hits = 0;

        // compute attribute proximity first
        String[] haystack = item.getNameParts();

        // get all relevant attributes for the active stereotype
        Map<String, Integer> attributeProbabilityMap = stereotype.getAttributeProbabilityMap();
        // check whether these attributes appear in the item, if so add their
        // weight to the proximity measure
        for (String needle : haystack) {
            if (attributeProbabilityMap.containsKey(needle)) {
                // if the needle is found add it's weight divided by the number
                // of all attributes of the stereotype
                int weight = attributeProbabilityMap.get(needle);
                proximity += weight;
                hits++;
            }
        }
        if (attributeProbabilityMap.containsKey(item.attributes().getAttributeById(Color.ID).currentValue().descriptor())){
            int weight = attributeProbabilityMap.get(item.attributes().getAttributeById(Color.ID).currentValue().descriptor());
            proximity += weight;
            hits++;
        }

        // depending on number of attribute hits set weight for brand impact
        int brandImpact = (hits> 2 ? hits / 2 : 1);
        Map<Label.Value, Integer> brandProbabilityMap = stereotype.getBrandProbabilityMap();

        Label.Value label = (Label.Value) item.attributes().getAttributeById(Label.ID).currentValue();
        if (brandProbabilityMap.containsKey(label)){
            proximity += brandProbabilityMap.get(label) * brandImpact;
            hits += brandImpact;
        }

        return hits > 0 ? proximity / hits : 0.0;
    }

    /**
     * Comparator for sorting clothing items in descending order by their
     * proximity value.
     *
     * @author Yannick Rödl
     *
     */
    private class ProximityComparator implements Comparator<Item> {

        public ProximityComparator() {}

        // Note: this comparator imposes orderings that are inconsistent with
        // equals.
        public int compare(Item a, Item b) {
            if (a.getProximityToStereotype() > b.getProximityToStereotype()) {
                return -1;
            } else if (b.getProximityToStereotype() == a.getProximityToStereotype()) {
                return 0;
            } else {
                return 1;
            }
        }

    }
}
