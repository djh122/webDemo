package com.djh.test.jfreechart;

import java.awt.Dimension;

import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;
import org.jfree.chart.*;
import org.jfree.chart.plot.PlotOrientation;

public class JfreeChartTest extends ApplicationFrame  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public JfreeChartTest(String title) {
		super(title);
		// TODO Auto-generated constructor stub
	}

	public static void pie() {
		// 创建饼图数据对象
		DefaultPieDataset dfp = new DefaultPieDataset();
		dfp.setValue("管理人员", 25);
		dfp.setValue("市场人员", 35);
		dfp.setValue("开发人员", 20);
		dfp.setValue("后勤人员", 5);
		dfp.setValue("财务人员", 15);
		JFreeChart a = ChartFactory.createPieChart("CityInfoPort公司组织架构图", dfp,
				true, true, true);

		ChartFrame frame = new ChartFrame("CityInfoPort公司组织架构图 ", a, true);
		frame.pack();
		frame.setVisible(true);
	}

	public static void line(double[] a) {
		JfreeChartTest jfreeChartTest = new JfreeChartTest("abc");
		jfreeChartTest.lineT(a,null);
	}
	public static void line(double[] a,String[] colxs) {
		JfreeChartTest jfreeChartTest = new JfreeChartTest("abc");
		jfreeChartTest.lineT(a,colxs);
	}
	public void lineT(double[] a,String[] colxs) {
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		for(int i=0;i<a.length;i++){
			if(colxs==null){
				dataset.addValue(a[i], "", "c"+i);
			}else {
				dataset.addValue(a[i], "", colxs[i]);
			}
		}
		JFreeChart chart = ChartFactory.createLineChart(
				"Java Standard Class Library",// 图表标题
				null, // 主轴标签
				"Class Count",// 范围轴标签
				dataset, // 数据集
				PlotOrientation.VERTICAL,// 方向
				false, // 是否包含图例
				true, // 提示信息是否显示
				false);// 是否使用urls
		ChartPanel chartPanel = new ChartPanel(chart, false);
		chartPanel.setPreferredSize(new Dimension(500, 270));
		setContentPane(chartPanel);
		this.pack();
		RefineryUtilities.centerFrameOnScreen(this);
		this.setVisible(true);
	}
	
	public static void bar(double[] a) {
		JfreeChartTest jfreeChartTest = new JfreeChartTest("abc");
		//double[] a = {1,2,3,4,7,5,2};
		jfreeChartTest.barT(a);
	}
	public void barT(double[] a) {
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		for(int i=0;i<a.length;i++){
			dataset.addValue(a[i], "", "c"+i);
		}
		JFreeChart chart = ChartFactory.createBarChart("Bar Chart Demo", // chart
				"Category", // domain axis label
				"Value", // range axis label
				dataset, // data
				PlotOrientation.VERTICAL, // orientation
				true, // include legend
				true, // tooltips?
				false // URLs?
				);
		ChartPanel chartPanel = new ChartPanel(chart, false);
		chartPanel.setPreferredSize(new Dimension(500, 270));
		setContentPane(chartPanel);
		this.pack();
		RefineryUtilities.centerFrameOnScreen(this);
		this.setVisible(true);
	}
	public static void main(String[] args) {
		pie();
		double[] a = {1,2,3,4,7,5,2};
		line(a);
		bar(a);
	}

}
