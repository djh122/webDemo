package com.djh.zz_generator;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
public class CreateJsp {
	//创建的bean名称
	static String beanName="serverApply";
	//创建的bean名称
	static String beanChineseName="服务器申请";
	//字段名称及展现形式
	
	public static void main(String[] args) throws IOException {
		addReplace();
		updateReplace();
		listReplace();
	}
	static final String[][] add={
			{"  项目名称  ",  "  itemName  ","   text    "},
			{"   状态：开发申请，组长申请，组长审核，组长不通过，运维通过，运维不通过  ",  "   status:0,1,2,3,4,5,6  ","   select   "},
			{"    部门名称 ",  "  department:depId,depName   ","   callback    "},
			{"  申请时间  ",  "  createTime   ","   date    "},
			{"  备注  ",  "  note   ","   text    "},
		
//			{"  创建时间 ",  "  createTime   ","   date    "},
//			{"  是否使用：使用中，禁用  ",  "  isUsed：1,0   ","   select    "},
			
			
			
//			{"     ",  "     ","   checkbox  "},
			//{"     ",  "     ","   radio   "},
//			{" 性别：男,女    ",  "  sex：1,2,3   ","   select   "},
//			{"     ",  "     ","   password   "},			
//			{"     ",  "     ","   textarea   "},
			//{"     ",  "     ","   date   "}		
	};
	static final String[][] update={
		{"  项目名称  ",  "  itemName  ","   text    "},
		{"   状态：开发申请，组长申请，组长审核，组长不通过，运维通过，运维不通过  ",  "   status:0,1,2,3,4,5,6  ","   select   "},
		{"    部门名称 ",  "  department:depId,depName   ","   callback    "},
		{"  备注  ",  "  note   ","   text    "},				
		
//		{"  服务器ID  ",  "  server:serverId,serverName  ","   callback    "},
//		{"   数据库类型：oracle，mysql,其他  ",  "   dbType:1,2,3  ","   select   "},
//		{"  ip地址   ",  "  ipAddress   ","   text    "},
//		{"  数据库用户  ",  "  dbName   ","   text    "},
//		{"  数据库密码  ",  "  dbPwd   ","   password    "},
//		{"  端口  ",  "  port   ","   text    "},
//		{"  备注  ",  "  note   ","   text    "}
//		{"  创建时间 ",  "  createTime   ","   date    "},
//		{"  是否使用：使用中，禁用  ",  "  isUsed：1,0   ","   select    "}	
	};
	static final String[][] list={
		{"  项目名称  ",  "  itemName  ","   text    "},
		{"   状态：开发申请，组长申请，组长审核，组长不通过，运维通过，运维不通过  ",  "   status:0,1,2,3,4,5,6  ","   select   "},
		{"    部门名称 ",  "  department:depId,depName   ","   callback    "},		
		{"  备注  ",  "  note   ","   text    "},
		{"  申请时间  ",  "  createTime   ","   date    "}
	};
	static String fisrtUpcaseName=(char)(beanName.charAt(0)-32)+beanName.substring(1);	
	//写入 添加页面 文件 的地址
	static String addFileUrl="template/jsp/"+beanName+"/add"+fisrtUpcaseName+".jsp";
	//写入 修改页面 文件 的地址
	static String updateFileUrl="template/jsp/"+beanName+"/update"+fisrtUpcaseName+".jsp";
	//写入 列表页面 文件 的地址
	static String listFileUrl="template/jsp/"+beanName+"/"+beanName+"List.jsp";
	//文件目录生成路径
	static String fileDirs="template/jsp/"+beanName+"/";
	
	//
	public static String addReplace() throws IOException {
		StringBuffer sb=new StringBuffer();
		BufferedReader br=new BufferedReader(
				new FileReader(
						new File("template/jsp/addMaster.jsp")));
			//System.out.println(br.toString());
		String temp="";
		while((temp=br.readLine())!=null){
			sb.append(temp+"\n");
		}
		br.close();
		String content=sb.toString();
		content=content.replaceAll("%BeanName%", fisrtUpcaseName);
		content=content.replaceAll("%content%",createAddContent() );
		openWriter(addFileUrl, content, "n");
		System.out.println("生成添加页面成功");
		return null;
	}
	
