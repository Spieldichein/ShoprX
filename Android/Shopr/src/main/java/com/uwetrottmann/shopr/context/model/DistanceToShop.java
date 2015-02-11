package com.uwetrottmann.shopr.context.model;

import com.uwetrottmann.shopr.R;
import com.uwetrottmann.shopr.ShoprApp;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Yannick on 11.02.15.
 *
 * This enum provides a measurement on how far away a given shop can be in order to display items from this shop
 */
public enum DistanceToShop {
    LESS_THAN_2KM(R.string.upTo2Km, 2),
    LESS_THAN_5KM(R.string.upTo5Km, 5),
    ANY_DISTANCE(R.string.anyShop, 0);

    int mDistance;
    int mInstanceNumber;

    private static Map<String, DistanceToShop> sAvailableDistances = new HashMap<String, DistanceToShop>();

    DistanceToShop(int idInR, int distanceToLocation) {
        this.mInstanceNumber = idInR;
        this.mDistance = distanceToLocation;
    }

    public static DistanceToShop getDistanceToShop(String description){
        if (sAvailableDistances.size() == 0){
            for (DistanceToShop dist : DistanceToShop.values()){
                sAvailableDistances.put(ShoprApp.getContext().getString(dist.mInstanceNumber), dist);
            }
        }

        return sAvailableDistances.get(description);
    }

    @Override
    public String toString(){
        return ShoprApp.getContext().getString(mInstanceNumber);
    }
}
