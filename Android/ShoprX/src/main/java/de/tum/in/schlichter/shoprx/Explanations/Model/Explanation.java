package de.tum.in.schlichter.shoprx.Explanations.Model;

/**
 * Created by Nicksteal on 23.03.2015.
 */
public class Explanation {
private AbstractExplanation abstractExplanation;
private SimpleExplanation[] simpleExplanations;

    public AbstractExplanation getAbstractExplanation() {
        return abstractExplanation;
    }

    public void setAbstractExplanation(AbstractExplanation abstractExplanation) {
        this.abstractExplanation = abstractExplanation;
    }

    public SimpleExplanation[] getSimpleExplanations() {
        return simpleExplanations;
    }

    public void setSimpleExplanations(SimpleExplanation[] simpleExplanations) {
        this.simpleExplanations = simpleExplanations;
    }
}
