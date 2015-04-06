
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

import de.tum.in.schlichter.shoprx.ShoprApp;
import de.tum.in.schlichter.shoprx.context.model.Company;
import de.tum.in.schlichter.shoprx.context.model.DayOfTheWeek;
import de.tum.in.schlichter.shoprx.context.model.Temperature;
import de.tum.in.schlichter.shoprx.context.model.TimeOfTheDay;
import de.tum.in.schlichter.shoprx.context.model.Weather;

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
        sSimilarValues.addEdge(Value.BLOUSE, Value.TUNIC);
        sSimilarValues.addEdge(Value.CARDIGAN, Value.SWEATSHIRT);
        sSimilarValues.addEdge(Value.CARDIGAN, Value.COAT);
        sSimilarValues.addEdge(Value.CHINO, Value.JEANS);
        sSimilarValues.addEdge(Value.CHINO, Value.SHORTS);
        sSimilarValues.addEdge(Value.COAT, Value.JACKET);
        sSimilarValues.addEdge(Value.DRESS, Value.TUNIC);
        sSimilarValues.addEdge(Value.HOODIE, Value.JACKET);
        sSimilarValues.addEdge(Value.HOODIE, Value.SHIRT);
        sSimilarValues.addEdge(Value.HOODIE, Value.T_SHIRT);
        sSimilarValues.addEdge(Value.JUMPER, Value.DRESS);
        sSimilarValues.addEdge(Value.JUMPER, Value.SWEATSHIRT);
        sSimilarValues.addEdge(Value.SHIRT, Value.BLOUSE);
        sSimilarValues.addEdge(Value.SHORTS, Value.SWIMWEAR);
        sSimilarValues.addEdge(Value.SHORTS, Value.TOP);
        sSimilarValues.addEdge(Value.SWEATSHIRT, Value.T_SHIRT);
        sSimilarValues.addEdge(Value.SKIRT, Value.SHORTS);
        sSimilarValues.addEdge(Value.SWEATSHIRT, Value.HOODIE);
        sSimilarValues.addEdge(Value.TROUSERS, Value.JEANS);
        sSimilarValues.addEdge(Value.TROUSERS, Value.SHORTS);
        sSimilarValues.addEdge(Value.TROUSERS, Value.SKIRT);
        sSimilarValues.addEdge(Value.TROUSERS, Value.CHINO);
        sSimilarValues.addEdge(Value.TOP, Value.BLOUSE);
        sSimilarValues.addEdge(Value.TOP, Value.SHIRT);
        sSimilarValues.addEdge(Value.T_SHIRT, Value.TOP);
        sSimilarValues.addEdge(Value.T_SHIRT,Value.SHIRT);

        if (sAvailableClothing.size() == 0) {
            for (Value value1 : Value.values()) {
                sAvailableClothing.put(value1.descriptor(), value1);
            }
        }
    }

    public static final String ID = "clothing-type";

    public enum Value implements Attributes.AttributeValue {

        BLOUSE(R.string.blouse,Colors.YELLOW),
        CARDIGAN(R.string.cardigan,Colors.WHITE),
        CHINO(R.string.chino,Colors.TURQUOISE),
        COAT(R.string.coat, Colors.SILVER),
        DRESS(R.string.dress, Colors.AZURE_BLUE),
        HOODIE(R.string.hoodie,Colors.BEIGE),
        JACKET(R.string.jacket, Colors.BLACK),
        JEANS(R.string.jeans,Colors.BLUE),
        JUMPER(R.string.jumper,Colors.BROWN),
        SKIRT(R.string.skirt,Colors.GOLD),
        SHORTS(R.string.shorts,Colors.GREEN),
        SHIRT(R.string.shirt,Colors.LILAC),
        SWEATSHIRT(R.string.sweatshirt,Colors.MULTICOLOR),
        SWIMWEAR(R.string.swimwear,Colors.OLIVE),
        TOP(R.string.top,Colors.ORANGE),
        TROUSERS(R.string.trousers,Colors.PEACH),
        T_SHIRT(R.string.t_shirt,Colors.PETROL),
        TUNIC(R.string.tunic,Colors.RED),
        UNKNOWN(R.string.unknownClothingType, Colors.PURPLE);

        int mDescriptor;
        private String valueName;
        private String color;
        private String simpleNamePrefix = "clothing_";
        private String simpleName;


        Value(int descriptor,String color) {
            mDescriptor = descriptor;
            valueName= ShoprApp.getContext().getString(descriptor);
            this.color =color;
            simpleName = simpleNamePrefix+valueName.toLowerCase();
        }

        @Override
        public String descriptor() {
            return ShoprApp.getContext().getString(mDescriptor);
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
        public String getColor() {
            return color;
        }
        @Override
        public String getSimpleName(){return simpleName;}
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

    public double getContextWeightsSum(){
        double weight = 0.0;
        //The weights are like static methods.
        weight += DayOfTheWeek.WORKDAY.getWeight(this);
        weight += TimeOfTheDay.AFTERNOON.getWeight(this);
        weight += Company.ALONE.getWeight(this);
        weight += Weather.CLOUDY.getWeight(this);
        weight += Temperature.ABOVE_30.getWeight(this);

        return weight;
    }
    @Override
    public Attributes.AttributeValue[] getAttributeValues() {
        return Value.values();
    }
}


