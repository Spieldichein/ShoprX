
package com.uwetrottmann.shopr.algorithm;

import com.uwetrottmann.shopr.algorithm.model.Attributes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Feedback {

    private boolean mIsPositiveFeedback;

    private List<Attributes.Attribute> mAttributes = new ArrayList<Attributes.Attribute>();

    public boolean isPositiveFeedback() {
        return mIsPositiveFeedback;
    }

    public Feedback isPositiveFeedback(boolean isPositiveFeedback) {
        mIsPositiveFeedback = isPositiveFeedback;
        return this;
    }

    public List<Attributes.Attribute> attributes() {
        return mAttributes;
    }

    public Feedback addAttributes(Attributes.Attribute... attributes) {
        mAttributes.addAll(Arrays.asList(attributes));
        return this;
    }

}
