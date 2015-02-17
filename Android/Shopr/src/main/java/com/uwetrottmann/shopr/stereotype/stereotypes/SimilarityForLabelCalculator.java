package com.uwetrottmann.shopr.stereotype.stereotypes;

import android.util.Log;

import com.uwetrottmann.shopr.algorithm.model.Label;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Yannick on 13.02.15.
 *
 * This class should calculate brand's similarity by comparing how often they have the same rating
 * among different stereotypes.
 */
public class SimilarityForLabelCalculator {

    /**
     * This method calculates a similarity graph for the label. It therefore compares how often a brand has
     * the same rating as another brand for all stereotypes. It is therefore important that all brandProbabilityMaps
     * used in the stereotypes do have exactly the same size and exactly the same brands in there. Otherwise the calculation
     * as provided here, would be wrong.
     * It outputs the similarity directly copyable into the label class.
     * @param args args for the main method are not used here.
     */
    public static void main(String[] args){
        Athlete athlete = new Athlete();
        Classy classy = new Classy();
        Emo emo = new Emo();
        Gothic gothic = new Gothic();
        Indie indie = new Indie();
        Mainstream mainstream = new Mainstream();
        Preppy preppy = new Preppy();
        Skater skater = new Skater();
        Urban urban = new Urban();

        List<AbstractStereotype> stereotypes = new LinkedList<AbstractStereotype>();
        stereotypes.add(athlete);
        stereotypes.add(classy);
        stereotypes.add(emo);
        stereotypes.add(gothic);
        stereotypes.add(indie);
        stereotypes.add(mainstream);
        stereotypes.add(preppy);
        stereotypes.add(skater);
        stereotypes.add(urban);

        double[][] similarRating = new double[athlete.brandProbabilityMap.size()][athlete.brandProbabilityMap.size()];

        // Calculate the similarity for all stereotypes
        for (AbstractStereotype stereotype : stereotypes) {
            int brandOne = 0;

            // For all brands in this stereotype do the following
            for (Label.Value brand : stereotype.brandProbabilityMap.keySet()) {
                int brandRating = stereotype.brandProbabilityMap.get(brand); // get the importance of this brand for the stereotype

                // Iterate over all other brands of this stereotype in order to compare them with the given brand.
                int iterator = 0;
                for (Label.Value brandComp : stereotype.brandProbabilityMap.keySet()) {
                    if (brand == brandComp) { // if the brands are equal go on
                        iterator++;
                        continue;
                    }

                    int brandRatingComp = stereotype.brandProbabilityMap.get(brandComp);
                    if (brandRating == brandRatingComp) { // Equal brand importance for the stereotype, then give it a point.
                        similarRating[brandOne][iterator] = similarRating[brandOne][iterator] + 1;
                    }

                    //Half a point for close to similar rating
                    if (brandRating == (brandRatingComp + 1) || brandRating == (brandRatingComp - 1)) {
                        similarRating[brandOne][iterator] = similarRating[brandOne][iterator] + 0.5;
                    }

                    iterator++;
                }

                brandOne++;
            }

        }

        //Iterate over the results and output them in a format which makes them copyable into the class.
        int i = 0;
        for (Label.Value brand : athlete.brandProbabilityMap.keySet()){
            int brandTwo = 0;
            for (Label.Value brand2 : athlete.brandProbabilityMap.keySet()) {
                if (similarRating[i][brandTwo] > 5.6) {
                    System.out.println(brand.descriptor() + "->" + brand2.descriptor() + ": " + similarRating[i][brandTwo]);
                    Log.d("Very similar", "sSimilarValues.addEdge( Value." + brand + ", Value." + brand2 +" );" );
                }

                brandTwo++;
            }
            i++;
        }

    }
}
