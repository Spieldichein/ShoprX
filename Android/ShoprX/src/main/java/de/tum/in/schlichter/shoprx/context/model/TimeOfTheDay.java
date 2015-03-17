package de.tum.in.schlichter.shoprx.context.model;

import de.tum.in.schlichter.shoprx.R;

import java.util.Calendar;

import de.tum.in.schlichter.shoprx.ShoprApp;

/**
 * Created by Yannick on 11.02.15.
 *
 * Keeps the context data on the day of the week, meaning whether it is a weekday or a holiday.
 */
public enum TimeOfTheDay implements DistanceMetric{
    NIGHT(R.string.night),
    MORNING(R.string.morning),
    MIDDAY(R.string.midday),
    AFTERNOON(R.string.afternoon),
    EVENING(R.string.evening);

    private static final double WEIGHT = 0.71;

    private int mTime;

    private static int END_OF_NIGHT = 6;
    private static int END_OF_MORNING = 11;
    private static int END_OF_MIDDAY = 14;
    private static int END_OF_AFTERNOON = 18;
    private static int END_OF_EVENING = 22;


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

    @Override
    public double getWeight() {
        return WEIGHT;
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
