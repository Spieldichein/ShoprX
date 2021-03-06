
package de.tum.in.schlichter.shoprx.ui;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;
import com.squareup.picasso.Picasso;

import de.tum.in.schlichter.shoprx.Explanations.Model.AbstractExplanation;
import de.tum.in.schlichter.shoprx.Explanations.Model.Argument;
import de.tum.in.schlichter.shoprx.Explanations.Model.ContextArgument;
import de.tum.in.schlichter.shoprx.Explanations.Model.DimensionArgument;
import de.tum.in.schlichter.shoprx.Explanations.Model.SimpleExplanation;
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
import de.tum.in.schlichter.shoprx.utils.ExplanationAdapter;

public class ItemDetailsActivity extends Activity {

    public interface InitBundle {
        String ITEM_ID = "item_id";
        String ITEM_POSITION = "item_position";
    }

    private Item mItem;
    private int mItemPosition;
    private ListView explanationListView;
    private LinearLayout testLayout2;
    private ExplanationAdapter explanationAdapter;


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
        image.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplication(), ImageActivity.class);
                intent.putExtra(ImageActivity.InitBundle2.IMAGE_URL,mItem.image());
                startActivity(intent);
            }
        });

        findViewById(R.id.buttonItemDetailsFinish).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                onFinishTask();
            }
        });

        //Explanations
        //   explanationListView = (ListView) findViewById(R.id.listViewExplanation);
           testLayout2 = (LinearLayout) findViewById(R.id.listViewExplanation);
        setupExplanationAdapter();

        // title
        TextView itemTitle = (TextView) findViewById(R.id.textViewItemDetailsTitle);
        itemTitle.setText(getString(R.string.choice_confirmation, mItem.name(), mItem.attributes().getAttributeById(Label.ID).currentValue().descriptor()));



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

    private void setupExplanationAdapter() {
        explanationAdapter = new ExplanationAdapter(this);

        for (SimpleExplanation explanation1: mItem.getExplanation().getSimpleExplanations()){
            explanationAdapter.add(explanation1);

        }
        final int adapterCount = explanationAdapter.getCount();

        for (int i = 0; i < adapterCount; i++) {
            View item = explanationAdapter.getView(i, null, null);
            testLayout2.addView(item);
        }


        //explanationListView.setAdapter(explanationAdapter);*/
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

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        final Activity activity = this;

        builder.setTitle("You really want to choose this item and finish?");
        builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Statistics.get().setSelectedItemPosition(mItemPosition);
                // finish task, store stats to database
                Uri statUri = Statistics.get().finishTask(activity);
                if (statUri == null) {
                    Toast.makeText(activity, "Task was not started.", Toast.LENGTH_LONG).show();
                    return;
                }

                // display results
                Intent intent = new Intent(activity, ResultsActivity.class);
                intent.putExtra(ResultsActivity.InitBundle.STATS_ID, Integer.valueOf(Stats.getStatId(statUri)));
                intent.putExtra(ResultsActivity.InitBundle.ITEM_ID, mItem.id());
                startActivity(intent);
            }
        });
        builder.setNegativeButton("Cancel",null);



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.item_details, menu);
        return true;
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode==1337){

            if (resultCode ==42){
                Log.d("result","resultcode = 42");
                setResult(RESULT_OK);
                finish();
            }
            else{
                Log.d("result","resultcode diffrent = "+resultCode);

            }
        }
        else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }


}
