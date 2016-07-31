package com.djh.test.stock;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.djh.test.base.Db;


public class Stock {

	public static BigDecimal avg(List<BigDecimal> array ,BigDecimal today,int l) {
		BigDecimal t = BigDecimal.ZERO;
		if(array.size()<l){
			array.add(today);
		}else {
			array.remove(0);
			array.add(today);
		}
		for(int i=1;i<array.size();i++){
			BigDecimal abc = array.get(i).subtract(array.get(i-1)).divide(array.get(i-1),5,BigDecimal.ROUND_HALF_UP);
			t = t.add(abc.multiply(BigDecimal.TEN.add(new BigDecimal(i))));
		}
		BigDecimal ed = new BigDecimal(array.size()).divide(new BigDecimal(2)).add(BigDecimal.TEN);
		BigDecimal rs = t.divide(ed,5,BigDecimal.ROUND_HALF_UP);
		System.out.println(rs);
		return rs;
	}
	
	public static BigDecimal avg1(List<BigDecimal> array ,BigDecimal today,int l) {
		BigDecimal t = BigDecimal.ZERO;
		array.remove(0);
		array.add(today);
		for(int i=1;i<=10;i++){
			t = t.add(array.get(i-1).multiply(new BigDecimal(i)));
		}
		//BigDecimal ed = new BigDecimal(array.size()).divide(new BigDecimal(2)).add(BigDecimal.TEN);
		System.out.println(t);
		return t;
	}
	public static void updateRatio() throws SQLException {
		 List<Map<String, Object>> list = Db.getList("select * from stock order by tran_date");
		 int len = list.size();
		 int ic = 0;
		 for(int i=0;i<len;i++){
			BigDecimal arg =  (BigDecimal)list.get(i).get("ratio");
			int flag = arg.compareTo(BigDecimal.ZERO);
			if(flag ==1){
				if(ic<0){
					ic=0;
				}else {
					ic += 1;
				}
			}else if (flag == -1) {
				if(ic>0){
					ic=0;
				}else {
					ic -= 1;
				}
			}
			long id = (Long) list.get(i).get("id");
			 Db.execute("update stock set int_1="+ic+" where id="+id);
		 }
	}
	
	public static void updateAvg_r() throws SQLException {
		 List<Map<String, Object>> list = Db.getList("select * from stock order by tran_date");
		 int len = list.size();
		 for(int i=0;i<len;i++){
			BigDecimal start_p =  (BigDecimal)list.get(i).get("start_p");
			BigDecimal end_p =  (BigDecimal)list.get(i).get("end_p");

			BigDecimal avg_r = end_p.subtract(start_p).divide(start_p,5,BigDecimal.ROUND_HALF_UP);
			
			long id = (Long) list.get(i).get("id");
			 Db.execute("update stock set avg_r="+avg_r+" where id="+id);
		 }
	}
	public static void updateAvg_3() throws SQLException {
		 List<Map<String, Object>> list = Db.getList("select * from stock order by tran_date");
		 int len = list.size();
		 for(int i=1;i<len;i++){
			BigDecimal end_p =  (BigDecimal)list.get(i-1).get("end_p");
			BigDecimal start_p =  (BigDecimal)list.get(i).get("start_p");

			BigDecimal avg3 = start_p.subtract(end_p).divide(end_p,5,BigDecimal.ROUND_HALF_UP);
			
			long id = (Long) list.get(i).get("id");
			 Db.execute("update stock set avg3="+avg3+" where id="+id);
		 }
	}
    public static void name() {
    	
        List<Map<String, Object>> list = Db.getList("select * from stock where tran_date>'2006-01-01' order by tran_date");
        
        final BigDecimal capital = new BigDecimal(5000);
        final BigDecimal gz = new BigDecimal(100);
        BigDecimal in = new BigDecimal(5000);
        //初始化账户
        Account account = new Account(capital, in, gz);
        
        //定投数量
       // BigDecimal dt = new BigDecimal(1000);
        
        int len= list.size();
        for(int i=0;i<10;i++){
			account.addRecord((BigDecimal)list.get(i).get("avg2"));
		 }
        for(int i=10;i<len;i++){
            BigDecimal swing = (BigDecimal) list.get(i).get("ratio");
            account.updateIn(swing.add(BigDecimal.ONE));//更新仓中净值
            account.increase();//每日加工资
            
            //int flag = account.addRecord((BigDecimal)list.get(i).get("avg2"));
            account.buy(account.getWait());
//            if(flag==1){
//            	
//            }else if (flag==-1) {
//				account.clear(account.getIn());;
//			}
            account.toString();
        }
        BigDecimal abc = account.getTotal().divide(capital,4,BigDecimal.ROUND_HALF_UP).subtract(BigDecimal.ONE);
        System.out.println("本金:"+account.getCapital()+"     总盈利:"+account.getTotal()+"   盈利比："+abc);
        //System.out.println(c_down);
        //System.out.println(c_up);
    }
    public static void main(String[] args) throws SQLException {
    	updateAvg_r();
    }

}
