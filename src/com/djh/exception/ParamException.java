package com.djh.exception;


/********************************************
 * 文件名称: DBException <br/>
 * 系统名称: 直销银行V1.0
 * 模块名称: WEB业务平台帐户类
 * 软件版权: 信雅达系统工程股份有限公司
 * 功能说明: 自定义direct-bank异常 DBException <br/>
 * 系统版本: 1.0.0.1
 * 开发人员:  Terrance
 * 开发时间: 2014年9月15日 下午11:54:47 <br/> 
 * 审核人员:
 * 相关文档:
 * 修改记录: 修改日期    修改人员    修改说明
 * V3.0.0.2 20130530-01  XXXX        提示报错 M201305300011
 *********************************************/

public class ParamException extends RuntimeException {

	private static final long serialVersionUID = 6332322293805194934L;

	private String message;
	
	public ParamException (String msg){     
	 super(msg);
	 message=msg;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}     

}     