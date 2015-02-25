package com.uwetrottmann.shopr.stereotype.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.uwetrottmann.shopr.R;
import com.uwetrottmann.shopr.algorithm.model.Sex;
import com.uwetrottmann.shopr.stereotype.stereotypes.AbstractStereotype;
import com.uwetrottmann.shopr.stereotype.stereotypes.Stereotype;
import com.uwetrottmann.shopr.stereotype.user.User;
import com.uwetrottmann.shopr.utils.Utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Yannick on 29.01.15.
 *
 * Adapter class to display the styles on a page.
 */
public class StereotypeStylesAdapter extends BaseAdapter {

    private static final int fixed_width = 250;
    private static final int fixed_height = 590;

    // references to our images
    private Map<Stereotype, Integer> stereotypeImagesMale;
    private Map<Stereotype, Integer> stereotypeImagesFemale;
    private Context mContext;
    List<AbstractStereotype> topThreeStereotypes;

    public StereotypeStylesAdapter(Context c, List<AbstractStereotype> topThreeStereotypes) {
        mContext = c;
        this.topThreeStereotypes = topThreeStereotypes;

        // initialize male stereotype images
        stereotypeImagesMale = new HashMap<Stereotype, Integer>();
        stereotypeImagesMale.put(Stereotype.ATHLETE, R.drawable.athlete_male);
        stereotypeImagesMale.put(Stereotype.CLASSY, R.drawable.classy_male);
        stereotypeImagesMale.put(Stereotype.EMO, R.drawable.emo_male);
        stereotypeImagesMale.put(Stereotype.GOTHIC, R.drawable.gothic_male);
        stereotypeImagesMale.put(Stereotype.INDIE, R.drawable.indie_male);
        stereotypeImagesMale.put(Stereotype.MAINSTREAM, R.drawable.mainstream_male);
        stereotypeImagesMale.put(Stereotype.PREPPY, R.drawable.preppy_male);
        stereotypeImagesMale.put(Stereotype.SKATER, R.drawable.skater_male);

        // initialize female stereotype images

        stereotypeImagesFemale = new HashMap<Stereotype, Integer>();
        stereotypeImagesFemale.put(Stereotype.ATHLETE, R.drawable.athlete_female);
        stereotypeImagesFemale.put(Stereotype.CLASSY, R.drawable.classy_female);
        stereotypeImagesFemale.put(Stereotype.EMO, R.drawable.emo_female);
        stereotypeImagesFemale.put(Stereotype.GIRLY, R.drawable.girly_female);
        stereotypeImagesFemale.put(Stereotype.GOTHIC, R.drawable.gothic_female);
        stereotypeImagesFemale.put(Stereotype.INDIE, R.drawable.indie_female);
        stereotypeImagesFemale.put(Stereotype.MAINSTREAM, R.drawable.mainstream_female);
        stereotypeImagesFemale.put(Stereotype.PREPPY, R.drawable.preppy_female);
        stereotypeImagesFemale.put(Stereotype.SKATER, R.drawable.skater_female);
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
		/*
		 * each grid contains an image and a check box below. Relative Layout
		 * achieved according to
		 * http://stackoverflow.com/questions/2305395/laying
		 * -out-views-in-relativelayout-programmatically
		 */

        Stereotype activeStereotype = topThreeStereotypes.get(position)
                .getStereotype();
        Integer id = null;

        if (User.getUser().getSex().equals(Sex.Value.MALE)) {
            id = stereotypeImagesMale.get(activeStereotype);
        } else if (User.getUser().getSex().equals(Sex.Value.FEMALE)) {
            id = stereotypeImagesFemale.get(activeStereotype);
        }

        if (id == null) {
            Log.e(StereotypeStylesAdapter.class.toString(),
                    "User has attribute sex set to BOTH. Can't determine picture to show.");
            throw new RuntimeException(
                    "User has attribute sex set to BOTH. Can't determine picture to show.");
        }

        DisplayMetrics metrics = new DisplayMetrics();
        ((Activity) mContext).getWindowManager().getDefaultDisplay().getMetrics(metrics);
        int height;
        int width = metrics.widthPixels;

        if (mContext.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) { //Portrait
            width = width / 3 - 5;

            double factor = 1.0d;
            if (width < fixed_width) {
                factor = 1.0d * width / fixed_width;
            }

            height = (int) (fixed_height * factor);
        } else {
            width = fixed_width;
            height = fixed_height;
        }

        //Log.d("height", ""+ height);
        //Log.d("width", ""+ width);

        ImageView imageView;
        if (convertView == null) { // if it's not recycled, initialize some
            // attributes
            imageView = new ImageView(mContext);
            imageView.setLayoutParams(new GridView.LayoutParams(width + 10, height + 10));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setPadding(10, 10, 10, 10);
        } else {
            imageView = (ImageView) convertView;
        }

        imageView.setImageBitmap(Utils.decodeSampledBitmapFromResource(parent.getResources(), id, width, height));

        return imageView;
    }
}
