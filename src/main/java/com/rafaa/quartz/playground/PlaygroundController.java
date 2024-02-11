package com.rafaa.quartz.playground;

import com.rafaa.quartz.info.TimerInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/timer")
public class PlaygroundController {
   private PlaygroundService service;

   @Autowired
   public PlaygroundController(PlaygroundService service) {
       this.service = service;
   }

   @PostMapping("/runJobs")
   public String runJobs() {
       service.runJobs();
       return "run_jobs";
   }
   @GetMapping("/test")
   public String test() {
      return  "test";
   }

   @GetMapping("/timers")
   public List<TimerInfo> getAllRunningTimers() {
       return service.getAllRunningTimers();
   }

   @GetMapping("/timers/{timerId}")
   public TimerInfo getRunningTimer(@PathVariable String timerId) {
       return service.getRunningTimer(timerId);
   }

}
