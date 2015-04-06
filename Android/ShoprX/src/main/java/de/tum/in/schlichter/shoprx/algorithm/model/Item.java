
package de.tum.in.schlichter.shoprx.algorithm.model;

import java.math.BigDecimal;
import java.util.Arrays;

import de.tum.in.schlichter.shoprx.Explanations.Model.Explanation;
import de.tum.in.schlichter.shoprx.context.model.ItemSelectedContext;

/**
 * Represents a item (e.g. clothing item), or one case in the case-base.
 */
public class Item {

    private int id;

    private String name;

    private BigDecimal price;

    private String image_url;

    private int shop_id;

    private Attributes attrs;

    private double querySimilarity;

    private double quality;

    private Sex sex;

    private double proximityToStereotype;

    private int mItemsInStock;

    private ItemSelectedContext mItemContext;

    private double mDistanceToContext;//Stores the calculated distance between the context and the item

    private int mTimesSelected; // How often was this item part of a context?

    private double mTemporaryQuality;

    private String[] mNameParts;

    private Explanation explanation;

    private boolean inFashion;

    public int id() {
        return id;
    }

    public Item id(int id) {
        explanation = new Explanation();
        this.id = id;
        return this;
    }

    public Explanation getExplanation(){
        return explanation;
    }
    public void setExplanation(Explanation explanation){
        this.explanation = explanation;
    }

    public String name() {
        return name;
    }

    public Item name(String name) {
        explanation = new Explanation();
        this.name = name;

        mNameParts = name.split("-");
        for (int i = 0; i < mNameParts.length; i++){
            mNameParts[i] = mNameParts[i].trim().toLowerCase();
            if (mNameParts[i].length() > 1) {
                mNameParts[i] = Character.toUpperCase(mNameParts[i].charAt(0)) + mNameParts[i].substring(1);
            } else {
                mNameParts[i] = "";
            }
        }

        return this;
    }

    /**
     * Gets an array with the parts of the name (strings that are part of the description)
     * @return an array with the parts of the name.
     */
    public String[] getNameParts(){
        String[] strings = Arrays.copyOf(mNameParts, mNameParts.length + 1);
        strings[strings.length -1 ] = attributes().getAttributeById(Color.ID).currentValue().descriptor();

        return strings;
    }

    public BigDecimal price() {
        return price;
    }

    public Item price(BigDecimal price) {
        explanation = new Explanation();
        this.price = price;
        return this;
    }

    public boolean isInFashion() {
        return inFashion;
    }

    public void setInFashion(boolean inFashion) {
        this.inFashion = inFashion;
    }

    public String image() {
        return image_url;
    }

    public Item image(String image_url) {
        explanation = new Explanation();
        this.image_url = image_url;
        return this;
    }

    public int shopId() {
        return shop_id;
    }

    public Item shopId(int shop_id) {
        this.shop_id = shop_id;
        return this;
    }

    public Attributes attributes() {
        return attrs;
    }

    public Item attributes(Attributes attrs) {
        explanation = new Explanation();
        this.attrs = attrs;
        return this;
    }

    public double querySimilarity() {
        return querySimilarity;
    }

    public Item querySimilarity(double querySimilarity) {
        this.querySimilarity = querySimilarity;
        return this;
    }

    public double quality() {
        return quality;
    }

    public Item quality(double quality) {
        explanation = new Explanation();
        this.quality = quality;
        return this;
    }

    public void setSex(Sex sex){
        this.sex = sex;
    }

    public Sex getSex(){
        return sex;
    }

    /**
     * Gets the proximity to the stereotype, meaning how similar this item and the stereotype are.
     * @return the proximity based on a previous calculation
     */
    public double getProximityToStereotype() {
        return proximityToStereotype;
    }

    /**
     * Sets the proximity to a stereotype of the user.
     * @param proximityToStereotype the proximity to the stereotype
     */
    public void setProximityToStereotype(double proximityToStereotype) {
        this.proximityToStereotype = proximityToStereotype;
    }

    /**
     * Method that sets the amount of items that are currently available in the given shop.
     * @param numberOfItems the number of items in stock.
     */
    public void setItemsInStock(int numberOfItems){
        mItemsInStock = numberOfItems;
    }

    public int getItemsInStock(){
        return mItemsInStock;
    }

    /**
     * Gets the context object, that states in which contexts the item has been selected how often.
     * @return The context object.
     */
    public ItemSelectedContext getItemContext() {
        return mItemContext;
    }

    /**
     * Sets a new Context Object which states in which contexts the item has been selected how often.
     * @param itemContext An item Context object that contains information about in which contexts the given item has been selected.
     */
    public void setItemContext(ItemSelectedContext itemContext) {
        this.mItemContext = itemContext;
    }

    /**
     * Get the distance to the active scenario context.
     * @return the distance
     */
    public double getDistanceToContext() {
        return mDistanceToContext;
    }

    /**
     * Sets the calculated distance to the currently active scenario.
     * @param mDistanceToContext the calculated distance
     */
    public void setDistanceToContext(double mDistanceToContext) {
        this.mDistanceToContext = mDistanceToContext;
    }

    /**
     * Get how often this item was selected in ANY context
     * @return times Selected.
     */
    public int getTimesSelected() {
        return mTimesSelected;
    }

    /**
     * Set how many times this item was selected in any context.
     * @param mTimesSelected how many times was the item selected.
     */
    public void setTimesSelected(int mTimesSelected) {
        this.mTimesSelected = mTimesSelected;
    }

    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", sex=" + sex.currentValue() +
                '}';
    }

    /**
     * This method sets a temporary parameter used in the calculation of the bounded greedy algorithm.
     * @param i the similarity
     */
    public void setTemporaryQuality(double i) {
        mTemporaryQuality = i;
    }

    /**
     * Gets the temporarily stored value.
     * @return the similarity for this recommendation cycle
     */
    public double getTemporaryQuality(){
        return mTemporaryQuality;
    }
}
