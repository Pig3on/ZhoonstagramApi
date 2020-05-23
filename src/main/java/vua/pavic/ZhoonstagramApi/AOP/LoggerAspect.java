package vua.pavic.ZhoonstagramApi.AOP;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Timer;

@Aspect
@Configuration
public class LoggerAspect {

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private long startedAt;
    @Before("execution(* *(..)) &&\n" +
            "(\n" +
            "    within(vua.pavic.ZhoonstagramApi.controllers..*) ||\n" +
            "    within(vua.pavic.ZhoonstagramApi.db..*) ||\n" +
            "    within(vua.pavic.ZhoonstagramApi.services..*)\n" +
            ")")
    public void before(JoinPoint joinPoint) {
        logger.info("Executing Executing: " + joinPoint.getSignature().getName());
        startedAt = System.nanoTime();
    }

    @After("execution(* *(..)) &&\n" +
            "(\n" +
            "    within(vua.pavic.ZhoonstagramApi.controllers..*) ||\n" +
            "    within(vua.pavic.ZhoonstagramApi.db..*) ||\n" +
            "    within(vua.pavic.ZhoonstagramApi.services..*)\n" +
            ")")
    public void after(JoinPoint joinPoint) {
        logger.info("Done Executing: " + joinPoint.getSignature().getName());

        long time = System.nanoTime();
        logger.info(("executed in " + (time - this.startedAt) + " nanoseconds"));
    }

}
