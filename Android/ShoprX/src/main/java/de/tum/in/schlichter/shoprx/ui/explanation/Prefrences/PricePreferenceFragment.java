package de.tum.in.schlichter.shoprx.ui.explanation.Prefrences;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import de.tum.in.schlichter.shoprx.R;
import de.tum.in.schlichter.shoprx.algorithm.model.Attributes.AttributeValue;
import de.tum.in.schlichter.shoprx.algorithm.model.Attributes.Attribute;
import de.tum.in.schlichter.shoprx.algorithm.model.Price;

/**
 * Created by Nicksteal on 10.04.2015.
 */
public class PricePreferenceFragment extends PreferenceFragment {

    protected final Attribute attribute() {
        return new Price();
    }

    protected final int layout() {
        return R.layout.explanation_price_preference;
    }

    protected final TextView explanationView() {
        return (TextView) getV().findViewById(R.id.textViewAttributeValuePreference);
    }

    protected final Button updatePreferenceButton() {
        return (Button) getV().findViewById(R.id.buttonUpdatePreferences);
    }
    @Override
    protected void doOptionalStuff(){

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        setV(inflater.inflate(R.layout.explanation_price_preference,
                container, false));
        setupViews();


        return getV();
    }

    protected final ArrayAdapter<AttributeValue> attributeValueAdapter() {
        return new MultiStateArrayAdapterStrings(getActivity());
        //return new PriceRangeAdapter(getActivity());
    }

    protected final AbsListView attributeValueList() {
        return (ListView) getV().findViewById(R.id.listViewAttributeValueList);
    }

    public class PriceRangeAdapter extends ArrayAdapter<AttributeValue> {

        private static final int LAYOUT = R.layout.common_checkable_row;
        private LayoutInflater mInflater;

        public PriceRangeAdapter(Context context) {
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

            final AttributeValue value = getItem(position);
            holder.name.setText(value.descriptor());

            return convertView;
        }

        class ViewHolder {
            TextView name;
        }
    }


    public class MultiStateArrayAdapterStrings extends ArrayAdapter<AttributeValue> {

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


            if (convertView == null) {
                holder = new ViewHolderMultiStateAdapterStrings();
                convertView = mLayoutInflater.inflate(LAYOUT, parent, false);
                holder.name = (TextView) convertView
                        .findViewById(R.id.textViewTitle);

                convertView.setTag(holder);
            } else {
                holder = (ViewHolderMultiStateAdapterStrings) convertView.getTag();
            }

            final AttributeValue attrValue = getItem(position);
            holder.name.setText(attrValue.descriptor());
            if (!getCheckStatus().containsKey(attrValue.getValueName())||getCheckStatus().get(attrValue.getValueName())==0)
                convertView.setBackgroundResource( R.drawable.selectable_background_shopr);
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