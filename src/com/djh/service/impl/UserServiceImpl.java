package com.djh.service.impl;

import java.io.IOException;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.djh.mapper.UserMapper;
import com.djh.service.UserService;
import com.djh.util.HttpClientUtil;

@Service
public class UserServiceImpl implements UserService {

	//@Autowired
	//UserMapper userMapper;

	
	public Map<String, Object> process( Map<String, String> params) throws Exception {
//		
		return null;
		//params.remove(key)
//		OrderRecharge order = new OrderRecharge();
//		//验证商户是否有访问权限
//		int flag = merchantMapper.merIdIsValid(StringUtil.handleNull(params.get("merId")));
//		
//		
//		if(flag <1){
//			return RetUtil.retMsg(RetCode.REQ_PARAMS_ERROR, RetDesc.NO_BUSINESS);
//		}
//		
//		order.setMerId(params.get("merId"));
//		order.setOrderNo(StringUtil.handleNull(params.get("orderNo")));
//		String localOrderNo = orderRechargeMapper.getLocalOrderNo(order);
//		if(localOrderNo == null){
//			return RetUtil.retMsg(RetCode.REQ_PARAMS_ERROR, RetDesc.NOT_EMPTY);
//		}
//		order.setMerId(RechargeConfig.interfaceId);//更新商户为本地商户
//		order.setLocalOrderNo(localOrderNo);
//		String url = RechargeConfig.url + RequestType.merQueryOrder(order);
//		
//		log.info( "商户[{}], 查询报文：{}", "", "{orderNo,MerId}"+params.get("merId")+params.get("orderNo") );
//		try {
//			// 请求查询
//			String response = HttpClientUtil.get(url, "utf-8");
//			// 解析返回报文
//			return RespHandler.handle( response );
//		} catch ( IOException e ) {
//			log.error( "通讯异常：{}", e.getMessage(), e );
//			return RetUtil.retMsg( RetCode.RESP_ANALY_ERROR, RetDesc.COMM_ERROR );
//		}
	}
	
	private final static Logger log = LoggerFactory.getLogger( "ticket" );
	
	public static void main(String[] args) throws IOException {
		
		String url = "192.168.18.141:8080/qPay-composite-v1/trans.do?merId=1000000001&orderNo=移动话费测试&businessCode=100102&sign=5fd38a70cb33f2fdf59f703217434098";
		//url = "http://121.199.2.76:8080/faaccept/MerQuery?oid=?§???¨è??è?????è??&type=0&cid=yinliansw&sign=c40e9c709f28e1d258505ecff1c706ce";
		//System.out.println(url.substring(51));
		//URLEncoder.encode(url);
		//System.out.println(url.substring(51));
		String result = HttpClientUtil.get(url, "utf-8");
		log.info(result);
	}

	@Override
	public boolean valid() {
		return false;
	}

}
