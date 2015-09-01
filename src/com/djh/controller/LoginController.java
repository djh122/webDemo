package com.djh.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class LoginController {
	
	@RequestMapping(value="/login.do",produces="text/plain;charset=UTF-8")
	@ResponseBody
	public String login(HttpServletRequest request,HttpServletResponse response) throws IOException {
		Map<String, Object> outMap = new HashMap<String, Object>();
		String name = request.getParameter("username");
		String password = request.getParameter("password");
		if(!("djh".equals(name) && "000000".equals(password))){
			outMap.put("code", "404");
			outMap.put("message", "登录失败");
		}else {
			outMap.put("code", "200");
			outMap.put("message", "登录成功");
		}
		String resultString = JSONObject.fromObject(outMap).toString();
		return resultString;
	}
}
