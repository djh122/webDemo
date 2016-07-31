package com.djh.test.jfreechart;

import org.jfree.data.general.DefaultPieDataset; 

import org.jfree.chart.*; 



public class JfreeChartTest { 

    public static void main(String[] args) { 

       //创建饼图数据对象 

       DefaultPieDataset dfp=new DefaultPieDataset(); 

       dfp.setValue("管理人员", 25); 

       dfp.setValue("市场人员", 35); 

       dfp.setValue("开发人员", 20); 

       dfp.setValue("后勤人员", 5); 

       dfp.setValue("财务人员", 15); 

        //Create JFreeChart object 

       JFreeChart a =ChartFactory.createPieChart("CityInfoPort公司组织架构图",dfp, true, true, true); 

       ChartFrame  frame=new ChartFrame ("CityInfoPort公司组织架构图 ",a,true); 

       frame.pack(); 

       frame.setVisible(true); 

} 

} 
