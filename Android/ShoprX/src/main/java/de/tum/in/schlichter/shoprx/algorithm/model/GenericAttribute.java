
package de.tum.in.schlichter.shoprx.algorithm.model;

import java.util.Arrays;
import java.util.Set;

import de.tum.in.schlichter.shoprx.algorithm.Query;
import de.tum.in.schlichter.shoprx.algorithm.model.Attributes.AttributeValue;
import de.tum.in.schlichter.shoprx.algorithm.model.Attributes.Attribute;



public abstract class GenericAttribute implements Attribute {

    private AttributeValue currentValue;
    public static double WEIGHT_UPPER_BOUND = 1.0;
    public static double WEIGHT_LOWER_BOUND = 0.0;
    double[] mValueWeights;

    public AttributeValue currentValue() {
        return currentValue;
    }

    public GenericAttribute currentValue(AttributeValue currentValue) {
        this.currentValue = currentValue;
        return this;
    }

    public double[] getValueWeights() {
        return mValueWeights;
    }

    public abstract AttributeValue[] getValueSymbols();

    @Override
    public String getValueWeightsString() {
        StringBuilder builder = new StringBuilder();
        AttributeValue[] values = getValueSymbols();

        builder.append("[");
        for (int i = 0; i < mValueWeights.length; i++) {
            if (mValueWeights[i] == 1) {
                return "[" + values[i].descriptor() + ":" + mValueWeights[i] + "]";
            }
            builder.append(values[i].descriptor()).append(":").append(mValueWeights[i]).append(" ");
        }
        builder.append("]");

        return builder.toString();
    }

    /**
     * Returns string for the given attribute having weight 1.0, e.g. only items
     * that have this value are recommended.
     */
    public String getOnlyString(Attributes.AttributeValue value) {
        return "only " + value.descriptor();
    }

    /**
     * Returns the verb to use in conjunction with a list of attribute values
     * that are avoided when recommending items (unless there are no other
     * options).
     */
    public String getAvoidString() {
        return "avoid %s";
    }

    /**
     * Returns string for the given attribute having the highest weight of all,
     * e.g. recommended items are likely to have this value.
     */
    public String getPreferablyString(Attributes.AttributeValue value) {
        return "preferably " + value.descriptor();
    }

    @Override
    public String getReasonString() {
        StringBuilder reason = new StringBuilder();
        Attributes.AttributeValue[] values = getValueSymbols();

        int maxIndex = 0; // assuming there is always a maximum
        for (int i = 0; i < mValueWeights.length; i++) {
            if (mValueWeights[i] == 1) {
                // e.g. "only Red"
                return getOnlyString(values[i]);
            }
            if (mValueWeights[i] == 0) {
                // e.g. ", avoid Red/Blue/Green"
                if (reason.length() != 0 && i < mValueWeights.length) {
                    reason.append("/");
                }

                reason.append(values[i].descriptor());
            } else if (mValueWeights[i] > mValueWeights[maxIndex]) {
                maxIndex = i;
            }
        }
        if (reason.length() != 0) {
            // add prefix to get e.g. "avoid Red/Blue/Green"
            reason = new StringBuilder(String.format(getAvoidString(), reason.toString()));
        }

        // check for global maximum
        boolean hasGlobalMaximum = true;
        for (int i = 0; i < mValueWeights.length; i++) {
            if (i == maxIndex) {
                continue;
            }
            if (mValueWeights[i] == mValueWeights[maxIndex]) {
                hasGlobalMaximum = false;
                break;
            }
        }

        // e.g. ", preferably Red"
        if (hasGlobalMaximum) {
            if (reason.length() != 0) {
                reason.append(", ");
            }
            reason.append(getPreferablyString(values[maxIndex]));
        }

        return reason.toString();
    }

    /**
     * Updates the given {@link de.tum.in.schlichter.shoprx.algorithm.Query}s {@link Attributes} given the positive or
     * negative critique based on the {@link #currentValue()} of this
     * {@link de.tum.in.schlichter.shoprx.algorithm.model.Attributes.Attribute}.
     */
    public void critiqueQuery(Query query, boolean isPositive) {
        // get value weight index, current weights
        Attributes.Attribute queryAttr = query.attributes().getAttributeById(id());
        if (queryAttr == null) {
            query.attributes().initializeAttribute(this);
        }

        int valueIndex = currentValue().index();
        double[] weights = query.attributes().getAttributeById(id()).getValueWeights();

        // calculate new weights
        if (isPositive) {
            likeValue(valueIndex, weights);
        } else {
            dislikeValue(valueIndex, weights);
        }
    }

