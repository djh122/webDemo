package com.djh.wx;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.apache.commons.codec.digest.DigestUtils;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.djh.common.Const;
import com.djh.common.cache.Cache;
import com.djh.common.cache.CacheManager;
import com.djh.util.HttpClientUtil;
import com.djh.wx.model.ReceiveXml;

public class WxUtil {

	//public static final String appid = "wxddf43284d1df035f";
	//public static final String appsecret = "bfdd04f113735fad71752a5b46815cdf";
	
	//public static final String url = "https://api.weixin.qq.com/cgi-bin/token";//?grant_type=client_credential&appid=APPID&secret=APPSECRET

	/**
	 * 获取微信access_token
	 */
	public static String getAccess_token() {
		//先从缓存中获取
		Cache cache = CacheManager.getCache("access_token");
		if(CacheManager.isHit(cache)){
			return cache.getValue().toString();
		}
		
		final String urlAtocken = "https://api.weixin.qq.com/cgi-bin/token";//?grant_type=client_credential&appid=APPID&secret=APPSECRET
		String rs = null;
		String url = urlAtocken+"?grant_type=client_credential&appid="+Const.appid+"&secret="+Const.appsecret;
		try {
			String str = HttpClientUtil.get(url, "utf-8");
			rs = JSONObject.fromObject(str).get("access_token").toString();
			CacheManager.addCache(new Cache("wx_access_token",rs, 7200));
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException("获取微信access_token失败");
		}
		return rs;
	}
	
	/**
	 * 
	 */
	//https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=ACCESS_TOKEN&type=jsapi
	
	public static String getTicket() {
		String ticket = null;
		//先从缓存中获取
		Cache cache = CacheManager.getCache("access_token");
		if(CacheManager.isHit(cache)){
			return cache.getValue().toString();
		}
		String urlTicket = "https://api.weixin.qq.com/cgi-bin/ticket/getticket";
		String access_token = getAccess_token();
		String url = urlTicket+"?access_token="+access_token+"&type=jsapi";
		try {
			String rs = HttpClientUtil.get(url, "utf-8");
			ticket = JSONObject.fromObject(rs).get("access_token").toString();
			CacheManager.addCache(new Cache("wx_ticket",ticket, 7200));

		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException("获取微信jsapi_ticket失败");
		}
		return ticket;
	}
	/**
	 * 注入权限验证配置
	 * @param url
	 * @param jsApiList
	 * @return
	 */
	public Map<String, Object> jsSign(String url,List<String> jsApiList) {
		Cache cache = CacheManager.getCache(url);
		if(CacheManager.isHit(cache)){
			return (Map<String, Object>) cache.getValue();
		}
		Map<String, Object> map = new TreeMap<String, Object>();
		map.put("appId", Const.appid);
		map.put("timestamp", System.currentTimeMillis());
		map.put("nonceStr", "suiji");
		map.put("jsApiList", getTicket());
		StringBuffer sb = new StringBuffer();
		for(String key:map.keySet()){
			sb.append("&").append(key).append("=").append(map.get(key));
		}
		sb = sb.delete(0, 1);
		String sha = DigestUtils.shaHex(sb.toString());
		//sb.append("&signature=").append(sha);
		map.put("signature", sha);
		String list = JSONArray.fromObject(jsApiList).toString();//new JSONArray();
		map.put("jsApiList", list);
		CacheManager.addCache(new Cache(url, map, Integer.MAX_VALUE));
		return map;
	}
	
//	public static void oauth2() {
//		final String oauth2Url = "https://open.weixin.qq.com/connect/oauth2/authorize";
//		// https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx841a97238d9e17b2&redirect_uri=http://cps.dianping.com/weiXinRedirect&response_type=code&scope=snsapi_userinfo
//	}
	
	public static ReceiveXml getXmlEntity(String strXml){
		ReceiveXml msg = null;
		try {
			if (strXml.length() <= 0 || strXml == null)
				return null;
			 
			// 将字符串转化为XML文档对象
			Document document = DocumentHelper.parseText(strXml);
			// 获得文档的根节点
			Element root = document.getRootElement();
			Iterator<?> iter = root.elementIterator();			
			msg = new ReceiveXml();
			
			//利用反射机制
			Class<?> c = Class.forName("com.sunyard.weixin.model.ReceiveXml");
			msg = (ReceiveXml)c.newInstance();//创建这个实体的对象
			
			while(iter.hasNext()){
				Element ele = (Element)iter.next();
				//获取set方法中的参数字段（实体类的属性）
				Field field = c.getDeclaredField(ele.getName());
				//获取set方法，field.getType())获取它的参数数据类型
				Method method = c.getDeclaredMethod("set"+ele.getName(), field.getType());
				//调用set方法
				method.invoke(msg, ele.getText());
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("xml 格式异常: "+ strXml);
			e.printStackTrace();
		}
		return msg;
	}
	
	public static void main(String[] args) {
		//getTicket();
		List<String> abc = new ArrayList<String>();
		abc.add("aa");
		abc.add("aa");
		System.out.println(JSONArray.fromObject(abc));
	}
}
