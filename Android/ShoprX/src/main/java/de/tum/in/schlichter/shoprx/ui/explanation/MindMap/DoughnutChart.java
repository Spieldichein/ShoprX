package de.tum.in.schlichter.shoprx.ui.explanation.MindMap;

import org.achartengine.ChartFactory;
import org.achartengine.GraphicalView;
import org.achartengine.model.CategorySeries;
import org.achartengine.model.MultipleCategorySeries;
import org.achartengine.renderer.DefaultRenderer;

import android.content.Context;

import de.tum.in.schlichter.shoprx.algorithm.model.Attributes.Attribute;


public class DoughnutChart extends AttributeChart {

	public DoughnutChart(Context context, Attribute attribute) {
		super(context, attribute);
	}

	public GraphicalView getView() {
        MultipleCategorySeries series = getDataSet();
		DefaultRenderer renderer = Charts.getDefaultRenderer(getElems());
        float xy= (float) 0.85;
        renderer.setScale(xy);
        int dps =13;
        final float scale = context.getResources().getDisplayMetrics().density;
        int pixels = (int) (dps * scale + 0.5f);
        renderer.setLabelsTextSize(pixels);
        renderer.setLegendTextSize(pixels);
        renderer.setShowLegend(true);
		return ChartFactory.getDoughnutChartView(context, series, renderer);
	}

	private MultipleCategorySeries getDataSet() {
		MultipleCategorySeries series = new MultipleCategorySeries(attribute.id());

		series.add(getTitles(), getValues());
		return series;
	}

}
