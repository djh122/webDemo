//package com.djh.test.langu;
//
//import java.math.BigDecimal;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Stack;
//
//import javax.management.RuntimeErrorException;
//
//import com.djh.test.langu.enums.Arith;
//import com.djh.test.s.Bean;
//
//public class Parser {
//
//	static List<Bean> list = new ArrayList<Parser.Bean>();
//	public static void parser(int start,String input) {
//		input = input.trim();
//		//起始位置
//		Bean bean = new Bean(start, start, Arith.start, "");
//		list.add(bean);
//		do{
//			start = bean.end;
//			start = skip(start, input);
//			char c = input.charAt(start);
//			if(c>='1'&&c<='9'){
//				bean = number(start, input);
//			}else if ((c<='Z'&&c>='A')||c<='z'&&c>='a') {
//				bean = literal(start, input);
//			}else {
//				bean = siglon(start,input);
//			}
//			list.add(bean);
//		}while(bean.end != input.length());
//	}
//	public static Bean quota(int start,String input) {
//		for(int i=start;;i++){
//			if (input.charAt(i)=='#') {
//				i++;
//			}else if(input.charAt(i)=='"'){
//				return new Bean(start, i, Arith.quota,input.substring(start,i+1));
//			}
//		}
//	}
//	public static boolean isDigest(char c) {
//		if(c<='9'&&c>='0'){
//			return true;
//		}else {
//			return false;
//		}
//	}
//	public static boolean isWord(char c) {
//		if(c<='9'&&c>='0'){
//			return true;
//		}else if(c<='Z'&&c>='A'){
//			return false;
//		}else if(c<='z'&&c>='a'){
//			return false;
//		}else {
//			return false;
//		}
//	}
//	public static Bean number(int start,String input) {
//		
//		for(int i=start;;){
//			if (isDigest(input.charAt(i))) {
//				i++;
//			}else{
//				return new Bean(start, i, Arith.number,input.substring(start,i));
//			}
//		}
//	}
//	public static Bean siglon(int s,String input) {
//		
//		char c = input.charAt(s);
//		System.out.println("--"+c+"----");
//		switch (c) {
//		case '(':
//			return new Bean(s, s+1, Arith.open, "");
//		case ')':
//			return new Bean(s, s+1, Arith.close, "");
//		case '+':
//			return new Bean(s, s+1, Arith.plus, "");
//		case '-':
//			return new Bean(s, s+1, Arith.sub, "");
//		case '*':
//			return new Bean(s, s+1, Arith.mul, "");
//		case '/':
//			return new Bean(s, s+1, Arith.div, "");
//		case '#':
//			return new Bean(s, s+1, Arith.escape, "");
//		case '"':
//			return new Bean(s, s+1, Arith.quota, "");
//		default:
//			throw new RuntimeException("神秘字符【"+c+"】");
//		}
//		
//	}
//	
//	public static Bean literal(int start,String input) {
//		for(int i=start;;){
//			if (input.charAt(i)=='#') {
//				i++;
//			}else if(input.charAt(i)=='"'){
//				return new Bean(start, i, Arith.string,input.substring(start,i+1));
//			}else {
//				i++;
//			}
//		}
//	}
//	//过滤空格
//	public static int skip(int start,String input) {
//		for(int i = start;;){
//			char c = input.charAt(i);
//			if(c=='\n'||c=='\t'||c=='\r'||c==' '){
//				i++;
//			}else {
//				return i;
//			}
//		}
//	}
//	static class Bean{
//		
//		int start;
//		int end ;
//		Arith type;
//		String value;
//		Bean(int start,int end,Arith type,String value){
//			this.start = start;
//			this.end = end;
//			this.type = type;
//			this.value = value;
//		}
//		@Override
//		public String toString() {
//			return "【start="+start+",end="+end+",type="+type.name()+",value="+value+"】";
//		}
//	}
//	static Stack<Integer> region = new Stack<Integer>();
//	static Stack<Double> data = new Stack<Double>();
//	public static void cal(List<Bean> beans) {
//		
//		if(beans.get(0).type.equals(Arith.open)){
//			region.push(1);
//		}else {
//			throw new RuntimeException("必须以小括号开头");
//		}
//		
//	}
//	public static int getChild(List<Bean> list,int index,int deep) {
//		if(deep==region.size()){
//			if(deep==0){
//				System.out.println();
//			}
//				
//			return index;
//		}
//		Bean v1 = list.get(index);
//		if(v1.type.equals(Arith.number)){
//			data.push(new Double(v1.value));
//			return index;
//		}else if(v1.type.equals(Arith.open)){
//			region.push(1);
//			Bean op = list.get(index+1);
//			if(op.type.equals(Arith.plus)){
//				getChild(list, index, deep);
//			}
//		}else if(v1.type.equals(Arith.close)){
//			region.pop();
//		}else {
//			throw new RuntimeException("语法错误");
//		}
//	}
//	public static double getRight(List<Bean> list,int index) {
//		Bean v1 = list.get(index);
//		if(v1.type.equals(Arith.number)){
//			return new Double(v1.value);
//		}else if(v1.type.equals(Arith.open)){
//			region.add(1);
//			Bean op = list.get(index+1);
//			if(op.type.equals(Arith.plus)){
//				
//			}
//		}else {
//			throw new RuntimeException("语法错误");
//		}
//	}
//	public static void main(String[] args) {
//		parser(0, "(+(/ 2 1) 3)");
//		System.out.println(list.toString());
//	}
//
//}
