package com.uwetrottmann.shopr.context.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.uwetrottmann.shopr.R;

/**
 * Created by Yannick on 06.02.15.
 *
 * This class provides a fragment where the shop-specific context can be set.
 */
public class ContextShopFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.context_fragment_shop, container, false);

        Button submit = ((Button) view.findViewById(R.id.button_context_proceed));

        submit.setOnClickListener(new View.OnClickListener() {

            public void onClick(android.view.View v) {
                ((ContextActivity) getActivity()).changeToSecondScreen();
            }

        });
        super.onCreateView(inflater, container, savedInstanceState);
        return view;
    }

}
