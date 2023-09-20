package com.zlx.javase.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;

import java.util.Arrays;

@Aspect
public class LogAspect {

    @Pointcut("execution(public int com.zlx.javase.aop.MathHandler.*(..))")
    public void pointCut(){};

    @Before("pointCut()")
    public void logStart(JoinPoint joinPoint) {
        System.out.println(joinPoint.getSignature().getName()+"开始运行，参数列表是："+ Arrays.toString(joinPoint.getArgs()));
    }

    // 无论执行结果如何 都会返回 且最后返回
    @After("pointCut()")
    public void logEnd(JoinPoint joinPoint) {
        System.out.println(joinPoint.getSignature().getName()+"执行结束");
    }

    @Around(value = "pointCut()")
    public Object logAround(ProceedingJoinPoint pjp) throws Throwable {
        System.out.println(pjp.getSignature().getName()+"正在执行中1...");
        Object proceed = pjp.proceed();
        System.out.println(pjp.getSignature().getName()+"正在执行中2...");
        return proceed;
    }

    @AfterThrowing(value = "pointCut()",throwing = "exception")
    public void logError(JoinPoint joinPoint,Exception exception){
        System.out.println(joinPoint.getSignature().getName()+"报错异常："+exception);
    }

    @AfterReturning(value = "pointCut()",returning = "result")
    public void logReturn(JoinPoint joinPoint,Object result){
        System.out.println(joinPoint.getSignature().getName()+"运算结束，结果是："+result.toString());
    }
}
