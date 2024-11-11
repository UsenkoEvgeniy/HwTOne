package ru.t1.java.demo.kafka;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;
import ru.t1.java.demo.dto.MetricsDto;
import ru.t1.java.demo.model.DataSourceErrorLog;
import ru.t1.java.demo.repository.DataSourceErrorLogRepository;

@Component
@Slf4j
@RequiredArgsConstructor
public class KafkaMetricsProducer {
    private static final String ERROR_TYPE_DATA_SOURCE = "DATA_SOURCE";
    private static final String ERROR_TYPE_METRICS = "METRICS";
    @Value("${t1.kafka.topic.metrics}")
    private String topic;
    private final DataSourceErrorLogRepository dataSourceErrorLogRepository;
    private final KafkaTemplate<String, Object> kafkaTemplate;

    public void sendLog(DataSourceErrorLog logEntry) {
        Message<DataSourceErrorLog> message = MessageBuilder.withPayload(logEntry)
                .setHeader("error_type", ERROR_TYPE_DATA_SOURCE).build();
        kafkaTemplate.send(topic, message).whenComplete((res, e) -> {
            if (e != null) {
                log.error("Error while sending message to Kafka", e);
                dataSourceErrorLogRepository.save(logEntry);
            }
        });
    }

    public void sendMetrics(MetricsDto metricsDto) {
        Message<MetricsDto> message = MessageBuilder.withPayload(metricsDto)
                .setHeader("error_type", ERROR_TYPE_METRICS).build();
        kafkaTemplate.send(topic, message).whenComplete((res, e) -> {
            if (e != null) {
                log.error("Error while sending message to Kafka", e);
            }
        });
    }
}
