package de.tum.in.schlichter.shoprx.ui.explanation.MindMap;

import android.view.View;

import de.tum.in.schlichter.shoprx.R;
import de.tum.in.schlichter.shoprx.algorithm.model.Attributes.Attribute;
import de.tum.in.schlichter.shoprx.algorithm.model.Attributes;
import de.tum.in.schlichter.shoprx.algorithm.model.ClothingType;
import de.tum.in.schlichter.shoprx.ui.explanation.Prefrences.ClothingTypePreferenceFragment;


public class ClothingTypeFragment extends MindMapFragment {

	@Override
	protected View getChartView() {
		return new PieChart(getActivity(), attribute()).getView();
	}

	@Override
	protected Attribute attribute() {
		return new ClothingType();
	}

	protected void onAttributePreferenceChangeRequested() {
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.emptyframe,new ClothingTypePreferenceFragment(),"").addToBackStack("").commit();
		//startActivity(ClothingTypePreferenceActivity.class);
	}
	
}
