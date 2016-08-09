package com.djh.test.mail;

import java.util.Set;

import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

public class Test {

	public static void main(String[] args) throws EmailException {
		Email email = new SimpleEmail();
			email.setHostName("smtp.163.com");
			email.setSmtpPort(25);
			email.setAuthentication("djh122@163.com", "wocaibuhuifangmimane");
			email.setSSLOnConnect(false);
			email.setFrom("djh122@163.com");
			email.setSubject("什么情况");
			email.setMsg("12311");
			email.addTo("1056109001@qq.com");
		String rs = email.send();
		System.out.println(rs);
	}
}
