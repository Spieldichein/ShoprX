package de.tum.in.schlichter.shoprx.context.model;

import android.util.Log;

import de.tum.in.schlichter.shoprx.R;

import java.util.HashMap;
import java.util.Map;

import de.tum.in.schlichter.shoprx.ShoprApp;
import de.tum.in.schlichter.shoprx.algorithm.model.ClothingType;
import de.tum.in.schlichter.shoprx.context.model.interfaces.DistanceMetric;

/**
 * Created by Yannick on 11.02.15.
 *
 * This class stores the current temperature within the given context scenario.
 */
public enum Temperature implements DistanceMetric {
    LESS_THAN_0(R.string.below0Degrees),
    BETWEEN_0_AND_5(R.string.between0And5Degrees),
    BETWEEN_5_AND_10(R.string.between5And10Degrees),
    BETWEEN_10_AND_15(R.string.between10And15Degrees),
    BETWEEN_15_AND_20(R.string.between15And20Degrees),
    BETWEEN_20_AND_25(R.string.between20And25Degrees),
    BETWEEN_25_AND_30(R.string.between25And30Degrees),
    ABOVE_30(R.string.above30Degrees);

    private static Map<ClothingType.Value, Double> sWeights;

    private static final String TAG = "Temperature";

    static{
        sWeights = new HashMap<ClothingType.Value, Double>();
        sWeights.put(ClothingType.Value.BLOUSE, 0.82);
        sWeights.put(ClothingType.Value.CARDIGAN, 0.92);
        sWeights.put(ClothingType.Value.COAT, 1.0);
        sWeights.put(ClothingType.Value.DRESS, 0.91);
        sWeights.put(ClothingType.Value.JACKET, 0.88);
        sWeights.put(ClothingType.Value.JEANS, 0.9);
        sWeights.put(ClothingType.Value.SHIRT, 1.0);
        sWeights.put(ClothingType.Value.SKIRT, 0.88);
        sWeights.put(ClothingType.Value.SWIMWEAR, 0.92);
        sWeights.put(ClothingType.Value.TOP, 0.84);
        sWeights.put(ClothingType.Value.TROUSERS, 0.8);

        sWeights.put(ClothingType.Value.UNKNOWN, 1.0);

        sWeights.put(ClothingType.Value.CHINO, sWeights.get(ClothingType.Value.TROUSERS));
        sWeights.put(ClothingType.Value.HOODIE, sWeights.get(ClothingType.Value.CARDIGAN) * 0.8 + sWeights.get(ClothingType.Value.JACKET) * 0.2); // 80% Cardigan 20% Jacket
        sWeights.put(ClothingType.Value.SHORTS, sWeights.get(ClothingType.Value.SWIMWEAR) * 0.5 + sWeights.get(ClothingType.Value.TROUSERS) * 0.5); //Half swimwear - half trousers
        sWeights.put(ClothingType.Value.SWEATSHIRT, sWeights.get(ClothingType.Value.CARDIGAN)); // Cardigan
        sWeights.put(ClothingType.Value.TUNIC, 0.5 * sWeights.get(ClothingType.Value.DRESS) + 0.5 * sWeights.get(ClothingType.Value.SHIRT)); // Half dress - half shirt
        sWeights.put(ClothingType.Value.T_SHIRT, sWeights.get(ClothingType.Value.SHIRT));
        sWeights.put(ClothingType.Value.JUMPER, 0.8 * sWeights.get(ClothingType.Value.SWEATSHIRT) + 0.2 * sWeights.get(ClothingType.Value.SHIRT)); // 80% Sweatshirt 20 % shirt

    }

    private int mDegrees;

    private static Map<String, Temperature> sAvailableTemperatures = new HashMap<String, Temperature>();

    Temperature(int degrees) {
         mDegrees = degrees;
    }

    public String descriptor(){
        return ShoprApp.getContext().getString(mDegrees);
    }

    public static Temperature getTemperature(String description){
        if (sAvailableTemperatures.size() == 0){
            for (Temperature temp : Temperature.values()){
                sAvailableTemperatures.put(ShoprApp.getContext().getString(temp.mDegrees), temp);
            }
        }

        return sAvailableTemperatures.get(description);
    }

    @Override
    public String toString(){
        return ShoprApp.getContext().getString(mDegrees);
    }

    public int getDegrees(){
        return mDegrees;
    }

    public static Temperature getTemperature(int match){
        for (Temperature value: values()){
            if (value.mDegrees == match){
                return value;
            }
        }
        return null;
    }

    @Override
    public boolean isMetricWithEuclideanDistance() {
        return true;
    }

    @SuppressWarnings("SuspiciousMethodCalls")
    @Override
    public double getWeight(ClothingType clothingType) {
        if (sWeights.containsKey(clothingType.currentValue())){
            return sWeights.get(clothingType.currentValue());
        }

        Log.d(TAG, "Did not find " + clothingType.currentValue().descriptor() + " weight.");

        return 1;
    }


    @Override
    public double distanceToContext(ScenarioContext scenarioContext) throws UnsupportedOperationException {
        throw new UnsupportedOperationException("Is Euclidean Distance");
    }

    @Override
    public int numberOfItems() throws UnsupportedOperationException {
        return values().length;
    }

    @Override
    public int currentOrdinal() throws UnsupportedOperationException {
        return ordinal();
    }
}
