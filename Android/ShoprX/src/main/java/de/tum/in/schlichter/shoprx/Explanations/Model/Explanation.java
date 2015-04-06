package de.tum.in.schlichter.shoprx.Explanations.Model;

import android.util.Log;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by Nicksteal on 23.03.2015.
 */
public class Explanation {
private AbstractExplanation abstractExplanation;
private ArrayList<SimpleExplanation> simpleExplanations;
    private CharSequence simpleReason;


    public Explanation(){
        simpleExplanations = new ArrayList<SimpleExplanation>();
    }
    public AbstractExplanation getAbstractExplanation() {
        return abstractExplanation;
    }

    public void setAbstractExplanation(AbstractExplanation abstractExplanation) {
        this.abstractExplanation = abstractExplanation;
    }

    public ArrayList<SimpleExplanation> getSimpleExplanations() {
        return simpleExplanations;
    }

    public void setSimpleExplanations(ArrayList<SimpleExplanation> simpleExplanations) {
        this.simpleExplanations = simpleExplanations;
    }


    private Collection<CharSequence> positiveReasons = new ArrayList<CharSequence>();
    private Collection<CharSequence> negativeReasons = new ArrayList<CharSequence>();

    public Explanation addPositiveReason(CharSequence positive) {
        positiveReasons.add(positive);
        return this;
    }

    public Collection<CharSequence> positiveReasons() {
        return positiveReasons;
    }

    public Explanation addNegativeReason(CharSequence negative) {
        negativeReasons.add(negative);
        return this;
    }

    public Collection<CharSequence> negativeReasons() {
        return negativeReasons;
    }

    public Explanation simple(CharSequence simpleReason) {
        this.simpleReason = simpleReason;
        return this;
    }

    public CharSequence simple() {
        return simpleReason;
    }

    public void transformStuff(){
        for (int i=0; i<positiveReasons.size();i++){
            Log.d("explanation",positiveReasons.toArray()[i].toString());

        }
    }


}
