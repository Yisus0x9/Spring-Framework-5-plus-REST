package org.yisus.spring.aop.poincuts;

import org.aspectj.lang.annotation.Pointcut;

public class MyAspectPoinCuts {
    @Pointcut("execution(* org.yisus.spring.aop.TargetObject.*(..))")
    public void myAspectPoinCut() {}

    @Pointcut("execution(* org.yisus.spring.aop.TargetObject.foo(..))")
    public void myAspectPoinCutFoo() {}
}
