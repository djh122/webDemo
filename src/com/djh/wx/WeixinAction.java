package com.djh.wx;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.Iterator;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.djh.common.Const;
import com.djh.util.HttpClientUtil;
import com.djh.wx.model.ReceiveXml;

@Controller
public class WeixinAction {
	
	
	/**
	 * 验证token的url
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value="/valid.do",produces="text/plain;charset=UTF-8")
	@ResponseBody
	public String valid(HttpServletRequest request,HttpServletResponse response) throws IOException {
		//获取请求参数
        String signature = request.getParameter("signature");
        String timestamp = request.getParameter("timestamp");
        String nonce = request.getParameter("nonce");
        String echostring = request.getParameter("echostr");
        String token = Const.token;    //读取设置的token       
        //对请求参数和自己的token进行排序，并连接排序后的结果为一个字符串
        String[] strSet = new String[]{token, timestamp, nonce};
        java.util.Arrays.sort(strSet);
        String total = "";
        for (String string : strSet) {
            total = total + string;
        }
        //SHA-1加密实例
        MessageDigest sha1 = null;
		try {
			sha1 = MessageDigest.getInstance("SHA-1");
		} catch (NoSuchAlgorithmException e) {e.printStackTrace();}//这可能报错吗？
        sha1.update(total.getBytes());
        byte[] codedBytes = sha1.digest();
        
        String codedString = new BigInteger(1, codedBytes).toString(16);//将加密后的字节数组转换成字符串。参见http://hi.baidu.com/aotori/item/c94813c4f15caa60f6c95d4a
        
        if(codedString.equals(signature)){
        	//String resultString = request.getParameter("echostr");
        	return echostring;
        }else {
			logger.error("微信token验证失败");
			throw new RuntimeException("微信token验证失败");
		}
	}
	
	/**
	 * 微信发送过来的信息回复处理
	 * @param request
	 * @return
	 * @throws DocumentException 
	 * @throws IOException 
	 */
	@RequestMapping(value="/inputMsg.do",produces="text/plain;charset=UTF-8")
	@ResponseBody
	public String inputMsg(HttpServletRequest request) throws DocumentException, IOException {
		// xml请求解析
		InputStream inputStream = request.getInputStream();  
	    // 读取输入流  
		SAXReader reader = new SAXReader();
		Document document = reader.read(inputStream);
		String requestXml = document.asXML();
		ReceiveXml receiveXml = WxUtil.getXmlEntity(requestXml);
		request.setAttribute("receiveXml", receiveXml);
		//根据不同的消息类型，获取相应的对象 信息。
		String msgType = receiveXml.getMsgType();
		String contents = "";
		String custContents = null;
		boolean isToCust = false;
		
		//发送文本消息
		if("text".equals(msgType)){
			String message = receiveXml.getContent();
			//转客服
			if(message.indexOf("投诉")!=-1||message.indexOf("你好")!=-1||message.indexOf("您好")!=-1||message.indexOf("在吗")!=-1){
    			isToCust = true;
				custContents = "<xml>"+
	            "<ToUserName><![CDATA["+receiveXml.getFromUserName()+"]]></ToUserName>"+
	            "<FromUserName><![CDATA["+receiveXml.getToUserName()+"]]></FromUserName> "+
	            "<CreateTime>"+new Date().getTime()+"</CreateTime>"+
	            "<MsgType><![CDATA[transfer_customer_service]]></MsgType>"+
	            "</xml>";
			}     				
			contents = "您发送的是文本消息！";       				
		}
		//发送图片
		else if("image".equals(msgType)){
			contents = "您发送的是图片！";
		}
		//发送图文消息
		else if("news".equals(msgType)){
			contents = "您发送的是图文消息！";
		}
		//事件推送
		else if("event".equals(msgType)){
			String eventType = receiveXml.getEvent();
			if("subscribe".equals(eventType)){
				//获取事件key值
				String eventKey = receiveXml.getEventKey();
				logger.info("===============eventKey====================="+eventKey);
				if(null!=eventKey&&eventKey.trim().length()>0){
					String a[] = eventKey.split("qrscene_");
					if(a.length==2){
						logger.info("===============sceneId====================="+eventKey);
						request.getSession().setAttribute("invite_id", a[1]);
						logger.info("===============邀请人的eaccNo====================="+a[1]);
						logger.info("===============ToUserName====================="+receiveXml.getToUserName());
						logger.info("===============FromUserName====================="+receiveXml.getFromUserName());
					}
				}				
				contents = "谢谢您的关注！";
			}
			else if("unsubscribe".equals(eventType)){
				contents = "希望您再次关注！";
			}
			else if("CLICK".equals(eventType)){
				contents = "开发进行中，敬请期待！";
//    					if("M0002".equals(eventKey)){
//    						contents = "您正在查看账户信息！";
//    					}else{
//    						contents = "开发进行中，敬请期待！";
//    					}      					
			}
		}           			
//    			String postStr = "<xml>"+
//			            "<ToUserName><![CDATA["+receiveXml.getFromUserName()+"]]></ToUserName>"+
//			            "<FromUserName><![CDATA["+receiveXml.getToUserName()+"]]></FromUserName> "+
//			            "<CreateTime>"+new Date().getTime()+"</CreateTime>"+
//			            "<MsgType><![CDATA[text]]></MsgType>"+
//			            "<Content><![CDATA["+contents+"]]></Content>"+
//			            "</xml>";
		
		
		String postStr = "<xml>"+
	            "<ToUserName><![CDATA["+receiveXml.getFromUserName()+"]]></ToUserName>"+
	            "<FromUserName><![CDATA["+receiveXml.getToUserName()+"]]></FromUserName> "+
	            "<CreateTime>"+new Date().getTime()+"</CreateTime>"+
	            "<MsgType><![CDATA[news]]></MsgType>"+
	            "<ArticleCount>2</ArticleCount>"+
	            "<Articles>"+
    			"<item>"+
    			"<Title><![CDATA[小C Bank]]></Title>"+ 
    			"<Description><![CDATA[最快捷、最安全，您最明智的选择]]></Description>"+
    			"<PicUrl><![CDATA[http://sunyard88.com/wx/v2/images/banner1.png]]></PicUrl>"+
    			"<Url><![CDATA[http://sunyard88.com/wx/productDealAction_productView.action]]></Url>"+
    			"</item>"+
    			"<item>"+
    			"<Title><![CDATA[小C Bank你手中的理财宝]]></Title>"+
    			"<Description><![CDATA[大智慧]]></Description>"+
    			"<PicUrl><![CDATA[http://xiaocbank.com/wx/v2/images/banner1.png]]></PicUrl>"+
    			"<Url><![CDATA[http://xiaocbank.com/wx/productDealAction_productView.action]]></Url>"+
    			"</item>"+
    			"</Articles>"+   			        
	            "</xml>";  			   			
		
		
		if(isToCust){
			return custContents; 
		}else{
			return postStr; 
		}
    }				
	
