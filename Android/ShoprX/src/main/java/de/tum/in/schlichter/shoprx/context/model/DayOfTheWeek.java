package de.tum.in.schlichter.shoprx.context.model;

import android.util.Log;

import de.tum.in.schlichter.shoprx.R;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import de.tum.in.schlichter.shoprx.ShoprApp;
import de.tum.in.schlichter.shoprx.algorithm.model.ClothingType;
import de.tum.in.schlichter.shoprx.context.model.interfaces.DistanceMetric;

/**
 * Created by Yannick on 11.02.15.
 *
 * Keeps the context data on the day of the week, meaning whether it is a weekday or a holiday.
 */
public enum DayOfTheWeek implements DistanceMetric {
    WORKDAY(R.string.workday),
    HOLIDAY(R.string.holiday);

    private static Map<ClothingType.Value, Double> sWeights;

    private int mDay;

    private static final String TAG = "DayOfTheWeek";

    static {
        sWeights = new HashMap<ClothingType.Value, Double>();
        sWeights.put(ClothingType.Value.BLOUSE, 1.00);
        sWeights.put(ClothingType.Value.CARDIGAN, 1.00);
        sWeights.put(ClothingType.Value.COAT, 0.86);
        sWeights.put(ClothingType.Value.DRESS, 1.0);
        sWeights.put(ClothingType.Value.JACKET, 0.89);
        sWeights.put(ClothingType.Value.JEANS, 0.87);
        sWeights.put(ClothingType.Value.SHIRT, 1.0);
        sWeights.put(ClothingType.Value.SKIRT, 1.0);
        sWeights.put(ClothingType.Value.SWIMWEAR, 0.9);
        sWeights.put(ClothingType.Value.TOP, 0.85);
        sWeights.put(ClothingType.Value.TROUSERS, 0.89);

        sWeights.put(ClothingType.Value.UNKNOWN, 1.0);

        sWeights.put(ClothingType.Value.CHINO, sWeights.get(ClothingType.Value.TROUSERS));
        sWeights.put(ClothingType.Value.HOODIE, sWeights.get(ClothingType.Value.CARDIGAN) * 0.8 + sWeights.get(ClothingType.Value.JACKET) * 0.2); // 80% Cardigan 20% Jacket
        sWeights.put(ClothingType.Value.SHORTS, sWeights.get(ClothingType.Value.SWIMWEAR) * 0.5 + sWeights.get(ClothingType.Value.TROUSERS) * 0.5); //Half swimwear - half trousers
        sWeights.put(ClothingType.Value.SWEATSHIRT, sWeights.get(ClothingType.Value.CARDIGAN)); // Cardigan
        sWeights.put(ClothingType.Value.TUNIC, 0.5 * sWeights.get(ClothingType.Value.DRESS) + 0.5 * sWeights.get(ClothingType.Value.SHIRT)); // Half dress - half shirt
        sWeights.put(ClothingType.Value.T_SHIRT, sWeights.get(ClothingType.Value.SHIRT));
        sWeights.put(ClothingType.Value.JUMPER, 0.8 * sWeights.get(ClothingType.Value.SWEATSHIRT) + 0.2 * sWeights.get(ClothingType.Value.SHIRT)); // 80% Sweatshirt 20 % shirt

    }

    DayOfTheWeek(int day) {
         this.mDay = day;
    }

    @Override
    public String toString(){
        return ShoprApp.getContext().getString(mDay);
    }

    public static DayOfTheWeek getDay(Calendar cal) {
        if (Calendar.SATURDAY == cal.get(Calendar.DAY_OF_WEEK) || Calendar.SUNDAY == cal.get(Calendar.DAY_OF_WEEK)){
            return HOLIDAY;
        } else {
            return WORKDAY;
        }
    }

    public static DayOfTheWeek getDay(String day) {
        if (DayOfTheWeek.WORKDAY.toString().equals(day)){
            return WORKDAY;
        } else if (DayOfTheWeek.HOLIDAY.toString().equals(day)){
            return HOLIDAY;
        }

        return null;
    }

    public int getDay(){
        return mDay;
    }

    public static DayOfTheWeek getDayOfTheWeek(int match){
        for (DayOfTheWeek value: values()){
            if (value.mDay == match){
                return value;
            }
        }
        return null;
    }


    @Override
    public boolean isMetricWithEuclideanDistance() {
        return true;
    }

    @SuppressWarnings("SuspiciousMethodCalls")
    @Override
    public double getWeight(ClothingType clothingType) {
        if (sWeights.containsKey(clothingType.currentValue())){
            return sWeights.get(clothingType.currentValue());
        }

        Log.d(TAG, "Did not find " + clothingType.currentValue().descriptor() + " weight.");

        return 1;
    }


    @Override
    public double distanceToContext(ScenarioContext scenarioContext) throws UnsupportedOperationException {
        throw new UnsupportedOperationException("Is Euclidean Metric");
    }

    @Override
    public int numberOfItems() {
        return values().length;
    }

    @Override
    public int currentOrdinal() {
        return ordinal();
    }


}
