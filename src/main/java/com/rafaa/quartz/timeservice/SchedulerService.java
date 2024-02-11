package com.rafaa.quartz.timeservice;

import com.rafaa.quartz.info.TimerInfo;
import com.rafaa.quartz.util.TimerUtils;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.quartz.*;
import org.quartz.impl.matchers.GroupMatcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

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
   public List<TimerInfo> getAllRunningTimers () {
       try {
          return scheduler.getJobKeys(GroupMatcher.anyGroup())
                   .stream()
                   .map(jobKey -> {
                       try {
                           final JobDetail jobDetail = scheduler.getJobDetail(jobKey);
                           return (TimerInfo) jobDetail.getJobDataMap().get(jobKey.getName());
                       } catch (SchedulerException e) {
                          Log.error(e.getMessage(), e);
                          return null;
                       }
                   })
                   .filter(Objects::nonNull)
                   .collect(Collectors.toList());

       } catch (SchedulerException e) {
           Log.error(e.getMessage(), e);
           return Collections.emptyList();
       }
   }

   public TimerInfo getRunningTimer(String timerId) {
       try {
           final JobDetail jobDetail = scheduler.getJobDetail(new JobKey(timerId));
           if(jobDetail == null) {
               return null;
           }
           return (TimerInfo) jobDetail.getJobDataMap().get(timerId);
       } catch (SchedulerException e) {
          Log.error(e.getMessage(), e);
          return null;
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
