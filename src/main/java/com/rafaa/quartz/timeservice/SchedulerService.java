package com.rafaa.quartz.timeservice;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
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
