package com.uwetrottmann.shopr.model;

import com.uwetrottmann.shopr.context.model.ShopOpeningHoursModel;

import java.util.Calendar;

/**
 * Created by Yannick on 13.02.15.
 *
 * This class shall store a shop's opening hours.
 */
public class OpeningHours {

    private int mShopOpeningTime;
    private int mShopClosingTime;

    public OpeningHours(int openingTime, int closingTime){
        this.mShopOpeningTime = openingTime;
        this.mShopClosingTime = closingTime;
    }

    public boolean isOpenAtTime(ShopOpeningHoursModel contextData){
        if (contextData.equals(ShopOpeningHoursModel.ANYTIME)){
            return true;
        }

        if (mShopOpeningTime == mShopClosingTime) {
            return false;
        }

        // Get minutes in the future, that the user wants the shop to be open.
        Calendar calendar = Calendar.getInstance();

        //Current minutes + minutes to be added
        int minutes = calendar.get(Calendar.MINUTE) + contextData.getOpeningHours();

        // current hour + overflow of minute addition
        int hours = (int) (calendar.get(Calendar.HOUR_OF_DAY) + Math.floor(minutes / 60));
        minutes = minutes % 60;

        int compare = hours * 100 + minutes;

        return (compare >= mShopOpeningTime && compare <= mShopClosingTime);
    }
}
