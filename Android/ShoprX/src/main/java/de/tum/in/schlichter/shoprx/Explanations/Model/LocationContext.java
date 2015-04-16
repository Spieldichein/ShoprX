package de.tum.in.schlichter.shoprx.Explanations.Model;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import de.tum.in.schlichter.shoprx.ShoprApp;
import de.tum.in.schlichter.shoprx.algorithm.model.Item;
import de.tum.in.schlichter.shoprx.model.Shop;

/**
 * Created by Nicksteal on 24.03.2015.
 */
public class LocationContext extends Context {
    private double userLatitude;
    private double userLongitude;

    static double SCORE_MAX = 1.0;
    static double SCORE_MIN = 0.0;

    public LocationContext(double userLatitude, double userLongitude) {
        this.userLatitude = userLatitude;
        this.userLongitude = userLongitude;
    }

    @Override
    public double explanationScore(Item item) {
        double distanceInMeters = distanceToUserInMeters(item);

        if (distanceInMeters <= 10)
            return 0.99;
        else if (distanceInMeters <= 25)
            return 0.97;
        else if (distanceInMeters <= 50)
            return 0.95;
        else if (distanceInMeters <= 100)
            return 0.90;
        else if (distanceInMeters <= 200)
            return 0.85;
        else if (distanceInMeters <= 400)
            return 0.75;
        else if (distanceInMeters <= 600)
            return 0.65;
        else if (distanceInMeters <= 800)
            return 0.60;
        else if (distanceInMeters <= 1000)
            return 0.55;
        else if (distanceInMeters > 1000 && distanceInMeters <= 2000)
            return 0.40;
        else
            return 0;
    }

    public double distanceToUserInMeters(Item item) {
        List<Shop> shops = ShoprApp.getShopList();
        Shop shop=null;
        if (shops ==null){
            return 100.0;
        }
        for (Shop aShop :shops){
            if (aShop.id() == item.shopId()){
                shop = aShop;
                break;
            }
        }
        if (shop == null){
            return 100.0;
        }
        double latitude = shop.getmLocation().latitude;
        double longitude =  shop.getmLocation().longitude;

        return calculateDistanceInMeters(userLatitude, userLongitude, latitude,
                longitude);
    }

    private double calculateDistanceInMeters(double fromLong, double fromLat,
                                             double toLong, double toLat) {
        double d2r = Math.PI / 180;
        double dLong = (toLong - fromLong) * d2r;
        double dLat = (toLat - fromLat) * d2r;
        double a = Math.pow(Math.sin(dLat / 2.0), 2) + Math.cos(fromLat * d2r)
                * Math.cos(toLat * d2r) * Math.pow(Math.sin(dLong / 2.0), 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double d = 6367000 * c;
        return Math.round(d);
    }

    @Override
    public double informationScore(Item item, List<Item> recommendations) {
        double R = calculateRange(recommendations);
        double I = calculateInformation(item, recommendations);
       // Log.d("Distance!!","Range: "+R+" and Info:"+I) ;

        return (R + I) / 2;
    }

    private double calculateInformation(Item item, List<Item> recommendations) {
        double n = recommendations.size();
        double h = calculateH(item, recommendations);
       // Log.d("Distance!!","h: "+h+" and n:"+n) ;
        return (n - h) / (n - 1);
    }

    private int calculateH(Item item, List<Item> recommendations) {
        List<Item> itemsWithSimilarES = filterBySimilarExplanationScore(recommendations, item);
        int itemIndex = itemsWithSimilarES.indexOf(item);

       /* if (itemIndex > -1 && itemIndex < 2) {
            return itemIndex + 1;
        } else {*/
            return itemsWithSimilarES.size();
       // }
    }

    private List<Item> filterBySimilarExplanationScore(List<Item> recommendations, Item refItem) {
        List<Item> itemsWithSimilarES = new ArrayList<Item>();
        double expScore = explanationScore(refItem);
        for(Item item: recommendations) {
            double scoreDifference = explanationScore(item) -expScore;
            if(Math.abs(scoreDifference) <= 0.05)
                itemsWithSimilarES.add(item);
        }
        return itemsWithSimilarES;
    }

    protected double calculateRange(List<Item> recommendations) {
        double max = SCORE_MIN;
        double min = SCORE_MAX;

        for (Item item : recommendations) {
            double ES = explanationScore(item);
            max = Math.max(max, ES);
            min = Math.min(min, ES);
        }

        return max - min;
    }

}