package de.tum.in.schlichter.shoprx.Explanations.Algorithm;

import android.util.Log;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import de.tum.in.schlichter.shoprx.Explanations.Model.AbstractExplanation;
import de.tum.in.schlichter.shoprx.Explanations.Model.Argument;
import de.tum.in.schlichter.shoprx.Explanations.Model.Context;
import de.tum.in.schlichter.shoprx.Explanations.Model.ContextArgument;
import de.tum.in.schlichter.shoprx.Explanations.Model.Dimension;
import de.tum.in.schlichter.shoprx.Explanations.Model.DimensionArgument;
import de.tum.in.schlichter.shoprx.algorithm.AdaptiveSelection;
import de.tum.in.schlichter.shoprx.algorithm.Query;
import de.tum.in.schlichter.shoprx.algorithm.model.Attributes.Attribute;
import de.tum.in.schlichter.shoprx.algorithm.model.Item;

/**
 * Created by Nicksteal on 23.03.2015.
 */
public class ArgumentGenerator {
    // Î± - compares explanation score
    public static double ALPHA = 3;//0.11;// 0.6;
    // Âµ - second criteria for explanation score (Âµ < Î±)
    public static double MU = 1.99;//0.09;// 0.51;
    // Î² - compares global score
    public static double BETA = 1.5;
    // Î³ - compares desired lowest information score
    public static double GAMMA = 0.5;
    // ÆŸ - compares the information score for negative arguments
    public static double TETA = 0.03;

    public AbstractExplanation select(Item item, Query query,
                                      List<Item> recommendedItems, List<Context> contexts) {

        AbstractExplanation explanation = new AbstractExplanation(item);
        List<DimensionArgument> sortedInitialArguments = generateSortedInitialArguments(
                item, query, recommendedItems);

        for (DimensionArgument dimensionArgument: sortedInitialArguments){
            Log.d("explanationscores",""+dimensionArgument.dimension().attribute().id()+" eS: "+dimensionArgument.dimension().explanationScore()+" iS; "+dimensionArgument.dimension().informationScore());
        }
        List<DimensionArgument> strongPrimaryArguments = filterBy(
                sortedInitialArguments, new StrongPrimaryArgumentFilter());

        List<DimensionArgument> weakPrimaryArguments = filterBy(
                sortedInitialArguments, new WeakPrimaryArgumentFilter());

        /*List<DimensionArgument> negativeArguments = generateNegativeArguments(item, query, recommendedItems);

        if(negativeArguments.size() > 0) {
            explanation.addNegativeArguments(negativeArguments);
        }*/

        // Select context arguments
        for (Context context : contexts) {

            if (context.isValidArgument(item, recommendedItems))
                explanation.addContextArgument(new ContextArgument(context,
                        true));
        }

        // last critique tag
        int lastCritiquedId = AdaptiveSelection.get().getLastCritiquedItem() != null ?
                AdaptiveSelection.get().getLastCritiquedItem().id()
                : -1;


        if(item.id() == lastCritiquedId){
            explanation.category(AbstractExplanation.Category.BY_LAST_CRITIQUE);
        }
        else if (strongPrimaryArguments.size() > 0) {
            explanation.addPrimaryArguments(strongPrimaryArguments);
            explanation.category(AbstractExplanation.Category.BY_STRONG_ARGUMENTS);
        }
        // Dimension provides low information, attempt to add supporting
        // arguments
        else if (weakPrimaryArguments.size() > 0) {
            explanation.addPrimaryArguments(weakPrimaryArguments);
            explanation.addSupportingArguments(filterBy(sortedInitialArguments,
                    new SecondaryArgumentFilter()));
            explanation.category(AbstractExplanation.Category.BY_WEAK_ARGUMENTS);

        }
        // No dimension is larger than alpha(Î±), no argument can be selected
        else {
            // Item is only a good average
            if (Valuator.globalScore(item, query) > BETA) {
                explanation.addSupportingArgument(new DimensionArgument(
                        Argument.Type.GOOD_AVERAGE));
                explanation.addSupportingArguments(filterBy(
                        sortedInitialArguments, new SecondaryArgumentFilter()));
                explanation.category(AbstractExplanation.Category.BY_GOOD_AVERAGE);
            }
            // Recommender couldn't find better alternatives
            else {
                explanation.addSupportingArgument(new DimensionArgument(
                        Argument.Type.SERENDIPITOUS));
                explanation.category(AbstractExplanation.Category.BY_SERENDIPITOUSITY);
            }
        }

        return explanation;
    }
    /*
        public AbstractExplanation selectLegacy(Item item, Query query,
                List<Item> recommendedItems) {

            AbstractExplanation explanation = new AbstractExplanation(item);
            List<DimensionArgument> sortedInitialArguments = generateSortedInitialArguments(
                    item, query, recommendedItems);
            DimensionArgument bestInitialArgument = sortedInitialArguments.get(0);
            DimensionArgument secondBestInitialArgument = sortedInitialArguments
                    .get(1);

            // Dimension is good enough for a first argument
            if (bestInitialArgument.dimension().explanationScore() > ALPHA) {
                explanation.addPrimaryArgument(bestInitialArgument);

                double informationScore = Valuator.informationScore(item, query,
                        bestInitialArgument.dimension(), recommendedItems);
                bestInitialArgument.dimension().informationScore(informationScore);

                // Dimension provides low information, attempt to add a supporting
                // argument
                if (informationScore < GAMMA
                        && secondBestInitialArgument.dimension().explanationScore() > MU) {
                    explanation.addSupportingArgument(secondBestInitialArgument);
                }
            }
            // No dimension is larger than alpha(Î±), no argument can be selected
            else {
                // Item is only a good average
                if (Valuator.globalScore(item, query) > BETA) {
                    explanation.addSupportingArgument(new DimensionArgument(
                            Type.GOOD_AVERAGE));
                    if (secondBestInitialArgument.dimension().explanationScore() > MU) {
                        explanation
                                .addSupportingArgument(secondBestInitialArgument);
                    }
                }
                // Recommender couldn't find better alternatives
                else {
                    explanation.addSupportingArgument(new DimensionArgument(
                            Type.SERENDIPITOUS));
                }
            }

            return explanation;
        }
    */
    private List<DimensionArgument> generateSortedInitialArguments(Item item,
                                                                   Query query, List<Item> recommendedItems) {
        List<DimensionArgument> arguments = new ArrayList<DimensionArgument>();

        for (Attribute attribute : item.attributes().values()) {
            Dimension dimension = new Dimension(attribute);
            dimension.explanationScore(Valuator.explanationScore(item, query,
                    dimension));
            dimension.informationScore(Valuator.informationScore(item, query,
                    dimension, recommendedItems));
            DimensionArgument dimensionArgument = new DimensionArgument(dimension,true);
            arguments.add(dimensionArgument);
        }
        sortDesc(arguments);
        return arguments;
    }

