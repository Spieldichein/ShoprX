
package de.tum.in.schlichter.shoprx.algorithm;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import de.tum.in.schlichter.shoprx.algorithm.model.Item;

public class BoundedGreedySelection {

    public static final double ALPHA = 0.95;

    /**
     * Chooses <code>bound*limit</code> items most similar to current query.
     * Returns <code>limit</code> items most similar to query and most
     * dissimilar to already selected items out of those.<br>
     * If there are less items than required, will still return (less)
     * recommendations.
     */
    public static List<Item> boundedGreedySelection(Query query, List<Item> caseBase, int limit, int bound) {
        caseBase = Utils.sortBySimilarityToQuery(query, caseBase);

        // Get first b*k items
        int numItems = limit * bound;
        numItems = Math.min(numItems, caseBase.size());

        List<Item> limitedCaseBase = new ArrayList<Item>();
        for (int i = 0; i < numItems; i++) {
            limitedCaseBase.add(caseBase.get(i));
        }

        // add recommendations
        int numRecs = Math.min(limit, limitedCaseBase.size());

        List<Item> recommendations = new ArrayList<Item>();
        for (int i = 0; i < numRecs; i++) {
            sortByQuality(limitedCaseBase, recommendations);

            // get top item, remove it from remaining cases
            recommendations.add(limitedCaseBase.remove(0));
        }

        return recommendations;
    }

    /**
     * Calculates the quality for each item, sorts the case base with highest
     * quality first.
     */
    private static void sortByQuality(List<Item> caseBase, List<Item> recommendations) {
        // Calculate current quality
        for (Item item : caseBase) {
            item.quality(ALPHA * item.querySimilarity() + (1 - ALPHA) * relativeDiversity(item, recommendations));
        }

        // sort by highest quality first
        Collections.sort(caseBase, new BoundedGreedyComparator());
    }

    /**
     * Calculates the relative diversity of an item to the current list of
     * recommendations.
     */
    private static double relativeDiversity(Item item, List<Item> recommendations) {
        if (recommendations.size() == 0) {
            // default to 1 for R={}
            return 1;
        }

        double similarity = 0;
        for (Item recommendation : recommendations) {
            similarity += 1 - AdaptiveSelectionSimilarity.similarity(item.attributes(), recommendation.attributes());
        }
        similarity /= recommendations.size();

        return similarity;
    }

}
