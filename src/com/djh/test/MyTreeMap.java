package com.djh.test;

public class MyTreeMap {
	
	static class Node{
		Node left;
		Node right;
		boolean color;//true 为red
		Object key;
		Object value;
		Node(Object key,Object value,Node left,Node right,boolean color){
			this.left = left;
			this.right = right;
			this.key = key;
			this.value = value;
			this.color = color;
		}
		public boolean isRed() {
			return color;
		}
	}

}
