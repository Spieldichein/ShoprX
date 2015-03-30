package de.tum.in.schlichter.shoprx.Explanations.Model;

/**
 * Created by Nicksteal on 23.03.2015.
 */
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Set;

import de.tum.in.schlichter.shoprx.algorithm.model.Item;


public class AbstractExplanation {
    private Item item;
    private Set<DimensionArgument> primaryArguments;
    private Set<DimensionArgument> supportingArguments;
    private Set<ContextArgument> contextArguments;
    private Set<DimensionArgument> negativeArguments;
    private Category category;

    public AbstractExplanation(Item item) {
        this.item = item;
        primaryArguments = new LinkedHashSet<DimensionArgument>();
        supportingArguments = new LinkedHashSet<DimensionArgument>();
        contextArguments = new LinkedHashSet<ContextArgument>();
        negativeArguments = new LinkedHashSet<DimensionArgument>();
    }

    public AbstractExplanation category(Category category) {
        this.category = category;
        return this;
    }

    public Category category() {
        return category;
    }

    public Set<DimensionArgument> primaryArguments() {
        return primaryArguments;
    }

    public Set<DimensionArgument> supportingArguments() {
        return supportingArguments;
    }

    public Set<ContextArgument> contextArguments() {
        return contextArguments;
    }

    public Set<DimensionArgument> negativeArguments() {
        return negativeArguments;
    }

    public DimensionArgument mainArgument() {
        if (primaryArguments.iterator().hasNext())
            return primaryArguments.iterator().next();
        else
            return null;
    }

    public void addPrimaryArgument(DimensionArgument argument) {
        primaryArguments.add(argument);
    }

    public void addPrimaryArguments(Collection<DimensionArgument> arguments) {
        primaryArguments.addAll(arguments);
    }

    public void addNegativeArguments(Collection<DimensionArgument> arguments) {
        negativeArguments.addAll(arguments);
    }

    public void addSupportingArgument(DimensionArgument argument) {
        supportingArguments.add(argument);
    }

    public void addSupportingArguments(Collection<DimensionArgument> arguments) {
        supportingArguments.addAll(arguments);
    }

    public void addContextArgument(ContextArgument argument) {
        contextArguments.add(argument);
    }

    public void addContextArguments(Collection<ContextArgument> arguments) {
        contextArguments.addAll(arguments);
    }

    public boolean hasPrimaryArguments() {
        return primaryArguments.size() > 0;
    }

    public boolean hasSupportingArguments() {
        return supportingArguments.size() > 0;
    }

    public boolean hasContextArguments() {
        return contextArguments.size() > 0;
    }

    public Item item() {
        return item;
    }

    public enum Category {
        BY_STRONG_ARGUMENTS,
        BY_WEAK_ARGUMENTS,
        BY_GOOD_AVERAGE,
        BY_SERENDIPITOUSITY,
        BY_LAST_CRITIQUE
    }
}