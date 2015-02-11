package com.uwetrottmann.shopr.context.model;

import android.util.Log;

import java.util.Calendar;

/**
 * Created by Yannick on 11.02.15.
 *
 * This class stores the context data of the scenario, which the user entered at the beginning of the app.
 * It matches the data to internal constants and provides methods for the retrieval of the context data.
 */
public class ScenarioContext {

    private static ScenarioContext _instance;
    private static final String TAG = "ScenarioContext";

    private DayOfTheWeek mDayOfTheWeek;
    private TimeOfTheDay mTimeOfTheDay;
    private DistanceToShop mDistanceToShop;
    private ShopOpeningHoursModel mOpeningHours;
    private Temperature mTemperature;
    private Weather mWeather;
    private Company mCompany;
    private boolean mCrowdedShopsAllowed;
    private boolean mOnlyItemsInStock;

    /**
     * Private Constructor to ensure the usage of the singleton. Instantiates the day of the week as
     * well as the current time
     */
    private ScenarioContext(){
        mTimeOfTheDay = TimeOfTheDay.getMatchingTimeOfTheDay(Calendar.getInstance());
        mDayOfTheWeek = DayOfTheWeek.getDay(Calendar.getInstance());

        mCrowdedShopsAllowed = true;
        mOnlyItemsInStock = false;
    }

    /**
     * Sets the temperature according to the given output string of the temperature
     * @param tempDesc the output string for the given temperature.
     */
    public void setTemperature(String tempDesc){
        mTemperature = Temperature.getTemperature(tempDesc);
    }

    /**
     * Gets the current temperature in the user scenario.
     * @return The temperature object.
     */
    public Temperature getTemperature(){
        return mTemperature;
    }

    /**
     * Returns the currently selected weather conditions
     * @return the current weather conditions
     */
    public Weather getWeather() {
        return mWeather;
    }

    /**
     * Sets the current weather
     * @param weatherDescription a description of the current weather.
     */
    public void setWeather(String weatherDescription) {
        this.mWeather = Weather.getWeather(weatherDescription);
    }

    /**
     * Gets the current company of the active person.
     * @return the current person's company.
     */
    public Company getCompany() {
        return mCompany;
    }

    /**
     * Sets the current person's company.
     * @param companyDescription a string describing the current person's company.
     */
    public void setCompany(String companyDescription) {
        this.mCompany = Company.getCompany(companyDescription);
    }

    /**
     * Returns the current time of the day as one of the enum entries
     * @return the current time of the day as enum entry
     */
    public TimeOfTheDay getTimeOfTheDay() {
        return mTimeOfTheDay;
    }

    /**
     * Returns the day of the week. Either it really is a workday or it is a public holiday.
     * @return the current day of the week.
     */
    public DayOfTheWeek getDayOfTheWeek() {
        return mDayOfTheWeek;
    }

    /**
     * Sets the by the user preferred distance to shops.
     * @param distanceString The selected text
     */
    public void setDistanceToShop(String distanceString) {
        mDistanceToShop = DistanceToShop.getDistanceToShop(distanceString);
    }

    /**
     * This method sets when the shops should be open.
     * @param opennessOfShops the selected String
     */
    public void setOpennessOfShops(String opennessOfShops) {
        mOpeningHours = ShopOpeningHoursModel.getShopOpeningHoursModel(opennessOfShops);
    }

    /**
     * This method sets whether items out of stock should be available as well or only items that are in stock
     * @param onlyItemsInStock true if only items in stock shall be displayed
     */
    public void setOnlyItemsInStock(boolean onlyItemsInStock) {
        mOnlyItemsInStock = onlyItemsInStock;
    }

    /**
     * Get whether it is allowed to also retrieve items, that are out of stock.
     * @return true if only items that are in stock should be retrieved.
     */
    public boolean isOnlyItemsInStock() {
        return mOnlyItemsInStock;
    }

    /**
     * This method sets whether crowded shops are allowed. This means that items within shops which are
     * frequented highly can be excluded.
     * @param crowdedShopsAllowed true if it is allowed to show shops that are highly frequented.
     */
    public void setCrowdedShopsAllowed(boolean crowdedShopsAllowed) {
        mCrowdedShopsAllowed = crowdedShopsAllowed;
    }

    /**
     * Get whether it is allowed to also retrieve shops that are crowded.
     * @return true if shops that are crowded are allowed.
     */
    public boolean isCrowdedShopsAllowed() {
        return mCrowdedShopsAllowed;
    }

    /**
     * Logs the current scenario context to the console
     */
    public void logScenarioContext(){
        Log.d(TAG, "Time of the day: " + mTimeOfTheDay);
        Log.d(TAG, "Day of the week: " + mDayOfTheWeek);
        Log.d(TAG, "Distance to shop: " + mDistanceToShop);
        Log.d(TAG, "Shop Opening Hours: " + mOpeningHours);
        Log.d(TAG, "Only items in stock: " + mOnlyItemsInStock);
        Log.d(TAG, "Crowded Shops allowed: " + mCrowdedShopsAllowed);
        Log.d(TAG, "Temperature: " + mTemperature);
        Log.d(TAG, "Weather: " + mWeather);
        Log.d(TAG, "Company: " + mCompany);
    }

    /**
     * Static method to make this class a singleton.
     * @return The valid instance of the ScenarioContext
     */
    public synchronized static ScenarioContext getInstance() {
        if (_instance == null) {
            _instance = new ScenarioContext();
        }
        return _instance;
    }
}
