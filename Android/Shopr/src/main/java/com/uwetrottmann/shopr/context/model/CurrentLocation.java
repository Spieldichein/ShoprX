package com.uwetrottmann.shopr.context.model;

import com.google.android.gms.maps.model.LatLng;
import com.uwetrottmann.shopr.R;
import com.uwetrottmann.shopr.ShoprApp;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Yannick on 19.02.15.
 *
 * This class shall provide the current location information for the different places, where a person can be.
 */
public enum CurrentLocation {
    MARIENPLATZ(ShoprApp.getContext().getString(R.string.Marienplatz), 48.137314, 11.575253),
    UNIVERSITAET(ShoprApp.getContext().getString(R.string.Universitaet), 48.1495409, 11.5812512),
    GARCHING(ShoprApp.getContext().getString(R.string.Deutsches_Museum), 48.129871, 11.58346),
    KARLSPLATZ(ShoprApp.getContext().getString(R.string.Karlsplatz), 48.139520, 11.56580);

    private String mPlaceName;
    private LatLng mLocation;

    private static Map<String, CurrentLocation> sAvailableLocations = new HashMap<String, CurrentLocation>();

    public static CurrentLocation getCurrentLocation(String placeName){
        if (sAvailableLocations.size() == 0){
            for (CurrentLocation loc : CurrentLocation.values()){
                sAvailableLocations.put(loc.getPlaceName(), loc);
            }
        }

        return sAvailableLocations.get(placeName);
    }

    CurrentLocation(String place, double lat, double lng) {
        mLocation = new LatLng(lat, lng);
        mPlaceName = place;
    }

    /**
     * The name of the place in which the user currently is in,
     * @return the place's name
     */
    public String getPlaceName(){
        return mPlaceName;
    }

    /**
     * Returns the current user's location as a {@link com.google.android.gms.maps.model.LatLng}.
     * @return current user's position
     */
    public LatLng getLatLng(){
        return mLocation;
    }
}
