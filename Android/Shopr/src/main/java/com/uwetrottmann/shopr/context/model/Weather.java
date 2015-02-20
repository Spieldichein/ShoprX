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
public enum Weather implements DistanceMetric{
    SUNNY(R.string.sunnyWeather),
    PARTLY_CLOUDY(R.string.partlyCloudyWeather),
    CLOUDY(R.string.cloudyWeather),
    VERY_CLOUDY(R.string.veryCloudyWeather),
    RAINING(R.string.RainyWeather),
    SNOWING(R.string.SnowyWeather),
    OTHER_CONDITIONS(R.string.OtherWeather);

    private int mWeatherIndicator;

    private static Map<String, Weather> sAvailableWeatherStates = new HashMap<String, Weather>();

    Weather(int weather) {
         mWeatherIndicator = weather;
    }

    public String descriptor(){
        return ShoprApp.getContext().getString(mWeatherIndicator);
    }

    public static Weather getWeather(String description){
        if (sAvailableWeatherStates.size() == 0){
            for (Weather weather : Weather.values()){
                sAvailableWeatherStates.put(ShoprApp.getContext().getString(weather.mWeatherIndicator), weather);
            }
        }

        return sAvailableWeatherStates.get(description);
    }

    @Override
    public String toString(){
        return ShoprApp.getContext().getString(mWeatherIndicator);
    }

    public int getWeatherIndicator(){
        return mWeatherIndicator;
    }

    public static Weather getWeather(int match){
        for (Weather value: values()){
            if (value.mWeatherIndicator == match){
                return value;
            }
        }
        return null;
    }

    @Override
    public boolean isMetricWithEuclideanDistance() {
        return false;
    }

    @Override
    public double distanceToContext(ScenarioContext scenarioContext) throws UnsupportedOperationException {
        return 0; // TODO
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
