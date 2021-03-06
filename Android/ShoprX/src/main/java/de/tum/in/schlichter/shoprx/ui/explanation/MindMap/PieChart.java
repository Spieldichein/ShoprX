package de.tum.in.schlichter.shoprx.ui.explanation.MindMap;

import org.achartengine.ChartFactory;
import org.achartengine.GraphicalView;
import org.achartengine.model.CategorySeries;
import org.achartengine.renderer.DefaultRenderer;

import android.content.Context;

import de.tum.in.schlichter.shoprx.algorithm.model.Attributes.Attribute;


public class PieChart extends AttributeChart {

	public PieChart(Context context, Attribute attribute) {

		super(context, attribute);
	}

	public GraphicalView getView() {
		CategorySeries series = getDataSet();
		DefaultRenderer renderer = Charts.getDefaultRenderer(getElems());
        float xy= (float) 0.87;
        renderer.setScale(xy);
        int dps =14;
        final float scale = context.getResources().getDisplayMetrics().density;
        int pixels = (int) (dps * scale + 0.5f);
        renderer.setLabelsTextSize(pixels);
        renderer.setLegendTextSize(pixels);
        return ChartFactory.getPieChartView(context, series, renderer);
	}

	protected CategorySeries getDataSet() {
		CategorySeries series = new CategorySeries(attribute.id());

		for (ChartElem elem : getElems()) {
			series.add(elem.name(), elem.value());
		}
		
		return series;
	}

}