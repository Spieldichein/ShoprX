package de.tum.in.schlichter.shoprx.Explanations.Algorithm;

import android.app.Fragment;
import android.content.Intent;
import android.graphics.Typeface;
import android.preference.PreferenceActivity;
import android.text.Html;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.style.ClickableSpan;
import android.view.View;

import java.util.Locale;

import de.tum.in.schlichter.shoprx.algorithm.model.Attributes;
import de.tum.in.schlichter.shoprx.algorithm.model.ClothingType;
import de.tum.in.schlichter.shoprx.algorithm.model.Color;
import de.tum.in.schlichter.shoprx.algorithm.model.Price;
import de.tum.in.schlichter.shoprx.algorithm.model.Sex;

/**
 * Created by Nicksteal on 29.03.2015.
 */
public class ShoprTextFormatter implements TextFormatter {
    private Fragment fragment;

    public ShoprTextFormatter(Fragment fragment) {
        this.fragment = fragment;
    }

    @Override
    public CharSequence fromHtml(String html) {
        return Html.fromHtml(html);
    }

    @Override
    public CharSequence renderSimpleClickable(AttributeText attributeText) {
        SpannableString ss = new SpannableString(attributeText.originalText());

        for (String toRender : attributeText.replacableTexts()) {
            setSpanOnLink(ss, toRender, new PreferenceClickableSpan(attributeText.attribute(), false));
        }
        return ss;
    }

    @Override
    public CharSequence renderClickable(AttributeText attributeText) {
        SpannableString ss = new SpannableString(attributeText.originalText());

        for (String toRender : attributeText.replacableTexts()) {
            setSpanOnLink(ss, toRender, new PreferenceClickableSpan(
                    attributeText.attribute(), true));
        }
        return ss;
    }

    private void setSpanOnLink(SpannableString ss, String link, ClickableSpan cs) {
        String text = ss.toString();
        // by lowercasing avoid having capital letters causing no match
        int start = text.toLowerCase(Locale.ENGLISH).indexOf(
                link.toLowerCase(Locale.ENGLISH));
        int end = start + link.length();
        if (start == -1) {
            start = 0;
            end = 0;
        }
        ss.setSpan(cs, start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
    }

    class PreferenceClickableSpan extends ShoprClickableSpan {
        private Attributes.Attribute attribute;
        private boolean hasExtra;

        public PreferenceClickableSpan(Attributes.Attribute attribute, boolean hasExtra) {
            this.attribute = attribute;
            this.hasExtra = hasExtra;
        }

        @Override
        public void onClick(View widget) {
            Class<?> cls = null;
            if (attribute instanceof Color) {
                cls = ColorPreferenceActivity.class;
            } else if (attribute instanceof ClothingType) {
                cls = ClothingTypePreferenceActivity.class;
            } else if (attribute instanceof Price) {
                cls = PricePreferenceActivity.class;
            } else if (attribute instanceof Sex) {
                cls = GenderPreferenceActivity.class;
            }
            Intent intent = new Intent(fragment.getActivity(), cls);
            if (hasExtra) {
                intent.putExtra(PreferenceActivity.EXTRAS_ATTRIBUTE_VALUE,
                        attribute.currentValue().explanatoryDescriptor()
                                .toLowerCase(Locale.ENGLISH));
            }
            fragment.startActivityForResult(intent,
                    RecommendationsFragment.REQUEST_CODE);
        }

    }

    abstract class ShoprClickableSpan extends ClickableSpan {

        @Override
        public void updateDrawState(TextPaint ds) {
            ds.setColor(android.graphics.Color.BLUE);
            ds.setTypeface(Typeface.DEFAULT_BOLD);
            ds.setUnderlineText(false);
        }
    }

    @Override
    public CharSequence renderClickable(CharSequence originalText,
                                        Attributes.Attribute attribute, String toRender) {
        SpannableString ss = new SpannableString(originalText);
        setSpanOnLink(ss, toRender, new PreferenceClickableSpan(attribute, true));
        return ss;
    }

    @Override
    public CharSequence concat(CharSequence... text) {
        return TextUtils.concat(text);
    }

}