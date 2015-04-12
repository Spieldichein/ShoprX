package de.tum.in.schlichter.shoprx.ui.explanation.Prefrences;

import de.tum.in.schlichter.shoprx.algorithm.model.Attributes;
import de.tum.in.schlichter.shoprx.algorithm.model.Color;

/**
 * Created by Nicksteal on 10.04.2015.
 */
public class ColorPreferenceFragment extends PreferenceGridFragment {

    public static final String TAG = ColorPreferenceFragment.class
            .getSimpleName();

    @Override
    protected Attributes.Attribute attribute() {
        return new Color();
    }


}
