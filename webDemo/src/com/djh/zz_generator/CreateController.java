package com.djh.zz_generator;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CreateController {
	//创建的bean名称
	static String beanName="ServerApply";
	//创建者姓名
	final static String createName="djh";

	static String chineseName="服务器申请";
	
	
	final static String path="com.jielan.jlop.controller";
		
	static String fisrtDowncaseName=(char)(beanName.charAt(0)+32)+beanName.substring(1);
	static String fileUrl="src/"+path.replaceAll("\\.", "/")+"/"+beanName+"Controller.java";
	public static void main(String[] args) throws IOException {
		replaceContent();
	}
	public static String replaceContent() throws IOException {
		StringBuffer sb=new StringBuffer();
		BufferedReader br=new BufferedReader(
				new FileReader(
						new File("template/controller/departmentController.java")));
		//System.out.println(br.toString());
		String temp="";
		while((temp=br.readLine())!=null){
			sb.append(temp+"\n");
		}
		br.close();
		String content=sb.toString();
		content=content.replaceAll("%Department%", beanName);
		content=content.replaceAll("%author%", createName);
		content=content.replaceAll("%createDate%", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
		content=content.replaceAll("%department%", fisrtDowncaseName);
		//System.out.println(content);
		content=content.replace("%部门%", chineseName);
		openWriter(fileUrl, content, "w");				
		return null;
	}
	/**
	 * 字符流文件写入
	 * @param fileUrl 文件路径
	 * @param content 文件内容
	 * @param flag    写入模式
	 * 						w:写入文件，删掉原来的内容；a:写入文件，追加到原来内容的结尾；
	 * 						n:写入文件，换行追加
	 * */
	public static void openWriter(String fileUrl,String content,String flag) {
		BufferedWriter bf=null;
		try {
			if(flag.equalsIgnoreCase("w")){	
				bf=new BufferedWriter(
						new FileWriter(new File(fileUrl)));					 		
			}else if (flag.equalsIgnoreCase("a")) {
				bf=new BufferedWriter(
						new FileWriter(new File(fileUrl),true));					 			
			}else if (flag.equalsIgnoreCase("n")) {
				bf=new BufferedWriter(
						new FileWriter(new File(fileUrl),true));
				bf.write("\r\n"+content);
				bf.close();
				return;
			}
			bf.write(content);
			bf.flush();
			System.out.println("文件生成成功");
			bf.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
