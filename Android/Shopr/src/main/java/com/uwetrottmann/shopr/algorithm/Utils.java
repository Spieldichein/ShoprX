
package com.uwetrottmann.shopr.algorithm;

import android.util.Log;

import com.uwetrottmann.shopr.algorithm.model.Item;
import com.uwetrottmann.shopr.stereotype.user.User;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Utils {

    /**
     * Returns a subset of the overall case base filtered by hard-limits like
     * location, availability and opening hours.<br>
     * Currently only returns sample items.
     */
    public static List<Item> getLimitedCaseBase(List<Item> wholeCaseBase) {
        ArrayList<Item> cases = new ArrayList<Item>();

        //Get the user settings from the stereotype matching
        User user;
        try {
            user = User.getUser();
        } catch (RuntimeException e){
            Log.d("User object null", "Returning whole case base");
            return wholeCaseBase;
        }


        Log.d("User sex", user.getSex().toString());

        //Filter out all that do not match the specified Sex of the user.
        for (Item item : wholeCaseBase){
            if (item.getSex().currentValue().equals(user.getSex())){ // Sex matches
                cases.add(item);
            }
        }

        return cases;
    }

    /**
     * Sorts items by similarity to query using the Sim function: sim(query,
     * item of caseBase).
     */
    public static List<Item> sortBySimilarityToQuery(Query query, List<Item> caseBase) {
        // Limit the case base (PRE-FILTERING)
        Log.d("Case Base before", caseBase.toString());
        caseBase = getLimitedCaseBase(caseBase);
        Log.d("Case Base after", caseBase.toString());

        // calculate similarity value for each item
        for (Item item : caseBase) {
            //Log.d("Item", item.toString());
            item.querySimilarity(Similarity.similarity(query.attributes(), item.attributes()));
        }

        // sort highest similarity first
        Collections.sort(caseBase, new Comparator<Item>() {
            @Override
            public int compare(Item o1, Item o2) {
                // is o2 smaller?
                if (o1.querySimilarity() > o2.querySimilarity()) {
                    return -1;
                }
                // is o2 bigger?
                if (o1.querySimilarity() < o2.querySimilarity()) {
                    return 1;
                }
                // they are equal!
                return 0;
            }
        });

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
                            + " qual " + item.quality());
        }
    }

}
