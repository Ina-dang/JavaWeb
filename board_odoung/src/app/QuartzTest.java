package app;

import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerFactory;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

import task.TimeTask;

//얘는 웹에서만 하는게아니라 자바 기본으로 함 
/*
 * 일정 시간마다 필요한 동작
 * */
public class QuartzTest {
	public static void main(String[] args) {		
		SchedulerFactory schedulerFactory = new StdSchedulerFactory();
		Scheduler scheduler;
		try {
			scheduler = schedulerFactory.getScheduler();
//빌더패턴											//타임태스크 들어간 작업이
			JobDetail job = JobBuilder.newJob(TimeTask.class)
					.withIdentity("time", Scheduler.DEFAULT_GROUP)
					.build();
			Trigger trigger = TriggerBuilder.newTrigger()
					.withIdentity("timetrigger", Scheduler.DEFAULT_GROUP)
					 												//매 초마다 작동
														//초 분 시 일 월 요일 연도 => *은 매번 ? 은 설정값없음
					.withSchedule(CronScheduleBuilder.cronSchedule("* * * * * ?"))
					
					.build();
			scheduler.scheduleJob(job, trigger);
			scheduler.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
//		for (int i = 10; i > 0; i--) {
//			System.out.println(i);
//			Thread.sleep();
//		}
	}
}
