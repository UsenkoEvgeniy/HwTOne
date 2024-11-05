package ru.t1.java.demo.aop;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import ru.t1.java.demo.model.DataSourceErrorLog;
import ru.t1.java.demo.repository.DataSourceErrorLogRepository;

import java.io.PrintWriter;
import java.io.StringWriter;

@Aspect
@RequiredArgsConstructor
@Component
@Slf4j
public class LogDataSourceError {

    private final DataSourceErrorLogRepository dataSourceErrorLogRepository;

    @AfterThrowing(pointcut = "execution(* ru.t1.java.demo.service..*(..))", throwing = "ex")
    public void logDatasourceError(JoinPoint joinPoint, Throwable ex) {
        log.error(ex.getMessage(), ex);
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        ex.printStackTrace(pw);
        DataSourceErrorLog logEntry = DataSourceErrorLog.builder()
                .message(ex.getMessage())
                .stackTrace(sw.toString())
                .methodSignature(joinPoint.getSignature().toString())
                .build();
        dataSourceErrorLogRepository.save(logEntry);
    }
}
