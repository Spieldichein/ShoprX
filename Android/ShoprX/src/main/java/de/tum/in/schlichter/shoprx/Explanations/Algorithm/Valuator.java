package de.tum.in.schlichter.shoprx.Explanations.Algorithm;

import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.tum.in.schlichter.shoprx.Explanations.Model.Dimension;
import de.tum.in.schlichter.shoprx.algorithm.Query;
import de.tum.in.schlichter.shoprx.algorithm.model.Attributes.Attribute;
import de.tum.in.schlichter.shoprx.algorithm.model.ClothingType;
import de.tum.in.schlichter.shoprx.algorithm.model.Color;
import de.tum.in.schlichter.shoprx.algorithm.model.Item;
import de.tum.in.schlichter.shoprx.algorithm.model.Label;
import de.tum.in.schlichter.shoprx.algorithm.model.Price;
import de.tum.in.schlichter.shoprx.algorithm.model.Sex;

/**
 * Created by Nicksteal on 23.03.2015.
 */
public class Valuator {

    /*
     * Calculates the information score which explains whether it is valuable to
     * explain an item from a given dimension.
     */
    public static double informationScore(Item item, Query query,
                                          Dimension dimension, List<Item> recommendations) {
        double R = calculateRangeNew(recommendations, query, dimension);
        double I = calculateInformation(item, recommendations, dimension);
        Log.d("scores", "R: " + R+" and i: "+I);

        return R*I;
        //return (R + I) / 2;
    }

    protected static double calculateInformation(Item item,
                                                 List<Item> recommendations, Dimension dimension) {
        double n = recommendations.size();
        double h = calculateH(item, recommendations, dimension);// findNumMostFrequentX(recommendations,
        // query,															// dimension);
        Log.d("scores", "n: " + n+" and h: "+h);

        return (n - h) / (n - 1);
    }

    protected static int calculateH(Item item, List<Item> recommendations,
                                    Dimension dimension) {
        List<Item> itemsWithSameDimensionValue = filterItemsByDimensionValue(
                recommendations, dimension);
        int itemIndex = itemsWithSameDimensionValue.indexOf(item);

     /*   if (itemIndex < 2) {
            return itemIndex + 1;
        } else {*/
            return itemsWithSameDimensionValue.size();
        //}
    }

    protected static List<Item> filterItemsByDimensionValue(
            List<Item> recommendations, Dimension dimension) {
        List<Item> filtered = new ArrayList<Item>();
        for (Item item : recommendations) {
            if (item.attributes().getAttributeById(dimension.attribute().id())
                    .currentValue() == dimension.attribute().currentValue()) {
                filtered.add(item);
            }
        }
        return filtered;
    }

    protected static int findNumMostFrequentX(List<Item> recommendations,
                                              Query query, Dimension dimension) {
        List<Double> explanationScores = mapToExplanationScore(recommendations,
                query, dimension);
        Map<Double, Integer> frequencyOfScores = new HashMap<Double, Integer>();
        int max = 0;
        for (Double score : explanationScores) {
            if (frequencyOfScores.containsKey(score)) {
                int newFreq = frequencyOfScores.get(score) + 1;
                if (newFreq > max)
                    max = newFreq;
                frequencyOfScores.put(score, newFreq);

            } else {
                frequencyOfScores.put(score, 1);
                if (max == 0)
                    max = 1;
            }
        }

        return max;
    }

    private static List<Double> mapToExplanationScore(
            List<Item> recommendations, Query query, Dimension dimension) {
        List<Double> explanationScores = new ArrayList<Double>();
        for (Item item : recommendations) {
            double expScore = explanationScore(item, query, dimension);
            explanationScores.add(expScore);
        }

        return explanationScores;
    }

    protected static double calculateRange(List<Item> recommendations,
                                           Query query, Dimension dimension) {
        double max = 0.0;
        double min = 1.0;

        for (Item item : recommendations) {
            double ES = explanationScore(item, query, dimension);
            max = Math.max(max, ES);
            min = Math.min(min, ES);
        }

        return max - min;
    }

