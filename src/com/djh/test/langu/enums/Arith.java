package com.djh.test.langu.enums;

public enum Arith {
	start(""),
	plus("+"),sub("-"),mul("*"),div("/"),
	open("("),close(")"),escape("#"),
	enter("#\\r"),newLine("#\\n"),table("#\\t"),space("#\\ "),quota("#\""),t("#\""),f("#\""),
	string(""),number(""),leteral("");
	String id;
	Arith(String id){
		this.id = id;
	}
	String getName(){
		return this.name();
	}
	@Override
	public String toString() {
		//String rs = id;
//		if(id.equals("")){
//			rs = 
//		}
		return id;
	}
}
