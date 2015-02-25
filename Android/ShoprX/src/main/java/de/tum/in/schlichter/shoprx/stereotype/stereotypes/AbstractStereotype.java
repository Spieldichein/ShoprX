package de.tum.in.schlichter.shoprx.stereotype.stereotypes;

import java.util.Map;

import de.tum.in.schlichter.shoprx.algorithm.model.Label;
import de.tum.in.schlichter.shoprx.algorithm.model.Sex;
import de.tum.in.schlichter.shoprx.stereotype.user.AgeRange;
import de.tum.in.schlichter.shoprx.stereotype.user.Job;
import de.tum.in.schlichter.shoprx.stereotype.user.Music;

/**
 * Created by Yannick on 29.01.15.
 *
 * The Abstract implementation of a stereotype. The given stereotypes will be based on this abstract implementation.
 * Also the recommendation algorithm will work with this abstract implementation.
 */
public abstract class AbstractStereotype {

    protected Map<AgeRange, Integer> ageRange;

    protected Map<Job, Integer> jobs;

    protected Map<Music, Integer> musicTaste;

    //unless specified differently stereotypes apply to both sexes
    protected Sex.Value sex = Sex.Value.UNISEX;

    protected Map<Label.Value, Integer> brandProbabilityMap;

    protected Map<String, Integer> attributeProbabilityMap;

    protected Stereotype stereotype;

    /**
     * @return the brandProbabilityMap
     */
    public Map<Label.Value, Integer> getBrandProbabilityMap() {
        return brandProbabilityMap;
    }

    /** * @return Returns the attributeProbabilityMap. */
    public Map<String, Integer> getAttributeProbabilityMap() {
        return attributeProbabilityMap;
    }

    /** * @return Returns the name. */
    public Stereotype getStereotype() {
        return stereotype;
    }

    /**
     * @return the age range
     */
    public Map<AgeRange, Integer> getAgeRange() {
        return ageRange;
    }

    /**
     * @return the jobs
     */
    public Map<Job, Integer> getJobs() {
        return jobs;
    }

    /**
     * @return the musicTaste
     */
    public Map<Music, Integer> getMusicTaste() {
        return musicTaste;
    }

    /**
     * @return the sex
     */
    public Sex.Value getSex() {
        return sex;
    }
}
