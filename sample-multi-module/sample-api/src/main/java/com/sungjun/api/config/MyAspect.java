package com.sungjun.api.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.sungjun.responseDto.ResponseLogDto;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


@Aspect
@Component
public class MyAspect {

    private static final Logger log = LoggerFactory.getLogger(MyAspect.class);

    @AfterReturning(value = "execution(* com.sungjun.api.controller.*.*(..))", returning = "result")
    public void afterReturningAdvice(JoinPoint joinPoint, Object result) throws Throwable {
        ObjectMapper mapper = new ObjectMapper();

        String methodName = joinPoint.getSignature().getName();
        String className = joinPoint.getTarget().getClass().toString();

        Object[] array = joinPoint.getArgs();
        log.info(":::::::::className :::::::{}", className);
        log.info(":::::::::methodName :::::::{}", methodName);
        log.info(":::::::::request body:::::::{}", mapper.writeValueAsString(array));
//        log.info(":::::::::result :::::::{}", mapper.writeValueAsString(result));

        ResponseLogDto responseLogDto = mapper.convertValue(result, ResponseLogDto.class);

        String responseData = new Gson().toJson(responseLogDto);

        log.info(":::::::response data ::::::: {}", responseData);
    }

    @Before(value = "execution(* com.sungjun.api.controller.*.*(..))")
    public void beforeAdvice(JoinPoint joinPoint) {
        log.info(" joinPoint.getSignature::::::: {}", joinPoint.getSignature());
    }
}


