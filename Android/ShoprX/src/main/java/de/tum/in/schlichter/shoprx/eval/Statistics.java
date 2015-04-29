
package de.tum.in.schlichter.shoprx.eval;

import android.content.ContentValues;
import android.content.Context;
import android.net.Uri;

import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;

import java.util.LinkedList;
import java.util.List;

import de.tum.in.schlichter.shoprx.ShoprApp;
import de.tum.in.schlichter.shoprx.algorithm.model.Item;
import de.tum.in.schlichter.shoprx.provider.ShoprContract;

/**
 * Stores data about the current task.
 */
public class Statistics {

    private static Statistics _instance;

    private long mStartTime;
    private long mStartTimeRecommendation;
    private int mCycleCount;
    private int mCyclePositiveCount;
    private String mUserName;
    private int mSelectedItemPosition;
    private List<Integer> mShowedIds;
    private String mSelectedStereotype;
    private int fashionSuggested;
    private int chartLookedAt;
    private int preferenceSettingStarted;
    private int preferenceChanged;
    private int labelPrefrenceStarted;
    private int alphaChanged;

    private boolean mIsStarted;

    private Statistics() {

    }

    public synchronized static Statistics get() {
        if (_instance == null) {
            _instance = new Statistics();
        }
        return _instance;
    }

    /**
     * Saves the current time, user name and task type until
     * {@link #finishTask(Context)} is called.
     */
    public synchronized void startTask(String username) {
        mIsStarted = true;
        mUserName = username;
        mStartTime = System.currentTimeMillis();
        mShowedIds = new LinkedList<Integer>();
        mCycleCount = 0;
        mCyclePositiveCount = 0;
        fashionSuggested =0;
        preferenceChanged =0;
        preferenceSettingStarted=0;
        chartLookedAt =0;
        labelPrefrenceStarted=0;
        alphaChanged =0;
    }

    /**
     * Increases the recommendation cycle count by 1. Also the positive cycle
     * count if isPositive is true.
     */
    public synchronized void incrementCycleCount(boolean isPositive) {
        mCycleCount++;
        if (isPositive) {
            mCyclePositiveCount++;
        }
    }

    public synchronized  void fashionSuggested(){
        fashionSuggested++;
    }

    public synchronized  void preferenceStarted(){
        preferenceSettingStarted++;
    }

    public synchronized  void preferenceChanged(){
        preferenceChanged++;
    }
    public synchronized  void chartStarted(){
        chartLookedAt++;
    }
    public synchronized  void labelPrefrenceStarted(){
        labelPrefrenceStarted++;
    }
    public synchronized  void alphaChanged(){
        alphaChanged++;
    }

    /**
     * Adds all the itemIds in the list to the member list of this class. This is used in order to detect how many
     * (distinct) items are shown to the user.
     * @param items A list of all the items used in this critiquing cycle
     */
    public synchronized void itemCoverageStatistics(List<Item> items){
        if (mShowedIds == null){
            mShowedIds = new LinkedList<Integer>();
        }

        for (Item item : items) {
            mShowedIds.add(item.id());
        }
    }

    /**
     * Stops the task and writes all data to the database.
     * 
     * @return The {@link Uri} pointing to the new data set or {@code null} if
     *         {@link #startTask(String)} was not called before.
     */
    public synchronized Uri finishTask(Context context) {
        if (!mIsStarted) {
            return null;
        }

        mIsStarted = false;
        long duration = System.currentTimeMillis() - mStartTime;
        long durationFromRecommendationStart = System.currentTimeMillis() - mStartTimeRecommendation;

        // Write to database
        ContentValues statValues = new ContentValues();
        statValues.put(ShoprContract.Stats.USERNAME, mUserName);
        statValues.put(ShoprContract.Stats.CYCLE_COUNT, mCycleCount + "(" + mCyclePositiveCount + "+)");
        statValues.put(ShoprContract.Stats.DURATION, duration);
        statValues.put(ShoprContract.Stats.DURATION_RECOMMENDATION, durationFromRecommendationStart);
        statValues.put(ShoprContract.Stats.ITEM_POSITION, mSelectedItemPosition);
        statValues.put(ShoprContract.Stats.FASHION_SUGGESTED, fashionSuggested);
        statValues.put(ShoprContract.Stats.STEREOTYPE, mSelectedStereotype);
        statValues.put(ShoprContract.Stats.CHARTS_LOOKED_AT, chartLookedAt);
        statValues.put(ShoprContract.Stats.PREFERENCE_CHANGED, preferenceChanged);
        statValues.put(ShoprContract.Stats.PREFERENCE_SETTING_STARTED, preferenceSettingStarted);
        statValues.put(ShoprContract.Stats.LABEL_PREFERENCE_STARTED, labelPrefrenceStarted);
        statValues.put(ShoprContract.Stats.ALPHA_CHANGED, alphaChanged);

        StringBuilder build = new StringBuilder();
        for(Integer i : mShowedIds){
            build.append(i);
            build.append(",");
        }
        statValues.put(ShoprContract.Stats.ITEM_COVERAGE, build.toString());

        final Uri inserted = context.getContentResolver().insert(ShoprContract.Stats.CONTENT_URI, statValues);

        Tracker t = ((ShoprApp) context.getApplicationContext()).getTracker();

        t.send(new HitBuilders.EventBuilder().setCategory("Results").setAction("Value").setLabel("Cycles").setValue((long) mCycleCount).build());
        t.send(new HitBuilders.EventBuilder().setCategory("Results").setAction("Value").setLabel("Cycles (positive)").setValue((long) mCyclePositiveCount).build());
        t.send(new HitBuilders.EventBuilder().setCategory("Results").setAction("Value").setLabel("Duration").setValue(duration).build());
        t.send(new HitBuilders.EventBuilder().setCategory("Results").setAction("Value").setLabel("Duration Recommendations").setValue(durationFromRecommendationStart).build());
        t.send(new HitBuilders.EventBuilder().setCategory("Results").setAction("Value").setLabel("Item Position").setValue((long) mSelectedItemPosition).build());

        return inserted;
    }

    public synchronized String getUserName() {
        return mUserName;
    }

    public synchronized void setSelectedItemPosition(int position){
        mSelectedItemPosition = position;
    }

    public synchronized void startRecommendingItems(){
        mStartTimeRecommendation = System.currentTimeMillis();
    }

    /**
     * This method is used to set the name of the stereotype, that is currently used.
     * @param stereotypeName the name of the currently selected stereotype
     */
    public synchronized void setStereotype(String stereotypeName){
        mSelectedStereotype = stereotypeName;
    }

}
