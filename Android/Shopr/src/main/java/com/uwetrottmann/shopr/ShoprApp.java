
package com.uwetrottmann.shopr;

import android.app.Application;
import android.content.Context;
import android.preference.PreferenceManager;

import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.Tracker;

import java.util.HashMap;

public class ShoprApp extends Application {

    public enum TrackerName {
        APP_TRACKER
    }

    HashMap<TrackerName, Tracker> mTrackers;
    private static Context mContext;

    public static final String GOOGLE_ANALYTICS_ID = "UA-59068924-1";

    @Override
    public void onCreate() {
        super.onCreate();

        mContext = this;

        PreferenceManager.setDefaultValues(this, R.xml.prefs, false);

        mTrackers = new HashMap<TrackerName, Tracker>();
    }

    public synchronized Tracker getTracker(){
        if (! mTrackers.containsKey(TrackerName.APP_TRACKER)){
            GoogleAnalytics analytics = GoogleAnalytics.getInstance(this);
            Tracker t = analytics.newTracker(GOOGLE_ANALYTICS_ID);

            t.enableAdvertisingIdCollection(true);

            mTrackers.put(TrackerName.APP_TRACKER, t);
        }

        return mTrackers.get(TrackerName.APP_TRACKER);
    }

    public static Context getContext() {
        return mContext;
    }

}
