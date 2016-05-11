//package com.djh.action;
//
//import java.io.File;
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.List;
//
//import org.apache.commons.io.FileUtils;
//import org.apache.commons.lang.CharSet;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.context.annotation.Scope;
//import org.springframework.stereotype.Controller;
//
//import com.google.common.base.Charsets;
//import com.google.common.io.Files;
//import com.sunyard.weixin.action.BaseAction;
//import com.sunyard.weixin.common.exception.AppException;
//
//@Controller
//@Scope("prototype")
//public class TestAction extends BaseAction{
//
//	private static Logger logger = LoggerFactory.getLogger(TestAction.class);
//
//	public final static String basePath = "C:/Users/djh/Workspaces/SunCmb_wx/";  
//	public final static String strutsPath = basePath+"src/struts.xml";
//	public final static String classPath = basePath+"src/com/sunyard/weixin/";
//	
//	public final static String deskPath = "C:/Users/djh/Desktop/";
//	
//	public final static String pagePath = basePath+"WebContent/WEB-INF/jsp/";
//	
//	public void command(){
//		try{
//			String cmd = request.getParameter("command");
//			cmd = cmd.trim();
//			String c1 = "create module";
//			if(cmd.startsWith(c1)){
//				ArrayList<String> list = new ArrayList<String>();
//				String fucmd = cmd.substring(c1.length()).trim();
//				fucmd = fucmd.substring(0, 1).toUpperCase()+fucmd.substring(1);
//				list.add(createAction(fucmd));
//				list.add(createService(fucmd));
//				list.add(createServiceImpl(fucmd));
//				list.add(createDao(fucmd));
//				list.add(createDaoXml(fucmd));
//				updateStruts(fucmd);
//				
//				responseWriter("执行成功,创建了名为【"+fucmd+"】的模块,新增文件"+list.toString());
//			}else{
//				String[] key = cmd.split("\\s+");
//				if(key.length>=3 && key[0].startsWith("module:") && key[1].startsWith("method:") && key[2].startsWith("page:")){
//					String moduleName = key[0].split(":")[1];
//					String methodName = key[1].split(":")[1];
//					String pageName = key[2].split(":")[1];
//					updateStrutsAddMethod(moduleName, methodName, pageName);
//					updateAction(moduleName,methodName,pageName);
//					responseWriter("为"+moduleName+"Action.java添加了一个名为【"+methodName+"】的方法");
//				}
//			}
//			
//			
//		}catch(AppException e){
//			e.printStackTrace();
//			responseWriter(e.getMessage());
//		}catch (IOException e) {
//			e.printStackTrace();
//			responseWriter("文件读写错误");
//		}catch (Exception e) {
//			e.printStackTrace();
//			responseWriter("未知异常");
//		}
//		responseWriter("未能识别该命令");
//	}
//	private void updateAction(String moduleName, String methodName,String pageName) throws IOException {
//		String funame = firstUpper(moduleName);
//		String path = classPath+"action/"+funame+"Action.java";
//		File file = new File(path);
//		List<String> list = FileUtils.readLines(file, "utf-8");
//		for(int i=0;i<list.size();i++){
//			if(list.get(i).trim().equals("/*write_place_flag*/")){
//				list.add(i, "\tpublic String "+methodName+"(){");
//				list.add(i+1,"\t\t//TODO");
//				list.add(i+2,"\t\treturn \""+methodName+"\";");
//				list.add(i+3,"\t}");
//				list.add(i+4,"");
//				break;
//			}
//		}
//		FileUtils.writeLines(file,"utf-8", list);
//		//FileUtils.writeli(file, data, encoding);
//		
//	}
//	private static String createServiceImpl(String fucmd) throws IOException {
//		String path = classPath+"/service/impl/"+fucmd+"ServiceImpl.java";
//		File file = new File(path);
//		String fileName = fucmd+"ServiceImpl.java";
//		int i=1;
//		while(file.exists()){
//			String path1 = classPath+"/service/impl/"+fucmd+"ServiceImpl("+i+").java";
//			file = new File(path1);
//			fileName = fucmd+"ServiceImpl("+i+").java";
//			i++;
//			//throw new AppException("5001", "文件已存在，创建文件错误"+U.format("method[{0}]", "createService"));
//		}
//		Files.createParentDirs(file);
//		file.createNewFile();
//		List<String> content = new ArrayList<String>();
//		content.add("package com.sunyard.weixin.service.impl;");
//		content.add("");
//		content.add("import javax.annotation.Resource;");
//		content.add("import org.springframework.stereotype.Service;");
//		content.add("import org.springframework.transaction.annotation.Transactional;");
//		content.add("import com.sunyard.weixin.dao."+fucmd+"Dao;");
//		content.add("import com.sunyard.weixin.service."+fucmd+"Service;");
//		content.add("");
//		content.add("import com.sunyard.weixin.dao."+fucmd+"Dao;");
//		content.add("@Service(\""+firstLowwer(fucmd)+"Service\")");
//		content.add("@Transactional");
//		content.add("public class "+fucmd+"ServiceImpl implements "+fucmd+"Service{");
//		content.add("");
//		content.add("\t@Resource");
//		content.add("\tprivate "+fucmd+"Dao "+firstLowwer(fucmd)+"Dao;");
//		content.add("");
//		content.add("\t/*write_place_flag*/");
//		content.add("}");
//		FileUtils.writeLines(file,"utf-8", content);
//		return fileName;
//	}
//	private static String createService(String fucmd) throws IOException {
//		String path = classPath+"/service/"+fucmd+"Service.java";
//		File file = new File(path);
//		String fileName = fucmd+"Service.java";
//		int i=1;
//		while(file.exists()){
//			String path1 = classPath+"/service/"+fucmd+"Service("+i+").java";
//			file = new File(path1);
//			fileName = fucmd+"Service("+i+").java";
//			i++;
//			//throw new AppException("5001", "文件已存在，创建文件错误"+U.format("method[{0}]", "createService"));
//		}
//		
//		Files.createParentDirs(file);
//		file.createNewFile();
//		List<String> content = new ArrayList<String>();
//		content.add("package com.sunyard.weixin.service;");
//		content.add("");
//		content.add("public interface "+fucmd+"Service {");
//		content.add("");
//		content.add("");
//		content.add("\t/*write_place_flag*/");
//		content.add("}");
//		FileUtils.writeLines(file,"utf-8", content);
//		return fileName;
//	}
//	
//	private static String firstLowwer(String s) {
//		return s.substring(0,1).toLowerCase()+s.substring(1);
//	}
//	private static String firstUpper(String s) {
//		return s.substring(0,1).toLowerCase()+s.substring(1);
//	}
//	public static void updateStruts(String fucmd) throws IOException {
//		String path = strutsPath;
//		String flname = firstLowwer(fucmd);
//		File file = new File(path);
//		List<String> list = FileUtils.readLines(file, "utf-8");
//		for(int i=0;i<list.size();i++){
//			if(list.get(i).trim().equals("<!--write_place_flag-->")){
//				String element0 = "\t\t<action name=\""+flname+"Action_*\" class=\""+flname+"Action\" method=\"{1}\">";
//				String element1 = "\t\t</action>";
//				list.add(i, element0);
//				list.add(i+1,element1);
//				break;
//			}
//		}
//		FileUtils.writeLines(file,"utf-8", list);
//	}
//	
//	public static void updateStrutsAddMethod(String fucmd,String url,String page) throws IOException {
//		String path = strutsPath;
//		String flname = firstLowwer(fucmd);
//		File file = new File(path);
//		List<String> list = FileUtils.readLines(file, "utf-8");
//		for(int i=0;i<list.size();i++){
//			if(list.get(i).trim().startsWith("<action name=\""+flname+"Action_*")){
//				String element = "\t\t\t<result name=\""+url+"\">/WEB-INF/jsp/"+page+"</result>";
//				list.add(i+1,element);
//				break;
//			}
//		}
//		FileUtils.writeLines(file,"utf-8", list);
//	}
//	public static String createDao(String fucmd) throws IOException {
//		String path = classPath+"/dao/"+fucmd+"Dao.java";
//		File file = new File(path);
//		String fileName = fucmd+"Dao.java";
//		int i=1;
//		while(file.exists()){
//			String path1 = classPath+"/dao/"+fucmd+"Dao("+i+").java";
//			file = new File(path1);
//			fileName = fucmd+"Dao("+i+").java";
//			i++;
//			//throw new AppException("5001", "文件已存在，创建文件错误"+U.format("method[{0}]", "createService"));
//		}
//		
//		Files.createParentDirs(file);
//		file.createNewFile();
//		List<String> content = new ArrayList<String>();
//		content.add("package com.sunyard.weixin.dao;");
//		content.add("");
//		content.add("import java.util.Map;");
//		content.add("");
//		content.add("public interface "+fucmd+"Dao {");
//		content.add("");
//		content.add("\tpublic Map<String, Object> get"+fucmd+"Model(Map<String, Object> param);");
//		content.add("");
//		content.add("\tpublic int update"+fucmd+"(Map<String, Object> param);");
//		content.add("");
//		content.add("\t/*write_place_flag*/");
//		content.add("}");
//		FileUtils.writeLines(file,"utf-8", content);
//		return fileName;
//	}
//	
//	public static String createDaoXml(String fucmd) throws IOException {
//		String path = classPath+"/dao/"+fucmd+"Dao.xml";
//		File file = new File(path);
//		String fileName = fucmd+"Dao.xml";
//		int i=1;
//		while(file.exists()){
//			String path1 = classPath+"/dao/"+fucmd+"Dao("+i+").xml";
//			file = new File(path1);
//			fileName = fucmd+"Dao("+i+").xml";
//			i++;
//			//throw new AppException("5001", "文件已存在，创建文件错误"+U.format("method[{0}]", "createService"));
//		}
//		
//		Files.createParentDirs(file);
//		file.createNewFile();
//		List<String> content = new ArrayList<String>();
//		content.add("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
//		content.add("<!DOCTYPE mapper PUBLIC \"-//mybatis.org//DTD Mapper 3.0//EN\" \"http://mybatis.org/dtd/mybatis-3-mapper.dtd\">");
//		content.add("<mapper namespace=\"com.sunyard.weixin.dao."+fucmd+"Dao\">");
//		content.add("");
//		content.add("\t<select id=\"get"+fucmd+"Model\" parameterType=\"map\" resultType=\"map\">");
//		content.add("\t\t<!-- TODO -->");
//		content.add("\t</select>");
//		content.add("");
//		content.add("\t<update id=\"update"+fucmd+"\" parameterType=\"map\">");
//		content.add("\t\t<!-- TODO -->");
//		content.add("\t</update>");
//		content.add("");
//		content.add("\t<!--write_place_flag-->");
//		content.add("</mapper>");
//		FileUtils.writeLines(file,"utf-8", content);
//		return fileName;
//	}
//	public static String createAction(String fumoduleName) throws IOException {
//		String fileName = fumoduleName+"Action.java";
//		String path = classPath+"/action/"+fumoduleName+"Action.java";
//		
//		File file = new File(path);
//		int i=1;
//		while(file.exists()){
//			String path1 = classPath+"/action/"+fumoduleName+"Action("+i+").java";
//			fileName = fumoduleName+"Action("+i+").java";
//			file = new File(path1);
//			i++;
//			//throw new AppException("5001", "文件已存在，创建文件错误"+U.format("method[{0}]", "createService"));
//		}
//		Files.createParentDirs(file);
//		file.createNewFile();
//		List<String> content = new ArrayList<String>();
//		content.add("package com.sunyard.weixin.action;");
//		content.add("");
//		content.add("import org.springframework.context.annotation.Scope;");
//		content.add("import org.springframework.stereotype.Controller;");
//		content.add("import com.sunyard.weixin.action.BaseAction;");
//		content.add("");
//		content.add("@Controller");
//		content.add("@Scope(\"prototype\")");
//		content.add("public class "+fumoduleName+"Action extends BaseAction{");
//		content.add("");
//		content.add("\t/*write_place_flag*/");
//		content.add("}");
//		FileUtils.writeLines(file,"utf-8", content);
//		return fileName;
//	}
//	
//	public static void apacheIO() {
//		  
//		
// 
// 
//	}
//	public static void main(String[] args) throws IOException {
////		String cmd = "create module abc";
////		String c1 = "create module";
////		String module = null;
////		if(cmd.startsWith(c1)){
////			module = cmd.substring(c1.length()).trim();
////			module = module.substring(0, 1).toUpperCase()+module.substring(1);
////		}
////		System.out.println(module);
//		//createAction(null);
//		//createAction("Djh");
//		//apacheIO();
//		//updateStruts("djh");
//		createAction("Djh1");
//	}
//}
