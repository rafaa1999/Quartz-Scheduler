package com.rafaa.quartz.jobs;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;

@Component
public class Jobs implements Job {

    private static final Logger Log = LoggerFactory.getLogger(Jobs.class);

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
       Log.info("Jobs");
    }

}
