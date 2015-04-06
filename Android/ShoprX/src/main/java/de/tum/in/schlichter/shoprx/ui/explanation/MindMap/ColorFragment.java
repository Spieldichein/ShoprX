package de.tum.in.schlichter.shoprx.ui.explanation.MindMap;

import android.view.View;

import de.tum.in.schlichter.shoprx.algorithm.model.Attributes.Attribute;
import de.tum.in.schlichter.shoprx.algorithm.model.Color;


public class ColorFragment extends MindMapFragment {

	@Override
	protected View getChartView() {
		return new PieChart(getActivity(), attribute()).getView();
	}

	protected Attribute attribute() {
		return new Color();
	}

	@Override
	protected void onAttributePreferenceChangeRequested() {
	//	startActivity(ColorPreferenceActivity.class);
	}

}
