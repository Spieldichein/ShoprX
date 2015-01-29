package com.uwetrottmann.shopr.stereotype.user;

import android.util.Log;

import com.uwetrottmann.shopr.algorithm.model.Sex;
import com.uwetrottmann.shopr.stereotype.stereotypes.AbstractStereotype;

import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * Created by Yannick on 29.01.15.
 *
 * Class to store the User model, which is needed for the stereotypes part of the app.
 */
public class User {

    private String userName;

    private Job job;

    private Sex.Value sex;

    private int age;

    private Set<Music> musicTaste;

    private AbstractStereotype stereotype;

    private static User INSTANCE = null;

    private Date timeStarted;

    private int critiqueCycles;

    private List<AbstractStereotype> potentialStereotypes;

    private User(String userName, Job job, Sex.Value sex, Set<Music> musicTaste) {
        this.userName = userName;
        this.job = job;
        this.sex = sex;
        this.musicTaste = musicTaste;
        this.timeStarted = new Date();
        this.critiqueCycles = 0;
    }

    public static User initializeUser(String userName, Job job, Sex.Value sex,
                                      Set<Music> musicTaste) {
        INSTANCE = new User(userName, job, sex, musicTaste);
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

    /** * @return Returns the userName. */
    public String getUserName() {
        return userName;
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
     * @param job
     *            the job to set
     */
    public void setJob(Job job) {
        this.job = job;
    }

    /**
     * @param age
     *            the age to set
     */
    public void setAge(int age) {
        this.age = age;
    }

    /**
     * @param musicTaste
     *            the musicTaste to set
     */
    public void setMusicTaste(Set<Music> musicTaste) {
        this.musicTaste = musicTaste;
    }

    public Date getTimeStarted() {
        return timeStarted;
    }

    public int getCritiqueCycles() {
        return critiqueCycles;
    }

    public void increaseCritiqueCycle() {
        this.critiqueCycles += 1;
    }

    public void reset() {
        this.critiqueCycles = 0;
        this.timeStarted = new Date();

    }

    /**
     * @return the potentialStereotypes
     */
    public List<AbstractStereotype> getPotentialStereotypes() {
        return potentialStereotypes;
    }

    /**
     * @param potentialStereotypes the potentialStereotypes to set
     */
    public void setPotentialStereotypes(
            List<AbstractStereotype> potentialStereotypes) {
        this.potentialStereotypes = potentialStereotypes;
    }
}
