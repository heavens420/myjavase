package com.zlx.javase.aop;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Spring5
 * 程序执行正常：
 * 1、环绕通知前
 * 2、@Before通知
 * 3、程序逻辑
 * 4、@AfterReturning通知
 * 5、@After通知
 * 6、环绕通知后
 *
 * 程序执行异常：
 * 1、环绕通知前
 * 2、@Before通知
 * 3、@AfterThrowing异常通知
 * 4、@After通知
 * 异常日志
 *
 *
 * Spring4
 * 程序执行正常：
 * 1、环绕通知前
 * 2、@Before通知
 * 3、程序逻辑
 * 4、环绕通知后
 * 5、@After通知
 * 6、@AfterReturning通知
 *
 * 程序执行异常：
 * 1、环绕通知前
 * 2、@Before通知
 * 3、@After通知
 * 4、@AfterThrowing异常通知
 * 异常日志
 */
public class Test {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext appContext = new AnnotationConfigApplicationContext(AopConfig.class);
        MathHandler mathHandler = appContext.getBean(MathHandler.class);
        mathHandler.add(2, 3);
        appContext.close();
    }
}
