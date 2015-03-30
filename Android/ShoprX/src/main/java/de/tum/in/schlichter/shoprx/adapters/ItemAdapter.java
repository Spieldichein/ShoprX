
package de.tum.in.schlichter.shoprx.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

import de.tum.in.schlichter.androidutils.CheatSheet;
import de.tum.in.schlichter.shoprx.Explanations.Model.AbstractExplanation;
import de.tum.in.schlichter.shoprx.Explanations.Model.DimensionArgument;
import de.tum.in.schlichter.shoprx.Explanations.Model.SimpleExplanation;
import de.tum.in.schlichter.shoprx.R;
import de.tum.in.schlichter.shoprx.algorithm.AdaptiveSelection;
import de.tum.in.schlichter.shoprx.algorithm.model.Color;
import de.tum.in.schlichter.shoprx.algorithm.model.Item;
import de.tum.in.schlichter.shoprx.utils.ValueConverter;

public class ItemAdapter extends ArrayAdapter<Item> {

    private static final int LAYOUT = R.layout.item_layout;

    private LayoutInflater mInflater;

    private OnItemCritiqueListener mCritiqueListener;

    private OnItemDisplayListener mItemListener;

    public interface OnItemCritiqueListener {
        public void onItemCritique(Item item, boolean isLike);
    }

    public interface OnItemDisplayListener {
        public void onItemDisplay(Item item);
    }

    public ItemAdapter(Context context, OnItemCritiqueListener critiqueListener,
            OnItemDisplayListener itemListener) {
        super(context, LAYOUT);
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mCritiqueListener = critiqueListener;
        mItemListener = itemListener;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = mInflater.inflate(LAYOUT, null);

            holder = new ViewHolder();
            holder.pictureContainer = convertView.findViewById(R.id.containerItemPicture);
            holder.picture = (ImageView) convertView.findViewById(R.id.imageViewItemPicture);
            holder.name = (TextView) convertView.findViewById(R.id.textViewItemName);
            holder.label = (TextView) convertView.findViewById(R.id.textViewItemLabel);
            holder.price = (TextView) convertView.findViewById(R.id.textViewItemPrice);
            holder.buttonLike = (ImageButton) convertView.findViewById(R.id.imageButtonItemLike);
            holder.buttonDislike = (ImageButton) convertView
                    .findViewById(R.id.imageButtonItemDislike);
            holder.lastCritiqueTag = convertView.findViewById(R.id.textViewItemLastCritiqueLabel);
            holder.iconLayout = (LinearLayout)convertView.findViewById(R.id.explanationIconContainer);


            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        final Item item = getItem(position);
        holder.name.setText(item.name());
        holder.label.setText(ValueConverter.getLocalizedStringForValue(getContext(), item
                .attributes().getAttributeById(Color.ID).currentValue()
                .descriptor()));
        holder.price.setText(NumberFormat.getCurrencyInstance(Locale.GERMANY).format(
                item.price().doubleValue()));
        holder.buttonLike.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mCritiqueListener != null) {
                    mCritiqueListener.onItemCritique(item, true);
                }
            }
        });
        CheatSheet.setup(holder.buttonLike, R.string.like);
        holder.buttonDislike.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mCritiqueListener != null) {
                    mCritiqueListener.onItemCritique(item, false);
                }
            }
        });
        CheatSheet.setup(holder.buttonDislike, R.string.dislike);

        holder.pictureContainer.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mItemListener != null) {
                    mItemListener.onItemDisplay(item);
                }
            }
        });

        // last critique tag
        int lastCritiquedId = AdaptiveSelection.get().getLastCritiquedItem() != null ?
                AdaptiveSelection.get().getLastCritiquedItem().id()
                : -1;
        holder.lastCritiqueTag.setVisibility(item.id() == lastCritiquedId ? View.VISIBLE
                : View.GONE);

        // load picture
        Picasso.with(getContext())
                .load(item.image())
                .placeholder(null)
                .error(R.drawable.ic_action_tshirt)
                .resizeDimen(R.dimen.default_image_width, R.dimen.default_image_height)
                .centerCrop()
                .into(holder.picture);


        AbstractExplanation abstractExplanation = item.getExplanation().getAbstractExplanation();
        ArrayList<SimpleExplanation> simpleExplanations = new ArrayList<SimpleExplanation>();
        if (abstractExplanation.hasPrimaryArguments()){
            Object[] arguments = abstractExplanation.primaryArguments().toArray();
            for ( Object argument :arguments){
                DimensionArgument dimensionArgument = (DimensionArgument) argument;
                if (dimensionArgument.dimension()!=null){
                    SimpleExplanation simpleExplanation = new SimpleExplanation(dimensionArgument.dimension());
                    simpleExplanations.add(simpleExplanation);
                }
            }
        }
        else if(abstractExplanation.hasSupportingArguments()){

        }
        else if (abstractExplanation.hasContextArguments()){

        }
        //holder.iconLayout


        for (SimpleExplanation explanation: simpleExplanations){
            ImageView imageView = new ImageView(getContext());
            imageView.setMaxHeight(10);
            imageView.setMaxWidth(10);
            switch (explanation.getIconType()){
                case PRICE:
                    imageView.setImageResource(R.drawable.euro);
                    break;
                case COLOR:
                    imageView.setImageResource(R.drawable.color);
                    break;
                default:
                    imageView.setImageResource(R.drawable.euro);
                    break;
            }
            holder.iconLayout.addView(imageView);
        }
        return convertView;
    }


    static class ViewHolder {
        LinearLayout iconLayout;
        View pictureContainer;
        ImageView picture;
        TextView name;
        TextView label;
        TextView price;
        ImageButton buttonLike;
        ImageButton buttonDislike;
        View lastCritiqueTag;
    }

}
