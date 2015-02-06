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
 * In this Fragment other context factors, such as the company you are with can be set.
 */
public class ContextOtherFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.context_fragment_other, container, false);
        Button submit = ((Button) view.findViewById(R.id.button_context_proceed));

        submit.setOnClickListener(new View.OnClickListener() {

            public void onClick(android.view.View v) {
                ((ContextActivity) getActivity()).contextDeterminationFinished();
            }

        });

        super.onCreateView(inflater, container, savedInstanceState);
        return view;
    }



}
