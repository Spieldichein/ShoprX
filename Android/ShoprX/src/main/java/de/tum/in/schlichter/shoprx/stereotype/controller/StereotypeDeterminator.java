package de.tum.in.schlichter.shoprx.stereotype.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import de.tum.in.schlichter.shoprx.algorithm.model.Sex;
import de.tum.in.schlichter.shoprx.stereotype.dto.StereotypeForm;
import de.tum.in.schlichter.shoprx.stereotype.stereotypes.AbstractStereotype;
import de.tum.in.schlichter.shoprx.stereotype.stereotypes.Athlete;
import de.tum.in.schlichter.shoprx.stereotype.stereotypes.Classy;
import de.tum.in.schlichter.shoprx.stereotype.stereotypes.Emo;
import de.tum.in.schlichter.shoprx.stereotype.stereotypes.Girly;
import de.tum.in.schlichter.shoprx.stereotype.stereotypes.Gothic;
import de.tum.in.schlichter.shoprx.stereotype.stereotypes.Indie;
import de.tum.in.schlichter.shoprx.stereotype.stereotypes.Mainstream;
import de.tum.in.schlichter.shoprx.stereotype.stereotypes.Preppy;
import de.tum.in.schlichter.shoprx.stereotype.stereotypes.Skater;
import de.tum.in.schlichter.shoprx.stereotype.user.AgeRange;
import de.tum.in.schlichter.shoprx.stereotype.user.Music;
import de.tum.in.schlichter.shoprx.utils.Utils;

/**
 * Created by Yannick on 29.01.15.
 *
 * This class determines which stereotypes could best fit the person's interests and styles.
 */
public class StereotypeDeterminator {

    private static List<AbstractStereotype> stereotypes;

    public StereotypeDeterminator(){
        stereotypes = new ArrayList<AbstractStereotype>();
        stereotypes.add(new Athlete());
        stereotypes.add(new Emo());
        stereotypes.add(new Classy());
        stereotypes.add(new Girly());
        stereotypes.add(new Gothic());
        stereotypes.add(new Indie());
        stereotypes.add(new Mainstream());
        stereotypes.add(new Preppy());
        stereotypes.add(new Skater());
    }

    public List<AbstractStereotype> determinePotentialStereotypes(
            StereotypeForm form) {
        Map<AbstractStereotype, Integer> stereotypeLikelihood = new HashMap<AbstractStereotype, Integer>();

        //determine likelihood of stereotype for given information in form
        for (AbstractStereotype stereotype : stereotypes){
            Integer likelihood = 0;
            if (stereotype.getSex().equals(Sex.Value.UNISEX) || stereotype.getSex().equals(form.getSex())){
                //Log.d("Current stereotype", stereotype.toString());
                likelihood += stereotype.getAgeRange().get(getAgeRange(form.getAge()));
                likelihood += stereotype.getJobs().get(form.getJob());
                for (Music music : form.getMusicTaste()){
                    likelihood += stereotype.getMusicTaste().get(music);
                }
            }
            stereotypeLikelihood.put(stereotype, likelihood);
        }
        //sort the map by the highest likelihood
        Map<AbstractStereotype, Integer> sortedMap = Utils.sortMapByValue(stereotypeLikelihood);
        List<AbstractStereotype> topThreeStereotypes = new ArrayList<AbstractStereotype>();
        Set<Map.Entry<AbstractStereotype, Integer>> entrySet = sortedMap.entrySet();
        for (Map.Entry<AbstractStereotype, Integer> entry : entrySet){
            topThreeStereotypes.add(entry.getKey());
            if (topThreeStereotypes.size() == 3)
                break;
        }
        return topThreeStereotypes;
    }

    /** *@return returns the age range. */
    public static AgeRange getAgeRange(int age) {
        if (age < 13)
            return AgeRange.CHILD;
        if (age >= 13 && age < 18)
            return AgeRange.TEENAGER;
        if (age >= 18 && age < 30)
            return AgeRange.YOUNG_ADULT;
        if (age >= 30 && age < 50)
            return AgeRange.ADULT;
        if (age >= 50 && age < 65)
            return AgeRange.OLDER_ADULT;
        if (age >= 65)
            return AgeRange.SENIOR;
        throw new RuntimeException("Could not determine age range.");
    }
}