    private List<DimensionArgument> generateNegativeArguments(Item item,
                                                              Query query, List<Item> recommendedItems) {
        List<DimensionArgument> arguments = new ArrayList<DimensionArgument>();

        for (Attribute attribute : item.attributes().values()) {
            Dimension dimension = new Dimension(attribute);
            dimension.explanationScore(Valuator.explanationScore(item, query,
                    dimension));

            if(dimension.explanationScore() < TETA)
                arguments.add(new DimensionArgument(dimension, false));
        }
        return arguments;
    }

    private void sortDesc(List<DimensionArgument> arguments) {
        Comparator<DimensionArgument> descComparator = new Comparator<DimensionArgument>() {

            @Override
            public int compare(DimensionArgument arg1, DimensionArgument arg2) {

                if (arg1.dimension().hybridScore() == arg2.dimension()
                        .hybridScore()) {
                    return 0;
                } else if (arg1.dimension().hybridScore() > arg2.dimension()
                        .hybridScore()) {
                    return -1;
                }
                return 1;
            }

        };

        Collections.sort(arguments, descComparator);
    }

    private <T> List<T> filterBy(List<T> elems, Filter<T> filter) {
        List<T> filtered = new ArrayList<T>();
        for (T elem : elems) {
            if (filter.applies(elem))
                filtered.add(elem);
        }
        return filtered;
    }

    public interface Filter<T> {
        public boolean applies(T elem);
    }

    public class SecondaryArgumentFilter implements Filter<DimensionArgument> {

        public final boolean applies(DimensionArgument arg) {
            return arg.dimension().explanationScore() > MU
                    && arg.dimension().explanationScore() <= ALPHA;
        }
    }

    public class WeakPrimaryArgumentFilter implements Filter<DimensionArgument> {

        public final boolean applies(DimensionArgument arg) {
            return arg.dimension().explanationScore() > ALPHA;
        }

    }

    public class StrongPrimaryArgumentFilter implements
            Filter<DimensionArgument> {

        public final boolean applies(DimensionArgument arg) {
            return arg.dimension().explanationScore() > ALPHA
                    && arg.dimension().informationScore() > GAMMA;
        }

    }

}