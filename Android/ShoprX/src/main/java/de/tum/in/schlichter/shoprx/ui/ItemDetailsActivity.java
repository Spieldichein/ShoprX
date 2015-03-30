
package de.tum.in.schlichter.shoprx.ui;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;
import com.squareup.picasso.Picasso;

import de.tum.in.schlichter.shoprx.Explanations.Model.AbstractExplanation;
import de.tum.in.schlichter.shoprx.Explanations.Model.Argument;
import de.tum.in.schlichter.shoprx.Explanations.Model.ContextArgument;
import de.tum.in.schlichter.shoprx.Explanations.Model.DimensionArgument;
import de.tum.in.schlichter.shoprx.R;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

import de.tum.in.schlichter.shoprx.ShoprApp;
import de.tum.in.schlichter.shoprx.algorithm.AdaptiveSelection;
import de.tum.in.schlichter.shoprx.algorithm.model.ClothingType;
import de.tum.in.schlichter.shoprx.algorithm.model.Color;
import de.tum.in.schlichter.shoprx.algorithm.model.Item;
import de.tum.in.schlichter.shoprx.algorithm.model.Label;
import de.tum.in.schlichter.shoprx.eval.ResultsActivity;
import de.tum.in.schlichter.shoprx.eval.Statistics;
import de.tum.in.schlichter.shoprx.model.Shop;
import de.tum.in.schlichter.shoprx.provider.ShoprContract.Stats;

public class ItemDetailsActivity extends Activity {

    public interface InitBundle {
        String ITEM_ID = "item_id";
        String ITEM_POSITION = "item_position";
    }

