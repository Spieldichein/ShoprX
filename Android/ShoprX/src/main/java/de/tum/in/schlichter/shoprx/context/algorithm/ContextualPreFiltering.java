package de.tum.in.schlichter.shoprx.context.algorithm;

import java.util.ArrayList;
import java.util.List;

import de.tum.in.schlichter.shoprx.ShoprApp;
import de.tum.in.schlichter.shoprx.algorithm.model.Item;
import de.tum.in.schlichter.shoprx.context.model.DistanceToShop;
import de.tum.in.schlichter.shoprx.context.model.ScenarioContext;
import de.tum.in.schlichter.shoprx.loaders.ItemLoader;
import de.tum.in.schlichter.shoprx.model.Shop;

/**
 * Created by Yannick on 12.02.15.
 *
 * This class is responsible for everything that deals with contextual pre-filtering.
 * So everything that should be pre-filtered concerning the context will be done here.
 */
public class ContextualPreFiltering {

    private static final int MINIMUM_ITEMS_NUMBER = 300; //Minimum number of items, that have to be returned.

    /**
     * In this method everything concerning the shops will be pre-filtered. Therefore we ensure
     * that the user gets shops within the selected distance as well as within the given opening
     * hours and other restrictions.
     * @param cases the current case base
     * @return a new (limited) case base
     */
    public static List<Item> filterShops(List<Item> cases, ScenarioContext scenarioContext){
        // Get a list of all shops within the current data set
        List<Shop> shops = ShoprApp.getShopList();

        //If there were shops and a context object for the distance to the shops, then we can proceed
        // if the size of the cases equals the number of items that should be loaded in the initial
        // case, we do not do the contextual pre filtering.
        if (shops != null && scenarioContext.isSet() && cases.size() != ItemLoader.LIMIT_ITEMS){

            // Initialize a new array where we can store whether a store should be accessed or not.
            boolean[] shopAvailable = listAvailableShops(shops, scenarioContext);

            //Create a new list in which we put all the items that are allowed. Afterwards we return the limited case base.
            List<Item> newCases = new ArrayList<Item>();
            for (Item item : cases) {
                if (shopAvailable[item.shopId()] && itemInStock(scenarioContext, item)){
                    newCases.add(item);
                }
            }

            //Check if the number of items is large enough. Otherwise we have to lessen some restrictions.
            // Also this number of cases is returned, if the Scenario Context restrictions cannot be relaxed further
            if (newCases.size() >= MINIMUM_ITEMS_NUMBER || !scenarioContext.relaxSomeConditions()){
                return newCases;
            } else {
                // try returning items again.
                //scenarioContext.logScenarioContext();
                return filterShops(cases, scenarioContext); // We already loosened some conditions (see above condition)
            }
        }

        return cases;
    }

    /**
     * Checks whether a shop is usually crowded and returns whether the crowdedness is okay.
     * @param shop the shop under inspection
     * @return true if the crowdedness of the shop is okay.
     */
    private static boolean isCrowdednessOkay(ScenarioContext scenarioContext, Shop shop){
        //Log.d("Crowded okay", "" + (scenarioContext.isCrowdedShopsAllowed() || !shop.isUsuallyCrowded()));
        return (scenarioContext.isCrowdedShopsAllowed() || !shop.isUsuallyCrowded());
    }

    /**
     * Returns whether the item is in stock and whether it should be included/excluded from the calculation
     * of the case base.
     * @param item The item under investigation
     * @return true if the item should be included, false if it is not in stock and only items in stock shall be shown
     */
    private static boolean itemInStock(ScenarioContext scenarioContext, Item item){
        //Log.d("Items in stock okay", ""+ !(scenarioContext.isOnlyItemsInStock() && item.getItemsInStock() == 0));
        return !(scenarioContext.isOnlyItemsInStock() && item.getItemsInStock() == 0);
    }

    /**
     * Checks whether the distance to the shop is okay. Which means that the context object is checked,
     * whether it should include all items or not and the allowed distance is compared with the real distance.
     * @param allowedDistanceToShop the context object holding the allowed distance.
     * @param distance the real calculated distance to the shop
     * @return true if the distance is okay.
     */
    private static boolean isDistanceOkay(DistanceToShop allowedDistanceToShop, float distance){
        //Log.d("Distance okay",""+(allowedDistanceToShop.getDistance() == 0 || distance <= allowedDistanceToShop.getDistance()));
        return (allowedDistanceToShop.getDistance() == 0 || distance <= allowedDistanceToShop.getDistance());
    }

    /**
     * Returns an array of booleans identified with the shops id. This id does <b>not</b> have to be decreased by 1.
     * @param shops All the shops that should be checked
     * @param scenarioContext The current context for this scenario
     * @return An array containing true, if the shop should be included.
     */
    private static boolean[] listAvailableShops(List<Shop> shops, ScenarioContext scenarioContext){
        boolean[] shopAvailable = new boolean[shops.size() + 1];

        // Write into the array whether the shops are within the given distance.
        for (Shop shop : shops) {
            float distance = ShoprApp.getDistanceToCurrentLocationInKmAsFloat(shop.getLocationObject());
            shopAvailable[shop.id()] = (isDistanceOkay(scenarioContext.getDistanceToShop(), distance) && isCrowdednessOkay(scenarioContext, shop) && shop.isOpen(scenarioContext.getShopOpeningHoursModel())); // if the distance is smaller than the allowed distance, then it will write true, else false
        }

        return shopAvailable;
    }
}
