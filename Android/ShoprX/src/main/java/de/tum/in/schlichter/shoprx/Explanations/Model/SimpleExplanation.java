package de.tum.in.schlichter.shoprx.Explanations.Model;

/**
 * Created by Nicksteal on 28.03.2015.
 */
public class SimpleExplanation {
    private String text;
    private IconType iconType;


    public enum IconType{
        PRICE,
        COLOR,
        LABEL,
        TYPE,
        SOME_CONTEXT,
        RANDOM,
        WEATHER,
        TEMPERATURE
    }


    public SimpleExplanation (Dimension dimension){
        this.text = dimension.attribute().id();
        if (dimension.attribute().id().equalsIgnoreCase("color")){
            this.iconType = IconType.COLOR;
        }
        else if (dimension.attribute().id().equalsIgnoreCase("clothing-type")){
            this.iconType = IconType.TYPE;
        }
        else if (dimension.attribute().id().equalsIgnoreCase("label")){
            this.iconType = IconType.LABEL;
        }
        else if (dimension.attribute().id().equalsIgnoreCase("price")){
            this.iconType = IconType.PRICE;
        }

    }

    public SimpleExplanation (String text, IconType iconType){
        this.text = text;
        this.iconType = iconType;
    }
    public String getText() {
        return text;
    }

    public IconType getIconType() {
        return iconType;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setIconType(IconType iconType) {
        this.iconType = iconType;
    }
}
