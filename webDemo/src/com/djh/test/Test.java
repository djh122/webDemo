package com.djh.test;

import java.io.IOException;
//public class Test {
//	public static String url = "http://121.199.2.76:8080/ faaccept/";
//	//oid=C0201009161748314437348&cid=test&pid=0101&pn=13134554942&
//	//pr=100&nb=2&fm=200&ru=http://127.0.0.1/Test/UrlReturnTe
//	//st.jsp&info1=a1&info2=a2&info3=a3&sign=577558e7fd414cd485f791a898848ffc
//
//	
//	public static void query() {
//		OrderRecharge order = new OrderRecharge();
//		order.setMerId("yinliansw");
//		order.setOrderNo("移动话费测试");
//		String url = RechargeConfig.url + RequestType.merQueryOrder(order);
//		System.out.println(url);
//		try {
//			System.out.println(HttpClientUtil.get(url, "utf-8"));
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		
//	}
//	public static void test() throws IOException {
//		OrderRecharge order = new OrderRecharge();
//		order.setMerId("yinliansw");
//		order.setOrderNo("移动话费测试");
//		//order.setLocalOrderNo("");
//		order.setGoodsNo("GG0101");
//		order.setChargeNumber("13429044942");
//		order.setGoodsPrice(1);
//		order.setGoodsNumber(1);
//		order.setChargeAmount(1);
//		order.setBackUrl("http://127.0.0.1/Test/UrlReturnTe//st.jsp");
//		order.setAccoutType("");
//		order.setChargeType("");
//		order.setChargeArea("");
//		order.setChargeServer("");
//		order.setInfo1("");
//		order.setInfo2("");
//		order.setInfo3("");
//		String url = RequestType.orderRequest(order);
//		//RequestType.merQueryOrder(order);
//		url = RechargeConfig.url+url;
//		System.out.println(RechargeConfig.url+url);
//		
//		String result = HttpClientUtil.get(url, "utf-8");
//		System.out.println(result);
//	}
//	public static void main(String[] args) throws IOException {
//		test();
//		query();
//	}
//}
