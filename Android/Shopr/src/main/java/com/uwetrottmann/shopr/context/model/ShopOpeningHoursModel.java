package com.uwetrottmann.shopr.context.model;

import com.uwetrottmann.shopr.R;
import com.uwetrottmann.shopr.ShoprApp;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Yannick on 11.02.15.
 *
 * This enum stores the shop opening hours selection from the user interface.
 */
public enum ShopOpeningHoursModel {
    NOW(R.string.now, 0),
    NOW_PLUS_30(R.string.nowPlus30, 30),
    NOW_PLUS_60(R.string.nowPlus60, 60),
    NOW_PLUS_90(R.string.nowPlus90, 90),
    ANYTIME(R.string.anyShop, 2880);

    int mStringSelctor;
    int mOpeningHours;

    private static Map<String, ShopOpeningHoursModel> sOpeningHours = new HashMap<String, ShopOpeningHoursModel>();

    ShopOpeningHoursModel(int stringSelector, int openingHours) {
        mStringSelctor = stringSelector;
        mOpeningHours = openingHours;
    }

    public static ShopOpeningHoursModel getShopOpeningHoursModel(String description){
        if (sOpeningHours.size() == 0){
            for (ShopOpeningHoursModel open : ShopOpeningHoursModel.values()){
                sOpeningHours.put(ShoprApp.getContext().getString(open.mStringSelctor), open);
            }
        }

        return sOpeningHours.get(description);
    }

    @Override
    public String toString(){
        return ShoprApp.getContext().getString(mStringSelctor);
    }

}
