package de.tum.in.schlichter.shoprx.ui.explanation.Prefrences;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import de.tum.in.schlichter.shoprx.R;
import de.tum.in.schlichter.shoprx.algorithm.model.Attributes;
import de.tum.in.schlichter.shoprx.algorithm.model.Label;

/**
 * Created by Nicksteal on 11.04.2015.
 */
public class LabelPreferenceFragment extends PreferenceFragment {
    private EditText searchText;
    private Button searchButton;

    protected final Attributes.Attribute attribute() {
        return new Label();
    }

    protected final int layout() {
        return R.layout.explanation_label_preference;
    }

    protected final TextView explanationView() {
        return (TextView) getV().findViewById(R.id.textViewAttributeValuePreference);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        setV(inflater.inflate(R.layout.explanation_label_preference,
                container, false));
        setupViews();


        return getV();
    }

    protected final Button updatePreferenceButton() {
        return (Button) getV().findViewById(R.id.buttonUpdatePreferences);
    }

    protected final ArrayAdapter<Attributes.AttributeValue> attributeValueAdapter() {
        return new MultiStateArrayAdapterStrings(getActivity());
       // return new LabelAdapter(getActivity());
    }

    protected final AbsListView attributeValueList() {
        return (ListView) getV().findViewById(R.id.listViewAttributeValueList);
    }
    @Override
    protected void doOptionalStuff(){


        searchButton = (Button)getV().findViewById(R.id.searchButton);
        searchText = (EditText) getV().findViewById(R.id.searchLabelField);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String search = searchText.getText().toString().toLowerCase().replace(".","").replace("&","");
                ArrayList<Attributes.AttributeValue> searchHits = new ArrayList<Attributes.AttributeValue>();
                for (int i=0;i< attribute().getAttributeValues().length;i++){
                    Attributes.AttributeValue attrValue = attribute().getAttributeValues()[i];
                    String labelString = attrValue.getValueName().toLowerCase().replace(".","").replace("&","");
                    if (labelString.contains(search)){
                        searchHits.add(attrValue);
                    }
                }
                mAdapter.clear();
                mAdapter.addAll(searchHits);

                mAdapter.notifyDataSetChanged();
              //  mAttributeValueListView.invalidateViews();
            }
        });


    }

   /* public class LabelAdapter extends ArrayAdapter<Attributes.AttributeValue> {

        private static final int LAYOUT = R.layout.common_checkable_row;
        private LayoutInflater mInflater;

        public LabelAdapter(Context context) {
            super(context, LAYOUT);
            mInflater = LayoutInflater.from(context);
        }

        @Override
        public View getView(final int position, View convertView,
                            ViewGroup parent) {
            ViewHolder holder;

            if (convertView == null) {
                convertView = mInflater.inflate(LAYOUT, null);

                holder = new ViewHolder();
                holder.name = (TextView) convertView.findViewById(R.id.textViewTitle);


                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            final Attributes.AttributeValue value = getItem(position);
            holder.name.setText(value.descriptor());

            return convertView;
        }

        class ViewHolder {
            TextView name;
        }
    }*/


    public class MultiStateArrayAdapterStrings extends ArrayAdapter<Attributes.AttributeValue> {

        private static final int LAYOUT = R.layout.common_checkable_row;
        private LayoutInflater mLayoutInflater;

        public MultiStateArrayAdapterStrings(Context context) {
            super(context, R.layout.explanation_attribute_value_layout);

            mLayoutInflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        @Override
        public View getView(final int position, View convertView, final ViewGroup parent) {
            final ViewHolderMultiStateAdapterStrings holder;


            if (true/*convertView == null||!(convertView.getClass()==ViewHolderMultiStateAdapterStrings.class)*/) {
                holder = new ViewHolderMultiStateAdapterStrings();
                convertView = mLayoutInflater.inflate(LAYOUT, parent, false);
                holder.name = (TextView) convertView
                        .findViewById(R.id.textViewTitle);

                convertView.setTag(holder);
            } else {
                holder = (ViewHolderMultiStateAdapterStrings) convertView.getTag();
            }

            final Attributes.AttributeValue attrValue = getItem(position);
          /*  String search = searchText.getText().toString().toLowerCase();
            String labelString = attrValue.getValueName().toLowerCase();
            if (search !=null && search.length()>1 && !labelString.contains(search))return new View(getContext());*/
            holder.name.setText(attrValue.descriptor());
            if (!getCheckStatus().containsKey(attrValue.getValueName())||getCheckStatus().get(attrValue.getValueName())==0)
                convertView.setBackgroundResource( R.drawable.noselect_background_shopr);
            else if (getCheckStatus().get(attrValue.getValueName())==1)
                convertView.setBackgroundResource( R.drawable.checked_background_shopr);
            else if(getCheckStatus().get(attrValue.getValueName())==2)
                convertView.setBackgroundResource( R.drawable.delete_background_shopr);
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

    }
    static class ViewHolderMultiStateAdapterStrings{
        TextView name;
    }


}