package com.uwetrottmann.shopr.context.model;

import com.uwetrottmann.shopr.R;
import com.uwetrottmann.shopr.ShoprApp;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Yannick on 11.02.15.
 *
 * This class stores the current temperature within the given context scenario.
 */
public enum Temperature implements DistanceMetric{
    LESS_THAN_0(R.string.below0Degrees),
    BETWEEN_0_AND_5(R.string.between0And5Degrees),
    BETWEEN_5_AND_10(R.string.between5And10Degrees),
    BETWEEN_10_AND_15(R.string.between10And15Degrees),
    BETWEEN_15_AND_20(R.string.between15And20Degrees),
    BETWEEN_20_AND_25(R.string.between20And25Degrees),
    BETWEEN_25_AND_30(R.string.between25And30Degrees),
    ABOVE_30(R.string.above30Degrees);

    private static final double WEIGHT = 0.89;

    private int mDegrees;

    private static Map<String, Temperature> sAvailableTemperatures = new HashMap<String, Temperature>();

    Temperature(int degrees) {
         mDegrees = degrees;
    }

    public String descriptor(){
        return ShoprApp.getContext().getString(mDegrees);
    }

    public static Temperature getTemperature(String description){
        if (sAvailableTemperatures.size() == 0){
            for (Temperature temp : Temperature.values()){
                sAvailableTemperatures.put(ShoprApp.getContext().getString(temp.mDegrees), temp);
            }
        }

        return sAvailableTemperatures.get(description);
    }

    @Override
    public String toString(){
        return ShoprApp.getContext().getString(mDegrees);
    }

    public int getDegrees(){
        return mDegrees;
    }

    public static Temperature getTemperature(int match){
        for (Temperature value: values()){
            if (value.mDegrees == match){
                return value;
            }
        }
        return null;
    }

    @Override
    public boolean isMetricWithEuclideanDistance() {
        return true;
    }

    @Override
    public double getWeight() {
        return WEIGHT;
    }

    @Override
    public double distanceToContext(ScenarioContext scenarioContext) throws UnsupportedOperationException {
        throw new UnsupportedOperationException("Is Euclidean Distance");
    }

    @Override
    public int numberOfItems() throws UnsupportedOperationException {
        return values().length;
    }

    @Override
    public int currentOrdinal() throws UnsupportedOperationException {
        return ordinal();
    }
}
