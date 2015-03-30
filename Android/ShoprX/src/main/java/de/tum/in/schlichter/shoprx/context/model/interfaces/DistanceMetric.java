package de.tum.in.schlichter.shoprx.context.model.interfaces;

import de.tum.in.schlichter.shoprx.algorithm.model.ClothingType;
import de.tum.in.schlichter.shoprx.context.model.ScenarioContext;

/**
 * Created by Yannick on 20.02.15.
 *
 * This interface serves as a basis for the enums used in the context scenario. It forces the
 * enums to implement basic information such as whether they are numeric or not.
 */
public interface DistanceMetric {

    /**
     * This method returns whether this context factor can be calculated with the euclidean distance metric
     * or whether it does need another distance metric
     * @return true if the euclidean distance can be used
     */
    public boolean isMetricWithEuclideanDistance();

    /**
     * This method returns the weight which should be assigned to the given context metric.
     * @return a double stating the weight of the given distance metric, between 0 and 1.
     */
    public double getWeight(ClothingType clothingType);

    /**
     * This method computes the distance to the scenario context and returns this distance as a double value.
     * @param scenarioContext the current context as set by the user
     * @return a double containing the distance to the currently active context
     * @throws UnsupportedOperationException In case that this method is not supported by this enum.
     */
    public double distanceToContext(ScenarioContext scenarioContext) throws UnsupportedOperationException;

    /**
     * This method is used to retrieve the number of {@link Enum} entries.
     * @return the number of enums within this class (the range)
     * @throws UnsupportedOperationException if this is not applicable
     */
    public int numberOfItems();

    /**
     * This method returns the current ordinal.
     * @return an integer representing the position within the enum
     * @throws UnsupportedOperationException if this method is not applicable
     */
    public int currentOrdinal();
}
