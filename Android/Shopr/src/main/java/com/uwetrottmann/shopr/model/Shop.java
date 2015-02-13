
package com.uwetrottmann.shopr.model;

import android.location.Location;

import com.google.android.gms.maps.model.LatLng;
import com.uwetrottmann.shopr.context.model.ShopOpeningHoursModel;

import java.util.Calendar;

public class Shop {

    private int id;
    private String mShopName;
    private LatLng mLocation;
    private Location mLocationObject;
    private boolean mUsuallyCrowded;
    private OpeningHours[] mOpeningHours;

    public int id() {
        return id;
    }

    public Shop id(int id) {
        this.id = id;
        return this;
    }

    public String name() {
        return mShopName;
    }

    public Shop name(String name) {
        this.mShopName = name;
        return this;
    }

    public LatLng location() {
        return mLocation;
    }

    public Shop location(LatLng location) {
        this.mLocation = location;

        mLocationObject = new Location(this.mShopName);
        mLocationObject.setLongitude(location.longitude);
        mLocationObject.setLatitude(location.latitude);

        return this;
    }

    /**
     * Sets whether the shop is usually crowded, this is a relevant filter criterion for users
     * @param crowded whether the shop is usually crowded
     */
    public void setUsuallyCrowded(boolean crowded){
        mUsuallyCrowded = crowded;
    }

    /**
     * Returns whether the shop is usually crowded.
     * @return true if the shop is usually crowded - meaning lots of people are there!
     */
    public boolean isUsuallyCrowded(){
        return mUsuallyCrowded;
    }

    public Location getLocationObject(){
        return mLocationObject;
    }

    /**
     * Determines whether the shop will be open at the given time.
     * @return true if the shop is open
     */
    public boolean isOpen(ShopOpeningHoursModel model){
        Calendar calendar = Calendar.getInstance();

        return mOpeningHours[calendar.get(Calendar.DAY_OF_WEEK) - 1].isOpenAtTime(model); //starts with 1 at Sunday
    }

    /**
     * Sets the opening hours of a given shop.
     * @param openingHours a map with the opening hours of a given shop
     */
    public void setShopOpeningHours(OpeningHours[] openingHours){
        mOpeningHours = openingHours;
    }

}
