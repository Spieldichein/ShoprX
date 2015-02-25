
package de.tum.in.schlichter.shoprx.provider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.util.Log;

import java.util.Arrays;

import de.tum.in.schlichter.shoprx.provider.ShoprContract.Items;
import de.tum.in.schlichter.shoprx.provider.ShoprContract.Shops;
import de.tum.in.schlichter.shoprx.provider.ShoprContract.Stats;
import de.tum.in.schlichter.shoprx.provider.ShoprDatabase.Tables;
import de.tum.in.schlichter.shoprx.utils.SelectionBuilder;

public class ShoprProvider extends ContentProvider {

    private static UriMatcher sUriMatcher;

    private static final int ITEMS = 100;
    private static final int ITEM_ID = 101;

    private static final int SHOPS = 200;
    private static final int SHOP_ID = 201;

    private static final int STATS = 300;
    private static final int STAT_ID = 301;

    private static final int CONTEXT_ITEM_RELATION = 400;
    private static final int CONTEXT_ITEM_RELATION_ID = 401;

    private static final String TAG = "ShoprXProvider";

    private static final boolean LOGV = false;

    /**
     * Build and return a {@link UriMatcher} that catches all {@link Uri}
     * variations supported by this {@link ContentProvider}.
     */
    private static UriMatcher buildUriMatcher(Context context) {
        final UriMatcher matcher = new UriMatcher(UriMatcher.NO_MATCH);
        final String authority = ShoprContract.CONTENT_AUTHORITY;

        // Items
        matcher.addURI(authority, ShoprContract.PATH_ITEMS, ITEMS);
        matcher.addURI(authority, ShoprContract.PATH_ITEMS + "/*", ITEM_ID);

        // Shops
        matcher.addURI(authority, ShoprContract.PATH_SHOPS, SHOPS);
        matcher.addURI(authority, ShoprContract.PATH_SHOPS + "/*", SHOP_ID);

        // Stats
        matcher.addURI(authority, ShoprContract.PATH_STATS, STATS);
        matcher.addURI(authority, ShoprContract.PATH_STATS + "/*", STAT_ID);

        matcher.addURI(authority, ShoprContract.PATH_CONTEXT_ITEM, CONTEXT_ITEM_RELATION);
        matcher.addURI(authority, ShoprContract.PATH_CONTEXT_ITEM + "/*", CONTEXT_ITEM_RELATION_ID);

        return matcher;
    }

    private ShoprDatabase mOpenHelper;

    @Override
    public boolean onCreate() {
        final Context context = getContext();
        sUriMatcher = buildUriMatcher(context);
        mOpenHelper = new ShoprDatabase(context);
        return true;
    }

