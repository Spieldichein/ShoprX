
package de.tum.in.schlichter.shoprx.model;

import android.location.Location;

import com.google.android.gms.maps.model.LatLng;

import java.util.Calendar;

import de.tum.in.schlichter.shoprx.R;
import de.tum.in.schlichter.shoprx.ShoprApp;
import de.tum.in.schlichter.shoprx.context.model.ShopOpeningHoursModel;

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

    /**
     * Returns a string containing the opening hours for today.
     * @return string including the opening hours today
     */
    public String openToday(){
        Calendar calendar = Calendar.getInstance();
        OpeningHours openingHour = mOpeningHours[calendar.get(Calendar.DAY_OF_WEEK) - 1];

        int open = openingHour.getShopOpeningTime();
        int close = openingHour.getShopClosingTime();
        if (open == close){
            return ShoprApp.getContext().getString(R.string.closed_today);
        }

        int open_minutes = open % 100;
        open = open / 100;

        int close_minutes = close % 100;
        close = close / 100;

        StringBuilder builder = new StringBuilder();
        builder.append(open);
        builder.append(":");
        builder.append(String.format("%02d",open_minutes));
        builder.append(" - ");
        builder.append(close);
        builder.append(":");
        builder.append(String.format("%02d",close_minutes));
        builder.append("h");

        return builder.toString();
    }

}
