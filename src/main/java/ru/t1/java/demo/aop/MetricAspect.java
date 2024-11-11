package ru.t1.java.demo.aop;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import ru.t1.java.demo.dto.MetricsDto;
import ru.t1.java.demo.kafka.KafkaMetricsProducer;

@Aspect
@Component
@Slf4j
@RequiredArgsConstructor
public class MetricAspect {

    private final KafkaMetricsProducer kafkaMetricsProducer;

    @Around("@annotation(metric)")
    public Object around(ProceedingJoinPoint joinPoint, Metric metric) throws Throwable {
        long metricValue = metric.value();
        long startTime = System.currentTimeMillis();
        Object result;
        try {
            result = joinPoint.proceed();
        } finally {
            long endTime = System.currentTimeMillis();
            long elapsedTime = endTime - startTime;
            if (elapsedTime > metricValue) {
                log.debug("Elapsed time: {} ms, sending message to Kafka", elapsedTime);
                MetricsDto metrics = new MetricsDto(elapsedTime, joinPoint.getSignature().getName(),
                        joinPoint.getArgs());
                kafkaMetricsProducer.sendMetrics(metrics);
            }
        }
        return result;
    }
}
