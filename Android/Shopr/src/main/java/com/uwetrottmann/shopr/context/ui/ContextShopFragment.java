package com.uwetrottmann.shopr.context.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Switch;

import com.uwetrottmann.shopr.R;
import com.uwetrottmann.shopr.context.model.ScenarioContext;

/**
 * Created by Yannick on 06.02.15.
 *
 * This class provides a fragment where the shop-specific context can be set.
 */
public class ContextShopFragment extends Fragment implements View.OnClickListener {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.context_fragment_shop, container, false);

        Button submit = ((Button) view.findViewById(R.id.button_context_proceed));

        submit.setOnClickListener(this);
        super.onCreateView(inflater, container, savedInstanceState);
        return view;
    }

    @Override
    public void onClick(View view) {
        processFormData();

        ((ContextActivity) getActivity()).changeToSecondScreen();
    }

    /**
     * In this method the data from the UI is loaded and send to the {@link com.uwetrottmann.shopr.context.model.ScenarioContext}
     * in order to get a full description of the current context.
     */
    private void processFormData() {
        ScenarioContext scenarioContext = ScenarioContext.createNewInstance();

        Spinner currentLocation = (Spinner) getActivity().findViewById(R.id.context_spinner_my_location);
        Spinner shopDistance = (Spinner) getActivity().findViewById(R.id.contextShopDistanceSpinner);
        Spinner shopOpeningHours = (Spinner) getActivity().findViewById(R.id.contextShopOpeningHoursSpinner);
        Switch onlyStock = (Switch) getActivity().findViewById(R.id.contextShopStockOptions);
        Switch crowdedShopsIncluded = (Switch) getActivity().findViewById(R.id.contextShopCrowdedness);

        scenarioContext.setCurrentLocation((String) currentLocation.getSelectedItem());
        scenarioContext.setDistanceToShop((String) shopDistance.getSelectedItem());
        scenarioContext.setOpennessOfShops((String) shopOpeningHours.getSelectedItem());
        scenarioContext.setOnlyItemsInStock(onlyStock.isChecked());
        scenarioContext.setCrowdedShopsAllowed(crowdedShopsIncluded.isChecked());
    }
}
