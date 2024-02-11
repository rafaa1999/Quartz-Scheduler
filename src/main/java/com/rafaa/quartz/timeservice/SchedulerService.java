package com.rafaa.quartz.timeservice;

import com.rafaa.quartz.info.TimerInfo;
import com.rafaa.quartz.util.TimerUtils;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SchedulerService {

   private final Scheduler scheduler;
   private static final Logger Log = LoggerFactory.getLogger(Scheduler.class);

   @Autowired
   public SchedulerService(Scheduler scheduler) {
       this.scheduler = scheduler;
   }

   public void schedule (final Class jobClass, final TimerInfo info) {

       final JobDetail jobDetail = TimerUtils.buildJobDetail(jobClass, info);
       final Trigger trigger = TimerUtils.buildTrigger(jobClass, info);

       try {
          scheduler.scheduleJob(jobDetail, trigger);
       }catch (SchedulerException e){
          Log.error(e.getMessage(), e);
       }

    }

   @PostConstruct
   public void init() {
       try {
           scheduler.start();
       }catch (SchedulerException e) {
          Log.error(e.getMessage(), e);
       }
   }

  @PreDestroy
  public void preDestroy() {
       try{
          scheduler.shutdown();
       }catch (SchedulerException e){
           Log.error(e.getMessage(), e);
       }
  }

}
