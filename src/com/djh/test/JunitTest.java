package com.djh.test;

import java.net.URI;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.djh.mapper.UserMapper;
import com.djh.model.UserBean;

public class JunitTest {
	static ApplicationContext context =null;
	@Before
	public static void	init(){
        context = new ClassPathXmlApplicationContext(new String[]{"classpath:applicationContext.xml",
        		"classpath:config/springMvc.xml","classpath:config/dataResource.xml"});
	}
	@Test
	public static void getBean(){
		//验证bean是否在spring中托管
		//System.out.println(context.getBean("userMapper").getClass().getName());
		UserMapper userMapper = (UserMapper) context.getBean("userMapper");
		UserBean user = userMapper.queryById(2);
		System.out.println(user.getUsername());
		logger.info(user.getPassword());
		//Assert.assertEquals(context.getBean("loginController").getClass(), LoginController.class);
	}
	
	public void testRechargeConfig(){
		//RechargeConfig rechargeConfig = (RechargeConfig) context.getBean("rechargeConfig");
		//System.out.println(rechargeConfig.url+rechargeConfig.interfaceId+rechargeConfig.key);
		/*
			public static String url = "http://121.199.2.76:8080/faaccept/";
			public static String interfaceId = "yinliansw";
			public static String key = "b9667a075fc2bd435d64fb0931efa9ae";
		 * */
		
	}
	public void testUpdate(){
		
	}
	public static void main(String[] args) {
		init();
		getBean();
	}
	
	static Logger logger = Logger.getLogger(JunitTest.class);
}
