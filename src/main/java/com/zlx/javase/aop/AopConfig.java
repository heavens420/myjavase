package com.zlx.javase.aop;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@EnableAspectJAutoProxy
public class AopConfig {

    @Bean
    public MathHandler mathHandler(){
        return new MathHandler();
    }

    @Bean
    public LogAspect logAspect(){
        return new LogAspect();
    }
}
