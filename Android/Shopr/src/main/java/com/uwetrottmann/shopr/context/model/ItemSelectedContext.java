package com.uwetrottmann.shopr.context.model;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Yannick on 19.02.15.
 *
 * This class stores the information on the contexts in which the {@link com.uwetrottmann.shopr.algorithm.model.Item} has been selected.
 * It should therefore be attached to an item.
 */
public class ItemSelectedContext {

    private Map<TimeOfTheDay, Integer> mTimeOfTheDay;
    private Map<DayOfTheWeek, Integer> mDayOfTheWeek;
    private Map<Temperature, Integer> mTemperature;
    private Map<Weather, Integer> mWeather;
    private Map<Company, Integer> mCompany;

    /**
     * Initializes the local data stores.
     */
    public ItemSelectedContext(){
        mTimeOfTheDay = new HashMap<TimeOfTheDay, Integer>();
        for (TimeOfTheDay t : TimeOfTheDay.values()){
            mTimeOfTheDay.put(t, 0);
        }

        mDayOfTheWeek = new HashMap<DayOfTheWeek, Integer>();
        for (DayOfTheWeek d : DayOfTheWeek.values()){
            mDayOfTheWeek.put(d, 0);
        }

        mTemperature = new HashMap<Temperature, Integer>();
        for (Temperature t : Temperature.values()){
            mTemperature.put(t, 0);
        }

        mWeather = new HashMap<Weather, Integer>();
        for (Weather w : Weather.values()){
            mWeather.put(w, 0);
        }

        mCompany = new HashMap<Company, Integer>();
        for (Company c : Company.values()){
            mCompany.put(c, 0);
        }
    }

    /**
     * Increases the counter for the times of the day in which this item has been selected.
     * @param t the TimeOfTheDay to increase.
     */
    public void increaseTimeOfTheDay(TimeOfTheDay t){
        mTimeOfTheDay.put(t, mTimeOfTheDay.get(t) + 1);
    }

    /**
     * Increases the counter for the day of the week in which this item has been selected.
     * @param d the DayOfTheWeek to increase
     */
    public void increaseDayOfTheWeek(DayOfTheWeek d){
        mDayOfTheWeek.put(d, mDayOfTheWeek.get(d) + 1);
    }

    /**
     * Increases the counter for the temperature in which this item has been selected.
     * @param t the Temperature to increase
     */
    public void increaseTemperature(Temperature t){
        mTemperature.put(t, mTemperature.get(t) + 1);
    }

    /**
     * Increases the counter for the weather in which this item has been selected.
     * @param w the Weather to increase
     */
    public void increaseWeather(Weather w){
        mWeather.put(w, mWeather.get(w) + 1);
    }

    /**
     * Increases the counter for the company in which this item has been selected.
     * @param c the Company to increase
     */
    public void increaseCompany(Company c){
        mCompany.put(c, mCompany.get(c) + 1);
    }

    @Override
    public String toString() {
        return "ItemSelectedContext{" +
                "mTimeOfTheDay=" + mTimeOfTheDay +
                ", mDayOfTheWeek=" + mDayOfTheWeek +
                ", mTemperature=" + mTemperature +
                ", mWeather=" + mWeather +
                ", mCompany=" + mCompany +
                '}';
    }
}