    private Item mItem;
    private int mItemPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_details);

        Bundle extras = getIntent().getExtras();
        if (extras == null) {
            finish();
            return;
        }

        mItemPosition = extras.getInt(InitBundle.ITEM_POSITION);

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

        setupViews();
    }

    private void setupViews() {
        ImageView image = (ImageView) findViewById(R.id.imageViewItemDetails);
        // load picture
        Picasso.with(this)
                .load(mItem.image())
                .placeholder(null)
                .error(R.drawable.ic_action_tshirt)
                .resizeDimen(R.dimen.default_image_width, R.dimen.default_image_height)
                .centerCrop()
                .into(image);

        findViewById(R.id.buttonItemDetailsFinish).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                onFinishTask();
            }
        });


        String exp="";
        AbstractExplanation explanation = mItem.getExplanation().getAbstractExplanation();
        if (explanation.hasPrimaryArguments()){
            DimensionArgument dimensionArgument = (DimensionArgument) explanation.primaryArguments().toArray()[0];
            if (dimensionArgument!=null) {
                Log.d("bugsearch","dimensionArgument not null: "+dimensionArgument.dimension());
                Log.d("bugsearch","arraysize: "+explanation.primaryArguments().toArray().length);
                exp = dimensionArgument.dimension().attribute().id();
            }
        }
        else if (explanation.hasSupportingArguments()){
            DimensionArgument dimensionArgument = (DimensionArgument) explanation.supportingArguments().toArray()[0];
            if (dimensionArgument!=null) {
                Log.d("bugsearch","dimensionArgument not null: "+dimensionArgument);
                Log.d("bugsearch","arraysize: "+explanation.supportingArguments().toArray().length);

                if (dimensionArgument.type()== Argument.Type.ON_DIMENSION) {
                    exp = dimensionArgument.dimension().attribute().id();
                }
                else if (dimensionArgument.type()== Argument.Type.GOOD_AVERAGE){
                    exp ="Good Average";
                }
                else if (dimensionArgument.type()== Argument.Type.SERENDIPITOUS){
                    exp ="Exploring";
                }
                else if (dimensionArgument.type()== Argument.Type.CONTEXT){
                    exp ="Context";
                }
            }
        }else if(explanation.hasContextArguments()) {
            ContextArgument contextArgument = (ContextArgument) explanation.contextArguments().toArray()[0];
            exp = "some context stuff";
        }

        // title
        TextView itemTitle = (TextView) findViewById(R.id.textViewItemDetailsTitle);
        itemTitle.setText(getString(R.string.choice_confirmation, mItem.name(), mItem.attributes().getAttributeById(Label.ID).currentValue().descriptor())+exp);



        // item attributes
        StringBuilder description = new StringBuilder();
        description.append(ShoprApp.getContext().getString(R.string.type));
        description.append(": ");
        description.append(mItem.attributes().getAttributeById(ClothingType.ID).currentValue().descriptor());
        description.append("\n");
        description.append(ShoprApp.getContext().getString(R.string.sex));
        description.append(": ");
        description.append(mItem.getSex().currentValue().descriptor());
        description.append("\n");
        description.append(ShoprApp.getContext().getString(R.string.color));
        description.append(": ");
        description.append(mItem.attributes().getAttributeById(Color.ID).currentValue().descriptor());
        description.append("\n");
        description.append(ShoprApp.getContext().getString(R.string.stock));
        description.append(": ");
        description.append(mItem.getItemsInStock());
        description.append(" ");
        if (mItem.getItemsInStock() != 1){ // Set the plural correctly
            description.append(ShoprApp.getContext().getString(R.string.items));
        } else {
            description.append(ShoprApp.getContext().getString(R.string.item));
        }
        description.append("\n");
        description.append(ShoprApp.getContext().getString(R.string.price));
        description.append(": ");
        description.append(NumberFormat.getCurrencyInstance(Locale.GERMANY).format(mItem.price().doubleValue()));
        TextView itemDescription = (TextView) findViewById(R.id.textViewItemDetailsAttributes);
        itemDescription.setText(description);

        List<Shop> shops = ShoprApp.getShopList();
        if (shops != null) {
            for (Shop shop : shops) {
                if (mItem.shopId() == shop.id()) {
                    TextView titleShop = (TextView) findViewById(R.id.popup_shopTitle);
                    TextView shopCrowded = (TextView) findViewById(R.id.popup_shopCrowded);
                    TextView open = (TextView) findViewById(R.id.popup_shopOpeningHoursToday);
                    TextView distance = (TextView) findViewById(R.id.popup_shopDistance);

                    titleShop.setText(ShoprApp.getContext().getString(R.string.availableAt, shop.name()));
                    if (shop.isUsuallyCrowded()) {
                        shopCrowded.setText(R.string.crowded);
                    } else {
                        shopCrowded.setText(R.string.not_crowded);
                    }

                    open.setText(shop.openToday());
                    String distanceString = ShoprApp.getDistanceToCurrentLocationInKmAsString(shop.getLocationObject());
                    Log.d("Distance String", distanceString);
                    Log.d("Location object", ""+shop.getLocationObject());
                    distance.setText(distanceString);

                    break;
                }
            }
        }
    }

    @Override
    public void onStart() {
        super.onStart();

        Tracker t = ((ShoprApp) getApplication()).getTracker();
        t.send(new HitBuilders.EventBuilder().setCategory("ItemDetails").setAction("Start").setValue(System.currentTimeMillis()).build());
    }

    @Override
    public void onStop() {
        super.onStop();

        Tracker t = ((ShoprApp) getApplication()).getTracker();
        t.send(new HitBuilders.EventBuilder().setCategory("ItemDetails").setAction("Stop").setValue(System.currentTimeMillis()).build());
    }

    protected void onFinishTask() {
        Statistics.get().setSelectedItemPosition(mItemPosition);
        // finish task, store stats to database
        Uri statUri = Statistics.get().finishTask(this);
        if (statUri == null) {
            Toast.makeText(this, "Task was not started.", Toast.LENGTH_LONG).show();
            return;
        }

        // display results
        Intent intent = new Intent(this, ResultsActivity.class);
        intent.putExtra(ResultsActivity.InitBundle.STATS_ID, Integer.valueOf(Stats.getStatId(statUri)));
        intent.putExtra(ResultsActivity.InitBundle.ITEM_ID, mItem.id());
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.item_details, menu);
        return true;
    }

}
