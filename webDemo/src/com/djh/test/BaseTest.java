package com.djh.test;

import java.util.Date;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class BaseTest {

	static ApplicationContext context =null;
	static{
	        context = new ClassPathXmlApplicationContext(new String[]{"classpath:applicationContext.xml"});
	}
	
	public static void getBean() {
		System.out.println(context.getBean("loginController"));
		//RechargeConfig rechargeConfig = (RechargeConfig) context.getBean("rechargeConfig");
		//System.out.println(rechargeConfig.url+rechargeConfig.interfaceId+rechargeConfig.key);
	}
	public static void  selectTest() {
//		OrderRechargeMapper rechargeMapper = (OrderRechargeMapper) context.getBean("orderRechargeMapper");
//		OrderRecharge order = new OrderRecharge();
//		order.setMerId("1000000001");
//		order.setOrderNo("F1433292453037");
//		
//		System.out.println("返回结果：  "+rechargeMapper.getLocalOrderNo(order));
		
	}
	
	public static void userMapperTest() {

		
		//rechargeMapper.
//		FlowBean flowBean = userMapper.selectTotalFlow("20150520");
//		//System.out.println("结果---"+flowBean.getCount());
//		//System.out.println(flowBean.getDate()+flowBean.getId());
//		
//		flowBean = new FlowBean();
//		//int flag = userMapper.insertFlow("20150420");
//		//System.out.println(flag);
//		
//		int flag = userMapper.updateTotalFlow("20150420", 100);
//		System.out.println(flag);
		//String prefixPhone = phone.substring(0, 7);
//		int flag = userMapper.selectPhoneOnYidong("13456783212".substring(0, 7));
//		System.out.println("what's the fuck?"+flag);
		//System.out.println(userMapper.selectPhoneOnYidong("13429010000".substring(0, 7)));
//		UserBean userBean = new UserBean();
//		userBean.setCreatedate(new Date());
//		userBean.setCorrect(6);
//		userBean.setPhone("821434143");
//		userBean.setUser("戴集荷");
//		int flag = userMapper.insertUserBean(userBean);
//		System.out.println(flag);
		//String ak = "wxd99304c607efa74e";//浙江职工安康之家
//		String aj = "wx0410edcf3c69bdc8";//浙江安监
//		userMapper.insertFlow(DateUtil.getDate(), "wxd99304c607efa74e");
//		userMapper.updateTotalFlow(DateUtil.getDate(), 999, "wxd99304c607efa74e");
		
	}
	public static void main(String[] args) {
		//userMapperTest();//1342901
		//selectTest();
		getBean();
	}

}
