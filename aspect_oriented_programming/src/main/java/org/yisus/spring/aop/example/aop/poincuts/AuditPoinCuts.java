package org.yisus.spring.aop.example.aop.poincuts;

import org.aspectj.lang.annotation.Pointcut;

public class AuditPoinCuts {

    @Pointcut("@annotation(org.yisus.spring.aop.example.anotations.Audit)")
    public void audit(){}
}
