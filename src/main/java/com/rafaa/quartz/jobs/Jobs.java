package com.rafaa.quartz.jobs;

import com.rafaa.quartz.info.TimerInfo;
import org.quartz.*;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;

@Component
public class Jobs implements Job {

    private static final Logger Log = LoggerFactory.getLogger(Jobs.class);

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
       JobDataMap jobDataMap = context.getJobDetail().getJobDataMap();
       TimerInfo info = (TimerInfo) jobDataMap.get(Jobs.class.getSimpleName());
       Log.info(info.getCallbackData());
    }

}
