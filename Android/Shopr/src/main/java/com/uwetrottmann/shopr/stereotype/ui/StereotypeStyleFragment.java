package com.uwetrottmann.shopr.stereotype.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.uwetrottmann.shopr.R;
import com.uwetrottmann.shopr.stereotype.adapter.StereotypeStylesAdapter;
import com.uwetrottmann.shopr.stereotype.stereotypes.AbstractStereotype;
import com.uwetrottmann.shopr.stereotype.user.User;
import com.uwetrottmann.shopr.ui.MainActivity;

import java.util.List;

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

                Log.d("Stereotype set to", stereotype.getStereotype().name());

                Intent intent = new Intent(getActivity(), MainActivity.class);
                startActivity(intent);
                getActivity().finish();
            }

        });

        return view;
    }
}
