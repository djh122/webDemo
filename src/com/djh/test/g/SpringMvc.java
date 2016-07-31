package com.djh.test.g;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

public class SpringMvc {

	@RMap("login.do")
	public void login(User user,@PM("code")String code) {
		System.out.println(user.name);
		System.out.println(code);
		System.out.println(user.pwd);
	}
	
	@RMap("out.do")
	public void loginOut(User user,@PM("out")String out) {
		System.out.println("goodbye,"+user.name+","+out);
	}
	
	public static class User{
		public String name;
		public String pwd;
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getPwd() {
			return pwd;
		}
		public void setPwd(String pwd) {
			this.pwd = pwd;
		}
	}
	public static void main(String[] args) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Map<String, String> request = new HashMap<String, String>();
		request.put("name", "djh");
		request.put("pwd", "123456");
		request.put("code", "9999");
		request.put("out", "oooooooooh");
		MyFactory.register(SpringMvc.class);
		/*
		 * 请求地址login.do, 请求参数
		 */
		MyFactory.exe("out.do", request);
	}
}
