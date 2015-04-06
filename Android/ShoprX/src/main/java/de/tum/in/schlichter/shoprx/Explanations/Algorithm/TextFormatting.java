package de.tum.in.schlichter.shoprx.Explanations.Algorithm;

import java.util.List;
import java.util.Locale;

import de.tum.in.schlichter.shoprx.algorithm.model.Attributes;

/**
 * Created by Nicksteal on 01.04.2015.
 */
public class TextFormatting  {

    public static String textify(ExplanationLocalizer localizer, List<String> elements) {
        String and = localizer.getAndString();

        if (elements.size() == 0) {
            return "";
        } else if (elements.size() == 1) {
            return elements.get(0);
        } else if (elements.size() == 2) {
            String first = elements.get(0);
            String second = elements.get(1);
            return first + and + second;
        } else {
            String last = elements.get(elements.size() - 1);
            List<String> headList = elements.subList(0, elements.size() - 1);
            return commaSeperated(localizer, headList) + and + last;
        }
    }

    private static String commaSeperated(ExplanationLocalizer localizer, List<String> elements) {
        String comma = localizer.getCommaString();

        if (elements.size() == 0) {
            return "";
        } else if (elements.size() == 1) {
            return elements.get(0);
        } else if (elements.size() == 2) {
            String first = elements.get(0);
            String second = elements.get(1);
            return first + comma + second;
        } else {
            String last = elements.get(elements.size() - 1);
            List<String> headList = elements.subList(0, elements.size() - 1);
            return commaSeperated(localizer, headList) + comma + last;
        }
    }

    public static String textOf(ExplanationLocalizer localizer, Attributes.AttributeValue value) {
        return textOf(localizer, value.descriptor());
    }

    public static String textOf(ExplanationLocalizer localizer, String text) {
        return localizer.getLocalizedValueDescriptor(text).toLowerCase(Locale.ENGLISH);
    }
}
