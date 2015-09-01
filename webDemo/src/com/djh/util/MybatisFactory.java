package com.djh.util;

import java.io.IOException;
import java.io.Reader;

import javax.sql.DataSource;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class MybatisFactory {
	private static SqlSessionFactory ssf;
	static{
		String resource = "com/jielan/mybatis/config.xml";
		try {
			Reader reader = Resources.getResourceAsReader(resource);
			ssf = new SqlSessionFactoryBuilder().build(reader);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static SqlSessionFactory getFactory() {
		return ssf;
	}
	
//	public static DataSource getDataSource(String driver,String url,String user,String password) {
//		return null;
//	}
}
