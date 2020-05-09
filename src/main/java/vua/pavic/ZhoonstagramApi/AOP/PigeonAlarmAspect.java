package vua.pavic.ZhoonstagramApi.AOP;

import com.google.common.collect.Multimap;
import org.aopalliance.intercept.Joinpoint;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.MultipartFile;
import vua.pavic.ZhoonstagramApi.model.api.FileUploadResponse;
import org.tensorflow.op.math.Mul;

import javax.servlet.ServletContext;
import java.io.File;
import java.util.Date;

@Aspect
@Configuration
public class PigeonAlarmAspect {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    ServletContext context;

    @AfterReturning(pointcut = "target(vua.pavic.ZhoonstagramApi.services.PigeonDetectionServiceImpl) && execution(double isPigeon(*))", returning="probability")
    public void around(Object probability) {
        double certainty = (double)probability;

        if(certainty < 0.95) {
            logger.error("SOMEONE UPLOADED A NON PIGEON IMAGE");
            //TODO send email or something
        }
    }
}