package de.tum.in.schlichter.shoprx.ui.explanation.MindMap;

import java.util.ArrayList;
import java.util.List;

import org.achartengine.GraphicalView;

import android.content.Context;
import android.graphics.Color;

import de.tum.in.schlichter.shoprx.algorithm.AdaptiveSelection;
import de.tum.in.schlichter.shoprx.algorithm.model.Attributes.AttributeValue;
import de.tum.in.schlichter.shoprx.algorithm.model.Attributes.Attribute;


public abstract class AttributeChart {

	protected Context context;
	protected Attribute attribute;

	public AttributeChart(Context context, Attribute attribute) {
		this.context = context;
		this.attribute = attribute;
	}

	public abstract GraphicalView getView();

	protected List<ChartElem> getElems() {
		List<ChartElem> elems = new ArrayList<ChartElem>();
		AdaptiveSelection manager = AdaptiveSelection.get();
		Attribute queryAttr = manager.getCurrentQuery().attributes()
				.getAttributeById(attribute.id());
		
		Attribute attr = (queryAttr !=null) ? queryAttr : attribute;

		if (attr != null) {
			AttributeValue[] values = attr.getAttributeValues();
			int i = 0;
          /* if (attr.id().equalsIgnoreCase("price")){
                for (int y = attr.getValueWeights().length; y> 0;y--){
                    AttributeValue value = values[y-1];
                    double weight = attr.getValueWeights()[y-1];
                    ChartElem elem = new ChartElem().name(value.descriptor())
                            .color(Color.parseColor(value.getColor())).value(weight);
                    elems.add(elem);
                }
                return elems;
            }*/
			for (double weight : attr.getValueWeights()) {
				AttributeValue value = values[i];
				ChartElem elem = new ChartElem().name(value.descriptor())
						.color(Color.parseColor(value.getColor())).value(weight);
				elems.add(elem);
				i++;
			}
		}

		return elems;
	}

	protected String[] getTitles() {
		List<ChartElem> elems = getElems();
		String[] titles = new String[elems.size()];

		int i = 0;
		for (ChartElem elem : getElems()) {
			titles[i] = (elem.name());
			i++;
		}

		return titles;
	}

	protected double[] getValues() {
		List<ChartElem> elems = getElems();
		double[] values = new double[elems.size()];

		int i = 0;
		for (ChartElem elem : getElems()) {
			values[i] = (elem.value());
			i++;
		}

		return values;
	}
}
