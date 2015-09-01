package test.mybatis;

import java.io.IOException;
import java.io.Reader;
import java.text.SimpleDateFormat;

import javax.sql.DataSource;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class MybatisTest {

	//mybatis初始化
	private static SqlSessionFactory ssf;
	static{
		String resource = "test/mybatis/config.xml";
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
	
	public static DataSource getDataSource(String driver,String url,String user,String password) {
		return null;
	}
//	public static SqlSessionFactory getFactoryByCode(DataSource dataSource) {
//
//		TransactionFactory tf = new JdbcTransactionFactory();
//		Environment environment = new Environment("development", tf, dataSource);
//		Configuration configuration = new Configuration(environment);
//		configuration.addMapper(BlogMapper.class);
//		SqlSessionFactory ssf = new SqlSessionFactoryBuilder().build(configuration);
//		return null;
//	}
	
	public static void getBlog() {
		SqlSessionFactory ssf = getFactory();
		SqlSession session = ssf.openSession();
		Blog blog = null;
		try{
			blog = session.selectOne("com.jielan.mybatis.BlogMapper.selectOne");
		}finally{session.close();}
		
		System.out.println(blog.getTitle());
	}
	
	public static void getBlog1() {
		SqlSession session = getFactory().openSession();
		try {
			BlogMapper1 mapper = session.getMapper(BlogMapper1.class);
			Blog blog = mapper.selectOne();
			System.out.println(blog.getContent());
		}finally{
			session.close();
		}
	}
	public static void main(String[] args) throws IOException {
		getBlog();
		//new SimpleDateFormat()
		//getBlog1();
	}

}
