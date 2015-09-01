package com.djh.zz_generator;

import java.util.regex.Pattern;

public class Test1 {
	public static void choice(int pageNo) {
		int beginIndex=0;
		int endIndex=0;
		switch(pageNo){
	    	case 1:
	    	case 2:
	    	case 3: beginIndex = 1;
		    		endIndex = 3;
		    		break;
            case 4:
            case 5:
            case 6:	beginIndex = 4;
            		endIndex = 6;
            		break;
            default:
            	System.out.println("error");
		}	
		System.out.println(beginIndex+"   "+endIndex);
	}
	public static void main(String[] args) {
		//Map<String, Object> inMap=new HashMap<String,Object>();
		//Map<String, Object>outMap=new OpDetailServiceImpl().getOpDetailList(inMap);
		//System.out.println(outMap.get(Result.CODE));		
		//choice(6);
//		String abc="上海，广州,深圳,杭州。北京;南京；天津";
//		String[] strings=abc.split("[,.;，。、;；]");
		//for(String s:strings){
			//System.out.println(s);
		//}
		//System.out.println("");
		//String string="  ufds fiws fowe     ifewi		o))fds".replaceAll("\\s+", "");
		//System.out.println("--"+string+"--");
		//boolean flag=Pattern.compile("\\s+").matcher("    ").matches();
		//System.out.println(flag);
		System.out.println("fd2s32f43434j".replaceAll("\\d+", "\\${0----"));
	}

}