    @Override
    public String getType(Uri uri) {
        final int match = sUriMatcher.match(uri);
        switch (match) {
            case ITEMS:
                return Items.CONTENT_TYPE;
            case ITEM_ID:
                return Items.CONTENT_ITEM_TYPE;
            case SHOPS:
                return Shops.CONTENT_TYPE;
            case SHOP_ID:
                return Shops.CONTENT_ITEM_TYPE;
            case STATS:
                return Stats.CONTENT_TYPE;
            case STAT_ID:
                return Stats.CONTENT_ITEM_TYPE;
            case CONTEXT_ITEM_RELATION:
                return ShoprContract.ContextItemRelation.CONTENT_TYPE;
            case CONTEXT_ITEM_RELATION_ID:
                return ShoprContract.ContextItemRelation.CONTENT_ITEM_TYPE;
            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
            String[] selectionArgs, String sortOrder) {
        if (LOGV) {
            Log.v(TAG, "query(uri=" + uri + ", proj=" + Arrays.toString(projection) + ")");
        }
        final SQLiteDatabase db = mOpenHelper.getReadableDatabase();

        // Currently all cases are handled with simple SelectionBuilder because
        // we do not require any joins, similar...
        final SelectionBuilder builder = buildSimpleSelection(uri);
        Cursor query = builder.where(selection, selectionArgs).query(db, projection,
                sortOrder);
        query.setNotificationUri(getContext().getContentResolver(), uri);
        return query;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        if (LOGV)
            Log.v(TAG, "insert(uri=" + uri + ", values=" + values.toString()
                    + ")");
        final SQLiteDatabase db = mOpenHelper.getWritableDatabase();
        final int match = sUriMatcher.match(uri);
        switch (match) {
            case ITEMS: {
                db.insertOrThrow(Tables.ITEMS, null, values);
                getContext().getContentResolver().notifyChange(uri, null);
                return Items.buildItemUri(values.getAsInteger(Items._ID));
            }
            case SHOPS: {
                db.insertOrThrow(Tables.SHOPS, null, values);
                getContext().getContentResolver().notifyChange(uri, null);
                return Shops.buildShopUri(values.getAsInteger(Shops._ID));
            }
            case STATS: {
                long id = db.insertOrThrow(Tables.STATS, null, values);
                getContext().getContentResolver().notifyChange(uri, null);
                return Stats.buildStatUri((int) id);
            }
            case CONTEXT_ITEM_RELATION: {
                long id = db.insertOrThrow(Tables.CONTEXT_ITEM_RELATION, null, values);
                getContext().getContentResolver().notifyChange(uri, null);
                return ShoprContract.ContextItemRelation.buildStatUri((int) id);
            }
            default: {
                throw new UnsupportedOperationException("Unknown uri: " + uri);
            }
        }
    }

    @Override
    public int bulkInsert(Uri uri, ContentValues[] values) {
        /*
         * A more efficient version of bulkInsert which matches the URI only
         * once.
         */
        int numValues;
        final int match = sUriMatcher.match(uri);
        switch (match) {
            case ITEMS: {
                numValues = bulkInsertHelper(Tables.ITEMS, values);
                getContext().getContentResolver().notifyChange(uri, null);
                break;
            }
            case SHOPS: {
                numValues = bulkInsertHelper(Tables.SHOPS, values);
                getContext().getContentResolver().notifyChange(uri, null);
                break;
            }
            case STATS: {
                numValues = bulkInsertHelper(Tables.STATS, values);
                getContext().getContentResolver().notifyChange(uri, null);
                break;
            }
            case CONTEXT_ITEM_RELATION: {
                numValues = bulkInsertHelper(Tables.CONTEXT_ITEM_RELATION, values);
                getContext().getContentResolver().notifyChange(uri, null);
                break;
            }
            default: {
                throw new UnsupportedOperationException("Unknown uri: " + uri);
            }
        }

        return numValues;
    }

    private int bulkInsertHelper(String table, ContentValues[] values) {
        final SQLiteDatabase db = mOpenHelper.getWritableDatabase();
        db.beginTransaction();
        try {
            for (ContentValues value : values) {
                db.insertOrThrow(table, null, value);
                db.yieldIfContendedSafely();
            }
            db.setTransactionSuccessful();
            return values.length;
        } finally {
            db.endTransaction();
        }
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
            String[] selectionArgs) {
        if (LOGV)
            Log.v(TAG, "update(uri=" + uri + ", values=" + values.toString() + ")");
        final SQLiteDatabase db = mOpenHelper.getWritableDatabase();
        final SelectionBuilder builder = buildSimpleSelection(uri);
        int retVal = builder.where(selection, selectionArgs).update(db, values);
        getContext().getContentResolver().notifyChange(uri, null);
        return retVal;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        if (LOGV)
            Log.v(TAG, "delete(uri=" + uri + ")");
        final SQLiteDatabase db = mOpenHelper.getWritableDatabase();
        final SelectionBuilder builder = buildSimpleSelection(uri);
        int retVal = builder.where(selection, selectionArgs).delete(db);
        getContext().getContentResolver().notifyChange(uri, null);
        return retVal;
    }

    /**
     * Build a simple {@link SelectionBuilder} to match the requested
     * {@link Uri}. This is usually enough to support {@link #insert},
     * {@link #update}, and {@link #delete} operations.
     */
    private SelectionBuilder buildSimpleSelection(Uri uri) {
        final SelectionBuilder builder = new SelectionBuilder();
        final int match = sUriMatcher.match(uri);
        switch (match) {
            case ITEMS: {
                return builder.table(Tables.ITEMS);
            }
            case ITEM_ID: {
                final String id = Items.getItemId(uri);
                return builder.table(Tables.ITEMS).where(Items._ID + "=?", id);
            }
            case SHOPS: {
                return builder.table(Tables.SHOPS);
            }
            case SHOP_ID: {
                final String id = Shops.getShopId(uri);
                return builder.table(Tables.SHOPS).where(Shops._ID + "=?", id);
            }
            case STATS: {
                return builder.table(Tables.STATS);
            }
            case STAT_ID: {
                final String id = Stats.getStatId(uri);
                return builder.table(Tables.STATS).where(Stats._ID + "=?", id);
            }
            case CONTEXT_ITEM_RELATION: {
                return builder.table(Tables.CONTEXT_ITEM_RELATION);
            }
            case CONTEXT_ITEM_RELATION_ID: {
                String id = ShoprContract.ContextItemRelation.getStatId(uri);
                return builder.table(Tables.CONTEXT_ITEM_RELATION).where(ShoprContract.ContextItemRelation._ID + "=?", id);
            }
            default: {
                throw new UnsupportedOperationException("Unknown uri: " + uri);
            }
        }
    }

}
