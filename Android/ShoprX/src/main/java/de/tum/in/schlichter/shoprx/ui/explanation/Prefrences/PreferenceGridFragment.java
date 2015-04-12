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
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import de.tum.in.schlichter.shoprx.R;
import de.tum.in.schlichter.shoprx.algorithm.model.Attributes.Attribute;
import de.tum.in.schlichter.shoprx.algorithm.model.Attributes.AttributeValue;

/**
 * Created by Nicksteal on 06.04.2015.
 */
public abstract class PreferenceGridFragment extends
        PreferenceFragment {

    protected abstract Attribute attribute();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        setV(inflater.inflate(R.layout.explanation_attribute_value_preference,
                container, false));
        setupViews();


        return getV();
    }

    @Override
    protected int layout() {
        return R.layout.explanation_attribute_value_preference;
    }

    protected final TextView explanationView() {
        return (TextView) getV().findViewById(R.id.textViewAttributeValuePreference);
    }

    protected final Button updatePreferenceButton() {
        return (Button)  getV().findViewById(R.id.buttonUpdatePreferences);
    }
    @Override
    protected void doOptionalStuff(){

    }
    protected final ArrayAdapter<AttributeValue> attributeValueAdapter() {
        Context context = getActivity();
        return new MultiStateArrayAdapter(context);
    }

    protected final AbsListView attributeValueList() {
        GridView gridView = (GridView) getV().findViewById(R.id.gridViewAttributeValueList);
        gridView.setNumColumns(numColums());
        return gridView;
    }

    protected int numColums() {
        return 4;
    }

    public class AttributeValueAdapter extends ArrayAdapter<AttributeValue> {

        private final int LAYOUT = R.layout.explanation_attribute_value_layout;
        private LayoutInflater mInflater;

        public AttributeValueAdapter(Context context) {
            super(context, R.layout.explanation_attribute_value_layout);
            mInflater = LayoutInflater.from(context);
        }

        @SuppressWarnings("deprecation")
        @Override
        public View getView(final int position, View convertView,
                            ViewGroup parent) {
            ViewHolder holder;

            if (convertView == null) {
                convertView = mInflater.inflate(LAYOUT, null);

                holder = new ViewHolder();
                holder.pictureContainer = convertView
                        .findViewById(R.id.containerAttributeValuePicture);
                holder.picture = (ImageView) convertView
                        .findViewById(R.id.imageViewAttributeValuePicture);
                holder.name = (TextView) convertView
                        .findViewById(R.id.textViewAttributeValueName);

                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            final AttributeValue value = getItem(position);
            holder.name.setText(value.descriptor());
            holder.picture.setBackgroundDrawable(getDrawableOrDefault(value.getSimpleName()));

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

        class ViewHolder {
            View pictureContainer;
            ImageView picture;
            TextView name;
        }
    }

}
