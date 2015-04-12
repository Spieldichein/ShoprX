package de.tum.in.schlichter.shoprx.ui.explanation.Prefrences;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.text.Html;
import android.text.TextUtils;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Map;

import de.tum.in.schlichter.shoprx.Explanations.Model.ShoprPreference;
import de.tum.in.schlichter.shoprx.R;
import de.tum.in.schlichter.shoprx.algorithm.AdaptiveSelection;
import de.tum.in.schlichter.shoprx.algorithm.model.Attributes.AttributeValue;
import de.tum.in.schlichter.shoprx.algorithm.model.Attributes.Attribute;
import de.tum.in.schlichter.shoprx.eval.Statistics;

/**
 * Created by Nicksteal on 06.04.2015.
 */
public abstract class PreferenceFragment extends Fragment implements
        OnItemClickListener {

    public static String EXTRAS_ATTRIBUTE_VALUE = "toEdit";

    private Button mButtonUpdatePreferences;
    protected ArrayAdapter<AttributeValue> mAdapter;
    protected AbsListView mAttributeValueListView;
    private String attributeValue = "";
    private View v;
    private Map <String,Integer> checkStatus;
    protected AttributeValue[] helpingAttrArray;

    @Override
    public final void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // setupViews();
        //setContentView(layout());
       //inflate yourself
    }

    public Map<String, Integer> getCheckStatus() {
        return checkStatus;
    }

    public void setCheckStatus(Map<String, Integer> checkStatus) {
        this.checkStatus = checkStatus;
    }

    public View getV() {
        return v;
    }

    public void setV(View v) {
        this.v = v;
    }

    public void setAttributeValue(String attributeValue) {
        this.attributeValue = attributeValue;
    }

    protected abstract int layout();

    protected abstract void doOptionalStuff();

    protected final void setupViews() {
        initExplanationView();
        doOptionalStuff();
        mButtonUpdatePreferences = initUpdatePreferenceButton();
        mAdapter = initAdapter();
        mAttributeValueListView = initAttributeValueList(mAdapter);
    }

    protected final TextView initExplanationView() {
        TextView explanation = explanationView();
        String template = getString(R.string.attribute_value_preference_update_explanation_attribute_value);
        String startTemplate = getString(R.string.attribute_value_preference_update_explanation_start);
        CharSequence changingAttributeValue = Html.fromHtml(String.format(template, attributeValue));

        String prefExp = getPreferenceExplanation();
        prefExp=prefExp.replace(" like", " <font color='#5d1291'>like</font>");
        prefExp = prefExp.replace("dislike"," <font color='red'>dislike</font>");
        Log.d("prefExp","prefExp: "+prefExp);
        if (attributeValue.isEmpty()){
            String first = startTemplate.concat(prefExp);//TextUtils.concat(startTemplate, getPreferenceExplanation());
            Log.d("prefExp","first: "+first);
            first=first.replace(" like", " <font color='#5d1291'>like</font>");
            first = first.replace("dislike"," <font color='red'>dislike</font>");
            explanation.setText(Html.fromHtml(first));
            return explanation;
        }
        CharSequence exp = TextUtils.concat(changingAttributeValue, Html.fromHtml(String.format(prefExp)));
        explanation.setText(exp);
        Log.d("prefExp","exp: "+exp);

        return explanation;
    }

    protected final Button initUpdatePreferenceButton() {
        Button updateButton = updatePreferenceButton();
        updateButton.setEnabled(true);
        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onUpdateAttributeValuePreferences();
            }
        });
        return updateButton;
    }

    protected final ArrayAdapter<AttributeValue> initAdapter() {
        ArrayAdapter<AttributeValue> adapter = attributeValueAdapter();
        adapter.clear();
        checkStatus = new HashMap<String, Integer>();
        for (int i=0;i< attribute().getAttributeValues().length;i++){
            String value = attribute().getAttributeValues()[i].getValueName();
            checkStatus.put(value,0);
        }
        adapter.addAll(attribute().getAttributeValues());

        return adapter;
    }

    protected abstract Attribute attribute();

    protected final AbsListView initAttributeValueList(
            ArrayAdapter<AttributeValue> adapter) {
        AbsListView absListView = attributeValueList();
        absListView.setOnItemClickListener(this);
        absListView.setChoiceMode(GridView.CHOICE_MODE_MULTIPLE);
        absListView.setAdapter(adapter);
        return absListView;
    }

    protected abstract TextView explanationView();

    protected abstract Button updatePreferenceButton();

    protected abstract ArrayAdapter<AttributeValue> attributeValueAdapter();

    protected abstract AbsListView attributeValueList();

    protected final String getPreferenceExplanation() {
        String baseExplanation = getString(R.string.attribute_value_preference_update_explanation);
        return String.format(baseExplanation, attribute().id());
    }

    protected final void onUpdateAttributeValuePreferences() {


        int splitter=0;
        int  divisor = attribute().getAttributeValues().length;
        for (int i=0; i< divisor;i++){
            String value =attribute().getAttributeValues()[i].getValueName();
            if (checkStatus.get(value)==0)splitter++;
            else if (checkStatus.get(value)==1)splitter = splitter+2;
        }
        ShoprPreference preference = new ShoprPreference(attribute(),checkStatus);

        for (int i=0;i < divisor;i++){
            AttributeValue value =attribute().getAttributeValues()[i];
            if (checkStatus.get(value.getValueName())==0)preference.add(value);
            else if (checkStatus.get(value.getValueName())==1)preference.addTwice(value);
        }
        AdaptiveSelection.get().submitPreference(preference);

        // Record cycles
       //TODO Statistics.get().incrementExplicityPreferenceChangeCount();
        onPreferencesUpdateFinish();
    }

    protected void onPreferencesUpdateFinish() {

        getActivity().setResult(42);
        getActivity().finish();
        //TODO restart loader query items
    }

    @Override
    public final void onItemClick(AdapterView<?> parent, View view,
                                  int position, long id) {
        //TODO
        AttributeValue value = mAdapter.getItem(position);
        int checkStat = 0;
        if (checkStatus.containsKey(value.getValueName())){
            checkStat = checkStatus.get(value.getValueName());
        }
        Log.d("checkStuff","old checkstat: "+checkStat);
        if (checkStat==2)checkStat=0;
        else checkStat++;
        checkStatus.put(value.getValueName(),checkStat);
        Log.d("checkStuff","new checkstat: "+checkStat);
        mAttributeValueListView.invalidateViews();
       // mButtonUpdatePreferences.setEnabled(true);
    }

    private boolean isButtonEnabled() {
        return hasAtLeastOneItemChecked();
    }

    private boolean hasAtLeastOneItemChecked() {
        boolean itemChecked = false;
        SparseBooleanArray checkedPositions = mAttributeValueListView
                .getCheckedItemPositions();
        for (int i = 0; i < checkedPositions.size(); i++) {
            if (checkedPositions.valueAt(i)) {
                itemChecked = true;
                break;
            }
        }

        return itemChecked;
    }



    public class MultiStateArrayAdapter extends ArrayAdapter<AttributeValue> {

        private final int LAYOUT = R.layout.explanation_attribute_value_layout;
        private LayoutInflater mLayoutInflater;
        private SparseBooleanArray mCheckedPositions = new SparseBooleanArray();

        public MultiStateArrayAdapter(Context context) {
            super(context, R.layout.explanation_attribute_value_layout);

            mLayoutInflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        @Override
        public View getView(final int position, View convertView, final ViewGroup parent) {
            final ViewHolderMultiStateAdapter holder;


            if (convertView == null) {
                holder = new ViewHolderMultiStateAdapter();
                convertView = mLayoutInflater.inflate(LAYOUT, parent, false);
                holder.pictureContainer = convertView
                        .findViewById(R.id.containerAttributeValuePicture);
                holder.picture = (ImageView) convertView
                        .findViewById(R.id.imageViewAttributeValuePicture);
                holder.name = (TextView) convertView
                        .findViewById(R.id.textViewAttributeValueName);

                convertView.setTag(holder);
            } else {
                holder = (ViewHolderMultiStateAdapter) convertView.getTag();
            }

            final AttributeValue attrValue = getItem(position);
            holder.name.setText(attrValue.descriptor());
            holder.picture.setBackgroundDrawable(getDrawableOrDefault(attrValue.getSimpleName()));
            getContext().getResources().getDrawable(R.drawable.checked_attribute_preference_background_shopr);
            if (!checkStatus.containsKey(attrValue.getValueName())||checkStatus.get(attrValue.getValueName())==0)
                convertView.setBackgroundResource( R.drawable.selectable_background_shopr);
            else if (checkStatus.get(attrValue.getValueName())==1)
                convertView.setBackgroundResource( R.drawable.checked_background_shopr);
            else if(checkStatus.get(attrValue.getValueName())==2)
            convertView.setBackgroundResource( R.drawable.delete_background_shopr);
            // checked_attribute_preference_background_shopr
            return convertView;
        }

        private Drawable getDrawableOrDefault(String name) {
            Resources resources = getContext().getResources();
            final int resourceId = getContext().getResources().getIdentifier(
                    name, "drawable", getContext().getPackageName());

            try {
                Drawable drawable = resources.getDrawable(resourceId);
                return drawable;
            } catch (Resources.NotFoundException e) {
                return resources.getDrawable(R.drawable.ic_action_tshirt);
            }
        }
        public SparseBooleanArray getCheckedPositions() {
            return mCheckedPositions;
        }

    }
    static class ViewHolderMultiStateAdapter{
        View pictureContainer;
        ImageView picture;
        TextView name;
    }

}