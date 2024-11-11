package ru.t1.java.demo.kafka;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import ru.t1.java.demo.dto.TransactionDto;
import ru.t1.java.demo.service.TransactionService;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Component
public class KafkaTransactionConsumer {

    private final TransactionService transactionService;

    @KafkaListener(topics = "${t1.kafka.topic.client_transactions}",
            containerFactory = "transactionListenerContainerFactory")
    public void listener(@Payload List<TransactionDto> transactionList,
                         Acknowledgment ack,
                         @Header(KafkaHeaders.RECEIVED_TOPIC) String topic,
                         @Header(KafkaHeaders.RECEIVED_KEY) String key) {
        log.debug("Transaction consumer: message processing started");

        try {
            transactionService.createTransactions(transactionList);
        } finally {
            ack.acknowledge();
        }
        log.debug("Transaction consumer: message processing completed");
    }
}