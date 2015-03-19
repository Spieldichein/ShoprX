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
public enum TimeOfTheDay implements DistanceMetric {
    NIGHT(R.string.night),
    MORNING(R.string.morning),
    MIDDAY(R.string.midday),
    AFTERNOON(R.string.afternoon),
    EVENING(R.string.evening);

    private static Map<ClothingType.Value, Double> sWeights;

    private static final String TAG = "TimeOfTheDay";

    private int mTime;

    private static int END_OF_NIGHT = 6;
    private static int END_OF_MORNING = 11;
    private static int END_OF_MIDDAY = 14;
    private static int END_OF_AFTERNOON = 18;
    private static int END_OF_EVENING = 22;

    static {
        sWeights = new HashMap<ClothingType.Value, Double>();
        sWeights.put(ClothingType.Value.BLOUSE, 1.00);
        sWeights.put(ClothingType.Value.CARDIGAN, 0.79);
        sWeights.put(ClothingType.Value.COAT, 0.76);
        sWeights.put(ClothingType.Value.DRESS, 1.0);
        sWeights.put(ClothingType.Value.JACKET, 0.79);
        sWeights.put(ClothingType.Value.JEANS, 0.86);
        sWeights.put(ClothingType.Value.SHIRT, 1.0);
        sWeights.put(ClothingType.Value.SKIRT, 0.77);
        sWeights.put(ClothingType.Value.SWIMWEAR, 0.75);
        sWeights.put(ClothingType.Value.TOP, 0.8);
        sWeights.put(ClothingType.Value.TROUSERS, 0.74);

        sWeights.put(ClothingType.Value.UNKNOWN, 1.0);

        sWeights.put(ClothingType.Value.CHINO, sWeights.get(ClothingType.Value.TROUSERS));
        sWeights.put(ClothingType.Value.HOODIE, sWeights.get(ClothingType.Value.CARDIGAN) * 0.8 + sWeights.get(ClothingType.Value.JACKET) * 0.2); // 80% Cardigan 20% Jacket
        sWeights.put(ClothingType.Value.SHORTS, sWeights.get(ClothingType.Value.SWIMWEAR) * 0.5 + sWeights.get(ClothingType.Value.TROUSERS) * 0.5); //Half swimwear - half trousers
        sWeights.put(ClothingType.Value.SWEATSHIRT, sWeights.get(ClothingType.Value.CARDIGAN)); // Cardigan
        sWeights.put(ClothingType.Value.T_SHIRT, sWeights.get(ClothingType.Value.SHIRT));
        sWeights.put(ClothingType.Value.TUNIC, 0.5 * sWeights.get(ClothingType.Value.DRESS) + 0.5 * sWeights.get(ClothingType.Value.SHIRT)); // Half dress - half shirt
        sWeights.put(ClothingType.Value.JUMPER, 0.8 * sWeights.get(ClothingType.Value.SWEATSHIRT) + 0.2 * sWeights.get(ClothingType.Value.SHIRT)); // 80% Sweatshirt 20 % shirt

    }


    TimeOfTheDay(int time) {
         this.mTime = time;
    }

    @Override
    public String toString(){
        return ShoprApp.getContext().getString(mTime);
    }

    public static TimeOfTheDay getTimeOfTheDay(int match){
        for (TimeOfTheDay value: values()){
            if (value.mTime == match){
                return value;
            }
        }
        return null;
    }

    public static TimeOfTheDay getMatchingTimeOfTheDay(Calendar now){
        //Set the two calendars to exactly the same time of the day.
        Calendar compareTime = Calendar.getInstance();
        compareTime.set(Calendar.YEAR, now.get(Calendar.YEAR));
        compareTime.set(Calendar.MONTH, now.get(Calendar.MONTH));
        compareTime.set(Calendar.DAY_OF_MONTH, now.get(Calendar.DAY_OF_MONTH));
        compareTime.set(Calendar.HOUR_OF_DAY, END_OF_NIGHT);
        compareTime.set(Calendar.MINUTE, 0);
        compareTime.set(Calendar.SECOND, 0);
        compareTime.set(Calendar.MILLISECOND, 0);

        // Is it night?
        if (now.before(compareTime)){
            return NIGHT;
        }

        // Then it might be Morning?
        compareTime.set(Calendar.HOUR_OF_DAY, END_OF_MORNING);
        if (now.before(compareTime)){
            return MORNING;
        }

        // Then it is midday?
        compareTime.set(Calendar.HOUR_OF_DAY, END_OF_MIDDAY);
        if (now.before(compareTime)){
            return MIDDAY;
        }

        //Then it is afternoon?
        compareTime.set(Calendar.HOUR_OF_DAY, END_OF_AFTERNOON);
        if (now.before(compareTime)){
            return AFTERNOON;
        }

        // Then it is evening?
        compareTime.set(Calendar.HOUR_OF_DAY, END_OF_EVENING);
        if (now.before(compareTime)){
            return EVENING;
        }

        //In this case it is really late!
        return NIGHT;
    }

    public static TimeOfTheDay getTimeOfTheDay(String time){
        if (TimeOfTheDay.MORNING.toString().equals(time)){
            return TimeOfTheDay.MORNING;
        } else if (TimeOfTheDay.MIDDAY.toString().equals(time)){
            return TimeOfTheDay.MIDDAY;
        } else if (TimeOfTheDay.AFTERNOON.toString().equals(time)){
            return TimeOfTheDay.AFTERNOON;
        } else if (TimeOfTheDay.EVENING.toString().equals(time)){
            return TimeOfTheDay.EVENING;
        } else if (TimeOfTheDay.NIGHT.toString().equals(time)){
            return TimeOfTheDay.NIGHT;
        }

        return null;
    }

    public int getTime(){
        return mTime;
    }

    @Override
    public boolean isMetricWithEuclideanDistance() {
        return false; // In fact it is, but we have a cycle here
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
        TimeOfTheDay time = scenarioContext.getTimeOfTheDay();
        int distance = time.currentOrdinal() - this.currentOrdinal();

        //This is the special case!
        if (this.equals(TimeOfTheDay.NIGHT) || time.equals(TimeOfTheDay.NIGHT)){
            if (this.equals(TimeOfTheDay.AFTERNOON) || time.equals(TimeOfTheDay.AFTERNOON)){
                distance = 1;
            }
        }

        //We have to take 2 off here, because the maximum range is not number of items -1, but in fact -2 due to the cyclic relationship
        return Math.sqrt( Math.pow( distance / (numberOfItems() - 2), 2) ); // square it and take the root
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
