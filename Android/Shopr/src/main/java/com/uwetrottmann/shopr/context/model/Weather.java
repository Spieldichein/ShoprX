package com.uwetrottmann.shopr.context.model;

import com.uwetrottmann.shopr.R;
import com.uwetrottmann.shopr.ShoprApp;

import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.ListenableUndirectedWeightedGraph;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Yannick on 11.02.15.
 *
 * This class stores the current temperature within the given context scenario.
 */
public enum Weather implements DistanceMetric{
    SUNNY(R.string.sunnyWeather),
    PARTLY_CLOUDY(R.string.partlyCloudyWeather),
    CLOUDY(R.string.cloudyWeather),
    VERY_CLOUDY(R.string.veryCloudyWeather),
    RAINING(R.string.RainyWeather),
    SNOWING(R.string.SnowyWeather),
    OTHER_CONDITIONS(R.string.OtherWeather);

    private static final double WEIGHT = 0.77;

    private int mWeatherIndicator;

    private static Map<String, Weather> sAvailableWeatherStates = new HashMap<String, Weather>();

    private static ListenableUndirectedWeightedGraph<Weather, DefaultWeightedEdge> sDistanceGraph;

    static {
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

    @Override
    public double getWeight() {
        return WEIGHT;
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
