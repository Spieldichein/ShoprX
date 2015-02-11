package com.uwetrottmann.shopr.context.model;

import com.uwetrottmann.shopr.R;
import com.uwetrottmann.shopr.ShoprApp;

/**
 * Created by Yannick on 11.02.15.
 *
 * This class stores the current temperature within the given context scenario.
 */
public enum Temperature {
    LESS_THAN_0(R.string.below0Degrees),
    BETWEEN_0_AND_5(R.string.between0And5Degrees),
    BETWEEN_5_AND_10(R.string.between5And10Degrees),
    BETWEEN_10_AND_15(R.string.between10And15Degrees),
    BETWEEN_15_AND_20(R.string.between15And20Degrees),
    BETWEEN_20_AND_25(R.string.between20And25Degrees),
    BETWEEN_25_AND_30(R.string.between25And30Degrees),
    ABOVE_30(R.string.above30Degrees);

    private int mDegrees;

    Temperature(int degrees) {
         mDegrees = degrees;
    }

    public String descriptor(){
        return ShoprApp.getContext().getString(mDegrees);
    }

}
