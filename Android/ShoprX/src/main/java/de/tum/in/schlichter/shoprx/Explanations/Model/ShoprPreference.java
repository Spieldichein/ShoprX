package de.tum.in.schlichter.shoprx.Explanations.Model;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import de.tum.in.schlichter.shoprx.algorithm.model.Attributes.Attribute;
import de.tum.in.schlichter.shoprx.algorithm.model.Attributes.AttributeValue;

/**
 * Created by Nicksteal on 06.04.2015.
 */
public class ShoprPreference {

    private Attribute attribute;
    private Set<AttributeValue> attributeValues = new HashSet<AttributeValue>();

    public ShoprPreference(Attribute attribute) {
        this.attribute = attribute;
    }

    public Attribute attribute() {
        return attribute;
    }

    public ShoprPreference attribute(Attribute attribute) {
        this.attribute = attribute;
        return this;
    }

    public Set<AttributeValue> attributeValues() {
        return attributeValues;
    }

    public ShoprPreference add(AttributeValue value) {
        attributeValues.add(value);
        return this;
    }

    public ShoprPreference add(AttributeValue... values) {
        attributeValues.addAll(Arrays.asList(values));
        return this;
    }
}