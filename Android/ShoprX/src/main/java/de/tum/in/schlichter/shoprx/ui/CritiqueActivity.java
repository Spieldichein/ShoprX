
package de.tum.in.schlichter.shoprx.ui;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.NavUtils;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;
import com.squareup.picasso.Picasso;

import de.tum.in.schlichter.shoprx.Explanations.Model.AbstractExplanation;
import de.tum.in.schlichter.shoprx.Explanations.Model.DimensionArgument;
import de.tum.in.schlichter.shoprx.Explanations.Model.Explanation;
import de.tum.in.schlichter.shoprx.Explanations.Model.SimpleExplanation;
import de.tum.in.schlichter.shoprx.R;

import java.lang.reflect.Array;
import java.util.List;

import de.tum.in.schlichter.shoprx.ShoprApp;
import de.tum.in.schlichter.shoprx.algorithm.AdaptiveSelection;
import de.tum.in.schlichter.shoprx.algorithm.Critique;
import de.tum.in.schlichter.shoprx.algorithm.Feedback;
import de.tum.in.schlichter.shoprx.algorithm.model.Attributes;
import de.tum.in.schlichter.shoprx.algorithm.model.ClothingType;
import de.tum.in.schlichter.shoprx.algorithm.model.Color;
import de.tum.in.schlichter.shoprx.algorithm.model.Item;
import de.tum.in.schlichter.shoprx.algorithm.model.Label;
import de.tum.in.schlichter.shoprx.algorithm.model.Price;
import de.tum.in.schlichter.shoprx.algorithm.model.Sex;
import de.tum.in.schlichter.shoprx.eval.Statistics;
import de.tum.in.schlichter.shoprx.ui.explanation.HelpActivity;
import de.tum.in.schlichter.shoprx.ui.explanation.MindMap.PriceRangeFragment;
import de.tum.in.schlichter.shoprx.utils.ExplanationAdapter;
import de.tum.in.schlichter.shoprx.utils.ValueConverter;

public class CritiqueActivity extends Activity {

    private ListView mListView;
    private ListView explanationListView;
    private Button mButtonUpdate;
    private ImageButton imageButton;
    private LinearLayout testLayout;
    private LinearLayout testLayout2;

    private ItemFeatureAdapter mAdapter;
    private ExplanationAdapter explanationAdapter;


    private Item mItem;
    private boolean mIsPositiveCritique;

    public interface InitBundle {
        String ITEM_ID = "item_id";
        String IS_POSITIVE_CRITIQUE = "is_positive";
        String IMAGE_URL = "image_url";
    }

    @Override
        protected void onCreate(Bundle savedInstanceState) {
            requestWindowFeature(Window.FEATURE_ACTION_BAR_OVERLAY);
            super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_critique);


        Bundle extras = getIntent().getExtras();
        if (extras == null) {
            finish();
            return;
        }

        int itemId = extras.getInt(InitBundle.ITEM_ID);
        List<Item> currentCaseBase = AdaptiveSelection.get().getCurrentRecommendations();
        for (Item item : currentCaseBase) {
            if (item.id() == itemId) {
                mItem = item;
                break;
            }
        }
        mIsPositiveCritique = extras.getBoolean(InitBundle.IS_POSITIVE_CRITIQUE);

