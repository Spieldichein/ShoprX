package com.uwetrottmann.shopr.context.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Spinner;

import com.uwetrottmann.shopr.R;
import com.uwetrottmann.shopr.context.model.ScenarioContext;

/**
 * Created by Yannick on 06.02.15.
 *
 * In this Fragment other context factors, such as the company you are with can be set.
 */
public class ContextOtherFragment extends Fragment implements View.OnClickListener {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.context_fragment_other, container, false);
        Button submit = ((Button) view.findViewById(R.id.button_context_proceed));

        submit.setOnClickListener(this);

        super.onCreateView(inflater, container, savedInstanceState);
        return view;
    }


    @Override
    public void onClick(View view) {
        Spinner temperature = (Spinner) getActivity().findViewById(R.id.contextSpinnerTemperature);
        Spinner company = (Spinner) getActivity().findViewById(R.id.contextSpinnerCompany);
        Spinner weather = (Spinner) getActivity().findViewById(R.id.contextSpinnerWeather);

        ScenarioContext scenarioContext = ScenarioContext.getInstance();
        scenarioContext.setTemperature((String) temperature.getSelectedItem());
        scenarioContext.setCompany((String) company.getSelectedItem());
        scenarioContext.setWeather((String) weather.getSelectedItem());

        ((ContextActivity) getActivity()).contextDeterminationFinished();
    }
}
