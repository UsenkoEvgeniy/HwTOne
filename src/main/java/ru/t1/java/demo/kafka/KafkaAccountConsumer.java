package ru.t1.java.demo.kafka;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import ru.t1.java.demo.dto.AccountDto;
import ru.t1.java.demo.service.AccountService;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Component
public class KafkaAccountConsumer {

    private final AccountService accountService;

    @KafkaListener(topics = "${t1.kafka.topic.accounts}",
            containerFactory = "accountListenerContainerFactory")
    public void listener(@Payload List<AccountDto> accountList,
                         Acknowledgment ack,
                         @Header(KafkaHeaders.RECEIVED_TOPIC) String topic,
                         @Header(KafkaHeaders.RECEIVED_KEY) String key) {
        log.debug("Account consumer: message processing started");

        try {
            accountService.createAccounts(accountList);
        } finally {
            ack.acknowledge();
        }
        log.debug("Account consumer: message processing completed");
    }
}
