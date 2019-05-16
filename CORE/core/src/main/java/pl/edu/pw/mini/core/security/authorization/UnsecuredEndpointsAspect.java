package pl.edu.pw.mini.core.security.authorization;

import lombok.extern.log4j.Log4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Objects;

@Log4j
@Aspect
@Component
@EnableAspectJAutoProxy
public class UnsecuredEndpointsAspect {

    @Around("@within(org.springframework.web.bind.annotation.RestController)")
    public Object catchEndpoint(final ProceedingJoinPoint proceedingJoinPoint) throws Throwable {

        MethodSignature signature = (MethodSignature) proceedingJoinPoint.getSignature();
        Method method = signature.getMethod();

        Annotation securedAnnotation = AnnotationUtils.findAnnotation(method, Secured.class);
        if(Objects.isNull(securedAnnotation)) {
            log.warn( "Not secured Method!" );
        }
        return proceedingJoinPoint.proceed();
    }
}
