
package de.tum.in.schlichter.shoprx.algorithm;

import java.util.ArrayList;
import java.util.List;

import de.tum.in.schlichter.shoprx.algorithm.model.Attributes;
import de.tum.in.schlichter.shoprx.algorithm.model.Item;
import de.tum.in.schlichter.shoprx.context.algorithm.ContextualPostFiltering;

public class AdaptiveSelection {

    private static final int NUM_RECOMMENDATIONS_DEFAULT = 8;
    private static final int BOUND_DEFAULT = 10;
    private static final int NUM_RECOMMENDATIONS_PRESELECTION = 30;

    private static AdaptiveSelection _instance;

    public static synchronized AdaptiveSelection get() {
        if (_instance == null) {
            _instance = new AdaptiveSelection();
        }
        return _instance;
    }

    private List<Item> mCaseBase;
    private Query mQuery;
    private Critique mCurrentCritique;
    private int mNumRecommendations;
    private List<Item> mCurrentRecommendations;

    private AdaptiveSelection() {
        mCaseBase = new ArrayList<Item>();
        mQuery = new Query();
        mNumRecommendations = NUM_RECOMMENDATIONS_DEFAULT;
        mCurrentRecommendations = new ArrayList<Item>();
    }

    /**
     * Call before {@link #getRecommendations()} to set the initial data
     * set. Resets the query.
     */
    public void setInitialCaseBase(List<Item> caseBase) {
        mQuery = new Query();
        mCurrentCritique = null;
        mCaseBase = caseBase;
    }

    /**
     * The maximum number of recommendations to return. Does not count the
     * carried item!
     */
    public void setMaxRecommendations(int limit) {
        mNumRecommendations = Math.max(1, limit);
    }

    /**
     * Returns a sorted list of recommendations based on the current query which
     * itself is shaped through passed critiques. The first time you may pass
     * {@code null} for the critique to get an initial set of diverse
     * recommendations.
     */
    public List<Item> getRecommendations() {
        // build a new set of recommendations
        List<Item> recommendations;
        // using adaptive selection (diversity on negative progress)
        recommendations = itemRecommend(mCaseBase, mQuery, mNumRecommendations, BOUND_DEFAULT, mCurrentCritique, NUM_RECOMMENDATIONS_PRESELECTION);

        mCurrentRecommendations = recommendations;

        return recommendations;
    }

    public List<Item> getCurrentRecommendations() {
        return mCurrentRecommendations;
    }

    /**
     * Updates the item query, will influence the next set of recommendations.
     * Call {@link #getRecommendations()} afterwards.
     */
    public void submitCritique(Critique critique) {
        // Update the current query with the new critique
        if (critique != null) {
            queryRevise(mQuery, critique);
            mCurrentCritique = critique;
        }
    }

    /**
     * Returns the last critiqued item. May be {@code null}.
     */
    public Item getLastCritiquedItem() {
        if (mCurrentCritique != null) {
            return mCurrentCritique.item();
        }
        return null;
    }

    public Query getCurrentQuery() {
        return mQuery;
    }

    /**
     * Takes the current query, number of recommended items to return, the last
     * critique. Returns a list of recommended items based on the case-base.
     * @param numItemsPreSelection Determines how many items should be part of the initial algorithm to
     *                            be filtered against the context scenario.
     */
    private static List<Item> itemRecommend(List<Item> caseBase, Query query, int numItems, int bound, Critique lastCritique, int numItemsPreSelection) {

        List<Item> recommendations = new ArrayList<Item>();

        if ( lastCritique != null && lastCritique.item() != null
                && lastCritique.feedback().isPositiveFeedback()) {
            /*
             * Positive progress: user liked one or more features of one of the
             * recommended items.
             *
             * REFINE: Show similar recommendations by sorting the case-base in
             * decreasing similarity to current query. Return top k items.
             */
            caseBase = Utils.sortBySimilarityToQuery(query, caseBase);
            for (int i = 0; i < numItemsPreSelection; i++) {
                recommendations.add(caseBase.get(i));
            }
        } else {
            /*
             * Negative progress: user disliked one or more of the features of
             * one recommended item. Or: first run.
             * REFOCUS: show diverse recommendations
             */
            recommendations = BoundedGreedySelection.boundedGreedySelection(query, caseBase, numItemsPreSelection, bound);
        }

//        Utils.dumpToConsole(recommendations, query);

        recommendations = ContextualPostFiltering.postFilterItems(recommendations, numItems);

        Utils.dumpToConsole(recommendations, query);

        // Carry the critiqued so the user may critique it further.
        if (lastCritique != null && lastCritique.item() != null) {
            // check if it is already in the list
            boolean isAlreadyPresent = false;
            for (Item item : recommendations) {
                if (item.id() == lastCritique.item().id()) {
                    isAlreadyPresent = true;
                    break;
                }
            }
            // if not: replace the last one with it
            if (!isAlreadyPresent) {
                recommendations.remove(recommendations.size() - 1);
                recommendations.add(lastCritique.item());
            }
        }

        return recommendations;
    }

    /**
     * Takes a liked/disliked item, which feature value was liked/disliked, the
     * current query. Returns a new query modified according to the given user
     * critique.
     */
    private static void queryRevise(Query query, Critique critique) {
        List<Attributes.Attribute> attributes = critique.feedback().attributes();
        for (Attributes.Attribute attribute : attributes) {
            critique.item().attributes().getAttributeById(attribute.id())
                    .critiqueQuery(query, critique.feedback().isPositiveFeedback());
        }
    }
}
