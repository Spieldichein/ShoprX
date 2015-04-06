package de.tum.in.schlichter.shoprx.Explanations.Algorithm;

import android.database.Cursor;
import android.util.Log;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import de.tum.in.schlichter.androidutils.Lists;
import de.tum.in.schlichter.shoprx.Explanations.Model.AbstractExplanation;
import de.tum.in.schlichter.shoprx.Explanations.Model.Context;
import de.tum.in.schlichter.shoprx.Explanations.Model.DimensionArgument;
import de.tum.in.schlichter.shoprx.Explanations.Model.Explanation;
import de.tum.in.schlichter.shoprx.algorithm.AdaptiveSelection;
import de.tum.in.schlichter.shoprx.algorithm.Query;
import de.tum.in.schlichter.shoprx.algorithm.Utils;
import de.tum.in.schlichter.shoprx.algorithm.model.Attributes;
import de.tum.in.schlichter.shoprx.algorithm.model.ClothingType;
import de.tum.in.schlichter.shoprx.algorithm.model.Color;
import de.tum.in.schlichter.shoprx.algorithm.model.Item;
import de.tum.in.schlichter.shoprx.algorithm.model.Label;
import de.tum.in.schlichter.shoprx.algorithm.model.Price;
import de.tum.in.schlichter.shoprx.algorithm.model.Sex;
import de.tum.in.schlichter.shoprx.provider.ShoprContract;
import de.tum.in.schlichter.shoprx.stereotype.user.User;

/**
 * Created by Nicksteal on 24.03.2015.
 */
public class ExplanationGenerator {
    private ExplanationLocalizer localizer;
    private TextFormatter formatter;
    private android.content.Context appContext;
    public static final int LIMIT_ITEMS = 300;


    public ExplanationGenerator(ExplanationLocalizer localizer, TextFormatter formatter) {
        this.localizer = localizer;
        this.formatter = formatter;
    }

    public ExplanationGenerator (android.content.Context appContext){
     this.appContext = appContext;
         AdaptiveSelection as = AdaptiveSelection.get();
       //  as.setInitialCaseBase(getInitialCaseBase());
    }


    public List<Item> explain(List<Item> recommendedItems,
                                        Query query, List<Context> contexts) {
        List<Item> explainedRecommendations = new ArrayList<Item>();
        for (Item item : recommendedItems) {
            item.setExplanation(explain(item, query, recommendedItems, contexts));
            explainedRecommendations.add(item);
          //  explainedRecommendations.add(new Item(item, explain(item,
             //       query, recommendedItems, contexts)));
        }

        return explainedRecommendations;
    }

    private Explanation explain(Item item, Query query,
                                List<Item> recommendedItems, List<Context> contexts) {
        AbstractExplanation abstractExplanation = new ArgumentGenerator().select(item, query,
                recommendedItems, contexts);
        /*if (abstractExplanation.hasSupportingArguments()){
            if (abstractExplanation.supportingArguments().toArray()[0] !=null){
                DimensionArgument dimensionArgument = (DimensionArgument) abstractExplanation.supportingArguments().toArray()[0];
            }
        }*/
        //return  explanation;

        return new SurfaceGenerator(localizer, formatter).transform(abstractExplanation);
    }


    //public static void main(String[] args) {
       // AdaptiveSelection as = AdaptiveSelection.get();
      //  as.setInitialCaseBase(getInitialCaseBase(), true);
		/*
		 * for (Recommendation r : new
		 * Discloser().explain(as.getRecommendations(), as.getCurrentQuery())) {
		 * System.out.println("" + r.item().name());
		 * System.out.println("Primary"); for (DimensionArgument arg :
		 * r.explanation().primaryArguments()) { System.out.println("" +
		 * arg.getType()); } System.out.println("Secondary"); for
		 * (DimensionArgument arg : r.explanation().supportingArguments()) {
		 * System.out.println("" + arg.getType()); } }
		 */
  //  }

    private List<Item> getInitialCaseBase() {
        List<Item> caseBase = Lists.newArrayList();

        // Determine the sex of the user in order to reduce the search space and improve selection time!
        String selectionString = restrictQuerySelection();

        String limitString = null;
        if (selectionString == null){
            limitString = " RANDOM() LIMIT " + LIMIT_ITEMS;
        }

        String[] columns = new String[] { ShoprContract.Items._ID, ShoprContract.Items.CLOTHING_TYPE, ShoprContract.Items.BRAND, ShoprContract.Items.PRICE, ShoprContract.Items.IMAGE_URL,
                ShoprContract.Items.COLOR, ShoprContract.Items.SEX, ShoprContract.Shops.REF_SHOP_ID, ShoprContract.Items.SEASON, ShoprContract.Items.NAME, ShoprContract.Items.STOCK };

        Cursor query = appContext.getContentResolver().query( ShoprContract.Items.CONTENT_URI, columns, selectionString, null, limitString);

        if (query != null) {
            while (query.moveToNext()) {
                Item item = new Item();

                item.id(query.getInt(0));
                item.image(query.getString(4));
                item.shopId(query.getInt(7));
                // name
                ClothingType type = new ClothingType(query.getString(1));

                item.name(query.getString(9));

                // price
                BigDecimal price = new BigDecimal(query.getDouble(3));
                item.price(price);

                // critiquable attributes
                item.attributes(new Attributes()
                        .putAttribute(type)
                        .putAttribute(new Color(query.getString(5)))
                        .putAttribute(new Price(price))
                        .putAttribute(new Label(query.getString(2))));
                //.putAttribute(new Sex(query.getString(6))));

                //Sets the sex of the item.
                item.setSex(new Sex(query.getString(6)));

                item.shopId(query.getInt(7));

                item.setItemsInStock(query.getInt(10));

                caseBase.add(item);
            }

            query.close();
        }

        return caseBase;
    }

    private String restrictQuerySelection(){
        try {
            User u = User.getUser();
            StringBuilder builder = new StringBuilder();
            builder.append(ShoprContract.Items.SEX + " LIKE '");
            if (u.getSex().equals(Sex.Value.MALE)){
                builder.append(Sex.Value.MALE);
            } else {
                builder.append(Sex.Value.FEMALE);
            }

            builder.append("' OR ");
            builder.append(ShoprContract.Items.SEX);
            builder.append(" LIKE '");
            builder.append(Sex.Value.UNISEX.descriptor().toLowerCase());
            builder.append("'");

            return builder.toString();
        } catch (RuntimeException re){
            return null;
        }
    }



}
