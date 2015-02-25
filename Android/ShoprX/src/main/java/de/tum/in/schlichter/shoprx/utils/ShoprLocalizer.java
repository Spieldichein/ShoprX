
package de.tum.in.schlichter.shoprx.utils;

import android.content.Context;

import de.tum.in.schlichter.shoprx.R;

import de.tum.in.schlichter.shoprx.algorithm.LocalizationModule;

public class ShoprLocalizer implements LocalizationModule {

    private Context mContext;

    public ShoprLocalizer(Context context) {
        mContext = context;
    }

    @Override
    public String getLocalizedValueDescriptor(String value) {
        return ValueConverter.getLocalizedStringForValue(mContext, value);
    }

    @Override
    public String getOnlyString() {
        return mContext.getString(R.string.only);
    }

    @Override
    public String getAvoidString() {
        return mContext.getString(R.string.avoid);
    }

    @Override
    public String getPreferablyString() {
        return mContext.getString(R.string.preferably);
    }

}