        // Show the Up button in the action bar.
        setupActionBar();
        setupViews();
        setupAdapter();
        setupExplanationAdapter();
    }

    /**
     * Set up the {@link android.app.ActionBar}.
     */
    private void setupActionBar() {
        getActionBar().setHomeButtonEnabled(false);
        getActionBar().setDisplayHomeAsUpEnabled(false);
        getActionBar().setDisplayShowTitleEnabled(false);
        getActionBar().setBackgroundDrawable(null);
    }

    private void setupViews() {


        TextView question = (TextView) findViewById(R.id.textViewCritiqueQuestion);
        question.setText(getString(mIsPositiveCritique ? R.string.detail_like
                : R.string.detail_dislike, mItem.name()));

        ImageView image = (ImageView) findViewById(R.id.imageViewCritiqueImage);
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

        //mListView = (ListView) findViewById(R.id.listViewCritique);
        testLayout = (LinearLayout)findViewById(R.id.listViewCritique);
       // explanationListView = (ListView) findViewById(R.id.listViewExplanation);
        testLayout2 = (LinearLayout)findViewById(R.id.listViewExplanation);


        mButtonUpdate = (Button) findViewById(R.id.buttonRecommend);
        imageButton = (ImageButton) findViewById(R.id.buttonDiversity);
        imageButton.setMaxWidth(imageButton.getHeight());
        imageButton.setMinimumWidth(imageButton.getHeight());
        final Context context = this;
        imageButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                LayoutInflater inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View aview = inflater.inflate(R.layout.alert_title_view, null);
                TextView textView = (TextView) aview.findViewById(R.id.titleViewText);
                AlertDialog.Builder builder = new AlertDialog.Builder(context);

                builder.setCustomTitle(aview);
                builder.setItems(new CharSequence[]
                                {"high diversity", "normal diversity", "low diversity"},
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // The 'which' argument contains the index position
                                // of the selected item
                                switch (which) {
                                    case 0:
                                        AdaptiveSelection.get().setAlpha(2);
                                        Statistics.get().alphaChanged();
                                        break;
                                    case 1:
                                        AdaptiveSelection.get().setAlpha(1);
                                        Statistics.get().alphaChanged();
                                        break;
                                    case 2:
                                        AdaptiveSelection.get().setAlpha(0);
                                        Statistics.get().alphaChanged();
                                        break;


                                }
                            }
                        });
                builder.create().show();
            }
        });
        mButtonUpdate.setEnabled(false);
        mButtonUpdate.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                onUpdateQuery();
            }

        });
    }

    private void setupAdapter() {
        mAdapter = new ItemFeatureAdapter(this);
        mAdapter.addAll(mItem.attributes().getAllAttributes());
        for (Attributes.Attribute attr : mItem.attributes().getAllAttributes()){
            if (attr.id().equals(Sex.ID)){ // Do not include Sex - we will include this in the algorithm.
                mAdapter.remove(attr);
            }
        }
        //mListView.setAdapter(mAdapter);
        final int adapterCount = mAdapter.getCount();

        for (int i = 0; i < adapterCount; i++) {
            View item = mAdapter.getView(i, null, null);
            testLayout.addView(item);
        }
    }

    private void setupExplanationAdapter() {
        explanationAdapter = new ExplanationAdapter(this);

       // explanationAdapter.add(new SimpleExplanation("for testing", SimpleExplanation.IconType.TYPE));
       // explanationAdapter.add(new SimpleExplanation("for testing", SimpleExplanation.IconType.LABEL));
       // explanationAdapter.add(new SimpleExplanation("for testing", SimpleExplanation.IconType.PRICE));
        for (SimpleExplanation explanation1: mItem.getExplanation().getSimpleExplanations()){
            explanationAdapter.add(explanation1);

        }
       // explanationListView.setAdapter(explanationAdapter);
        final int adapterCount = explanationAdapter.getCount();

        for (int i = 0; i < adapterCount; i++) {
            View item = explanationAdapter.getView(i, null, null);
            testLayout2.addView(item);
        }
    }

    @Override
    public void onStart() {
        super.onStart();

        Tracker t = ((ShoprApp) getApplication()).getTracker();
        t.send(new HitBuilders.EventBuilder().setCategory("Critique").setAction("Start").setValue(System.currentTimeMillis()).build());
    }

    @Override
    public void onStop() {
        super.onStop();

        Tracker t = ((ShoprApp) getApplication()).getTracker();
        t.send(new HitBuilders.EventBuilder().setCategory("Critique").setAction("Stop").setValue(System.currentTimeMillis()).build());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.critique, menu);
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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // This ID represents the Home or Up button. In the case of this
                // activity, the Up button is shown. Use NavUtils to allow users
                // to navigate up one level in the application structure. For
                // more details, see the Navigation pattern on Android Design:
                //
                // http://developer.android.com/design/patterns/navigation.html#up-vs-back
                //
                NavUtils.navigateUpFromSameTask(this);
                return true;
            case R.id.action_settings:
                startActivity(new Intent(this, SettingsActivity.class));
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void onUpdateQuery() {
        // Get selected attributes
        Feedback feedback = new Feedback();
        feedback.isPositiveFeedback(mIsPositiveCritique);

        SparseBooleanArray checkedPositions = mAdapter.getCheckedPositions();
        for (int i = 0; i < mAdapter.getCount(); i++) {
            if (checkedPositions.get(i)) {
                feedback.addAttributes(mAdapter.getItem(i));
            }
        }

        Critique critique = new Critique();
        critique.item(mItem);
        critique.feedback(feedback);

        // Submit to algorithm backend
        AdaptiveSelection.get().submitCritique(critique);

        // Record critiquing cycle
        Statistics.get().incrementCycleCount(mIsPositiveCritique);

        setResult(RESULT_OK);
        finish();
    }

    public class ItemFeatureAdapter extends ArrayAdapter<Attributes.Attribute> {

        private static final int LAYOUT = R.layout.feature_row;
        private LayoutInflater mLayoutInflater;
        private SparseBooleanArray mCheckedPositions = new SparseBooleanArray();

        public ItemFeatureAdapter(Context context) {
            super(context, LAYOUT);
            mLayoutInflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            ViewHolder holder;

            if (convertView == null) {
                convertView = mLayoutInflater.inflate(LAYOUT, parent, false);

                holder = new ViewHolder();
                holder.title = (TextView) convertView.findViewById(R.id.textViewFeatureTitle);
                holder.value = (CheckBox) convertView.findViewById(R.id.checkBoxFeatureValue);

                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            Attributes.Attribute item = getItem(position);

            holder.value.setOnCheckedChangeListener(new OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    mCheckedPositions.put(position, isChecked);

                    // enable update button if at least one item is selected
                    boolean isButtonEnabled = false;
                    for (int i = 0; i < mCheckedPositions.size(); i++) {
                        if (mCheckedPositions.valueAt(i)) {
                            isButtonEnabled = true;
                            break;
                        }
                    }
                    mButtonUpdate.setEnabled(isButtonEnabled);
                }
            });

            Log.d("ID", item.toString());

            String title = "";
            String id = item.id();
            if (id.equals(Color.ID)) {
                title = getContext().getString(R.string.color);
            } else if (id.equals(Label.ID)) {
                title = getContext().getString(R.string.label);
            } else if (id.equals(ClothingType.ID)) {
                title = getContext().getString(R.string.type);
            } else if (id.equals(Price.ID)) {
                title = getContext().getString(R.string.price);
            } else if (id.equals(Sex.ID)) {
                title = getContext().getString(R.string.sex);
            }

            holder.title.setText(title);
            if (!id.equals(Label.ID)) {
                holder.value.setText(ValueConverter.getLocalizedStringForValue(getContext(), item
                        .currentValue().descriptor()));
            } else {
                holder.value.setText(item.currentValue().descriptor());
            }

            return convertView;
        }

        public SparseBooleanArray getCheckedPositions() {
            return mCheckedPositions;
        }

    }


    static class ViewHolder {
        TextView title;
        CheckBox value;
    }


}
