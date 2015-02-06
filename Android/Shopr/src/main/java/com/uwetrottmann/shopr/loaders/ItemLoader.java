
package com.uwetrottmann.shopr.loaders;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import com.google.android.gms.maps.model.LatLng;
import com.uwetrottmann.androidutils.Lists;
import com.uwetrottmann.shopr.algorithm.AdaptiveSelection;
import com.uwetrottmann.shopr.algorithm.model.Attributes;
import com.uwetrottmann.shopr.algorithm.model.ClothingType;
import com.uwetrottmann.shopr.algorithm.model.Color;
import com.uwetrottmann.shopr.algorithm.model.Item;
import com.uwetrottmann.shopr.algorithm.model.Label;
import com.uwetrottmann.shopr.algorithm.model.Price;
import com.uwetrottmann.shopr.algorithm.model.Sex;
import com.uwetrottmann.shopr.importer.CsvImportTask;
import com.uwetrottmann.shopr.provider.ShoprContract.Items;
import com.uwetrottmann.shopr.provider.ShoprContract.Shops;
import com.uwetrottmann.shopr.settings.AppSettings;
import com.uwetrottmann.shopr.stereotype.user.User;
import com.uwetrottmann.shopr.utils.ShoprLocalizer;
import com.uwetrottmann.shopr.utils.ValueConverter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Returns a list of items based on the current user model.
 */
public class ItemLoader extends GenericSimpleLoader<List<Item>> {

    private static final String TAG = "ItemLoader";
    private LatLng mLocation;
    private boolean mIsInit;

    public ItemLoader(Context context, LatLng location, boolean isInit) {
        super(context);
        mLocation = location;
        mIsInit = isInit;
    }

    @Override
    public List<Item> loadInBackground() {
        if (mLocation == null) {
            return new ArrayList<Item>();
        }

        AdaptiveSelection manager = AdaptiveSelection.get();

        // get initial case base
        if (mIsInit) {
            manager.setLocalizationModule(new ShoprLocalizer(getContext()));

            Log.d(TAG, "Initializing case base.");
            List<Item> caseBase = getInitialCaseBase();
            manager.setInitialCaseBase(caseBase);

            int maxRecommendations = AppSettings.getMaxRecommendations(getContext());
            AdaptiveSelection.get().setMaxRecommendations(maxRecommendations);
        }

        Log.d(TAG, "Fetching recommendations.");

        return manager.getRecommendations();
    }

    /**
     * With this method the SQlite database is searched for the specific items and the case base for
     * the algorithm calculation is returned.
     * @return A list featuring the case base
     */
    private List<Item> getInitialCaseBase() {
        List<Item> caseBase = Lists.newArrayList();

        // Determine the sex of the user in order to reduce the search space and improve selection time!
        String selectionString = restrictQuerySelection();

        Cursor query = getContext().getContentResolver().query(
                Items.CONTENT_URI,
                new String[] {
                        Items._ID, Items.CLOTHING_TYPE, Items.BRAND, Items.PRICE, Items.IMAGE_URL,
                        Items.COLOR, Items.SEX, Shops.REF_SHOP_ID
                }, selectionString, null, null);

        if (query != null) {
            while (query.moveToNext()) {
                Item item = new Item();

                item.id(query.getInt(0));
                item.image(query.getString(4));
                item.shopId(query.getInt(7));
                // name
                ClothingType type = new ClothingType(query.getString(1));
                String brand = query.getString(2);

                item.name(ValueConverter.getLocalizedStringForValue(getContext(), type
                        .currentValue().descriptor())
                        + " " + brand);

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

                caseBase.add(item);
            }

            query.close();
        }

        return caseBase;
    }

    /**
     * This method shall restrict the query selection. This will reduce the amount of items loaded from
     * the database and therefore how much memory the app is using.
     * @return either null or the selection arguments
     */
    private String restrictQuerySelection(){
        try {
            User u = User.getUser();
            StringBuilder builder = new StringBuilder();
            builder.append(Items.SEX + " LIKE '");
            if (u.getSex().equals(Sex.Value.MALE)){
                builder.append(CsvImportTask.SEX_MALE_DB);
            } else {
                builder.append(CsvImportTask.SEX_FEMALE_DB);
            }

            builder.append("' OR " + Items.SEX + " LIKE '" + CsvImportTask.SEX_UNISEX + "'");

            return builder.toString();
        } catch (RuntimeException re){
            return null;
        }
    }

}
