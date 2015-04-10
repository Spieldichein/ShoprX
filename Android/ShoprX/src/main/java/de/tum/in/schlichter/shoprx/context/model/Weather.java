package de.tum.in.schlichter.shoprx.context.model;

import android.util.Log;

import de.tum.in.schlichter.shoprx.R;

import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.ListenableUndirectedWeightedGraph;

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
public enum Weather implements DistanceMetric {
    SUNNY(R.string.sunnyWeather),
    PARTLY_CLOUDY(R.string.partlyCloudyWeather),
    CLOUDY(R.string.cloudyWeather),
    VERY_CLOUDY(R.string.veryCloudyWeather),
    RAINING(R.string.RainyWeather),
    SNOWING(R.string.SnowyWeather),
    OTHER_CONDITIONS(R.string.OtherWeather);

    private static Map<ClothingType.Value, Double> sWeights;

    private static final String TAG = "Weather";

    private int mWeatherIndicator;

    private static Map<String, Weather> sAvailableWeatherStates = new HashMap<String, Weather>();

    private static ListenableUndirectedWeightedGraph<Weather, DefaultWeightedEdge> sDistanceGraph;

    static {
        sWeights = new HashMap<ClothingType.Value, Double>();
        sWeights.put(ClothingType.Value.BLOUSE, 0.85);
        sWeights.put(ClothingType.Value.CARDIGAN, 0.92);
        sWeights.put(ClothingType.Value.COAT, 0.75);
        sWeights.put(ClothingType.Value.DRESS, 1.0);
        sWeights.put(ClothingType.Value.JACKET, 0.74);
        sWeights.put(ClothingType.Value.JEANS, 0.74);
        sWeights.put(ClothingType.Value.SHIRT, 1.0);
        sWeights.put(ClothingType.Value.SKIRT, 0.87);
        sWeights.put(ClothingType.Value.SWIMWEAR, 0.82);
        sWeights.put(ClothingType.Value.TOP, 0.8);
        sWeights.put(ClothingType.Value.TROUSERS, 1.0);

        sWeights.put(ClothingType.Value.UNKNOWN, 1.0);

        sWeights.put(ClothingType.Value.CHINO, sWeights.get(ClothingType.Value.TROUSERS));
        sWeights.put(ClothingType.Value.HOODIE, sWeights.get(ClothingType.Value.CARDIGAN) * 0.8 + sWeights.get(ClothingType.Value.JACKET) * 0.2); // 80% Cardigan 20% Jacket
        sWeights.put(ClothingType.Value.SHORTS, sWeights.get(ClothingType.Value.SWIMWEAR) * 0.5 + sWeights.get(ClothingType.Value.TROUSERS) * 0.5); //Half swimwear - half trousers
        sWeights.put(ClothingType.Value.SWEATSHIRT, sWeights.get(ClothingType.Value.CARDIGAN)); // Cardigan
        sWeights.put(ClothingType.Value.TUNIC, 0.5 * sWeights.get(ClothingType.Value.DRESS) + 0.5 * sWeights.get(ClothingType.Value.SHIRT)); // Half dress - half shirt
        sWeights.put(ClothingType.Value.T_SHIRT, sWeights.get(ClothingType.Value.SHIRT));
        sWeights.put(ClothingType.Value.JUMPER, 0.8 * sWeights.get(ClothingType.Value.SWEATSHIRT) + 0.2 * sWeights.get(ClothingType.Value.SHIRT)); // 80% Sweatshirt 20 % shirt



        sDistanceGraph = new ListenableUndirectedWeightedGraph<Weather, DefaultWeightedEdge>(DefaultWeightedEdge.class);

        sDistanceGraph.addVertex(SUNNY);
        sDistanceGraph.addVertex(PARTLY_CLOUDY);
        sDistanceGraph.addVertex(CLOUDY);
        sDistanceGraph.addVertex(VERY_CLOUDY);
        sDistanceGraph.addVertex(RAINING);
        sDistanceGraph.addVertex(SNOWING);
        sDistanceGraph.addVertex(OTHER_CONDITIONS);

        DefaultWeightedEdge edge = sDistanceGraph.addEdge(SUNNY, PARTLY_CLOUDY);
        sDistanceGraph.setEdgeWeight(edge, 0.1);

        edge = sDistanceGraph.addEdge(CLOUDY, SUNNY);
        sDistanceGraph.setEdgeWeight(edge, 0.3);

        edge = sDistanceGraph.addEdge(SUNNY, VERY_CLOUDY);
        sDistanceGraph.setEdgeWeight(edge, 0.6);

        edge = sDistanceGraph.addEdge(RAINING, SUNNY);
        sDistanceGraph.setEdgeWeight(edge, 1);

        edge = sDistanceGraph.addEdge(SUNNY, SNOWING);
        sDistanceGraph.setEdgeWeight(edge, 1);

        edge = sDistanceGraph.addEdge(CLOUDY, PARTLY_CLOUDY);
        sDistanceGraph.setEdgeWeight(edge, 0.1);

        edge = sDistanceGraph.addEdge(PARTLY_CLOUDY, VERY_CLOUDY);
        sDistanceGraph.setEdgeWeight(edge, 0.3);

        edge = sDistanceGraph.addEdge(RAINING, PARTLY_CLOUDY);
        sDistanceGraph.setEdgeWeight(edge, 0.8);

        edge = sDistanceGraph.addEdge(PARTLY_CLOUDY, SNOWING);
        sDistanceGraph.setEdgeWeight(edge, 0.8);

        edge = sDistanceGraph.addEdge(VERY_CLOUDY, CLOUDY);
        sDistanceGraph.setEdgeWeight(edge, 0.1);

        edge = sDistanceGraph.addEdge(CLOUDY, RAINING);
        sDistanceGraph.setEdgeWeight(edge, 0.7);

	edge = sDistanceGraph.addEdge(CLOUDY, SNOWING);
        sDistanceGraph.setEdgeWeight(edge, 0.7);

        edge = sDistanceGraph.addEdge(RAINING, VERY_CLOUDY);
        sDistanceGraph.setEdgeWeight(edge, 0.4);

        edge = sDistanceGraph.addEdge(VERY_CLOUDY, SNOWING);
        sDistanceGraph.setEdgeWeight(edge, 0.4);

        edge = sDistanceGraph.addEdge(SNOWING, RAINING);
        sDistanceGraph.setEdgeWeight(edge, 0.6);

    }

