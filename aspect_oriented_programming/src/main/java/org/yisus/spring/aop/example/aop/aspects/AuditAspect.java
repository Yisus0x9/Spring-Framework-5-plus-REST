package org.yisus.spring.aop.example.aop.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.yisus.spring.aop.example.models.AuditLog;
import org.yisus.spring.aop.example.services.AuditService;

import java.util.Date;

@Component
@Aspect
public class AuditAspect {
    @Autowired
    AuditService auditService;

    @Around("org.yisus.spring.aop.example.aop.poincuts.AuditPoinCuts.audit())")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        AuditLog auditLog= new AuditLog();
        auditLog.setAction(joinPoint.getSignature().getName());
        auditLog.setParams(joinPoint.getArgs());
        auditLog.setTimestamp(new Date());
        Object object=joinPoint.proceed();
        if(object!=null){
            auditLog.setResult(object);
        }
        auditService.audit(auditLog);
        return object;
    }
}
