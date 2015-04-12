package de.tum.in.schlichter.shoprx.ui.explanation.MindMap;

import android.view.View;

import de.tum.in.schlichter.shoprx.R;
import de.tum.in.schlichter.shoprx.algorithm.model.Attributes.Attribute;
import de.tum.in.schlichter.shoprx.algorithm.model.Price;
import de.tum.in.schlichter.shoprx.ui.explanation.Prefrences.PricePreferenceFragment;


public class PriceRangeFragment extends MindMapFragment {

	@Override
	protected View getChartView() {
		return new DoughnutChart(getActivity(), attribute()).getView();
	}
	
	protected Attribute attribute() {
		return new Price();
	}

	@Override
	protected void onAttributePreferenceChangeRequested() {
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.emptyframe,new PricePreferenceFragment(),"").addToBackStack("").commit();
	}
	
}
