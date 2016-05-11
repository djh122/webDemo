package com.djh.exception;

/**
 * 结算异常
 * @author djh
 *
 */
public class AppException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	//错误码
	private String recode;
	
	public AppException(String recode,String msg,Throwable throwable){
		super(msg,throwable);
		this.recode = recode;
	}
	public AppException(String recode,String msg){
		super(msg);
		this.recode = recode;
	}
//	public AppException(Recode recode){
//		super(recode.getRemsg());
//		this.recode = recode.getRecode();
//	}
//	public AppException(Recode recode,Throwable throwable){
//		super(recode.getRemsg());
//		this.recode = recode.getRecode();
//	}
	public String getRecode() {
		return this.recode;
	}
	public static void main(String[] args) {
//		TrusteeshipException te = new TrusteeshipException("111", "SS", new Throwable());
//		System.out.println(te.getMessage());
//		System.out.println(te.getRecode());
//		System.out.println(te.getCause());
//		te.printStackTrace();
		try{
			System.out.println("try");
			new NullPointerException();
		}catch(Exception e){
			System.out.println("catch before");
			new RuntimeException();
			System.out.println("catch after");
		}finally{
			System.out.println("finally");
		}
	}
	
	@Override
	public String toString() {
		return super.toString()+",错误码："+getRecode();
	}
}
