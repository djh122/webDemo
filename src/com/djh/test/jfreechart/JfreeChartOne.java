package com.djh.test.jfreechart;

import java.awt.Font; 

import javax.swing.JPanel; 

import org.jfree.chart.ChartFactory; 

import org.jfree.chart.ChartPanel; 

import org.jfree.chart.JFreeChart; 

import org.jfree.chart.plot.PiePlot; 

import org.jfree.data.general.DefaultPieDataset; 

import org.jfree.data.general.PieDataset; 

import org.jfree.ui.ApplicationFrame; 



public class JfreeChartOne extends ApplicationFrame { 

    private static final long serialVersionUID = 1L; 

    public JfreeChartOne(String s) 

    { 

       super(s); 

       setContentPane(createJPanel()); 

    } 

    public static void main(String[] args) { 

       JfreeChartOne one=new JfreeChartOne("CityInfoPort公司组织架构图"); 

       one.pack(); 

       one.setVisible(true); 

    } 

    //利用静态方法设定数据源(饼状图) 

    public static PieDataset createPieDataset() { 

       DefaultPieDataset defaultpiedataset = new DefaultPieDataset(); 

       defaultpiedataset.setValue("管理人员", 10.02D); 

       defaultpiedataset.setValue("市场人员", 20.23D); 

       defaultpiedataset.setValue("开发人员", 60.02D); 

       defaultpiedataset.setValue("OEM人员", 10.02D); 

       defaultpiedataset.setValue("其他人员", 5.11D); 

       return defaultpiedataset; 

    } 

    // 通过ChartFactory创建JFreeChart的实例 

    public static JFreeChart createJFreeChart(PieDataset p) 

    { 

       JFreeChart a =ChartFactory.createPieChart("CityInfoPort公司组织架构图",p, true, true, true); 

       PiePlot  pie=(PiePlot )a.getPlot(); 

       pie.setLabelFont(new Font("SansSerif",Font.BOLD,12)); 

       pie.setNoDataMessage("No data available"); 

       pie.setCircular(true); 

       pie.setLabelGap(0.01D);//间距 

       return a; 

    } 

    public static JPanel  createJPanel (){ 

       JFreeChart jfreechart = createJFreeChart(createPieDataset()); 

       return new ChartPanel(jfreechart); 

    } 

} 