    Weather(int weather) {
         mWeatherIndicator = weather;
    }

    public String descriptor(){
        return ShoprApp.getContext().getString(mWeatherIndicator);
    }

    public static Weather getWeather(String description){
        if (sAvailableWeatherStates.size() == 0){
            for (Weather weather : Weather.values()){
                sAvailableWeatherStates.put(ShoprApp.getContext().getString(weather.mWeatherIndicator), weather);
            }
        }

        return sAvailableWeatherStates.get(description);
    }

    @Override
    public String toString(){
        return ShoprApp.getContext().getString(mWeatherIndicator);
    }

    public int getWeatherIndicator(){
        return mWeatherIndicator;
    }

    public static Weather getWeather(int match){
        for (Weather value: values()){
            if (value.mWeatherIndicator == match){
                return value;
            }
        }
        return null;
    }

    @Override
    public boolean isMetricWithEuclideanDistance() {
        return false;
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
        // If the scenario context is exactly the same, as this object, we are going to return 0 (because we are equal)
        // Otherwise we have to search in a graph structure (undirected) to see how far the distance between
        // the context and this object is.
        if (scenarioContext.getWeather().equals(this)){
            return 0;
        } else if (scenarioContext.getWeather().equals(OTHER_CONDITIONS) || this.equals(OTHER_CONDITIONS)){
            return 1;
        }

        return sDistanceGraph.getEdgeWeight(sDistanceGraph.getEdge(scenarioContext.getWeather(), this));

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
