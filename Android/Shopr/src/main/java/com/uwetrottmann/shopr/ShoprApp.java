
package com.uwetrottmann.shopr;

import android.app.Application;
import android.content.Context;
import android.preference.PreferenceManager;

import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.Tracker;
import com.google.android.gms.maps.model.LatLng;
import com.uwetrottmann.shopr.model.Shop;

import java.util.HashMap;
import java.util.List;

public class ShoprApp extends Application {

    public enum TrackerName {
        APP_TRACKER
    }

    HashMap<TrackerName, Tracker> mTrackers;
    private static Context mContext;

    public static final String GOOGLE_ANALYTICS_ID = "UA-59068924-1";

    private static LatLng sLastLocation;

    private static List<Shop> sShopList;

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

    public static synchronized void setLastLocation(LatLng lastLocation){
        sLastLocation = lastLocation;
    }

    public static synchronized LatLng getLastLocation(){
        return sLastLocation;
    }

    public static synchronized void setShopList(List<Shop> shopList){
        sShopList = shopList;
    }

    public static synchronized List<Shop> getShopList(){
        return sShopList;
    }

}
