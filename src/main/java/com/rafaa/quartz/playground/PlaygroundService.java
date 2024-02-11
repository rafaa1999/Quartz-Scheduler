package com.rafaa.quartz.playground;

import com.rafaa.quartz.info.TimerInfo;
import com.rafaa.quartz.jobs.Jobs;
import com.rafaa.quartz.timeservice.SchedulerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlaygroundService {
   final private SchedulerService scheduler;

   @Autowired
   public PlaygroundService(final SchedulerService scheduler) {
       this.scheduler = scheduler;
   }

   public void runJobs() {
      final TimerInfo info = new TimerInfo();
      info.setTotalFireCount(5);
      info.setRepeatIntervalMs(2000);
      info.setInitialOffsetMs(1000);
      info.setCallbackData("My callback data");
      scheduler.schedule(Jobs.class, null);
   }

}
