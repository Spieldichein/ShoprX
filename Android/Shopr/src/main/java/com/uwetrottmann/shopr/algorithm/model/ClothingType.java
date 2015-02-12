
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ClothingType extends GenericAttribute {

    private static UndirectedGraph<ClothingType.Value, DefaultEdge> sSimilarValues;
    private static Map<String, Value> sAvailableClothing = new HashMap<String, Value>();

    static {
        sSimilarValues = new SimpleGraph<ClothingType.Value, DefaultEdge>(DefaultEdge.class);

        Value[] values = Value.values();
        for (Value value : values) {
            sSimilarValues.addVertex(value);
        }

        /**
         * Store similar clothing type values in an undirected graph.
         */
//        sSimilarValues.addEdge(Value.SHIRT, Value.POLOSHIRT);
//        sSimilarValues.addEdge(Value.SHIRT, Value.BLOUSE);
//        sSimilarValues.addEdge(Value.TROUSERS, Value.JEANS);
//        sSimilarValues.addEdge(Value.TROUSERS, Value.SHORTS);
//        sSimilarValues.addEdge(Value.TROUSERS, Value.SKIRT);
//        sSimilarValues.addEdge(Value.SKIRT, Value.SHORTS);
//        sSimilarValues.addEdge(Value.CARDIGAN, Value.SWEATER);
        sSimilarValues.addEdge(Value.TOP, Value.SHIRT);
        sSimilarValues.addEdge(Value.T_SHIRT, Value.TOP);
        sSimilarValues.addEdge(Value.T_SHIRT,Value.SHIRT);
        sSimilarValues.addEdge(Value.COAT, Value.JACKET);
//        sSimilarValues.addEdge(Value.TOP, Value.BLOUSE);

        if (sAvailableClothing.size() == 0) {
            for (Value value1 : Value.values()) {
                sAvailableClothing.put(value1.descriptor(), value1);
            }
        }
    }

    public static final String ID = "clothing-type";

    public enum Value implements AttributeValue {
        //SWIMSUIT(R.string.swim_suit),
        //TRUNKS(R.string.trunks),
        BLOUSE(R.string.blouse), // TODO new - draw similarity graph
        CARDIGAN(R.string.cardigan), //TODO new - draw similarity graph
        CHINO(R.string.chino), //TODO new - draw similarity graph
        COAT(R.string.coat), //TODO new - draw similarity graph
        DRESS(R.string.dress),
        HOODIE(R.string.hoodie),//TODO new - draw similarity graph
        JACKET(R.string.jacket), //TODO new - draw similarity graph
        JEANS(R.string.jeans),
        JUMPER(R.string.jumper), //TODO new - draw similarity graph
        SKIRT(R.string.skirt),
        SHORTS(R.string.shorts),//TODO new - draw similarity graph
        SHIRT(R.string.shirt), //TODO new - draw similarity graph
        SWEATSHIRT(R.string.sweatshirt), //TODO new - draw similarity graph
        SWIMWEAR(R.string.swimwear), //TODO new - draw similarity graph
        TOP(R.string.top), //TODO new - draw similiarity graph
        TROUSER(R.string.trousers),  //TODO new - draw similiarity graph
        T_SHIRT(R.string.t_shirt), //TODO new - draw similarity graph
        TUNIC(R.string.tunic),//TODO new - draw similarity graph
        UNKNOWN(R.string.unknownClothingType);

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
        if (sAvailableClothing.containsKey(value)) {
            setWeights(sAvailableClothing.get(value));
        } else if (value.equals("Trousers-chino")){ //TODO Revise!
            setWeights(Value.CHINO);
        } else {
            setWeights(Value.UNKNOWN);
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
