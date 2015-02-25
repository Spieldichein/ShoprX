package de.tum.in.schlichter.shoprx.context.model;

import de.tum.in.schlichter.shoprx.R;

import java.util.HashMap;
import java.util.Map;

import de.tum.in.schlichter.shoprx.ShoprApp;

/**
 * Created by Yannick on 11.02.15.
 *
 * This enum provides a measurement on how far away a given shop can be in order to display items from this shop
 */
public enum DistanceToShop {
    LESS_THAN_2KM(R.string.upTo2Km, 2000),
    LESS_THAN_5KM(R.string.upTo5Km, 5000),
    ANY_DISTANCE(R.string.anyShop, 0);

    float mDistance;
    int mInstanceNumber;

    private static Map<String, DistanceToShop> sAvailableDistances = new HashMap<String, DistanceToShop>();

    DistanceToShop(int idInR, float distanceToLocation) {
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

    public float getDistance(){
        return mDistance;
    }
}
