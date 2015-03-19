package de.tum.in.schlichter.shoprx.context.model;

import android.util.Log;

import de.tum.in.schlichter.shoprx.R;

import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleDirectedWeightedGraph;

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
public enum Company implements DistanceMetric {
    ALONE(R.string.alone),
    WITH_FAMILY(R.string.withFamily),
    WITH_FRIEND(R.string.withAFriend);

    private int mCompanyType;

    private static Map<String, Company> sAvailableCompany = new HashMap<String, Company>();

    private static SimpleDirectedWeightedGraph<Company, DefaultWeightedEdge> sDistanceGraph;

    private static Map<ClothingType.Value, Double> sWeights;

    private static final String TAG = "Company";

    static {
        sWeights = new HashMap<ClothingType.Value, Double>();
        sWeights.put(ClothingType.Value.BLOUSE, 0.75);
        sWeights.put(ClothingType.Value.CARDIGAN, 0.82);
        sWeights.put(ClothingType.Value.COAT, 0.68);
        sWeights.put(ClothingType.Value.DRESS, 0.91);
        sWeights.put(ClothingType.Value.JACKET, 0.9);
        sWeights.put(ClothingType.Value.JEANS, 0.91);
        sWeights.put(ClothingType.Value.SHIRT, 0.9);
        sWeights.put(ClothingType.Value.SKIRT, 1.0);
        sWeights.put(ClothingType.Value.SWIMWEAR, 0.76);
        sWeights.put(ClothingType.Value.TOP, 0.76);
        sWeights.put(ClothingType.Value.TROUSERS, 0.82);

        sWeights.put(ClothingType.Value.UNKNOWN, 1.0);

        sWeights.put(ClothingType.Value.CHINO, sWeights.get(ClothingType.Value.TROUSERS));
        sWeights.put(ClothingType.Value.HOODIE, sWeights.get(ClothingType.Value.CARDIGAN) * 0.8 + sWeights.get(ClothingType.Value.JACKET) * 0.2); // 80% Cardigan 20% Jacket
        sWeights.put(ClothingType.Value.SHORTS, sWeights.get(ClothingType.Value.SWIMWEAR) * 0.5 + sWeights.get(ClothingType.Value.TROUSERS) * 0.5); //Half swimwear - half trousers
        sWeights.put(ClothingType.Value.SWEATSHIRT, sWeights.get(ClothingType.Value.CARDIGAN)); // Cardigan
        sWeights.put(ClothingType.Value.TUNIC, 0.5 * sWeights.get(ClothingType.Value.DRESS) + 0.5 * sWeights.get(ClothingType.Value.SHIRT)); // Half dress - half shirt
        sWeights.put(ClothingType.Value.T_SHIRT, sWeights.get(ClothingType.Value.SHIRT));
        sWeights.put(ClothingType.Value.JUMPER, 0.8 * sWeights.get(ClothingType.Value.SWEATSHIRT) + 0.2 * sWeights.get(ClothingType.Value.SHIRT)); // 80% Sweatshirt 20 % shirt


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
