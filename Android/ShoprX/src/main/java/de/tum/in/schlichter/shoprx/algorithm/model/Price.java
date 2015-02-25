
package de.tum.in.schlichter.shoprx.algorithm.model;

import de.tum.in.schlichter.shoprx.R;

import org.jgrapht.Graphs;
import org.jgrapht.UndirectedGraph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import de.tum.in.schlichter.shoprx.ShoprApp;

public class Price extends GenericAttribute {

    private static UndirectedGraph<Price.Value, DefaultEdge> sSimilarValues;

    static {
        sSimilarValues = new SimpleGraph<Price.Value, DefaultEdge>(DefaultEdge.class);

        Value[] values = Value.values();
        for (Value value : values) {
            sSimilarValues.addVertex(value);
        }

        /**
         * Stores similar price values in an undirected graph. Price regions
         * right above or below are similar.
         */
        sSimilarValues.addEdge(Value.SUB_25, Value.BETWEEN_25_50);
        sSimilarValues.addEdge(Value.BETWEEN_25_50, Value.BETWEEN_50_75);
        sSimilarValues.addEdge(Value.BETWEEN_50_75, Value.BETWEEN_75_100);
        sSimilarValues.addEdge(Value.BETWEEN_75_100, Value.BETWEEN_100_150);
        sSimilarValues.addEdge(Value.BETWEEN_100_150, Value.BETWEEN_150_200);
        sSimilarValues.addEdge(Value.BETWEEN_150_200, Value.ABOVE_200);
    }

    public static final String ID = "price";

    public enum Value implements Attributes.AttributeValue {
        SUB_25(R.string.less_than_25),
        BETWEEN_25_50(R.string._25_to_50),
        BETWEEN_50_75(R.string._50_to_75),
        BETWEEN_75_100(R.string._75_to_100),
        BETWEEN_100_150(R.string._100_to_150),
        BETWEEN_150_200(R.string._150_to_200),
        ABOVE_200(R.string._200_or_more);

        private int mDescriptor;

        Value(int descriptor) {
            mDescriptor = descriptor;
        }

        @Override
        public String descriptor() {
            return ShoprApp.getContext().getString(mDescriptor);
        }

        @Override
        public int index() {
            return ordinal();
        }

    }

    public Price() {
        int numValues = Value.values().length;
        mValueWeights = new double[numValues];
        Arrays.fill(mValueWeights, 1.0 / numValues);
    }

    public Price(BigDecimal price) {
        mValueWeights = new double[Value.values().length];
        Arrays.fill(mValueWeights, 0.0);

        // determine price range
        Value value;
        if (price.doubleValue() < 25.0) {
            value = Value.SUB_25;
        } else if (price.doubleValue() < 50.0) {
            value = Value.BETWEEN_25_50;
        } else if (price.doubleValue() < 75.0) {
            value = Value.BETWEEN_50_75;
        } else if (price.doubleValue() < 100.0) {
            value = Value.BETWEEN_75_100;
        } else if (price.doubleValue() < 150.0) {
            value = Value.BETWEEN_100_150;
        } else if (price.doubleValue() < 200.0) {
            value = Value.BETWEEN_150_200;
        } else {
            value = Value.ABOVE_200;
        }

        mValueWeights[value.ordinal()] = 1.0;
        currentValue(value);
    }

    @Override
    public String id() {
        return ID;
    }

    @Override
    public Attributes.AttributeValue[] getValueSymbols() {
        return Value.values();
    }

    /**
     * Changes the behavior to also increase the weight of neighboring weights.
     * E.g. a user might like items priced in a little more expensive or a
     * little cheaper based on the price range he liked.
     */
    @Override
    public void likeValue(int indexLiked, double[] weights) {
        Value[] values = Value.values();
        Value valueLiked = values[indexLiked];
        List<Value> similarValues = Graphs.neighborListOf(sSimilarValues, valueLiked);

        // do regular like for liked value
        super.likeValue(indexLiked, weights);

        if (similarValues.isEmpty()) {
            // no similars: done!
            return;
        }

        // now do dampened like on similar values
        double increaseLiked = 1.0 / (weights.length - 1);
        double increaseSimilars = increaseLiked / 2;
        // per similar value increase
        double increaseSimilar = increaseSimilars / similarValues.size();
        // per non-similar and non-liked value decrease
        double decreaseOthers = increaseSimilars / (weights.length - similarValues.size() - 1);

        // actually add and subtract
        for (int i = 0; i < weights.length; i++) {
            if (i == indexLiked) {
                // skip liked value
                continue;
            }
            if (hasValueWithSameIndex(similarValues, i)) {
                // increase similar values
                weights[i] += increaseSimilar;
            } else {
                // decrease other values
                weights[i] -= decreaseOthers;
                // floor at 0.0
                if (weights[i] < 0) {
                    weights[i] = 0.0;
                }
            }
        }

        ensureSumBound(weights);
    }

    /**
     * Checks whether one of the values has the given index.
     */
    private boolean hasValueWithSameIndex(List<Value> values, int index) {
        for (Value value : values) {
            if (value.index() == index) {
                return true;
            }
        }
        return false;
    }

}
