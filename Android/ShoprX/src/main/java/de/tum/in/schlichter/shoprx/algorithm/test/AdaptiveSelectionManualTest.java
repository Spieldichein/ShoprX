package de.tum.in.schlichter.shoprx.algorithm.test;

/**
 * Created by Yannick on 04.02.15.
 */
public class AdaptiveSelectionManualTest {

    private static final boolean DUMP_INVENTORY = false;
    /**
     * **DO NOT USE** Only for internal testing.
     */
//    public static void main(String[] args) {
//        adaptiveSelection();
//    }

    /**
     * **DO NOT USE** Only for testing of the Adaptive Selection cycle using a
     * console program.
     */
    /*private static void adaptiveSelection() {
        Query query = new Query();
        Critique critique = new Critique();
        critique.item(null);

        /*
         * The caseBase will later be stored in a database (due to its size).
         * Think about optimizations which could be applied.
         */
    // Filter case-base to match hard-limits (location, opening hours)
    //List<Item> caseBase = Utils.getLimitedCaseBase(mCaseBase);

    // dump database
//        if (DUMP_INVENTORY) {
//            dumpInventory(query, caseBase);
//        }
//
//        while (true) {
//            List<Item> recommendations = itemRecommend(caseBase, query,
//                    NUM_RECOMMENDATIONS_DEFAULT, BOUND_DEFAULT,
//                    critique);
//            critique = userReview(recommendations, query);
//            queryRevise(query, critique);
//        }
//    }

    /*private static void dumpInventory(Query query, List<Item> caseBase) {
        System.out.println("*** START INVENTORY DUMP ***");
        Utils.dumpToConsole(caseBase, query);
        System.out.println("*** DONE INVENTORY DUMP ***");
    }*/


    /**
     * Takes the list of recommended items and elicits a critique on a feature
     * of one item from the user. Returns the liked/disliked item and which
     * feature value (! not just which feature !) was liked/disliked.
     */
    /*private static Critique userReview(List<Item> recommendations, Query query) {
        Utils.dumpToConsole(recommendations, query);

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        System.out.print("Enter which item to critique: ");
        int selection;
        try {
            selection = Integer.valueOf(in.readLine());

            System.out.print("Like (1) or Dislike(0)? ");
            boolean isPositiveCritique = Integer.valueOf(in.readLine()) == 1;

            // Does only critique one item at a time.
            System.out.print("Color (0), type (1), label(2), price(3)? Enter , separated: ");
            String input = in.readLine();
            String[] attrs = input.split(",");
            Feedback feedback = new Feedback();
            for (String i : attrs) {
                switch (Integer.valueOf(i)) {
                    case 0:
                        feedback.addAttributes(new Color());
                        break;
                    case 1:
                        feedback.addAttributes(new ClothingType());
                        break;
                    case 2:
                        feedback.addAttributes(new Label());
                        break;
                    case 3:
                        feedback.addAttributes(new Price());
                        break;
                }
            }

            Critique critique = new Critique();
            critique.item(recommendations.get(selection));
            critique.feedback(feedback.isPositiveFeedback(isPositiveCritique));

            return critique;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }*/
}
