package com.djh.test.stock.stat;

import java.sql.Date;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.apache.commons.math3.fitting.PolynomialCurveFitter;
import org.apache.commons.math3.fitting.WeightedObservedPoints;
import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;
import org.apache.http.client.utils.DateUtils;

import com.djh.test.base.Db;
import com.djh.test.jfreechart.JfreeChartTest;

public class Stat {

	public static void po() throws SQLException {
		String[] between = {"2000-01-01",""};//"2015-05-01","2015-06-01"
		String sqlString = "select * from stock where 1=1";
		if(!between[0].equals("")){
			sqlString = sqlString +" and tran_date>='"+between[0]+"'";
		}
		if(!between[1].equals("")){
			sqlString = sqlString +" and tran_date<'"+between[1]+"'";
		}
		
		List<Map<String, Object>> list = Db.getList(sqlString);
		double[] a = new double[list.size()];
		for(int i=0;i<list.size();i++){
			Map<String, Object> map = list.get(i);
			a[i] = new Double(map.get("start_p").toString());
		}
		int minpo = 10;
		double maxError = 200;
		//int count = 0;
		for(int i=0;i<a.length-minpo;i++){
			double var = polynomial(a, i, i+minpo);
			//System.out.println(var);
			if(var<maxError){
				System.out.println(var);
				for(int k=minpo;;){//增加两端的拟合点
					double varInc = polynomial(a, i, i+k);
					if(varInc<maxError){
						//System.out.println(varInc);
						k++;
					}else {
						String tran_st = DateUtils.formatDate((Date)list.get(i).get("tran_date"), "yyyy-MM-dd");
						String tran_end = DateUtils.formatDate((Date)list.get(i+k).get("tran_date"), "yyyy-MM-dd");//(String) list.get(i+k).get("tran_date");
						Db.execute("update stock set bd_point='start"+i+"' where tran_date='"+tran_st+"'");
						Db.execute("update stock set bd_point='end"+i+"' where tran_date='"+tran_end+"'");
						i += k;
						minpo = 10;
						break;
					}
				}
				//count++;
			}else {
				minpo = 10;
			}
		}
		//System.out.println("============="+count);
	}
	//分段一元一次函数拟合
	public static double polynomial(double[] a,int start,int end) {
		WeightedObservedPoints points = new WeightedObservedPoints();
		for(int i=start;i<end;i++){
			points.add(i,a[i]);
		}
		PolynomialCurveFitter fitter = PolynomialCurveFitter.create(1);
		double[] rs = fitter.fit(points.toList());
		double[] forecast = new double[end-start];

		for(int i=start,j=0;i<end;i++,j++){
			forecast[j] = Math.abs(rs[0]+rs[1]*i-a[i]);
		}
		DescriptiveStatistics stat = new DescriptiveStatistics(forecast);
		double var = stat.getVariance();
		return var;
		//System.out.println("==========="+count);
		
		//System.out.println(stat.getVariance()>1e-4);
	}
	
	public static Map<String, Object> getList() {
		String[] between = {"2000-01-01",""};//"2015-05-01","2015-06-01"
		String sqlString = "select * from stock where 1=1";
		if(!between[0].equals("")){
			sqlString = sqlString +" and tran_date>='"+between[0]+"'";
		}
		if(!between[1].equals("")){
			sqlString = sqlString +" and tran_date<'"+between[1]+"'";
		}
		
		List<Map<String, Object>> list = Db.getList(sqlString);
		double[] ratios = new double[list.size()];
		String[] dates = new String[list.size()];
		double[] rs = new double[list.size()];
		double[] rs1 = new double[list.size()];
		double sum = 1;
		double sum1 = new Double(list.get(0).get("start_p").toString());
		for(int i=0;i<list.size();i++){
			Map<String, Object> map = list.get(i);
			ratios[i] = new Double(map.get("ratio").toString());
			dates[i] = DateUtils.formatDate((java.util.Date)map.get("tran_date"),"yyyy-MM-dd");
			sum += ratios[i];
			rs[i] = sum;
			sum1 = sum1*(1+ratios[i]); 
			rs1[i] = sum1; 
		}
		JfreeChartTest.line(ratios,dates);
		//JfreeChartTest.line(rs);
		JfreeChartTest.line(rs1,dates);
		return null;
	}
	public static Map<String, Object> showRatio() {
		List<Map<String, Object>> list = Db.getList("select * from stock where tran_date>='2000-01-01'");
		double[] ratios = new double[list.size()];
		for(int i=0;i<list.size();i++){
			Map<String, Object> map = list.get(i);
			ratios[i] = new Double(map.get("ratio").toString());
		}
		DescriptiveStatistics stat = new DescriptiveStatistics(ratios);
		System.out.println(stat.getMax());
		System.out.println(stat.getMin());
		System.out.println(stat.getMean());
		System.out.println(stat.getStandardDeviation());
		System.out.println(stat.getVariance());
		//System.out.println(Arrays.toString(ratios));
		return null;
	}
	public static void name() {
		WeightedObservedPoints points = new WeightedObservedPoints();
		double[] xArray = {1,2,3};
		double[] yArray = {2,9,28};
		for(int i=0;i<xArray.length;i++){
			points.add(xArray[i],yArray[i]);
		}
		PolynomialCurveFitter fitter = PolynomialCurveFitter.create(3);
		double[] rs = fitter.fit(points.toList());
		System.out.println(Arrays.toString(MyMathUtils.getScala(rs, 4)));
	}
	public static void main(String[] args) throws SQLException {
		//getList();
		//System.out.println(polynomial(new double[]{1,5,6}, 0, 3));
		//po();
		name();
	}

}