	public String oauth2(HttpServletRequest request) throws IOException {
		String code = request.getParameter("code"); 
		String state = request.getParameter("state"); 
		String appId = Const.appid;
		String secret = Const.appid;
		String result = null;
			//调用第三方应用，进行一个http请求
		String postUrl = "https://api.weixin.qq.com/sns/oauth2/access_token?appid="+appId+"&secret="+secret+"&code="+code+"&grant_type=authorization_code";
		result = HttpClientUtil.get(postUrl, "utf-8");
		logger.info("返回微信用户信息为："+result);
		JSONObject jsonObject = JSONObject.fromObject(result);
		
		String openId = jsonObject.get("openid").toString();
		return "";
	}
	/**
	 * 签名
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value="/valid.do",produces="text/plain;charset=UTF-8")
	@ResponseBody
	public String jsSign(HttpServletRequest request,HttpServletResponse response) throws IOException {
		//Map<String, Object> outMap = new TreeMap<String, Object>();
		
//	    long timestamp = System.currentTimeMillis();
//	    outMap.put("token", TOKEN);
//	    outMap.put("timestamp", timestamp);
//	    outMap.put("nonce", 123456);
//	    String source = "";
//	    for(String key:outMap.keySet()){
//	    	source = source+outMap.get(key);
//	    }
		String resultString = request.getParameter("echostr");
		return resultString;
	}
	
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
	
	static Logger logger = Logger.getLogger(WeixinAction.class);
}
