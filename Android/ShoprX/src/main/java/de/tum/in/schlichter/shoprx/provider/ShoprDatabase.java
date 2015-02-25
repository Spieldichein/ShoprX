
package de.tum.in.schlichter.shoprx.provider;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import de.tum.in.schlichter.shoprx.provider.ShoprContract.Items;
import de.tum.in.schlichter.shoprx.provider.ShoprContract.Shops;
import de.tum.in.schlichter.shoprx.provider.ShoprContract.Stats;

public class ShoprDatabase extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "shoprx.db";

    public static final int DATABASE_VERSION = 11;

    public interface Tables {
        String ITEMS = "items";

        String SHOPS = "shops";

        String STATS = "stats";

        String CONTEXT_ITEM_RELATION = "context_item";
    }

    public interface References {
        String SHOP_ID = "REFERENCES " + Tables.SHOPS + "(" + Shops._ID + ")";

        String ITEM_ID = "REFERENCES " + Tables.ITEMS + "(" + Items._ID + ")";
    }

    private static final String CREATE_ITEMS_TABLE = "CREATE TABLE "
            + Tables.ITEMS + " ("

            + Items._ID + " INTEGER PRIMARY KEY,"

            + Shops.REF_SHOP_ID + " INTEGER " + References.SHOP_ID + ","

            + Items.BRAND + " TEXT,"

            + Items.CLOTHING_TYPE + " TEXT,"

            + Items.COLOR + " TEXT,"

            + Items.PRICE + " REAL,"

            + Items.SEX + " TEXT,"

            + Items.IMAGE_URL + " TEXT,"

            + Items.NAME + " TEXT,"

            + Items.SEASON + " TEXT,"

            + Items.STOCK + " INTEGER"

            + ");";

    private static final String CREATE_SHOPS_TABLE = "CREATE TABLE "
            + Tables.SHOPS + " ("

            + Shops._ID + " INTEGER PRIMARY KEY,"

            + Shops.NAME + " TEXT NOT NULL,"

            + Shops.OPENING_HOURS + " TEXT,"

            + Shops.LAT + " REAL,"

            + Shops.LONG + " REAL,"

            + Shops.CROWDED + " INTEGER"  //There is no explicit boolean type - therefore we use int 0 = false, 1 = true

            + ");";

    private static final String CREATE_STATS_TABLE = "CREATE TABLE "
            + Tables.STATS + " ("

            + Stats._ID + " INTEGER PRIMARY KEY,"

            + Stats.USERNAME + " TEXT,"

            + Stats.DURATION + " INTEGER,"

            + Stats.DURATION_RECOMMENDATION + " INTEGER,"

            + Stats.CYCLE_COUNT + " INTEGER,"

            + Stats.ITEM_POSITION + " INTEGER,"

            + Stats.ITEM_COVERAGE + " TEXT,"

            + Stats.STEREOTYPE + " TEXT"

            + ");";

    private static final String CREATE_CONTEXT_ITEM_RELATION = "CREATE TABLE "
            + Tables.CONTEXT_ITEM_RELATION + "("

            + ShoprContract.ContextItemRelation._ID + " INTEGER PRIMARY KEY," // AUTOINCREMENT is not needed. It is explicitly done by SQlite.

            + ShoprContract.ContextItemRelation.REF_ITEM_ID + " INTEGER " + References.ITEM_ID + ","

            + ShoprContract.ContextItemRelation.CONTEXT_TIME + " INTEGER,"

            + ShoprContract.ContextItemRelation.CONTEXT_DAY + " INTEGER,"

            + ShoprContract.ContextItemRelation.CONTEXT_TEMPERATURE + " INTEGER,"

            + ShoprContract.ContextItemRelation.CONTEXT_HUMIDITY + " INTEGER,"

            + ShoprContract.ContextItemRelation.CONTEXT_COMPANY + " INTEGER"

            + ");";

    private static final String TAG = "ShoprDatabase";

    public ShoprDatabase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_SHOPS_TABLE);

        // items refs shop ids, so create shops table first
        db.execSQL(CREATE_ITEMS_TABLE);

        db.execSQL(CREATE_STATS_TABLE);

        db.execSQL(CREATE_CONTEXT_ITEM_RELATION);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // always start from scratch
        onResetDatabase(db);
    }

    /**
     * Drops all tables and creates an empty database.
     */
    private void onResetDatabase(SQLiteDatabase db) {
        Log.w(TAG, "Database has incompatible version, starting from scratch");

        db.execSQL("DROP TABLE IF EXISTS " + Tables.ITEMS);
        db.execSQL("DROP TABLE IF EXISTS " + Tables.SHOPS);
        db.execSQL("DROP TABLE IF EXISTS " + Tables.STATS);
        db.execSQL("DROP TABLE IF EXISTS " + Tables.CONTEXT_ITEM_RELATION);

        onCreate(db);
    }

}
