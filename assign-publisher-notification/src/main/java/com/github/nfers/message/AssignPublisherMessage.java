package com.github.nfers.message;

import com.github.nfers.message.exception.MessageProducerException;
import com.github.nfers.service.domain.dto.SubscriptionMessageDTO;
import io.quarkus.logging.Log;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class AssignPublisherMessage {

  private final MessagePublisher messagePublisher;

  @Inject
  public AssignPublisherMessage(MessagePublisher messagePublisher) {
    this.messagePublisher = messagePublisher;
  }

  public void publishMessage(SubscriptionMessageDTO message) {

    try {
      Log.infof("Sending message: %s", message);

      messagePublisher.publishMessage(message);

    } catch (RuntimeException e) {
      Log.errorf(
          "Error to send message with data: %s - Error: %s", message.toString(), e.getMessage());
      throw new MessageProducerException("Failed to produce message: " + e.getMessage());
    }
  }
}
