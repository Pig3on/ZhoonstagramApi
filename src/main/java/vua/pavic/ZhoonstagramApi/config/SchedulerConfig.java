package vua.pavic.ZhoonstagramApi.config;

import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import vua.pavic.ZhoonstagramApi.jobs.DeleteReportedPostsJob;

@Configuration
public class SchedulerConfig {

    @Bean
    JobDetail deleteReportedPostsJobDetail(){
        return JobBuilder.newJob(DeleteReportedPostsJob.class)
                .withIdentity("deleteReportedPostsJob").storeDurably().build();
    }


    @Bean
    public Trigger announcementJobTrigger() {
        SimpleScheduleBuilder scheduleBuilder = SimpleScheduleBuilder.simpleSchedule()
                .withIntervalInMinutes(5).repeatForever();
        return TriggerBuilder.newTrigger().forJob(deleteReportedPostsJobDetail())
                .withIdentity("deleteReportedPosts").withSchedule(scheduleBuilder).build();
    }
}
