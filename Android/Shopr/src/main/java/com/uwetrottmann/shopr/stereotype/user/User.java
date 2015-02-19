package com.uwetrottmann.shopr.stereotype.user;

import android.util.Log;

import com.uwetrottmann.shopr.algorithm.model.Sex;
import com.uwetrottmann.shopr.stereotype.stereotypes.AbstractStereotype;

import java.util.List;
import java.util.Set;

/**
 * Created by Yannick on 29.01.15.
 *
 * Class to store the User model, which is needed for the stereotypes part of the app.
 */
public class User {

    private Job job;

    private Sex.Value sex;

    private int age;

    private Set<Music> musicTaste;

    private AbstractStereotype stereotype;

    private static User INSTANCE = null;

    private List<AbstractStereotype> potentialStereotypes;

    private User(Job job, Sex.Value sex, Set<Music> musicTaste) {
        this.job = job;
        this.sex = sex;
        this.musicTaste = musicTaste;
    }

    public static User initializeUser(Job job, Sex.Value sex,
                                      Set<Music> musicTaste) {
        INSTANCE = new User(job, sex, musicTaste);
        return INSTANCE;
    }

    public static User getUser() {
        if (INSTANCE == null) {
            Log.e(User.class.toString(), "No User object initialized.");
            throw new RuntimeException("User object is null.");
        } else {
            return INSTANCE;
        }

    }

    /** * @return Returns the stereotypes. */
    public AbstractStereotype getStereotype() {
        return stereotype;
    }

    /** * @param stereotype The stereotype to set. */
    public void setStereotype(AbstractStereotype stereotype) {
        this.stereotype = stereotype;
    }

    /** * @return Returns the job. */
    public Job getJob() {
        return job;
    }

    /** * @return Returns the sex. */
    public Sex.Value getSex() {
        return sex;
    }

    /** * @return Returns the musicTaste. */
    public Set<Music> getMusicTaste() {
        return musicTaste;
    }

    /** * @return Returns the age. */
    public int getAge() {
        return age;
    }

    /**
     * @return whether user object has Stereotype
     */
    public boolean hasStereotype() {
        return stereotype != null;
    }

    /**
     * @param job the job to set
     */
    public void setJob(Job job) {
        this.job = job;
    }

    /**
     * @param age the age to set
     */
    public void setAge(int age) {
        this.age = age;
    }

    /**
     * @param musicTaste the musicTaste to set
     */
    public void setMusicTaste(Set<Music> musicTaste) {
        this.musicTaste = musicTaste;
    }

    /**
     * Returns the potential stereotypes, that the user could be.
     * @return the potentialStereotypes
     */
    public List<AbstractStereotype> getPotentialStereotypes() {
        return potentialStereotypes;
    }

    /**
     * Sets the potential stereotypes based on the calculation made by the {@link com.uwetrottmann.shopr.stereotype.controller.StereotypeDeterminator}.
     * @param potentialStereotypes the potentialStereotypes to set
     */
    public void setPotentialStereotypes(List<AbstractStereotype> potentialStereotypes) {
        this.potentialStereotypes = potentialStereotypes;
    }
}
