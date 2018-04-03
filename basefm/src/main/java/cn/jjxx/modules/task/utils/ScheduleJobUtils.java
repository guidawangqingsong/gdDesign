package cn.jjxx.modules.task.utils;

import cn.jjxx.modules.task.entity.ScheduleJob;

public class ScheduleJobUtils {
     public static cn.jjxx.core.quartz.data.ScheduleJob entityToData(ScheduleJob scheduleJobEntity){
    	 cn.jjxx.core.quartz.data.ScheduleJob scheduleJob=new cn.jjxx.core.quartz.data.ScheduleJob();
    	 scheduleJob.setBeanClass(scheduleJobEntity.getBeanClass());
    	 scheduleJob.setCronExpression(scheduleJobEntity.getCronExpression());
    	 scheduleJob.setDescription(scheduleJobEntity.getDescription());
    	 scheduleJob.setIsConcurrent(scheduleJobEntity.getIsConcurrent());
    	 scheduleJob.setJobName(scheduleJobEntity.getJobName());
    	 scheduleJob.setJobGroup(scheduleJobEntity.getJobGroup());
    	 scheduleJob.setJobStatus(scheduleJobEntity.getJobStatus());
    	 scheduleJob.setMethodName(scheduleJobEntity.getMethodName());
    	 scheduleJob.setSpringBean(scheduleJobEntity.getSpringBean());
    	 return scheduleJob;
     }
}
