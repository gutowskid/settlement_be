package pl.edu.pw.mini.core.logging;

import lombok.extern.log4j.Log4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Log4j
@Aspect
@Component
public class RestControllerLogger {

    @Around("@within(org.springframework.web.bind.annotation.RestController)")
    public Object logAround(final ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        Object result = proceedingJoinPoint.proceed();
        StringBuffer buffer = new StringBuffer();
        buffer.append("Time: ");
        buffer.append(System.currentTimeMillis() - startTime);
        buffer.append(" ms, ");
        buffer.append(proceedingJoinPoint.getSignature().toShortString());
        buffer.append(" with arguments");
        buffer.append(Arrays.toString(proceedingJoinPoint.getArgs()));
        log.info(buffer.toString());
        return result;
    }
}
