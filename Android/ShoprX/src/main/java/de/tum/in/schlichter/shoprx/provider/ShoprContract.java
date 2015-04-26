
package de.tum.in.schlichter.shoprx.provider;

import android.net.Uri;
import android.provider.BaseColumns;

public class ShoprContract {

    public static final String CONTENT_AUTHORITY = "de.tum.in.schlichter.shoprx.provider";

    interface ItemsColumns {
        String CLOTHING_TYPE = "item_clothing_type";

        String BRAND = "item_brand";

        String SEX = "item_sex";

        String COLOR = "item_color";

        String PRICE = "item_price";

        String IMAGE_URL = "item_image";

        String NAME = "item_name";

        String SEASON = "item_season";

        String ISTRENDY = "item_istrendy";

        String STOCK = "item_stock";
    }

    interface ShopsColumns {
        /** NOT in this table! Used to reference ID from other tables. */
        String REF_SHOP_ID = "shop_id";

        String NAME = "shop_name";

        String LAT = "shop_lat";

        String LONG = "shop_long";

        String OPENING_HOURS = "shop_opening_hours";

        String CROWDED = "shop_crowded";
    }

    interface StatsColumns {
        String USERNAME = "stats_user";

        String DURATION = "stats_duration";

        String DURATION_RECOMMENDATION = "stats_duration_recommendation";

        String CYCLE_COUNT = "stats_cycles";

        String ITEM_POSITION = "stats_item_position";

        String ITEM_COVERAGE = "stats_item_coverage";

        String STEREOTYPE = "stats_stereotype";

        String FASHION_SUGGESTED = "stats_fashion_suggested";

        String CHARTS_LOOKED_AT = "stats_charts_looked_at";

        String PREFERENCE_SETTING_STARTED = "stats_preference_setting_started";

        String PREFERENCE_CHANGED = "stats_preference_changed";

        String LABEL_PREFERENCE_STARTED = "stats_label_preference_started";

        String ALPHA_CHANGED = "stats_alpha_changed";

    }

    interface ContextItemRelationColumns {

        String REF_ITEM_ID = "item_id";

        String CONTEXT_TIME = "time_of_day";

        String CONTEXT_DAY = "day_of_week";

        String CONTEXT_TEMPERATURE = "temperature";

        String CONTEXT_HUMIDITY = "humidity";

        String CONTEXT_COMPANY = "company";

    }

    private static final Uri BASE_CONTENT_URI = Uri.parse("content://"
            + CONTENT_AUTHORITY);

    public static final String PATH_ITEMS = "items";

    public static final String PATH_SHOPS = "shops";

    public static final String PATH_STATS = "stats";

    public static final String PATH_CONTEXT_ITEM = "contextitemrelation";

    /**
     * Represents clothing items.
     */
    public static class Items implements ItemsColumns, BaseColumns {
        public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon()
                .appendPath(PATH_ITEMS).build();

        /** Use if multiple items get returned */
        public static final String CONTENT_TYPE = "vnd.android.cursor.dir/vnd.shopr.item";

        /** Use if a single item is returned */
        public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/vnd.shopr.item";

        public static Uri buildItemUri(int itemId) {
            return CONTENT_URI.buildUpon().appendPath(String.valueOf(itemId))
                    .build();
        }

        public static String getItemId(Uri uri) {
            return uri.getLastPathSegment();
        }
    }

    /**
     * Represents shops where clothing items are available for purchase.
     */
    public static class Shops implements ShopsColumns, BaseColumns {
        public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon()
                .appendPath(PATH_SHOPS).build();

        /** Use if multiple items get returned */
        public static final String CONTENT_TYPE = "vnd.android.cursor.dir/vnd.shopr.shop";

        /** Use if a single item is returned */
        public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/vnd.shopr.shop";

        public static Uri buildShopUri(int shopId) {
            return CONTENT_URI.buildUpon().appendPath(String.valueOf(shopId))
                    .build();
        }

        public static String getShopId(Uri uri) {
            return uri.getLastPathSegment();
        }
    }

    /**
     * Represents statistics items from one user task.
     */
    public static class Stats implements StatsColumns, BaseColumns {
        public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon()
                .appendPath(PATH_STATS).build();

        /** Use if multiple items get returned */
        public static final String CONTENT_TYPE = "vnd.android.cursor.dir/vnd.shopr.stats";

        /** Use if a single item is returned */
        public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/vnd.shopr.stats";

        public static Uri buildStatUri(int statId) {
            return CONTENT_URI.buildUpon().appendPath(String.valueOf(statId))
                    .build();
        }

        public static String getStatId(Uri uri) {
            return uri.getLastPathSegment();
        }
    }

    public static class ContextItemRelation implements ContextItemRelationColumns, BaseColumns{
        public  static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon().appendPath(PATH_CONTEXT_ITEM).build();

        /** Use if multiple items get returned */
        public static final String CONTENT_TYPE = "vnd.android.cursor.dir/vnd.shopr.contextitemrelation";

        /** Use if a single item is returned */
        public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/vnd.shopr.contextitemrelation";

        public static Uri buildStatUri(int statId) {
            return CONTENT_URI.buildUpon().appendPath(String.valueOf(statId))
                    .build();
        }

        public static String getStatId(Uri uri) {
            return uri.getLastPathSegment();
        }
    }
}
