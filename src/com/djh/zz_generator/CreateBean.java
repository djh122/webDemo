package com.djh.zz_generator;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
/**
 * @author djh
 * 
 * 作用：自动生成bean
 * */
public class CreateBean {
	//表名
	static String tableName="jlop_mpm";
	//类名
	static String className="Mpm";
		
	//包ַ名
	static String packageUrl="com.jielan.jlop.model";	
	//sql配置文件路径
	static String resourceUrl="/resource/config/jdbc.properties";
	
	public static void main(String[] args) {
		
		try {
			writeContent(gernator(packageUrl, className), packageUrl, className);
			System.out.println(className+".java 创建成功");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static Connection getConnection() {
		Connection conn = null;
		try {
			Properties properties=new Properties();
			try {
				properties.load(new FileInputStream(new File(System.getProperty("user.dir")+resourceUrl)));
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			Class.forName(properties.get("driver").toString());
			String url = properties.get("url").toString();
			String user = properties.get("username").toString();
			String pass =  properties.get("password").toString();
			conn = DriverManager.getConnection(url, user, pass);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
	public static List<Bean> getAllColumn() {
		Connection conn = getConnection();
		List<Bean> list=new ArrayList<Bean>();
		String sql = "select * from "+tableName;
		PreparedStatement stmt;
		try {
			stmt = conn.prepareStatement(sql);
			DatabaseMetaData dataAll=conn.getMetaData();
			ResultSet resultSet=dataAll.getTables(null, "%", "%", new String[] { "TABLE" });
			ResultSet rs1 = stmt.executeQuery(sql);
			ResultSetMetaData data = rs1.getMetaData();
			for (int i = 1; i <= data.getColumnCount(); i++) {
				Bean bean=new Bean();			
				bean.type=data.getColumnClassName(i);
				bean.name=data.getColumnLabel(i);
				list.add(bean);			
			}
			while (resultSet.next()) {
				 String tableName=resultSet.getString("TABLE_NAME");
				 if(tableName.equalsIgnoreCase(CreateBean.tableName)){
					 	ResultSet rs = dataAll.getColumns(null, "%", tableName, "%");
					 	int i=0;
	                    while(rs.next()){
	        				list.get(i).columnRemark=rs.getString("REMARKS");
	        				i++;
	                    }
				 }
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public static String gernator(String packageUrl,String className) {
		List<Bean> list=getAllColumn();
		StringBuffer content=new StringBuffer();
		content.append("package "+packageUrl+";\n\n");
		if(isContainDate()){
			content.append("import java.util.Date;\n\n");
		}
		content.append("public class "+className+" {\n");
		for(int i=0;i<list.size();i++){
			Bean bean=list.get(i);
			if(!bean.type.contains("."))
				continue;
			String typeShort=bean.type.substring(bean.type.lastIndexOf('.')+1);
			if(typeShort.equals("Integer")){
				typeShort="int";
			}else if (typeShort.equals("Double")) {
				typeShort="double";
			}else if (typeShort.equals("Long")) {
				typeShort="int";
			}else if(typeShort.equals("Boolean")){
				typeShort="boolean";
			}else if (typeShort.equals("Timestamp")) {
				typeShort="Date";
			}
			content.append("\tprivate "+typeShort+" "+bean.name+";"+"  /*"+bean.columnRemark+"*/\n\n");						
		}
		for(int i=0;i<list.size();i++){
			Bean bean=list.get(i);
			String typeShort=bean.type.substring(bean.type.lastIndexOf('.')+1);
			if(typeShort.equals("Integer")){
				typeShort="int";
			}else if (typeShort.equals("Double")) {
				typeShort="double";
			}else if (typeShort.equals("Long")) {
				typeShort="int";
			}else if(typeShort.equals("Boolean")){
				typeShort="boolean";
			}else if (typeShort.equals("Timestamp")) {
				typeShort="Date";
			}
			String firstUpcaseName=(char)(bean.name.charAt(0)-32)+bean.name.substring(1);
			content.append("\tpublic "+typeShort+" get"+firstUpcaseName+"() {\n");
			content.append("\t\treturn "+bean.name+";\n\t}\n");
			content.append("\tpublic void set"+firstUpcaseName+"("+typeShort+" "+bean.name+") {\n");
			content.append("\t\tthis."+bean.name+" = "+bean.name+";\n\t}\n");
		}
		content.append("}");
		return content.toString();
	}
	private static boolean isContainDate() {
		List<Bean> list=getAllColumn();
		for(int i=0;i<list.size();i++){
			//if(list.get(i).type.contains("Timestamp"))
			if(list.get(i).type.contains("Date")||list.get(i).type.contains("Timestamp")){
				return true;
			}							
		}
		return false;
	}
	
	public static void writeContent(String content,String packageUrl,String className) throws IOException {
		packageUrl=packageUrl.replaceAll("\\.","/");
		File file=new File(System.getProperty("user.dir")+"/src/"+packageUrl+"/"+className+".java");
		BufferedWriter bw=new BufferedWriter(new FileWriter(file));
		bw.write(content);
		bw.flush();
		bw.close();	
	}
	
}
class Bean{
	String type;
	String name;
	String columnRemark;
}