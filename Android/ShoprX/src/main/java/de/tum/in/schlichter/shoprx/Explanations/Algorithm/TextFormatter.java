package de.tum.in.schlichter.shoprx.Explanations.Algorithm;

import java.util.List;

import de.tum.in.schlichter.shoprx.algorithm.model.Attributes.Attribute;

/**
 * Created by Nicksteal on 24.03.2015.
 */
public interface TextFormatter {
    public CharSequence fromHtml(String html);

    public CharSequence renderSimpleClickable(AttributeText attributeText);

    public CharSequence renderClickable(AttributeText attributeText);

    public CharSequence renderClickable(CharSequence originalText, Attribute attribute, String toRender);

    public CharSequence concat(CharSequence... text);

    public class AttributeText {
        private CharSequence originalText;
        private Attribute attribute;
        private List<String> replacableTexts;

        public AttributeText(CharSequence originalText, Attribute attribute, List<String> replacableTexts) {
            this.originalText = originalText;
            this.attribute = attribute;
            this.replacableTexts = replacableTexts;
        }

        public CharSequence originalText() {
            return originalText;
        }

        public Attribute attribute() {
            return attribute;
        }

        public List<String> replacableTexts() {
            return replacableTexts;
        }
    }
}