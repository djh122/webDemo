//package test.base;
//
//import net.sf.json.JSONObject;
//
//public class Generator {
//	/**
//	 * json格式：
//	 * 		{name:"Test",package:"com.sunyard.cms.manage.dao",method:["pageList","pageCount","add","delete","modify"]}
//	 * @param json
//	 */
//	private StringBuffer sb = new StringBuffer();
//	public Generator() {
//		JSONObject params = JSONObject.fromObject(json);
//		sb.append(header_xml.replace("com.sunyard.cms.manage.dao.TestDao", params.getString("package")+"."+params.getString("name")+"Dao"));
//		
//		
//		
//		sb.append("</mapper>");
//	}
//
//	@Override
//	public String toString() {
//		return sb.toString();
//	}
//	
//	private final String  header_xml = "<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\n"+
//			"<!DOCTYPE mapper PUBLIC \"-//mybatis.org//DTD Mapper 3.0//EN\" \"http://mybatis.org/dtd/mybatis-3-mapper.dtd\">\n<mapper namespace=\"com.sunyard.cms.manage.dao.TestDao\">\n";
//	
//	public static void main(String[] args) {
//		//new Generator("")
//	}
//}
