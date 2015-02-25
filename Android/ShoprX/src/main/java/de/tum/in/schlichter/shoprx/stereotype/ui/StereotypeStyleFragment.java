package de.tum.in.schlichter.shoprx.stereotype.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import de.tum.in.schlichter.shoprx.R;

import java.util.List;

import de.tum.in.schlichter.shoprx.context.ui.ContextActivity;
import de.tum.in.schlichter.shoprx.eval.Statistics;
import de.tum.in.schlichter.shoprx.stereotype.adapter.StereotypeStylesAdapter;
import de.tum.in.schlichter.shoprx.stereotype.stereotypes.AbstractStereotype;
import de.tum.in.schlichter.shoprx.stereotype.user.User;

/**
 * Created by Yannick on 29.01.15.
 *
 * In this fragment, the pictures for the final stereotype selection are displayed.
 */
public class StereotypeStyleFragment extends Fragment {

    List<AbstractStereotype> topThreeStereotypes;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        topThreeStereotypes = User.getUser().getPotentialStereotypes();
        if (topThreeStereotypes == null || topThreeStereotypes.size() != 3) {
            Log.e(StereotypeStyleFragment.class.toString(),
                    "List of top three stereotype is not initialized.");
            throw new RuntimeException(
                    "List of top three stereotype is not initialized.");
        }
        View view = inflater.inflate(R.layout.fragment_stereotype_style,
                container, false);
        GridView gridView = (GridView) view
                .findViewById(R.id.stereotype_style_gridview);
        gridView.setAdapter(new StereotypeStylesAdapter(getActivity(), topThreeStereotypes));

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                AbstractStereotype stereotype = topThreeStereotypes
                        .get(position);
                User.getUser().setStereotype(stereotype);

                Statistics.get().setStereotype(stereotype.getStereotype().name());
                Log.d("Stereotype set to", stereotype.getStereotype().name());

                Intent intent = new Intent(getActivity(), ContextActivity.class);
                startActivity(intent);
                getActivity().finish();
            }

        });

        return view;
    }
}
