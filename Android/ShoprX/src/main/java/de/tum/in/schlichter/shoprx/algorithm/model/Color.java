
package de.tum.in.schlichter.shoprx.algorithm.model;

import android.util.Log;

import de.tum.in.schlichter.shoprx.R;

import org.jgrapht.Graphs;
import org.jgrapht.UndirectedGraph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import de.tum.in.schlichter.shoprx.ShoprApp;
import de.tum.in.schlichter.shoprx.algorithm.Query;
import de.tum.in.schlichter.shoprx.algorithm.model.Attributes.AttributeValue;

public class Color extends GenericAttribute {

    private static UndirectedGraph<Color.Value, DefaultEdge> sSimilarValues;
    private static Map<String, Value> sAvailableColors = new HashMap<String, Value>();

    static {
        sSimilarValues = new SimpleGraph<Color.Value, DefaultEdge>(DefaultEdge.class);

        Value[] values = Value.values();
        for (Value value : values) {
            sSimilarValues.addVertex(value);
        }

        /**
         * Stores similar price values in an undirected graph. This is rather
         * subjective, e.g. white is similar to grey. Red is similar to pink,
         * etc.
         */
        sSimilarValues.addEdge(Value.BLUE, Value.LILAC);
        sSimilarValues.addEdge(Value.BLUE, Value.TURQUOISE);
        sSimilarValues.addEdge(Value.RED, Value.LILAC);
        sSimilarValues.addEdge(Value.RED, Value.PINK);
        sSimilarValues.addEdge(Value.YELLOW, Value.ORANGE);
        sSimilarValues.addEdge(Value.BLACK, Value.GREY);
        sSimilarValues.addEdge(Value.WHITE, Value.BEIGE);
        sSimilarValues.addEdge(Value.WHITE, Value.GREY);
        sSimilarValues.addEdge(Value.BROWN, Value.BEIGE);
        sSimilarValues.addEdge(Value.BROWN, Value.OLIVE);
        sSimilarValues.addEdge(Value.OLIVE, Value.GREEN);
        sSimilarValues.addEdge(Value.PETROL, Value.BLUE);
        sSimilarValues.addEdge(Value.PETROL, Value.GREEN);
        sSimilarValues.addEdge(Value.GOLD, Value.YELLOW);
        sSimilarValues.addEdge(Value.GOLD, Value.SILVER);
        sSimilarValues.addEdge(Value.SILVER, Value.GREY);
    }

    public static final String ID = "color";

    public enum Value implements Attributes.AttributeValue {
        BEIGE(R.string.beige,"beige", Colors.BEIGE),
        BLACK(R.string.black,"black", Colors.BLACK),
        BLUE(R.string.blue,"blue",Colors.BLUE),
        BROWN(R.string.brown,"brown",Colors.BROWN),
        COLORED(R.string.colored,"multicolor", Colors.MULTICOLOR),
        GOLD(R.string.gold,"gold",Colors.GOLD),
        GREY(R.string.grey,"grey",Colors.GREY),
        GREEN(R.string.green,"green",Colors.GREEN),
        LILAC(R.string.lilac,"lilac",Colors.LILAC),
        OLIVE(R.string.olive,"olive",Colors.OLIVE),
        ORANGE(R.string.orange,"orange",Colors.ORANGE),
        PETROL(R.string.petrol,"petrol",Colors.PETROL),
        PINK(R.string.pink,"pink",Colors.PINK), // rosa zu Deutsch
        RED(R.string.red,"red",Colors.RED),
        SILVER(R.string.silver,"silver", Colors.SILVER),
        TURQUOISE(R.string.turquoise,"turquoise",Colors.TURQUOISE),
        WHITE(R.string.white,"white", Colors.WHITE),
        YELLOW(R.string.yellow,"yellow", Colors.YELLOW);

        int mDescriptor;
        private String valueName;
        private String color;
        private String simpleNamePrefix = "color_";
        private String simpleName;



        Value(int name,String value, String color) {
            //valueName=value;
            mDescriptor = name;
            valueName= ShoprApp.getContext().getString(name);
            simpleName = simpleNamePrefix+value;

            this.color = color;
        }
        Value(String name) {
            valueName = name;
        }

        @Override
        public String descriptor() {
            return ShoprApp.getContext().getString(mDescriptor);
        }

        @Override
        public String getColor() {
            return color;
        }

        @Override
        public int index() {
            return ordinal();
        }

        @Override
        public String getValueName(){
            return valueName;
        }

        @Override
        public String getSimpleName(){
            return simpleName;
        }
    }

    static {
        if (sAvailableColors.size() == 0) {
            for (Value value1 : Value.values()) {
                sAvailableColors.put(value1.descriptor(), value1);
            }
        }
    }

    public Color() {
        int numValues = Value.values().length;
        mValueWeights = new double[numValues];
        Arrays.fill(mValueWeights, 1.0 / numValues);
    }

    public Color(Value value) {
        setWeights(value);
    }

    /**
     * Tries to match the given string with a {@link Color.Value}.
     */
    public Color(String value) {
        if (sAvailableColors.containsKey(value)){
            setWeights(sAvailableColors.get(value));
        } else {
            Log.d("Color", "Unknown: " + value.toUpperCase().replace(" ", "_") + "(\"" + value + "\"), ");
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
    public Attributes.AttributeValue[] getValueSymbols() {
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

    @Override
    public AttributeValue[] getAttributeValues() {
        return Value.values();
    }



}
