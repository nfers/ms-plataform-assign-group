package com.github.nfers.message;

import com.github.nfers.message.exception.MessageConsumerException;
import com.github.nfers.service.domain.dto.MessageReceivedDTO;
import com.github.nfers.service.domain.service.ConsumerService;
import io.quarkus.logging.Log;
import io.vertx.core.json.JsonObject;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import org.eclipse.microprofile.reactive.messaging.Incoming;

@ApplicationScoped
public class AssignConsumerMessage {
    private final ConsumerService consumerService;

    public AssignConsumerMessage(ConsumerService consumerService) {
        this.consumerService = consumerService;
    }

    @Incoming("assign-queue")
    @Transactional
    public void receiveMessage(JsonObject message) {
        try {
            Log.infof("Received message: %s", message);

            var dto = transformJsonObjectInDTO(message);

            consumerService.saveMessageInEntity(dto);

        } catch (RuntimeException e) {
            throw new MessageConsumerException("Failed to process message", e);
        }

    }

    public MessageReceivedDTO transformJsonObjectInDTO(JsonObject message) {
       try {
           var dto = new MessageReceivedDTO();

           dto.setNotificationType(message.getString("notificationType"));
           dto.setSubscription(message.getString("subscription"));

           return dto;

       } catch (NullPointerException e) {
           throw new MessageConsumerException("Failed to transform message because the object came empty", e);
       }
    }

}
