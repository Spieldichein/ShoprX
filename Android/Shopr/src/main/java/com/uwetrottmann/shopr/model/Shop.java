
package com.uwetrottmann.shopr.model;

import android.location.Location;

import com.google.android.gms.maps.model.LatLng;

public class Shop {

    private int id;
    private String name;
    private LatLng location;
    private Location mLocationObject;

    public int id() {
        return id;
    }

    public Shop id(int id) {
        this.id = id;
        return this;
    }

    public String name() {
        return name;
    }

    public Shop name(String name) {
        this.name = name;
        return this;
    }

    public LatLng location() {
        return location;
    }

    public Shop location(LatLng location) {
        this.location = location;

        mLocationObject = new Location(this.name);
        mLocationObject.setLongitude(location.longitude);
        mLocationObject.setLatitude(location.latitude);

        return this;
    }

    public Location getLocationObject(){
        return mLocationObject;
    }

}
