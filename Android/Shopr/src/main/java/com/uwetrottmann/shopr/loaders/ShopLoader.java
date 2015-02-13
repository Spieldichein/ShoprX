
package com.uwetrottmann.shopr.loaders;

import android.content.Context;
import android.database.Cursor;

import com.google.android.gms.maps.model.LatLng;
import com.uwetrottmann.shopr.model.OpeningHours;
import com.uwetrottmann.shopr.model.Shop;
import com.uwetrottmann.shopr.provider.ShoprContract.Shops;

import java.util.ArrayList;
import java.util.List;

public class ShopLoader extends GenericSimpleLoader<List<Shop>> {

    private List<Shop> mShops;

    public ShopLoader(Context context) {
        super(context);
    }

    @Override
    public List<Shop> loadInBackground() {
        if (mShops == null) {
            final Cursor query = getContext().getContentResolver().query(Shops.CONTENT_URI,
                    new String[]{
                            Shops._ID, Shops.NAME, Shops.LAT, Shops.LONG, Shops.OPENING_HOURS, Shops.CROWDED
                    }, null, null,
                    null);

            mShops = new ArrayList<Shop>();

            if (query != null) {
                while (query.moveToNext()) {
                    Shop shop = new Shop();
                    shop.id(query.getInt(0));
                    shop.name(query.getString(1));
                    shop.location(new LatLng(query.getDouble(2), query.getDouble(3)));

                    shop.setShopOpeningHours(extractOpeningHours(query.getString(4)));
                    shop.setUsuallyCrowded(query.getInt(5) == 1); // Evaluates to true if the database contains a 1 for this field.

                    mShops.add(shop);
                }

                query.close();
            }
        }

        return mShops;
    }

    /**
     * Extracts the raw data string with the shop opening hours and converts it to the internal data
     * structure.
     * @param rawOpeningHours String describing the opening hours, as loaded into the database
     * @return the opening hours in the internal description
     */
    private OpeningHours[] extractOpeningHours(String rawOpeningHours){
        OpeningHours[] shopOpeningHours = new OpeningHours[7];

        rawOpeningHours = rawOpeningHours.substring(1,rawOpeningHours.length() -1 );
        String[] openByDays = rawOpeningHours.split("; ");
        for (int i = 0; i < openByDays.length; i++){
            String shopOpen = openByDays[i];
            shopOpen = shopOpen.substring(1, shopOpen.length() -1);
            String[] pureOpen = shopOpen.split(", ");
            OpeningHours openingHours = new OpeningHours(Integer.parseInt(pureOpen[0]), Integer.parseInt(pureOpen[1]));
            shopOpeningHours[i] = openingHours;
        }

        return shopOpeningHours;
    }

}
