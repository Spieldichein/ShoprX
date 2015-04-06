package de.tum.in.schlichter.shoprx.ui.explanation.Prefrences;

import de.tum.in.schlichter.shoprx.algorithm.model.Attributes.Attribute;
import de.tum.in.schlichter.shoprx.algorithm.model.ClothingType;

/**
 * Created by Nicksteal on 06.04.2015.
 */
public class ClothingTypePreferenceFragment extends PreferenceGridFragment {

    public static final String TAG = ClothingTypePreferenceFragment.class
            .getSimpleName();

    @Override
    protected Attribute attribute() {
        return new ClothingType();
    }


}
