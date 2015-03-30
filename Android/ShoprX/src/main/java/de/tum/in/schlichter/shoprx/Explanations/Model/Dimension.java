package de.tum.in.schlichter.shoprx.Explanations.Model;

import de.tum.in.schlichter.shoprx.algorithm.model.Attributes.Attribute;

/**
 * Created by Nicksteal on 23.03.2015.
 */
public class Dimension {
    // A dimension relates to an attribute
    private Attribute attribute;
    private double explanationScore;
    private double informationScore;

    public Dimension(Attribute attribute) {
        this.attribute = attribute;
    }

    public double hybridScore() {
        return (explanationScore + informationScore) / 2;
    }

    public Attribute attribute() {
        return attribute;
    }

    public Dimension explanationScore(double explanationScore) {
        this.explanationScore = explanationScore;
        return this;
    }

    public double explanationScore() {
        return explanationScore;
    }

    public Dimension informationScore(double informationScore) {
        this.informationScore = informationScore;
        return this;
    }

    public double informationScore() {
        return informationScore;
    }

}