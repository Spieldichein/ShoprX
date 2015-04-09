package de.tum.in.schlichter.shoprx.utils;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import de.tum.in.schlichter.shoprx.Explanations.Model.SimpleExplanation;
import de.tum.in.schlichter.shoprx.R;
import de.tum.in.schlichter.shoprx.ui.explanation.HelpActivity;
import de.tum.in.schlichter.shoprx.ui.explanation.MindMap.PriceRangeFragment;

/**
 * Created by Nicksteal on 04.04.2015.
 */
public class ExplanationAdapter extends ArrayAdapter<SimpleExplanation> {

    private static final int LAYOUT = R.layout.explanation_row;
    private LayoutInflater mLayoutInflater;
    private SparseBooleanArray mCheckedPositions = new SparseBooleanArray();
    private FragmentActivity activityContext;

    public ExplanationAdapter(Context context) {
        super(context, LAYOUT);
        if (context instanceof FragmentActivity) {
            Log.d("ActivityContext","LOL");

            activityContext = (FragmentActivity)context;
        }
        mLayoutInflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {
        final ViewHolderExplanation holder;


        if (convertView == null) {
            convertView = mLayoutInflater.inflate(LAYOUT, parent, false);

            holder = new ViewHolderExplanation();
            holder.title = (TextView) convertView.findViewById(R.id.explanationText);
            holder.icon = (ImageView) convertView.findViewById(R.id.imageViewExplanationIcon);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolderExplanation) convertView.getTag();
        }

        final SimpleExplanation explanation = getItem(position);
        holder.title.setText(explanation.getText());
        switch (explanation.getIconType()){
            case PRICE:
                holder.icon.setImageResource(R.drawable.euro);
                break;
            case COLOR:
                holder.icon.setImageResource(R.drawable.color);
                break;
            case TYPE:
                holder.icon.setImageResource(R.drawable.clothing);
                break;
            case LABEL:
                holder.icon.setImageResource(R.drawable.brand);
                break;
            case RANDOM:
                holder.icon.setImageResource(R.drawable.exploring);
                break;
            case WEATHER:
                holder.icon.setImageResource(R.drawable.weather);
                break;
            case TEMPERATURE:
                holder.icon.setImageResource(R.drawable.temperature);
                break;
            case TRENDY:
                holder.icon.setImageResource(R.drawable.trendy);
                break;
            default:
                holder.icon.setImageResource(R.drawable.euro);
        }

        holder.icon.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {
                Intent intent;
                switch (explanation.getIconType()){
                    case PRICE:
                        intent = new Intent(getContext(), HelpActivity.class);
                        intent.putExtra(HelpActivity.InitBundle.PUSHED_VIEW, "price");
                        intent.putExtra(HelpActivity.InitBundle.PUSHING_VIEW, "null"); // Start for counting is 0
                        getContext().startActivity(intent);
                        break;
                    case COLOR:
                        intent = new Intent(getContext(), HelpActivity.class);
                        intent.putExtra(HelpActivity.InitBundle.PUSHED_VIEW, "color");
                        intent.putExtra(HelpActivity.InitBundle.PUSHING_VIEW, "null"); // Start for counting is 0
                        getContext().startActivity(intent);
                        break;
                    case TYPE:
                        intent = new Intent(getContext(), HelpActivity.class);
                        intent.putExtra(HelpActivity.InitBundle.PUSHED_VIEW, "type");
                        intent.putExtra(HelpActivity.InitBundle.PUSHING_VIEW, "null"); // Start for counting is 0
                        getContext().startActivity(intent);
                        break;
                    case LABEL:
                        intent = new Intent(getContext(), HelpActivity.class);
                        intent.putExtra(HelpActivity.InitBundle.PUSHED_VIEW, "label");
                        intent.putExtra(HelpActivity.InitBundle.PUSHING_VIEW, "null"); // Start for counting is 0
                        getContext().startActivity(intent);
                        break;
                    default:
                        Log.d("DEFAULT","LOL");

                        break;
                }
                //todo push correct screen for correcting algorithm
            }
        });




        return convertView;
    }

    public SparseBooleanArray getCheckedPositions() {
        return mCheckedPositions;
    }


    static class ViewHolderExplanation{
        TextView title;
        ImageView icon;
    }
}



