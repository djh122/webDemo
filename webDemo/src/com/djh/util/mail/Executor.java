package com.djh.util.mail;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

import org.quartz.CronTrigger;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.impl.StdSchedulerFactory;

public class Executor {

	public static void executor() throws SchedulerException, ParseException {
		Scheduler scheduler = new  StdSchedulerFactory().getScheduler();
		JobDetail jobDetail = new JobDetail();
		jobDetail.setJobClass(MyJob.class);
		jobDetail.setName("myjob");
		
//		SimpleTrigger trigger = new SimpleTrigger();
//		trigger.setStartTime(new Date());
//		trigger.setRepeatInterval(2000);
//		trigger.setRepeatCount(100);
//		trigger.setName("mytrigger");
		
		CronTrigger trigger = new CronTrigger("cron", "group");
		//秒 分 时 天 月 周 年
		trigger.setCronExpression("0/5 * * ? * * ");
		
		trigger.setEndTime(getDate(2));
		
		//jobDetail.set
		scheduler.scheduleJob(jobDetail,trigger);
		//scheduler.a
		scheduler.start();
	}
	
	public static Date getDate(int minute) {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MINUTE, minute);
		return calendar.getTime();
	}
	public static void main(String[] args) throws SchedulerException, ParseException {
		executor();
	}

}
