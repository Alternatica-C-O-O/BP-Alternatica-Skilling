package com.skilling.lms.notifications_service.service.impl;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Service
@Slf4j
@RequiredArgsConstructor
public class NotificacionProducerService {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    private static final String NOTIFICATION_TOPIC = "notification-events";

    public Mono<Void> sendNotificationEvent(Object event, String eventType) {
        String messageKey = eventType + "-" + System.currentTimeMillis();

        log.info("Enviando evento de notificación al topic '{}' con clave '{}', tipo '{}'", NOTIFICATION_TOPIC, messageKey, eventType);
        return Mono.fromFuture(kafkaTemplate.send(NOTIFICATION_TOPIC, messageKey, event))
                .doOnSuccess(result -> log.info("Evento de notificación enviado exitosamente: offset = {}, partition = {}", result.getRecordMetadata().offset(), result.getRecordMetadata().partition()))
                .doOnError(error -> log.error("Error al enviar evento de notificación: {}", error.getMessage()))
                .then();
    }
}
