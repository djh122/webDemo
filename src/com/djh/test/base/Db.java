package com.djh.test.base;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
/**
 * 作用：无
 * @author djh
 */
public class Db {
	//连接地址、用户名、密码
	final static String url = "jdbc:mysql://localhost:3306/test?useUnicode=true&amp;characterEncoding=utf-8";
	final static String user = "root";
	final static String password = "123456";
//	final static String url = "jdbc:mysql://120.26.220.33:3306/test?useUnicode=true&amp;characterEncoding=utf-8";
//	final static String user = "root";
//	final static String password = "sunyard@33";
	//驱动名
	final static String driver = "com.mysql.jdbc.Driver";
	
	private static Connection connection = null;
	//private static List<Map<String, Object>> datas = null;
	//private static Map<String, Object> data = null;
	static{
		try {
			Class.forName(driver);
			connection = DriverManager.getConnection(url, user, password);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public static Connection getConnection() {
		return connection;
	}
//	public static void close() throws SQLException {
//		if(connection != null){
//			//connection.close();
//		}
//	}
/*
	public static void startTrans() throws SQLException {
		connection.setAutoCommit(false);
	}
	public static void commit() throws SQLException {
		connection.commit();
	}
	public static ResultSet query(String sql) throws SQLException {
		return connection.createStatement().executeQuery(sql);
	}
*/
	public static int execute(String sql) throws SQLException {
		System.out.println(sql);
		return connection.createStatement().executeUpdate(sql);
	}

	public static List<Map<String, Object>> getList(String sql) {
		try{
			System.out.println(sql);
			PreparedStatement ps = getConnection().prepareStatement(sql);
			
            ResultSet rs = ps.executeQuery(); 
            ResultSetMetaData rsmd = ps.getMetaData(); 
            int columnCount = rsmd.getColumnCount(); 
            List<Map<String, Object>> datas = new ArrayList<Map<String, Object>>(); 
            while (rs.next()) { 
            	Map<String, Object> data = new LinkedHashMap<String, Object>(); 
                for (int i = 1; i <= columnCount; i++) { 
                    data.put(rsmd.getColumnLabel(i), rs.getObject(rsmd.getColumnLabel(i))); 
                } 
                datas.add(data); 
            } 
            return datas; 
        } catch (Exception e) { 
            throw new RuntimeException(e); 
        } finally { 
        } 
	}
	public static Map<String, Object> getOne(String sql) {
		try{
			PreparedStatement ps = getConnection().prepareStatement(sql);
			
            ResultSet rs = ps.executeQuery(); 
            ResultSetMetaData rsmd = ps.getMetaData(); 
            int columnCount = rsmd.getColumnCount(); 
            
        	Map<String, Object> data = new LinkedHashMap<String, Object>();
        	while(rs.next()){
        		for (int i = 1; i <= columnCount; i++) { 
                    data.put(rsmd.getColumnLabel(i), rs.getObject(rsmd.getColumnLabel(i))); 
                    
                    //System.out.println("结果集"+rs);
                    //System.out.println(rs.next());
//                    if(rs.next()){
//                    	throw new SQLException("只能有一条查询记录！");
//                    }
                } 
        		rs.last();
        		int len = rs.getRow();
        		if(len>1){
        			
        			throw new SQLException("只能有一条查询记录！但实际查询到 "+len+"条");
        		}
        	}
            return data; 
        } catch (Exception e) { 
        	e.printStackTrace();
            //throw new RuntimeException("查询异常！"); 
        } finally { 
        } 
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public static void toString(Object object) {
		if(object instanceof List){
			StringBuffer sb = new StringBuffer("{\n");
			int count = 0;
			for(Map<String, Object> map:(List<Map<String, Object>>)object){
				if(count == 0){
					//表头
					sb.append("[");
					for(String key:map.keySet()){
						sb.append(key+",  ");
					}
					sb.deleteCharAt(sb.length()-2);
					sb.append("]\n");
					sb.append("-----------------------------------------------");
					sb.append("-----------------------------------------------\n");
					count++;
				}
				sb.append("[");
				for(String key:map.keySet()){
					sb.append(map.get(key)+",  ");
				}
				sb.deleteCharAt(sb.length()-3);
				sb.append("]\n");
			}
			sb.append("}");
			System.out.println(sb.toString());
		}else if (object instanceof Map) {
			StringBuffer sb = new StringBuffer("{");
			Map<String, Object> map = (Map<String, Object>) object;
			for(String key:map.keySet()){
				sb.append(key+":"+map.get(key)+",  ");
			}
			sb.append("}");
			System.out.println(sb.toString());
		}
	}
	public static void main(String[] args) throws SQLException {
		//System.out.println(query("select * from eacc_accountinfo"));
		//ResultSet rs = query("select * from prod_productinfo");
		//System.out.println(rs.getRow());
//		toString(getList("select * from stock"));
		//toString(getList("select * from eacc_accountinfo where eacc_no=123456789"));
		//}
		//new Date()
		System.out.println(Db.getOne("select now()"));
	}
}
