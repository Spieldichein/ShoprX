package de.tum.in.schlichter.shoprx.Explanations.Model;

import android.util.Log;

import java.util.List;

import de.tum.in.schlichter.shoprx.algorithm.model.Item;

/**
 * Created by Nicksteal on 23.03.2015.
 */
public abstract class Context {
    // threshold for explanation score
    public static double ALPHA = 0.8;
    // threshold for information score
    public static double GAMMA = 0.7;

    public abstract double explanationScore(Item item);
    public abstract double informationScore(Item item, List<Item> recommendations);

    public boolean isValidArgument(Item item, List<Item> recommendations) {
       double es=explanationScore(item);
               Log.d("context Distance","es:"+es);
        double is = informationScore(item,recommendations);
        Log.d("context Distance","is:"+is);



        return explanationScore(item) > ALPHA && informationScore(item, recommendations) > GAMMA;
    }
}