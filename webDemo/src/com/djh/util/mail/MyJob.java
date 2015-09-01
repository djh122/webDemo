package com.djh.util.mail;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.mail.MessagingException;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class MyJob implements Job{

	public void job() throws MessagingException, IOException {
		System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
		//MailUtils.createSession("163.com", "djh122", "910722");
		Mail mail = new Mail();
		mail.setSubject("信雅达");
		mail.setFrom("djh122@163.com");
		mail.setContent("测试");
		mail.addToAddress("850094944@qq.com");
		MailUtils.send(MailUtils.createSession("smtp.163.com", "djh1221", "000000"), mail);
	}

	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		try {
			job();
		} catch (MessagingException e) {
			System.out.println("mail error");// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}