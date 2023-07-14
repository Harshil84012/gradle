package com.sungjun.api.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.sungjun.api.util.BaseMethods;
import com.sungjun.responseDto.AuditLog;
import com.sungjun.responseDto.ResponseLogDto;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;


@Aspect
@Component
public class MyAspect {

    private static final Logger log = LoggerFactory.getLogger(MyAspect.class);

    @AfterReturning(value = "execution(* com.sungjun.api.controller.*.*(..))", returning = "result")
    public void afterReturningAdvice(JoinPoint joinPoint, Object result) throws Throwable {
        ObjectMapper mapper = new ObjectMapper();

        String methodName = joinPoint.getSignature().getName();
        String className = joinPoint.getTarget().getClass().toString();
        Object[] args = joinPoint.getArgs();

        String traceId = BaseMethods.getTraceId();
        String spanId = BaseMethods.getSpanId();
        Object[] array = joinPoint.getArgs();
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String header = request.getHeader("Authorization");

//        log.info(":::::::::::::header :::::::::::{}", header);
//        log.info(":::::::::className :::::::{}", className);
//        log.info(":::::::::apiName :::::::{}", methodName);
//        log.info(":::::::::request body:::::::{}", mapper.writeValueAsString(array));
//        log.info("::::::::::traceId :::::::::{}", traceId);
//        log.info(":::::::::::spanId:::::::{}", spanId);
//        log.info("::::::::::::args::::::::{}", args);
//        log.info("::::::::result :::::::::{}", result.toString());
        ResponseLogDto responseLogDto = mapper.convertValue(result, ResponseLogDto.class);
        String responseData = new Gson().toJson(responseLogDto);

        AuditLog auditLog = new AuditLog();
        auditLog.setResponse(responseData);
        auditLog.setRequest(mapper.writeValueAsString(array));
        auditLog.setApiName(methodName);
        auditLog.setSpanId(spanId);
        auditLog.setTraceId(traceId);
        auditLog.setSessionId(header);

//        log.info(":::::::response data:::::::{}", responseData);
        log.info(":::::::::Audit Log::::::::::{}", auditLog.toString());

    }

    @Before(value = "execution(* com.sungjun.api.controller.*.*(..))")
    public void beforeAdvice(JoinPoint joinPoint) {
        log.info(" joinPoint.getSignature::::::: {}", joinPoint.getSignature());
    }
}


