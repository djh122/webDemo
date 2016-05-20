package com.djh.test.jfreechart;

import java.awt.Color;
import java.util.Map;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.AxisLocation;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.ApplicationFrame;

public class Test extends ApplicationFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Test(String title,JFreeChart jfreechart) {
		super(title);
		ChartPanel chartPanel = new ChartPanel(jfreechart);
		this.setContentPane(chartPanel);
		this.setBounds(100, 100, 8000, 600);
	}
	private static CategoryDataset createDataset(){
		String series1 = "First";

		DefaultCategoryDataset defaultcategorydataset = new DefaultCategoryDataset();
//		Map<Integer, Integer> m = RandomNumber.getGaussian();
//		for(Integer var:m.keySet()){
//			defaultcategorydataset.addValue(m.get(var), series1, var);
//		}
		Map<Integer, Double> map = RandomNumber.getTan();
		for(Integer var:map.keySet()){
			defaultcategorydataset.addValue(map.get(var), series1, var);
		}
		return defaultcategorydataset;
	} 

	public static void main(String[] args) {
		JFreeChart jFreeChart = ChartFactory.createLineChart("test", "x轴", "y轴", createDataset(), PlotOrientation.VERTICAL, true, true, false);
		jFreeChart.setBackgroundPaint(Color.white);
		CategoryPlot categoryPlot = jFreeChart.getCategoryPlot();
		categoryPlot.setBackgroundPaint(Color.LIGHT_GRAY);
		categoryPlot.setDomainGridlinePaint(Color.white);
		categoryPlot.setDomainGridlinesVisible(true);
		categoryPlot.setRangeGridlinePaint(Color.white);
		categoryPlot.setDomainAxisLocation(AxisLocation.TOP_OR_RIGHT);
		categoryPlot.setRangeAxisLocation(AxisLocation.BOTTOM_OR_RIGHT);
		NumberAxis numberAxis = (NumberAxis) categoryPlot.getRangeAxis();
		numberAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
		//LineAndShapeRenderer lineAndShapeRenderer = (LineAndShapeRenderer) categoryPlot.getRenderer();
		//lineAndShapeRenderer.setShapesVisible(true);
		//lineAndShapeRenderer.setser
		jFreeChart.createBufferedImage(100, 100);
		Test test = new Test("tu", jFreeChart);
		test.show(true);
	}

}
