
package de.tum.in.schlichter.shoprx.algorithm;

import de.tum.in.schlichter.shoprx.algorithm.model.Item;

public class Critique {

    private Item item;
    private Feedback feedback;

    public Item item() {
        return item;
    }

    public Critique item(Item item) {
        this.item = item;
        return this;
    }

    public Feedback feedback() {
        return feedback;
    }

    public Critique feedback(Feedback feedback) {
        this.feedback = feedback;
        return this;
    }

}
