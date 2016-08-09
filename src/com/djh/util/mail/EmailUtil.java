package com.djh.util.mail;

import java.io.File;

import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.MultiPartEmail;
import org.apache.commons.mail.SimpleEmail;

public class EmailUtil {

	public static String send(String from,String password,String to,String subject,String msg,File attachment) throws EmailException {
		String host = "smtp."+from.substring(from.indexOf('@')+1); 
		MultiPartEmail email = new MultiPartEmail();
			email.setHostName(host);
			email.setSmtpPort(25);
			email.setAuthentication(from,password);
			email.setSSLOnConnect(false);
			email.setFrom(from);
			email.setSubject(subject);
			email.setMsg(msg);
			email.addTo(to);
			email.attach(attachment);
		String rs = email.send();
		return rs;
	}
	public static String send(String from,String password,String to,String subject,String msg) throws EmailException {
		String host = "smtp."+from.substring(from.indexOf('@')+1); 
		Email email = new SimpleEmail();
			email.setHostName(host);
			email.setSmtpPort(25);
			email.setAuthentication(from,password);
			email.setSSLOnConnect(true);
			email.setFrom(from);
			email.setSubject(subject);
			email.setMsg(msg);
			email.addTo(to);
		String rs = email.send();
		return rs;
	}
	public static void main(String[] args) throws EmailException {
		System.out.println(send("djh122@163.com", "1", "1056109001@qq.com", "这是一张图", "rt", new File("F:/cloud.jpg")));
	
		System.out.println(send("djh122@163.com", "1", "1056109001@qq.com", "主题不对吗", "rt"));

	}
}
