package com.uwetrottmann.shopr.context.algorithm;

import android.location.Location;

import com.google.android.gms.maps.model.LatLng;
import com.uwetrottmann.shopr.ShoprApp;
import com.uwetrottmann.shopr.algorithm.model.Item;
import com.uwetrottmann.shopr.context.model.DistanceToShop;
import com.uwetrottmann.shopr.context.model.ScenarioContext;
import com.uwetrottmann.shopr.model.Shop;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yannick on 12.02.15.
 *
 * This class is responsible for everything that deals with contextual pre-filtering.
 * So everything that should be pre-filtered concerning the context will be done here.
 */
public class ContextualPreFiltering {

    private static boolean sOnlyItemsInStock = true;

    /**
     * In this method everything concerning the shops will be pre-filtered. Therefore we ensure
     * that the user gets shops within the selected distance as well as within the given opening
     * hours and other restrictions.
     * @param cases the current case base
     * @return a new (limited) case base
     */
    public static List<Item> filterShops(List<Item> cases){
        //Filter out shops (and their items) which are too far away.
        //First we get the current coordinates - where are we now.
        // And build a location object based on these coordinates.
        LatLng latLng = ShoprApp.getLastLocation();
        Location currentLocation = new Location("Current Location");
        currentLocation.setLatitude(latLng.latitude);
        currentLocation.setLongitude(latLng.longitude);

        // Second we get a list of all shops within the current data set
        List<Shop> shops = ShoprApp.getShopList();

        // Third we check whether there is a context set at the moment.
        ScenarioContext scenarioContext = ScenarioContext.getInstance();
        DistanceToShop distanceToShop = scenarioContext.getDistanceToShop();
        sOnlyItemsInStock = scenarioContext.isOnlyItemsInStock();

        //If there were shops and a context object for the distance to the shops, then we can proceed
        if (shops != null && distanceToShop != null){

            // Initialize the allowed distance to the shop and check whether this distance is other than 0 (which means to include all objects.
            float allowedDistance = distanceToShop.getDistance();
            if (allowedDistance != 0) {

                // Initialize a new array where we can store whether a store should be accessed or not.
                boolean[] shopAvailable = new boolean[shops.size() + 1];

                // Write into the array whether the shops are within the given distance.
                for (Shop shop : shops) {
                    float distance = currentLocation.distanceTo(shop.getLocationObject());
                    shopAvailable[shop.id()] = distance <= allowedDistance; // if the distance is smaller than the allowed distance, then it will write true, else false
                }

                //Create a new list in which we put all the items that are allowed. Afterwards we return the limited case base.
                List<Item> newCases = new ArrayList<Item>();
                for (Item item : cases) {
                    if (shopAvailable[item.shopId()] && itemInStock(item)){
                        newCases.add(item);
                    }
                }
                return newCases;
            }
        }

        return cases;
    }

    /**
     * Returns whether the item is in stock and whether it should be included/excluded from the calculation
     * of the case base.
     * @param item The item under investigation
     * @return true if the item should be included, false if it is not in stock and only items in stock shall be shown
     */
    private static boolean itemInStock(Item item){
        return !(sOnlyItemsInStock && item.getItemsInStock() == 0);
    }
}
