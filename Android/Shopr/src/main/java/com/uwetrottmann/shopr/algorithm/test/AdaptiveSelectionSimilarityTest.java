
package com.uwetrottmann.shopr.algorithm.test;

import com.uwetrottmann.shopr.algorithm.AdaptiveSelectionSimilarity;
import com.uwetrottmann.shopr.algorithm.model.Attributes;
import com.uwetrottmann.shopr.algorithm.model.ClothingType;
import com.uwetrottmann.shopr.algorithm.model.Color;

import org.junit.Test;

import static org.fest.assertions.api.Assertions.assertThat;

public class AdaptiveSelectionSimilarityTest {

    @Test
    public void testSimilarity() {
        Attributes a1 = new Attributes();
        a1.putAttribute(new Color(Color.Value.RED));
        a1.putAttribute(new ClothingType(ClothingType.Value.DRESS));

        Attributes a2 = new Attributes();
        a2.putAttribute(new Color(Color.Value.BLACK));

        // different color
        assertThat(AdaptiveSelectionSimilarity.similarity(a1, a2)).isEqualTo(0.0);

        // same color
        a1.putAttribute(new Color(Color.Value.BLACK));
        assertThat(AdaptiveSelectionSimilarity.similarity(a1, a2)).isEqualTo(1.0);

        // return 0 on no comparable attributes
        Attributes a3 = new Attributes();
        a3.putAttribute(new ClothingType(ClothingType.Value.DRESS));
        assertThat(AdaptiveSelectionSimilarity.similarity(a3, a2)).isEqualTo(0.0);

        // different color, same type
        a1.putAttribute(new Color(Color.Value.RED));
        a2.putAttribute(new ClothingType(ClothingType.Value.DRESS));
        assertThat(AdaptiveSelectionSimilarity.similarity(a1, a2)).isEqualTo(0.5);
    }

}
