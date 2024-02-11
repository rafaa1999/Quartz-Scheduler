package com.rafaa.quartz.playground;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/timer")
public class PlaygroundController {
   private PlaygroundService service;

   @Autowired
   public PlaygroundController(PlaygroundService service) {
       this.service = service;
   }

   @PostMapping("/runJobs")
   public void runJobs() {
       service.runJobs();
   }
}
