package com.rafaa.quartz.playground;

import com.rafaa.quartz.info.TimerInfo;
import com.rafaa.quartz.jobs.Jobs;
import com.rafaa.quartz.timeservice.SchedulerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
      info.setRemainingFireCount(info.getTotalFireCount());
      info.setRepeatIntervalMs(2000);
      info.setInitialOffsetMs(1000);
      info.setCallbackData("My callback data");
      scheduler.schedule(Jobs.class, info);
   }

   public List<TimerInfo> getAllRunningTimers() {
      return scheduler.getAllRunningTimers();
   }

   public TimerInfo getRunningTimer(String timerId) {
     return scheduler.getRunningTimer(timerId);
   }

   public Boolean deleteTimer(final String timerId) {
      return scheduler.deleteTimer(timerId);
   }
}
