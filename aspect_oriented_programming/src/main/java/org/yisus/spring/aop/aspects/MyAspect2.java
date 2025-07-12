package org.yisus.spring.aop.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.reflect.Modifier;

@Component
@Aspect
@Order(0)
public class MyAspect2 {
    private static final Logger log = LoggerFactory.getLogger(MyAspect2.class);

    @Before("org.yisus.spring.aop.poincuts.MyAspectPoinCuts.myAspectPoinCut()")
    public void before(JoinPoint joinPoint) {
        log.warn("  My Aspect2: Method name : {}",joinPoint.getSignature().getName());
        log.warn("  My Aspect2: Method modifier : {}", Modifier.toString(joinPoint.getSignature().getModifiers()));
        log.warn("  My Aspect2: Method declaringType : {}",joinPoint.getSignature().getDeclaringType());
        log.warn("  My Aspect2: Method declaringTypeName : {}",joinPoint.getSignature().getDeclaringTypeName());
        log.warn("  My Aspect2: Method args : {}",joinPoint.getArgs());
        log.warn("  My Aspect2: MyAspect2.before");
    }
}