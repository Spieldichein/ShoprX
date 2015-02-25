
package de.tum.in.schlichter.shoprx.eval;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.text.format.DateUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import de.tum.in.schlichter.shoprx.R;

import java.util.List;

import de.tum.in.schlichter.shoprx.algorithm.AdaptiveSelection;
import de.tum.in.schlichter.shoprx.algorithm.model.Item;
import de.tum.in.schlichter.shoprx.context.model.ScenarioContext;
import de.tum.in.schlichter.shoprx.provider.ShoprContract;
import de.tum.in.schlichter.shoprx.provider.ShoprContract.Stats;

/**
 * Displays the given stats and allows to start a new task.
 */
public class ResultsActivity extends Activity {

    public interface InitBundle {
        String STATS_ID = "stats_id";
        String ITEM_ID = "item_id";
    }

    private int mStatId;
    private Item mItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        // extract stat id
        Bundle extras = getIntent().getExtras();
        if (extras == null) {
            finish();
            return;
        }
        // get stats for this eval task
        mStatId = extras.getInt(InitBundle.STATS_ID);
        // get the item the user selected
        int itemId = extras.getInt(InitBundle.ITEM_ID);
        List<Item> currentCaseBase = AdaptiveSelection.get().getCurrentRecommendations();
        for (Item item : currentCaseBase) {
            if (item.id() == itemId) {
                mItem = item;
                break;
            }
        }

        if (mItem == null) {
            finish();
            return;
        }

        saveTheContext();

        setupViews();
    }

    /**
     * This method is called in order to save the current context of the user scenario, together with
     * the item, such that it can be used in further instances to provide better recommendations.
     */
    private void saveTheContext() {
        ScenarioContext scenarioContext = ScenarioContext.getInstance();

        ContentValues contentValues = new ContentValues();
        contentValues.put(ShoprContract.ContextItemRelation.REF_ITEM_ID, mItem.id());
        contentValues.put(ShoprContract.ContextItemRelation.CONTEXT_TIME, scenarioContext.getTimeOfTheDay().getTime());
        contentValues.put(ShoprContract.ContextItemRelation.CONTEXT_DAY, scenarioContext.getDayOfTheWeek().getDay());
        contentValues.put(ShoprContract.ContextItemRelation.CONTEXT_TEMPERATURE, scenarioContext.getTemperature().getDegrees());
        contentValues.put(ShoprContract.ContextItemRelation.CONTEXT_HUMIDITY, scenarioContext.getWeather().getWeatherIndicator());
        contentValues.put(ShoprContract.ContextItemRelation.CONTEXT_COMPANY, scenarioContext.getCompany().getCompanyType());

        Uri uri = ShoprContract.ContextItemRelation.CONTENT_URI;
        getContentResolver().insert(uri, contentValues);
    }

    private void setupViews() {
        TextView textViewUserName = (TextView) findViewById(R.id.textViewResultsUsername);
        TextView textViewDuration = (TextView) findViewById(R.id.textViewResultsDuration);
        TextView textViewDurationRecommendation = (TextView) findViewById(R.id.textViewResultsDurationRecommendation);
        TextView textViewCycles = (TextView) findViewById(R.id.textViewResultsCycles);
        TextView textViewItemPosition = (TextView) findViewById(R.id.textViewResultsItemPosition);
        TextView textViewItemCoverage = (TextView) findViewById(R.id.textViewResultsItemCoverage);

        final Cursor query = getContentResolver().query(Stats.buildStatUri(mStatId), new String[] {
                Stats._ID, Stats.USERNAME, Stats.DURATION, Stats.DURATION_RECOMMENDATION, Stats.CYCLE_COUNT, Stats.ITEM_POSITION, Stats.ITEM_COVERAGE
        }, null, null, null);
        if (query != null) {
            if (query.moveToFirst()) {
                textViewUserName.setText(query.getString(1));
                long duration = query.getLong(2) / DateUtils.SECOND_IN_MILLIS;
                long durationRec = query.getLong(3) /DateUtils.SECOND_IN_MILLIS;
                textViewDuration.setText(String.format("%dh:%02dm:%02ds", duration / 3600,
                        (duration % 3600) / 60, (duration % 60)));
                textViewDurationRecommendation.setText(String.format("%dh:%02dm:%02ds", durationRec / 3600,
                        (durationRec % 3600) / 60, (durationRec % 60)));
                textViewCycles.setText(query.getString(4));
                textViewItemPosition.setText(query.getString(5));
                textViewItemCoverage.setText(query.getString(6));
            }
            query.close();
        }

        // display selected item info
        TextView textViewItem = (TextView) findViewById(R.id.textViewResultsItem);
        textViewItem.setText(mItem.id() + " " + mItem.name() + " "
                + mItem.attributes().getReasonString());
        ImageView imageViewItem = (ImageView) findViewById(R.id.imageViewResultsItemPicture);
        Picasso.with(this)
                .load(mItem.image())
                .resizeDimen(R.dimen.default_image_width, R.dimen.default_image_height)
                .error(R.drawable.ic_action_tshirt)
                .centerCrop()
                .into(imageViewItem);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.results, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_restart:
                // go back to setup activity
                startActivity(new Intent(this, TestSetupActivity.class)
                        .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
