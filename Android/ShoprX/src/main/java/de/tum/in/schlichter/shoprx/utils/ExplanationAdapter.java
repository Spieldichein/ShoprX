package de.tum.in.schlichter.shoprx.utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.text.Html;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import de.tum.in.schlichter.shoprx.Explanations.Model.SimpleExplanation;
import de.tum.in.schlichter.shoprx.R;
import de.tum.in.schlichter.shoprx.algorithm.AdaptiveSelection;
import de.tum.in.schlichter.shoprx.eval.Statistics;
import de.tum.in.schlichter.shoprx.ui.explanation.HelpActivity;
import de.tum.in.schlichter.shoprx.ui.explanation.MindMap.PriceRangeFragment;

/**
 * Created by Nicksteal on 04.04.2015.
 */
public class ExplanationAdapter extends ArrayAdapter<SimpleExplanation> {

    private static final int LAYOUT = R.layout.explanation_row;
    private LayoutInflater mLayoutInflater;
    private SparseBooleanArray mCheckedPositions = new SparseBooleanArray();
    private Activity activity;

    public ExplanationAdapter(Context context) {
        super(context, LAYOUT);
        if (context instanceof Activity) {
            Log.d("ActivityContext","LOL");

            activity = (Activity)context;
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
            holder.goOn = (ImageView) convertView.findViewById(R.id.iconClickable);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolderExplanation) convertView.getTag();
        }

        final SimpleExplanation explanation = getItem(position);
        CharSequence test = Html.fromHtml(explanation.getText());
        holder.title.setText(test);
        switch (explanation.getIconType()){
            case PRICE:
                holder.icon.setImageResource(R.drawable.euro);
                holder.goOn.setVisibility(View.VISIBLE);
                break;
            case COLOR:
                holder.icon.setImageResource(R.drawable.color);
                holder.goOn.setVisibility(View.VISIBLE);
                break;
            case TYPE:
                holder.icon.setImageResource(R.drawable.clothing);
                holder.goOn.setVisibility(View.VISIBLE);
                break;
            case LABEL:
                holder.icon.setImageResource(R.drawable.brand);
                holder.goOn.setVisibility(View.VISIBLE);
                break;
            case RANDOM:
                holder.icon.setImageResource(R.drawable.random);
                holder.goOn.setVisibility(View.VISIBLE);
                break;
            case WEATHER:
                holder.icon.setImageResource(R.drawable.weather);
                holder.goOn.setVisibility(View.GONE);
                break;
            case TEMPERATURE:
                holder.icon.setImageResource(R.drawable.temperature);
                holder.goOn.setVisibility(View.GONE);
                break;
            case TRENDY:
                holder.icon.setImageResource(R.drawable.trendy);
                holder.goOn.setVisibility(View.VISIBLE);
                break;
            case LAST_CRITIQUE:
                holder.icon.setImageResource(R.drawable.repeat);
                holder.goOn.setVisibility(View.GONE);
                break;
            case LOCATION:
                holder.icon.setImageResource(R.drawable.location);
                holder.goOn.setVisibility(View.GONE);
                break;
            default:
                holder.icon.setImageResource(R.drawable.euro);
                holder.goOn.setVisibility(View.GONE);
        }

        View.OnClickListener onClickListener = new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent;
                switch (explanation.getIconType()){
                    case PRICE:
                        intent = new Intent(getContext(), HelpActivity.class);
                        intent.putExtra(HelpActivity.InitBundle.PUSHED_VIEW, "price");
                        intent.putExtra(HelpActivity.InitBundle.PUSHING_VIEW, "null"); // Start for counting is 0
                        Statistics.get().chartStarted();

                        //getContext().startActivity(intent);
                        activity.startActivityForResult(intent,1337);

                        break;
                    case COLOR:
                        intent = new Intent(getContext(), HelpActivity.class);
                        intent.putExtra(HelpActivity.InitBundle.PUSHED_VIEW, "color");
                        intent.putExtra(HelpActivity.InitBundle.PUSHING_VIEW, "null"); // Start for counting is 0
                        Statistics.get().chartStarted();

                        activity.startActivityForResult(intent,1337);
                        break;
                    case TYPE:
                        intent = new Intent(getContext(), HelpActivity.class);
                        intent.putExtra(HelpActivity.InitBundle.PUSHED_VIEW, "type");
                        intent.putExtra(HelpActivity.InitBundle.PUSHING_VIEW, "null"); // Start for counting is 0
                        Statistics.get().chartStarted();
                        activity.startActivityForResult(intent,1337);
                        break;
                    case LABEL:
                        intent = new Intent(getContext(), HelpActivity.class);
                        intent.putExtra(HelpActivity.InitBundle.PUSHED_VIEW, "label");
                        intent.putExtra(HelpActivity.InitBundle.PUSHING_VIEW, "null"); // Start for counting is 0
                        Statistics.get().labelPrefrenceStarted();
                        activity.startActivityForResult(intent,1337);
                        break;
                    case RANDOM:
                        LayoutInflater inflater= (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                        View view = inflater.inflate(R.layout.alert_title_view, null);
                        TextView textView = (TextView) view.findViewById(R.id.titleViewText);
                        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

                        builder.setCustomTitle(view);
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
                        break;
                    case TRENDY:
                        inflater= (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                        view = inflater.inflate(R.layout.alert_title_view, null);
                        textView = (TextView) view.findViewById(R.id.titleViewText);
                        builder = new AlertDialog.Builder(getContext());
                        textView.setText("Our recommended items are selected by award winning fashion bloggers.");

                        builder.setCustomTitle(view);
                        builder.setPositiveButton("ok",null);
                       // builder.setCancelable(false);
                       /* builder.setItems(new CharSequence[]
                                        {"ok"},
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        // The 'which' argument contains the index position
                                        // of the selected item

                                    }
                                });*/
                        builder.create().show();

                        break;
                    default:

                        break;
                }

            }
        };
        holder.icon.setOnClickListener(onClickListener);
        holder.title.setOnClickListener(onClickListener);
        holder.goOn.setOnClickListener(onClickListener);
        //todo push correct screen for correcting algorithm



        return convertView;
    }

    public SparseBooleanArray getCheckedPositions() {
        return mCheckedPositions;
    }


    static class ViewHolderExplanation{
        TextView title;
        ImageView icon;
        ImageView goOn;
    }
}



