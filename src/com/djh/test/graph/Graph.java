package com.djh.test.graph;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;


public class Graph {

	//所有顶点
	List<Integer> list = new ArrayList<Integer>();
	//邻接矩阵（无向，无自环图）
	Map<Integer,List<Integer>> neiMatrix = new HashMap<Integer,List<Integer>>();

	//添加边
	public void addEdge(int v1,int v2) {
		if(neiMatrix.get(v1)==null){
			list.add(v1);
			neiMatrix.put(v1, new ArrayList<Integer>());
		}
		if(neiMatrix.get(v2)==null){
			neiMatrix.put(v2, new ArrayList<Integer>());
			list.add(v2);
		}
		neiMatrix.get(v1).add(v2);
		neiMatrix.get(v2).add(v1);
	}
	//删除点
	public void delVertex(int v1) {
		List<Integer> neighbor = neiMatrix.remove(v1);
		delElement(v1, list);
		for(Integer v:neighbor){
			delElement(v1, neiMatrix.get(v));
		}
	}
	//删除边
	public void delEdge(int v1,int v2) {
		List<Integer> neighbor1 = neiMatrix.get(v1);
		List<Integer> neighbor2 = neiMatrix.get(v2);
		delElement(v2, neighbor1);
		delElement(v1, neighbor2);
	}
	/*删list中的整数*/
	private static void delElement(Integer v1,List<Integer> list) {
		Iterator<Integer> it = list.iterator();
		while (it.hasNext()) {
			Integer integer = (Integer) it.next();
			if(integer.equals(v1)){
				it.remove();
				break;
			}
		}
	}
//	public boolean isExsit(int v1) {
//		return list.contains(v1);
//	}
	//是否连通图
	public boolean isConnect() {
		return dfs(new HashMap<Integer, Integer>(), list.get(0))==getSize();
	}
	public int travel(String type,int start) {
		if("bfs".equals(type)){
			return bfs(new HashMap<Integer, Integer>(), start, new LinkedBlockingQueue<Integer>());
		}
		return dfs(new HashMap<Integer, Integer>(), start);
	}
	/*深度优先搜索*/
	public int dfs(Map<Integer, Integer> marked,Integer start) {
		List<Integer> list = neiMatrix.get(start);
		marked.put(start, 1);
		for(Integer v:list){
			if(marked.get(v)==null){
				dfs(marked, v);
			}
		}
		return marked.size();
	}
	/*广度优先搜索*/
	public int bfs(Map<Integer, Integer> marked,Integer start,Queue<Integer> queue) {
		Integer tv;
		queue.addAll(neiMatrix.get(start));
		marked.put(start, 1);
		while(!queue.isEmpty()){
			tv = queue.poll();
			if(marked.get(tv)!=null){//当前元素已访问则直接丢弃后继续
				continue;
			}
			marked.put(tv, 1);
			queue.addAll(neiMatrix.get(tv));
		}
		return marked.size();
	}
	public int getSize() {
		return list.size();
	}

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		for(Integer i:neiMatrix.keySet()){
			sb.append(i).append(" : ").append(neiMatrix.get(i).toString()).append("\n");
		}
		return sb.toString();
	}
	public static void main(String[] args) throws IOException {
		Graph graph = new Graph();
		
		/**
		    1 2
			0 3
			2 6
			3 5
			3 4
			3 6
			1 7
			3 7
			5 8
		 */
		BufferedReader br = new BufferedReader(new FileReader(new File(System.getProperty("user.dir")+"/src/test/graph/test.txt")));
		String temp = null;
		while((temp=br.readLine())!=null){
			String[] s = temp.split(" ");
			int v1 = Integer.valueOf(s[0]);
			int v2 = Integer.valueOf(s[1]);
			graph.addEdge(v1, v2);
		}
		br.close();

		System.out.println(graph);
		graph.delVertex(5);
		graph.addEdge(3, 8);
		System.out.println(graph.travel("bfs",graph.list.get(1)));
		System.out.println(graph.list.toString());
		System.out.println(graph.isConnect());
	}
}
