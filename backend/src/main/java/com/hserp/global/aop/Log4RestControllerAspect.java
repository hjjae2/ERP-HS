package com.hserp.global.aop;

import com.hserp.global.dto.ApiLogDto;
import com.hserp.global.service.ApiLogService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Log4j2
@Aspect
@Component
public class Log4RestControllerAspect {

    private final ApiLogService apiLogService;

    @Pointcut("@within(com.hserp.global.annotation.Log4RestController)")
    public void Log4RestController() {}

    @Around("Log4RestController()")
    public Object log4RequestAndResponse(ProceedingJoinPoint joinPoint) throws Throwable {
        HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
        Object response = null;

        String requestMethod = request.getMethod();
        String requestURI = request.getRequestURI();
        String requestDeclaringType = joinPoint.getSignature().getDeclaringTypeName();
        String requestPayload = Arrays.stream(joinPoint.getArgs()).map(arg -> String.format("%s", arg)).collect(Collectors.joining("|"));
        String responsePayload = null;
        String processingTimeUnit = "ms";

        long startTime = System.currentTimeMillis();

        try {
            response = joinPoint.proceed();
            responsePayload = (response != null)
                    ? response.toString()
                    : null;

        } catch (Exception exception) {
            responsePayload = exception.getMessage();
            throw exception;
        } finally {
            apiLogService.create(ApiLogDto.builder()
                    .uri(requestURI)
                    .method(requestMethod)
                    .handler(requestDeclaringType)
                    .request(requestPayload)
                    .response(responsePayload)
                    .processingTime((System.currentTimeMillis() - startTime) + processingTimeUnit)
                    .build());
        }

        return response;
    }
}
