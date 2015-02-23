package com.uwetrottmann.shopr.context.model;

import android.util.Log;

import com.uwetrottmann.shopr.R;
import com.uwetrottmann.shopr.ShoprApp;

import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Yannick on 11.02.15.
 *
 * This class stores the context data of the scenario, which the user entered at the beginning of the app.
 * It matches the data to internal constants and provides methods for the retrieval of the context data.
 */
public class ScenarioContext implements Cloneable {

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
    private CurrentLocation mCurrentLocation;

    private List<String> mRelaxations = new LinkedList<String>();

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
     * Returns whether the context is set or not, meaning whether we can work with it, or not.
     * @return true if working with the context is allowed.
     */
    public boolean isSet(){
        return mDistanceToShop != null;
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
     * Gets the context setting for the shop opening hours.
     * @return the shopOpeningHoursModel of the current context.
     */
    public ShopOpeningHoursModel getShopOpeningHoursModel(){
        return mOpeningHours;
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
     * With this method you can get the current location of the user.
     * @return a current location object containing the user's current location
     */
    public CurrentLocation getCurrentLocation() {
        return mCurrentLocation;
    }

    /**
     * Method that sets the current location within the scenario context and as well updates the current location
     * in the whole app wherever this is necessary.
     * @param place the place the user selected to be
     */
    public void setCurrentLocation(String place) {
        this.mCurrentLocation = CurrentLocation.getCurrentLocation(place);

        ShoprApp.setLastLocation(getCurrentLocation().getLatLng());
    }

    /**
     * Relaxes some of the conditions used in this scenario, in order to be able to retrieve more cases
     * from the case base. We start with the condition whether the shop has to not crowded. The next thing we check is
     * whether items in stock are included and at last we try to increase the searching distance.
     * @return a boolean indicating whether the relaxation of at least one condition has been successful
     */
    public boolean relaxSomeConditions(){
        // In the night normally nothing is open, so we have to relax this to any shop open.
        if (getTimeOfTheDay().equals(TimeOfTheDay.NIGHT) && !(getShopOpeningHoursModel().equals(ShopOpeningHoursModel.ANYTIME))){
            setOpennessOfShops(ShopOpeningHoursModel.ANYTIME.toString());
            mRelaxations.add(ShoprApp.getContext().getString(R.string.relaxed_to_anytime_open));
            return true;
        }

        // If it is evening it might be the case, that we restrict too hard. So here we also use every time open.
        if (getTimeOfTheDay().equals(TimeOfTheDay.EVENING) && ! getShopOpeningHoursModel().equals(ShopOpeningHoursModel.ANYTIME)){
            setOpennessOfShops(ShopOpeningHoursModel.ANYTIME.toString());
            mRelaxations.add(ShoprApp.getContext().getString((R.string.relaxed_to_anytime_open)));
            return true;
        }

        //In the morning, we might have to relax it in small intervals.
        if (getTimeOfTheDay().equals(TimeOfTheDay.MORNING)){
            if (getShopOpeningHoursModel().equals(ShopOpeningHoursModel.NOW)){
                setOpennessOfShops(ShopOpeningHoursModel.NOW_PLUS_30.toString());
                mRelaxations.add(ShoprApp.getContext().getString(R.string.relaxed_to_open_in_30));
                return true;
            } else if (getShopOpeningHoursModel().equals(ShopOpeningHoursModel.NOW_PLUS_30)){
                if (mRelaxations.contains(ShoprApp.getContext().getString(R.string.relaxed_to_open_in_30))){
                    mRelaxations.remove(ShoprApp.getContext().getString(R.string.relaxed_to_open_in_30));
                }
                setOpennessOfShops(ShopOpeningHoursModel.NOW_PLUS_60.toString());
                mRelaxations.add(ShoprApp.getContext().getString(R.string.relaxed_to_open_in_60));
                return true;
            } else if (getShopOpeningHoursModel().equals(ShopOpeningHoursModel.NOW_PLUS_60)){
                if (mRelaxations.contains(ShoprApp.getContext().getString(R.string.relaxed_to_open_in_60))){
                    mRelaxations.remove(ShoprApp.getContext().getString(R.string.relaxed_to_open_in_60));
                }
                setOpennessOfShops(ShopOpeningHoursModel.NOW_PLUS_90.toString());
                mRelaxations.add(ShoprApp.getContext().getString(R.string.relaxed_to_open_in_90));
                return true;
            } else if (getShopOpeningHoursModel().equals(ShopOpeningHoursModel.NOW_PLUS_90)){
                if (mRelaxations.contains(ShoprApp.getContext().getString(R.string.relaxed_to_open_in_90))){
                    mRelaxations.remove(ShoprApp.getContext().getString(R.string.relaxed_to_open_in_90));
                }
                setOpennessOfShops(ShopOpeningHoursModel.ANYTIME.toString());
                mRelaxations.add(ShoprApp.getContext().getString((R.string.relaxed_to_anytime_open)));
                return true;
            }
        }

        //First relax the crowded shops restriction
        if (!isCrowdedShopsAllowed()){
            setCrowdedShopsAllowed(true);
            mRelaxations.add(ShoprApp.getContext().getString(R.string.relaxedCrowdednessRestriction));
            return true;
        }

        //Then relax the items in stock restriction, because the shop might have updated its collection, but not send the data to us.
        if (isOnlyItemsInStock()){
            mRelaxations.add(ShoprApp.getContext().getString(R.string.relaxedItemInStockRestriction));
            setOnlyItemsInStock(false);
            return true;
        }

        //Then increase the allowed distance
        if (getDistanceToShop().equals(DistanceToShop.LESS_THAN_2KM)){
            mRelaxations.add(ShoprApp.getContext().getString(R.string.relaxedDistanceToShopTo5KM));
            setDistanceToShop(DistanceToShop.LESS_THAN_5KM.toString());
            return true;
        } else if (getDistanceToShop().equals(DistanceToShop.LESS_THAN_5KM)){
            if (mRelaxations.contains(ShoprApp.getContext().getString(R.string.relaxedDistanceToShopTo5KM))){
                mRelaxations.remove(ShoprApp.getContext().getString(R.string.relaxedDistanceToShopTo5KM));
            }
            mRelaxations.add(ShoprApp.getContext().getString(R.string.relaxedDistanceToShopToAnyDistance));
            setDistanceToShop(DistanceToShop.ANY_DISTANCE.toString());
            return true;
        }

        return false;
    }

    /**
     * Logs the current scenario context to the console
     */
    public void logScenarioContext(){
        Log.v(TAG, "Time of the day: " + mTimeOfTheDay);
        Log.v(TAG, "Day of the week: " + mDayOfTheWeek);
        Log.v(TAG, "Distance to shop: " + mDistanceToShop);
        Log.v(TAG, "Shop Opening Hours: " + mOpeningHours);
        Log.v(TAG, "Only items in stock: " + mOnlyItemsInStock);
        Log.v(TAG, "Crowded Shops allowed: " + mCrowdedShopsAllowed);
        Log.v(TAG, "Temperature: " + mTemperature);
        Log.v(TAG, "Weather: " + mWeather);
        Log.v(TAG, "Company: " + mCompany);
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

    /**
     * This method creates a new ScenarioContext object (equal to resetting all entries)
     * @return A new ScenarioContextObject.
     */
    public synchronized static ScenarioContext createNewInstance(){
        _instance = new ScenarioContext();
        return _instance;
    }

    /**
     * This method provides a copy of the currently active instance of the context scenario. This copy
     * may be manipulated without having an influence on the standard scenario context. The alteration
     * of context caused problems in conjunction with the 300 items that shall be loaded by the item loader.
     * @return A new instance of the scenario context with the same variables as the current instances
     */
    public synchronized static ScenarioContext getACopy(){
        if (_instance == null){
            return getInstance();
        }
        try {
            return _instance.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Clones the current object.
     * @return  new instance of the scenario context with this object's functions.
     */
    protected ScenarioContext clone() throws CloneNotSupportedException {
        try {
            super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        ScenarioContext copy = new ScenarioContext();
        copy.mDistanceToShop = mDistanceToShop;
        copy.mCrowdedShopsAllowed = mCrowdedShopsAllowed;
        copy.mCompany = mCompany;
        copy.mOnlyItemsInStock = mOnlyItemsInStock;
        copy.mWeather = mWeather;
        copy.mDayOfTheWeek = mDayOfTheWeek;
        copy.mOpeningHours = mOpeningHours;
        copy.mTemperature = mTemperature;
        copy.mTimeOfTheDay = mTimeOfTheDay;

        return copy;
    }

    public DistanceToShop getDistanceToShop() {
        return mDistanceToShop;
    }

    /**
     * Sets the relaxations
     * @param relaxations a list of done relaxations.
     */
    public void setRelaxations(List<String> relaxations){
        this.mRelaxations = relaxations;
    }

    /**
     * Returns the relaxations that were done.
     * @return a list containing the made relaxations.
     */
    public List<String> getRelaxations(){
        return mRelaxations;
    }
}