    protected static double calculateRangeNew(List<Item> recommendations,
                                           Query query, Dimension dimension) {
        double max = 0.0;
        double min = 1.0;

        for (Item item : recommendations) {
            double ES = explanationScore(item, query, dimension);
            max = Math.max(max, ES);
            min = Math.min(min, ES);
        }

        double result = (max-min)* (query.attributes().getAttributeById(dimension.attribute().id()).getAttributeValues().length-1);
        return result;
    }

    /*
     * Calculates a score that measures how fit an item is overall to the user's
     * preferences.
     */
    public static double globalScore(Item item, Query query) {
        double scoreSum = 0.0;
        double numDimensions = item.attributes().values().size();
        for (Attribute attribute : item.attributes().values()) {
            Dimension dimension = new Dimension(attribute);
            scoreSum += explanationScoreNew(item, query, dimension);
        }

        return scoreSum / numDimensions;
    }

    /*
     * Calculates a score that measures whether an item is a good fit to the
     * user's preferences in a given dimension.
     */
    public static double explanationScore(Item item, Query query,
                                          Dimension dimension) {
        Attribute itemAttr = item.attributes().getAttributeById(
                dimension.attribute().id());
        Attribute queryAttr = query.attributes().getAttributeById(
                dimension.attribute().id());
        double[] itemValueWeights = itemAttr.getValueWeights();
        double[] queryValueWeights;

        if (itemAttr != null && queryAttr != null) {
            queryValueWeights = queryAttr.getValueWeights();
        } else {
            Attribute missingQueryAttr = query.attributes().initializeAndReturnAttribute(
                    dimension.attribute());
            queryValueWeights = missingQueryAttr.getValueWeights();
        }

        return calculateLocalScoreOld(itemValueWeights,queryValueWeights);
      //  return getDimensionWeight(dimension)
       //         * calculateLocalScore(itemValueWeights, queryValueWeights);
    }

    public static double explanationScoreNew(Item item, Query query,
                                          Dimension dimension) {
        Attribute itemAttr = item.attributes().getAttributeById(
                dimension.attribute().id());
        Attribute queryAttr = query.attributes().getAttributeById(
                dimension.attribute().id());
        double[] itemValueWeights = itemAttr.getValueWeights();
        double[] queryValueWeights;

        if (itemAttr != null && queryAttr != null) {
            queryValueWeights = queryAttr.getValueWeights();
        } else {
            Attribute missingQueryAttr = query.attributes().initializeAndReturnAttribute(
                    dimension.attribute());
            queryValueWeights = missingQueryAttr.getValueWeights();
        }

        return calculateLocalScore(itemValueWeights,queryValueWeights);
        //  return getDimensionWeight(dimension)
        //         * calculateLocalScore(itemValueWeights, queryValueWeights);
    }

  /* private static double getDimensionWeight(Dimension dimension) {
        List<Attribute> attributes = new ArrayList<Attribute>();
        attributes.add(new Color());
        attributes.add(new Price());
        attributes.add(new ClothingType());
        attributes.add(new Label());

        double totalAttributeValues = 0;
        for (Attribute attr : attributes) {
            totalAttributeValues += attr.getAttributeValues().length;
        }

        return Math.sqrt(dimension.attribute().getAttributeValues().length
                / totalAttributeValues);
    }*/

    private static double calculateLocalScore(double[] itemWeights,
                                              double[] queryWeights) {
        double score = 0.0;
        for (int i = 0; i < itemWeights.length; i++) {
            score += itemWeights[i] * queryWeights[i];
        }
        score = score * itemWeights.length;
        return score;
    }

    private static double calculateLocalScoreOld(double[] itemWeights,
                                              double[] queryWeights) {
        double score = 0.0;
        for (int i = 0; i < itemWeights.length; i++) {
            score += itemWeights[i] * queryWeights[i];
        }
        return score;
    }

}