    /**
     * Increases the weight of the liked value by {@code 1 / weights.length},
     * clamps to 1.0. Subtracts the difference evenly from other weights, clamps
     * them to zero if liked value weight is 1.0.
     */
    public void likeValue(int valueIndex, double[] weights) {
        // calculate weight increase based on number of other values
        double weightIncrease = 1.0 / (weights.length - 1);
        weights[valueIndex] += weightIncrease;

        if (bindExceedingWeight(valueIndex, weights)) {
            return;
        }

        // subtract increase from other non-zero weights
        int count = 0;
        for (double weight : weights) {
            if (weight != 0) {
                count++;
            }
        }
        // calculate share for each value, excluding the liked one
        double redistributed = weightIncrease / (count - 1);
        for (int i = 0; i < weights.length; i++) {
            if (i != valueIndex) {
                weights[i] -= redistributed;
                // floor at 0.0
                if (weights[i] < 0) {
                    weights[i] = 0.0;
                }
            }
        }
    }

    /**
     * Ensures the given value weight does not exceed one, resets all other
     * weights to 0. and given weight to 1.0 if that is the case.
     * 
     * @return If the weight at the given index exceeded one.
     */
    public static boolean bindExceedingWeight(int valueIndex, double[] weights) {
        // if liked value weight exceeds 1.0, sum exceeds 1.0
        if (weights[valueIndex] > 1.0) {
            // reset other weights to 0.0
            Arrays.fill(weights, 0.0);
            weights[valueIndex] = 1.0;
            return true;
        }
        return false;
    }

    /**
     * Distributes overweight (above 1.0) onto all values until the sum is very
     * close to 1.0.
     */
    public static void ensureSumBound(double[] weights) {
        // sum can not exceed 1.0
        double sum = getSum(weights);
        if (sum > 1.0) {
            // distribute remaining weight weighted over all values
            double overWeight = sum - 1.0;
            double weightDecrease = overWeight / weights.length;

            for (int i = 0; i < weights.length; i++) {
                weights[i] -= weightDecrease;
                if (weights[i] <= 0.0) {
                    // distribute negative weight over remaining weights
                    weightDecrease -= weights[i] / (weights.length - i - 1);
                    weights[i] = 0.0;
                }
            }
        }
    }

    /**
     * Calculates the sum of all the given values.
     */
    public static double getSum(double[] values) {
        double sum = 0;
        for (double d : values) {
            sum += d;
        }
        return sum;
    }

    /**
     * Sets the disliked values weight to zero, distributes its ex-weight evenly
     * to other weights.
     */
    public void dislikeValue(int valueIndex, double[] weights) {
        double dislikedWeight = weights[valueIndex];

        weights[valueIndex] = 0.0;

        int nonZeroCount = 0;
        for (double weight : weights) {
            if (weight != 0) {
                nonZeroCount++;
            }
        }

        // check if all others are 0.0 already
        if (nonZeroCount == 0) {
            // distribute evenly over all others
            double redistributed = dislikedWeight / (weights.length - 1);
            for (int i = 0; i < weights.length; i++) {
                if (i != valueIndex) {
                    weights[i] += redistributed;
                }
            }
        } else {
            // distribute evenly over all non-zero weights
            double redistributed = dislikedWeight / nonZeroCount;
            for (int i = 0; i < weights.length; i++) {
                if (weights[i] != 0) {
                    weights[i] += redistributed;
                }
            }
        }
    }

    @Override
    public void updateQuery(Query query, Set<AttributeValue> preferredValues,Set<AttributeValue> preferredValuesDouble) {
        Attribute queryAttr =  query.attributes().getAttributeById(id());
        if (queryAttr == null) {
            queryAttr = query.attributes().initializeAndReturnAttribute(this);
        }

        queryAttr = (GenericAttribute) query.attributes().getAttributeById(id());
        preferAttributeValues(preferredValues,preferredValuesDouble, queryAttr.getValueWeights());
    }
    protected void preferAttributeValues(Set<AttributeValue> values,Set<AttributeValue> preferredValues, double[] weights) {
        int divisor = values.size()+preferredValues.size();
        double favorValue = WEIGHT_UPPER_BOUND /divisor*2;
        double normalValue = WEIGHT_UPPER_BOUND /divisor;
        Arrays.fill(weights, WEIGHT_LOWER_BOUND);
        for(AttributeValue value : values)
        if (preferredValues.contains(value))
            weights[value.index()] = favorValue;
        else weights[value.index()] = normalValue;

    }
}
