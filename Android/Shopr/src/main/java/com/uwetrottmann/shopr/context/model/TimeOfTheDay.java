package com.uwetrottmann.shopr.context.model;

import com.uwetrottmann.shopr.R;
import com.uwetrottmann.shopr.ShoprApp;

import java.util.Calendar;

/**
 * Created by Yannick on 11.02.15.
 *
 * Keeps the context data on the day of the week, meaning whether it is a weekday or a holiday.
 */
public enum TimeOfTheDay {
    NIGHT(R.string.night),
    MORNING(R.string.morning),
    MIDDAY(R.string.midday),
    AFTERNOON(R.string.afternoon),
    EVENING(R.string.evening);

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

    public int getTime(){
        return mTime;
    }
}
