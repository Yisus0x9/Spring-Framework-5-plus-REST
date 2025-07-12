package org.yisus.spring.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

import java.lang.reflect.Modifier;

@Component
@Aspect
public class MyAspect {
    private static final Logger log = LoggerFactory.getLogger(MyAspect.class);

    @Before("execution(* org.yisus.spring.aop.TargetObject.*(..))")
    public void before(JoinPoint joinPoint) {
        log.warn("Method name : {}",joinPoint.getSignature().getName());
        log.warn("Method modifier : {}", Modifier.toString(joinPoint.getSignature().getModifiers()));
        log.warn("Method declaringType : {}",joinPoint.getSignature().getDeclaringType());
        log.warn("Method declaringTypeName : {}",joinPoint.getSignature().getDeclaringTypeName());
        log.warn("Method args : {}",joinPoint.getArgs());
        log.warn("MyAspect.before");
    }
}
