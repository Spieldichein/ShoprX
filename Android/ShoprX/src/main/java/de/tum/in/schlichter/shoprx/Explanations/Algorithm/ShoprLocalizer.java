package de.tum.in.schlichter.shoprx.Explanations.Algorithm;

import android.content.Context;
import android.text.Html;

import de.tum.in.schlichter.shoprx.R;

/**
 * Created by Nicksteal on 29.03.2015.
 */
public class ShoprLocalizer implements ExplanationLocalizer {

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

    @Override
    public String getIndifferentToAnyTemplate() {
        return mContext
                .getString(R.string.explanation_on_preference_indifferent);
    }

    @Override
    public String getOnlySomeTemplate() {
        return mContext
                .getString(R.string.explanation_on_preference_only_interested_in_some);
    }

    @Override
    public String getAvoidsSomeTemplate() {
        return mContext
                .getString(R.string.explanation_on_preference_avoiding_some);
    }

    @Override
    public String getPrefersSomeTemplate() {
        return mContext
                .getString(R.string.explanation_on_preference_prefer_some);
    }

    @Override
    public String getCommaString() {
        return mContext.getString(R.string.conjunction_comma);
    }

    @Override
    public String getAndString() {
        return mContext.getString(R.string.conjunction_and);
    }

    @Override
    public String[] getStrongArgumentTemplates() {
        return new String[] {
                mContext.getString(R.string.explanation_template_on_dimension_strong_1),
                mContext.getString(R.string.explanation_template_on_dimension_strong_2) };
    }

    @Override
    public String[] getWeakArgumentTemplates() {
        return new String[] {
                mContext.getString(R.string.explanation_template_on_dimension_weak_1),
                mContext.getString(R.string.explanation_template_on_dimension_weak_2) };
    }

    @Override
    public String[] getSerendipitousityTemplates() {
        return new String[] {
                mContext.getString(R.string.explanation_template_serendipidity_1),
                mContext.getString(R.string.explanation_template_serendipidity_2),
                mContext.getString(R.string.explanation_template_serendipidity_3) };
    }

    @Override
    public String[] getContextArgumentTemplates() {
        return new String[] {
                mContext.getString(R.string.explanation_template_context_location),
                mContext.getString(R.string.explanation_template_context_location_average) };
    }

    @Override
    public String getNegativeArgumentTemplate() {
        return mContext.getString(R.string.explanation_template_negative);
    }

    @Override
    public String getSupportingArgumentTemplate() {
        return mContext
                .getString(R.string.explanation_template_on_dimension_weak_supporting_1);
    }

    @Override
    public String[] getSupportingArgumentTemplateSolo() {
        return new String[]{
                mContext.getString(R.string.explanation_template_on_dimension_weak_supporting_solo),
                mContext.getString(R.string.explanation_template_on_dimension_weak_supporting_solo2)
        };

    }

    @Override
    public String getGoodAverageTemplate() {
        return mContext.getString(R.string.explanation_template_average_item);
    }

    @Override
    public String getLastCritiqueTemplate() {
        return mContext.getString(R.string.explanation_last_critique);
    }

}