	public static String updateReplace() throws IOException {
		StringBuffer sb=new StringBuffer();
		BufferedReader br=new BufferedReader(
				new FileReader(
						new File("template/jsp/updateMaster.jsp")));
		String temp="";
		while((temp=br.readLine())!=null){
			sb.append(temp+"\n");
		}
		br.close();
		String content=sb.toString();
		content=content.replaceAll("%Master%", fisrtUpcaseName);
		content=content.replace("%content%",createUpdateContent());
		openWriter(updateFileUrl, content, "n");
		System.out.println("生成修改页面");
		return null;
	}
	public static String listReplace() throws IOException {
		StringBuffer sb=new StringBuffer();
		BufferedReader br=new BufferedReader(
				new FileReader(
						new File("template/jsp/masterList.txt")));
		String temp="";
		while((temp=br.readLine())!=null){
			sb.append(temp+"\n");
		}
		br.close();
		String content=sb.toString();
		content=content.replaceAll("%Master%", fisrtUpcaseName);
		content=content.replaceAll("%用户%", beanChineseName);
		content=content.replace("%content%",createListContent());
		openWriter(listFileUrl, content, "n");
		System.out.println("生成列表成功");
		return null;
	}
	//生成所需添加的内容
	public static String createAddContent() {
		StringBuffer sb=new StringBuffer();
		for(int i=0;i<add.length;i++){
			if(i%3==0&&i==0){
				sb.append("<tr>");
			}else if(i%3==0&&i*3<add.length&&i!=0){		
				sb.append("</tr>\n<tr>");			
			} 
			if((add[i][2].trim()).equals("text")){
				String temp="<td class=\"ctext\" style=\"text-align:center;border-top:1px #d0d0d0 solid;\">%文本名%</td>"+
						"<td class=\"ctext\" style=\"border-top:1px #d0d0d0 solid;\">"+
						"<input type=\"text\"  value=\"\" name=\"%textName%\"  style=\"width:150px;\" class=\"required\"/>"+
						"</td>";
				temp=temp.replace("%文本名%", add[i][0].trim());
				temp=temp.replace("%textName%", add[i][1].trim());
				sb.append(temp);
			}else if ((add[i][2].trim()).equals("callback")){
				String temp="<td class=\"ctext\" style=\"text-align:center;\">选择%部门%</td>"+
						"<td class=\"ctext\"  colspan=\"3\" style=\"text-align:left;\">"+
						"<input name=\"%department%.%depId%\" value=\"\" type=\"hidden\" style=\"float:left;margin-top:5px;\"/>"+
						"<input name=\"%department%.%depName%\" type=\"text\"  style=\"float:left;margin-top:5px;\" readonly=\"readonly\" size=\"30\" />"+
						"<a class=\"btnLook\" href=\"Master/%department%Id.do\" lookupGroup=\"%department%\" style=\"margin-top:5px;\">查找带回</a>%部门%。"+
						"</td>";
				temp=temp.replace("%部门%", add[i][0].trim());
				String callbacks=add[i][1].replace("\\s+", "").trim();			
				String[] strings=spiltString(callbacks);
				temp=temp.replace("%department%", strings[0].trim());
				temp=temp.replace("%depId%", strings[1].trim());
				temp=temp.replace("%depName%", strings[2].trim());
				sb.append(temp);
			}else if ((add[i][2].trim()).equals("select")){
				StringBuffer sbSelect=new StringBuffer();
				String temp="<td class=\"ctext\" style=\"text-align:center;border-top:1px #d0d0d0 solid;\">%性别%</td>"
						+ "<td class=\"ctext\" style=\"border-top:1px #d0d0d0 solid;\">"
						+ "<select name=\"%sex%\" class=\"combox\">";
				sbSelect.append(temp);
				String chineseName=add[i][0].replace("\\s+", "");
				String[] chineseNames=spiltString(chineseName);
				String name=add[i][1].replace("\\s+", "");
				String[] names=spiltString(name);
				for(int j=1;j<chineseNames.length;j++){
					sbSelect.append("<option value=\""+names[j]+"\">"+chineseNames[j]+"</option>");
				}
				sbSelect.append("</select></td>");
				temp=sbSelect.toString();
				temp=temp.replace("%性别%", chineseNames[0]);
				temp=temp.replace("%sex%", names[0]);
				sb.append(temp);
			}else if ((add[i][2].trim()).equals("radio")){
				String temp="";
				temp=temp.replace("%文本名%", add[i][0].trim());
				temp=temp.replace("%textName%", add[i][1].trim());
				sb.append(temp);
			}else if ((add[i][2].trim()).equals("checkbox")){
				String temp="";
				temp=temp.replace("%文本名%", add[i][0].trim());
				temp=temp.replace("%textName%", add[i][1].trim());
				sb.append(temp);
			}else if ((add[i][2].trim()).equals("password")){
				String temp="";
				temp=temp.replace("%文本名%", add[i][0].trim());
				temp=temp.replace("%textName%", add[i][1].trim());
				sb.append(temp);
			}
			if (i==(add.length-1)) {
				sb.append("</tr>");
			}
		}
		return sb.toString();
	}
	//生成所需修改的信息
	public static String createUpdateContent() {
		StringBuffer sb=new StringBuffer();
		for(int i=0;i<add.length;i++){
			if(i%3==0&&i==0){
				sb.append("<tr>");
			}else if(i%3==0&&i*3<add.length&&i!=0){		
				sb.append("</tr>\n<tr>");			
			} 
			
			if((add[i][2].trim()).equals("text")){
				String temp="<td class=\"ctext\" style=\"text-align:center;border-top:1px #d0d0d0 solid;\">%文本名%</td>"+
						"<td class=\"ctext\" style=\"border-top:1px #d0d0d0 solid;\">"+
						"<input type=\"text\"  value=\"${bean."+add[i][1].trim()+" }\" name=\"%textName%\"  style=\"width:150px;\" class=\"required\"/>"+
						"</td>";
				temp=temp.replace("%文本名%", add[i][0].trim());
				temp=temp.replace("%textName%", add[i][1].trim());
				sb.append(temp);
			}else if ((add[i][2].trim()).equals("callback")){
				String temp="<td class=\"ctext\" style=\"text-align:center;\">选择%部门%</td>"+
						"<td class=\"ctext\"  colspan=\"3\" style=\"text-align:left;\">"+
						"<input name=\"%department%.%depId%\" value=\"%depId%\" type=\"hidden\" style=\"float:left;margin-top:5px;\"/>"+
						"<input name=\"%department%.%depName%\" value=\"%depName%\" type=\"text\"  style=\"float:left;margin-top:5px;\" readonly=\"readonly\" size=\"30\" />"+
						"<a class=\"btnLook\" href=\"Master/%department%Id.do\" lookupGroup=\"%department%\" style=\"margin-top:5px;\">查找带回</a>%部门%。"+
						"</td>";
				temp=temp.replace("%部门%", add[i][0].trim());
				String callbacks=add[i][1].replace("\\s+", "");
				String[] strings=spiltString(callbacks);
				temp=temp.replace("%department%", strings[0].trim());
				temp=temp.replace("%depId%", strings[1].trim());
				temp=temp.replace("%depName%", strings[2].trim());
				sb.append(temp);
			}else if ((add[i][2].trim()).equals("select")){
				StringBuffer sbSelect=new StringBuffer();
				String temp="<td class=\"ctext\" style=\"text-align:center;border-top:1px #d0d0d0 solid;\">%性别%</td>"
						+ "<td class=\"ctext\" style=\"border-top:1px #d0d0d0 solid;\">"
						+ "<select name=\"%sex%\" class=\"combox\">";
				sbSelect.append(temp);
				String chineseName=add[i][0].replace("\\s+", "");
				String[] chineseNames=spiltString(chineseName.trim());
				String name=deleteSpaces(add[i][1]);
				String[] names=spiltString(name.trim());
				for(int j=1;j<chineseNames.length;j++){
					sbSelect.append("<option value=\""+names[j].trim()+"\" <c:if test=\"${bean."+names[0].trim()+"=='"+names[j].trim()+"'}\">selected=\"selected\"</c:if>>"+chineseNames[j]+"</option>");
				}
				sbSelect.append("</select></td>");
				temp=sbSelect.toString();
				temp=temp.replace("%性别%", chineseNames[0].trim());
				temp=temp.replace("%sex%", names[0].trim());
				sb.append(temp);
			}else if ((add[i][2].trim()).equals("radio")){
				String temp="<td class=\"ctext\" style=\"text-align:center;border-top:1px #d0d0d0 solid;\">%文本名%</td><td class=\"ctext\" style=\"border-top:1px #d0d0d0 solid;\"><input type=\"text\"  value=\"${bean."+add[i][1].trim()+" }\" name=\"%textName%\"  style=\"width:150px;\" class=\"required\"/></td>";
				temp=temp.replace("%文本名%", add[i][0].trim());
				temp=temp.replace("%textName%", add[i][1].trim());
				sb.append(temp);
			}else if ((add[i][2].trim()).equals("checkbox")){
				String temp="<td class=\"ctext\" style=\"text-align:center;border-top:1px #d0d0d0 solid;\">%文本名%</td><td class=\"ctext\" style=\"border-top:1px #d0d0d0 solid;\"><input type=\"text\" value=\"${bean."+add[i][1].trim()+" }\" name=\"%textName%\"  style=\"width:150px;\" class=\"required\"/></td>";
				temp=temp.replace("%文本名%", add[i][0].trim());
				temp=temp.replace("%textName%", add[i][1].trim());
				sb.append(temp);
			}else if ((add[i][2].trim()).equals("password")){
				String temp="<td class=\"ctext\" style=\"text-align:center;border-top:1px #d0d0d0 solid;\">%文本名%</td><td class=\"ctext\" style=\"border-top:1px #d0d0d0 solid;\"><input type=\"text\"  value=\"${bean."+add[i][1].trim()+" }\" name=\"%textName%\"  style=\"width:150px;\" class=\"required\"/></td>";
				temp=temp.replace("%文本名%", add[i][0].trim());
				temp=temp.replace("%textName%", add[i][1].trim());
				sb.append(temp);
			}
			if (i==(add.length-1)) {
				sb.append("</tr>");
			}
		}
		//System.out.println(sb.toString());
		return sb.toString();	
	}
		//生成列表的信息
		public static String createListContent() {
		StringBuffer sb=new StringBuffer();
		sb.append("<thead>\n\t<tr>");
		for(int i=0;i<list.length;i++){
			if((list[i][2].trim()).equals("radio")||(list[i][2].trim()).equals("select")){
				String chineseName=list[i][0].replaceAll("\\s+", "");
				String[] chineseNames=spiltString(chineseName);
				sb.append("<th width=\"10%\">"+chineseNames[0].trim()+"</th>");
			}else {
				System.out.println(list[i][0]);
				sb.append("<th width=\"10%\">"+list[i][0].trim()+"</th>");
			}		
		}
		sb.append(	"</tr></thead><tbody>"
				+ "<c:forEach items=\"${list}\" var=\"bean\" varStatus=\"status\">"
				+ "<tr target=\"list_item_id\" rel=\"${bean.id}\">");
		for(int i=0;i<list.length;i++){
			if((list[i][2].trim()).equals("radio")||(list[i][2].trim()).equals("select")){
				StringBuffer sbSelect=new StringBuffer();
				String chineseName=list[i][0].replace("\\+", "");
				String[] chineseNames=spiltString(chineseName);
				String name=deleteSpaces(list[i][1]);
				String[] names=spiltString(name);
				sb.append("<td>");
				for(int j=1;j<chineseNames.length;j++){
					sbSelect.append("<c:if test=\"${bean."+names[0].trim()+"=="+names[j].trim()+"}\">"+chineseNames[j].trim()+"</c:if> ");
				}
				sb.append(sbSelect);
				sb.append("</td>");
			}else {
				sb.append("<td>${bean."+list[i][1].trim()+" }</td>");
			}
		}
		sb.append("<td><a href=\"%Master%/update%Master%.do?id=${bean.id}\" target=\"navTab\" rel=\"%Master%/update%Master%\" title=\"修改\"><span>修改</span></a>"
				+ "<a href=\"%Master%/delete%Master%.do?id=${bean.id}\" target=\"ajaxTodo\" rel=\"%Master%/delete%Master%\" title=\"删除\"><span>删除</span></a>"
				+ "</td></tr></c:forEach></tbody>");
		return sb.toString();
	}
	private static String deleteSpaces(String input) {
		return input.replaceAll("\\s+", "");
	}
	private static String[] spiltString(String string) {
		return string.split("[,:.，；：。]");
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
		File fileDirs=new File("template/jsp/"+beanName+"/");
		if(!fileDirs.exists()){
			fileDirs.mkdirs();
		}
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
