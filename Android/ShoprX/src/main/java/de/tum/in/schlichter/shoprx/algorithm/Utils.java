
package de.tum.in.schlichter.shoprx.algorithm;

import android.util.Log;

import java.util.Collections;
import java.util.List;

import de.tum.in.schlichter.shoprx.algorithm.model.Item;
import de.tum.in.schlichter.shoprx.context.algorithm.ContextualPreFiltering;
import de.tum.in.schlichter.shoprx.context.model.ScenarioContext;
import de.tum.in.schlichter.shoprx.stereotype.algorithm.StereotypeFiltering;
import de.tum.in.schlichter.shoprx.stereotype.user.User;

public class Utils {

    /**
     * Returns a subset of the overall case base filtered by hard-limits like
     * location, availability and opening hours. <br/>
     * In the extended version (not for the baseline) it also returns items that were filtered by the stereotype settings of the user.
     */
    public static List<Item> getLimitedCaseBase(List<Item> wholeCaseBase) {
        List<Item> cases;

        //Pre-filter based on the current active scenario context.
        long start = System.currentTimeMillis();
        ScenarioContext scenarioContext = ScenarioContext.getACopy();
        cases = ContextualPreFiltering.filterShops(wholeCaseBase, scenarioContext);
        //Write the relaxations to the real instance
        ScenarioContext.getInstance().setRelaxations(scenarioContext.getRelaxations());
        Log.d("Context pre-filtering", ""+ (System.currentTimeMillis() - start) + " ms");

        //Do the calculation of stereotype proximity.
        start = System.currentTimeMillis();
        try {
            User u = User.getUser();
            if (u.hasStereotype()) {
                StereotypeFiltering stereotypeFiltering = new StereotypeFiltering();
                cases = stereotypeFiltering.computeStereotypeProximity(u.getStereotype(), cases);
            }
        } catch (RuntimeException re){
            Log.d("User", "User object not initialized.");
        }
        Log.d("Stereotype filtering", ""+ (System.currentTimeMillis() - start) + " ms");

        return cases;
    }

    /**
     * Sorts items by similarity to query using the Sim function: sim(query,
     * item of caseBase).
     */
    public static List<Item> sortBySimilarityToQuery(Query query, List<Item> caseBase) {
        // Limit the case base (PRE-FILTERING)
        Log.d("CB before limiting", ""+caseBase.size());
        caseBase = getLimitedCaseBase(caseBase);
        Log.d("CB after limiting", ""+caseBase.size());

        long start = System.currentTimeMillis();
        // calculate similarity value for each item
        for (Item item : caseBase) {
            //Log.d("Item", item.toString());
            item.querySimilarity(AdaptiveSelectionSimilarity.similarity(query.attributes(), item.attributes()));
        }
        Log.d("AdaptiveSelection", ""+ (System.currentTimeMillis() - start) + " ms");

        // sort highest similarity first
        Collections.sort(caseBase, new AdaptiveSelectionComparator());

        return caseBase;
    }

    public static void dumpToConsole(List<Item> cases, Query query) {
        if (query.attributes() != null) {
            System.out.println("Query " + query.attributes().getAllAttributesString());
        } else {
            System.out.println("Query EMPTY");
        }
        for (int i = 0; i < cases.size(); i++) {
            Item item = cases.get(i);
            Log.d("[" + i + "] Item", "" + item.attributes().getAllAttributesString()
                            + " sim " + item.querySimilarity()
                            + " qual " + item.quality()
                            + " stereo " + item.getProximityToStereotype());
        }
    }

}
