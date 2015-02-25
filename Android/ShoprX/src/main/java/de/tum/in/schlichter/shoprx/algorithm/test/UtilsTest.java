
package de.tum.in.schlichter.shoprx.algorithm.test;

import org.junit.Test;

import java.util.ArrayList;

import de.tum.in.schlichter.shoprx.algorithm.Query;
import de.tum.in.schlichter.shoprx.algorithm.Utils;
import de.tum.in.schlichter.shoprx.algorithm.model.Attributes;
import de.tum.in.schlichter.shoprx.algorithm.model.Color;
import de.tum.in.schlichter.shoprx.algorithm.model.Item;

import static org.fest.assertions.api.Assertions.assertThat;

public class UtilsTest {

    @Test
    public void testSortBySimilarityToQuery() {
        // unsorted list of items
        ArrayList<Item> unsortedCases = new ArrayList<Item>();
        unsortedCases
                .
                add(new Item().attributes(new Attributes().putAttribute(new Color(Color.Value.RED))));
        unsortedCases.
                add(new Item().attributes(new Attributes()
                        .putAttribute(new Color(Color.Value.BLACK))));
        unsortedCases
                .add(new Item().attributes(new Attributes()
                        .putAttribute(new Color(Color.Value.RED))));
        unsortedCases
                .add(new Item().attributes(new Attributes().putAttribute(new Color(
                        Color.Value.GREEN))));
        unsortedCases
                .add(new Item().attributes(new Attributes()
                        .putAttribute(new Color(Color.Value.BLUE))));

        // sample query
        Query query = new Query();
        query.attributes().putAttribute(new Color(Color.Value.RED));

        Utils.sortBySimilarityToQuery(query, unsortedCases);

        // top two items are red?
        Utils.dumpToConsole(unsortedCases, query);
        assertThat(
                unsortedCases.get(0).attributes().getAttributeById(Color.ID).getValueWeights()[Color.Value.RED
                        .ordinal()]).isEqualTo(1.0);
        assertThat(
                unsortedCases.get(1).attributes().getAttributeById(Color.ID).getValueWeights()[Color.Value.RED
                        .ordinal()]).isEqualTo(1.0);

        // equal weight on all colors
        query.attributes().putAttribute(new Color());

        Utils.sortBySimilarityToQuery(query, unsortedCases);

        // nothing should have changed
        Utils.dumpToConsole(unsortedCases, query);
        assertThat(
                unsortedCases.get(0).attributes().getAttributeById(Color.ID).getValueWeights()[Color.Value.RED
                        .ordinal()]).isEqualTo(1.0);
        assertThat(
                unsortedCases.get(1).attributes().getAttributeById(Color.ID).getValueWeights()[Color.Value.RED
                        .ordinal()]).isEqualTo(1.0);
    }

}
