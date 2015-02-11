package com.uwetrottmann.shopr.context.model;

import com.uwetrottmann.shopr.R;
import com.uwetrottmann.shopr.ShoprApp;

import java.util.Calendar;

/**
 * Created by Yannick on 11.02.15.
 *
 * Keeps the context data on the day of the week, meaning whether it is a weekday or a holiday.
 */
public enum DayOfTheWeek {
    WORKDAY(R.string.workday),
    HOLIDAY(R.string.holiday);

    private int day;

    DayOfTheWeek(int day) {
         this.day = day;
    }

    @Override
    public String toString(){
        return ShoprApp.getContext().getString(day);
    }

    public static DayOfTheWeek getDay(Calendar cal) {
        if (Calendar.SATURDAY == cal.get(Calendar.DAY_OF_WEEK) || Calendar.SUNDAY == cal.get(Calendar.DAY_OF_WEEK)){
            return HOLIDAY;
        } else {
            return WORKDAY;
        }
    }
}
