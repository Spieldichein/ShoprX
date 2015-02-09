
package com.uwetrottmann.shopr.algorithm.model;

import android.util.Log;

import com.uwetrottmann.shopr.R;
import com.uwetrottmann.shopr.ShoprApp;
import com.uwetrottmann.shopr.algorithm.model.Attributes.AttributeValue;

import org.jgrapht.Graphs;
import org.jgrapht.UndirectedGraph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;

import java.util.Arrays;
import java.util.List;

public class ClothingType extends GenericAttribute {

    private static UndirectedGraph<ClothingType.Value, DefaultEdge> sSimilarValues;

    static {
        sSimilarValues = new SimpleGraph<ClothingType.Value, DefaultEdge>(DefaultEdge.class);

        Value[] values = Value.values();
        for (Value value : values) {
            sSimilarValues.addVertex(value);
        }

        /**
         * Store similar clothing type values in an undirected graph.
         */
        sSimilarValues.addEdge(Value.SHIRT, Value.POLOSHIRT);
        sSimilarValues.addEdge(Value.SHIRT, Value.BLOUSE);
        sSimilarValues.addEdge(Value.TROUSERS, Value.JEANS);
        sSimilarValues.addEdge(Value.TROUSERS, Value.SHORTS);
        sSimilarValues.addEdge(Value.TROUSERS, Value.SKIRT);
        sSimilarValues.addEdge(Value.SKIRT, Value.SHORTS);
        sSimilarValues.addEdge(Value.CARDIGAN, Value.SWEATER);
        sSimilarValues.addEdge(Value.TOP, Value.SHIRT);
        sSimilarValues.addEdge(Value.TOP, Value.BLOUSE);
    }

    public static final String ID = "clothing-type";

    public enum Value implements AttributeValue {
        SWIMSUIT(R.string.swim_suit),
        TRUNKS(R.string.trunks),
        BLOUSE(R.string.blouse),
        SHIRT(R.string.shirt),
        TROUSERS(R.string.trousers),
        JEANS(R.string.jeans),
        DRESS(R.string.dress),
        POLOSHIRT(R.string.poloshirt),
        SWEATER(R.string.sweater), // Pullover
        SKIRT(R.string.skirt),
        SHORTS(R.string.shorts),
        CARDIGAN(R.string.cardigan), // Strickjacke
        TOP(R.string.top_t_shirt);

        int mDescriptor;

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

    public ClothingType() {
        int numValues = Value.values().length;
        mValueWeights = new double[numValues];
        Arrays.fill(mValueWeights, 1.0 / numValues);
    }

    public ClothingType(Value value) {
        setWeights(value);
    }

    public ClothingType(String value) {
        if ("Badeanzug".equals(value)) {
            setWeights(Value.SWIMSUIT);
        }
        else if ("Badehose".equals(value)) {
            setWeights(Value.TRUNKS);
        }
        else if ("Bluse".equals(value)) {
            setWeights(Value.BLOUSE);
        }
        else if ("Hemd".equals(value)) {
            setWeights(Value.SHIRT);
        }
        else if ("Hose".equals(value)) {
            setWeights(Value.TROUSERS);
        }
        else if ("Jeans".equals(value)) {
            setWeights(Value.JEANS);
        }
        else if ("Kleid".equals(value)) {
            setWeights(Value.DRESS);
        }
        else if ("Poloshirt".equals(value)) {
            setWeights(Value.POLOSHIRT);
        }
        else if ("Pullover".equals(value)) {
            setWeights(Value.SWEATER);
        }
        else if ("Rock".equals(value)) {
            setWeights(Value.SKIRT);
        }
        else if ("Strickjacke".equals(value)) {
            setWeights(Value.CARDIGAN);
        }
        else if ("Top".equals(value)) {
            setWeights(Value.TOP);
        }
        else if ("Shorts".equals(value)) {
            setWeights(Value.SHORTS);
        } else {
            Log.d("ClothingType", "Unknown: " + value.toUpperCase().replace(" ", "_") + "(\"" + value + "\"), ");
        }
    }

    private void setWeights(Value value) {
        mValueWeights = new double[Value.values().length];
        Arrays.fill(mValueWeights, 0.0);
        mValueWeights[value.ordinal()] = 1.0;
        currentValue(value);
    }

    @Override
    public String id() {
        return ID;
    }

    @Override
    public Value[] getValueSymbols() {
        return Value.values();
    }

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
