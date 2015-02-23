package com.uwetrottmann.shopr.context.model;

import com.uwetrottmann.shopr.R;
import com.uwetrottmann.shopr.ShoprApp;

import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleDirectedWeightedGraph;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Yannick on 11.02.15.
 *
 * This class stores the current temperature within the given context scenario.
 */
public enum Company implements DistanceMetric{
    ALONE(R.string.alone),
    WITH_FAMILY(R.string.withFamily),
    WITH_FRIEND(R.string.withAFriend);

    private static final double WEIGHT = 0.69;

    private int mCompanyType;

    private static Map<String, Company> sAvailableCompany = new HashMap<String, Company>();

    private static SimpleDirectedWeightedGraph<Company, DefaultWeightedEdge> sDistanceGraph;

    static {
        sDistanceGraph = new SimpleDirectedWeightedGraph<Company, DefaultWeightedEdge>(DefaultWeightedEdge.class);

        sDistanceGraph.addVertex(ALONE);
        sDistanceGraph.addVertex(WITH_FAMILY);
        sDistanceGraph.addVertex(WITH_FRIEND);

        //Unfortunately there is no undirected weighted graph. :(
        DefaultWeightedEdge edge = sDistanceGraph.addEdge(ALONE, WITH_FAMILY);
        sDistanceGraph.setEdgeWeight(edge, 0.7);

        edge = sDistanceGraph.addEdge(WITH_FRIEND, ALONE);
        sDistanceGraph.setEdgeWeight(edge, 0.4);

        edge = sDistanceGraph.addEdge(WITH_FRIEND, WITH_FAMILY);
        sDistanceGraph.setEdgeWeight(edge, 1.0);
    }

    Company(int company) {
         mCompanyType = company;
    }

    public String descriptor(){
        return ShoprApp.getContext().getString(mCompanyType);
    }

    public static Company getCompany(String description){
        if (sAvailableCompany.size() == 0){
            for (Company company : Company.values()){
                sAvailableCompany.put(ShoprApp.getContext().getString(company.mCompanyType), company);
            }
        }

        return sAvailableCompany.get(description);
    }

    @Override
    public String toString(){
        return ShoprApp.getContext().getString(mCompanyType);
    }

    public int getCompanyType(){
        return mCompanyType;
    }

    public static Company getCompany(int match){
        for (Company value: values()){
            if (value.mCompanyType == match){
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
        if (scenarioContext.getCompany().equals(this)){
            return 0;
        }

        return sDistanceGraph.getEdgeWeight(sDistanceGraph.getEdge(scenarioContext.getCompany(), this));
    }

    @Override
    public int numberOfItems() {
        return values().length;
    }

    @Override
    public int currentOrdinal() {
        return ordinal();
    }
}
