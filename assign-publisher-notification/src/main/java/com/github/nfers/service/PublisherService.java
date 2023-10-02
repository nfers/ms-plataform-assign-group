package com.github.nfers.service;

import static io.smallrye.reactive.messaging.providers.helpers.Validation.isBlank;

import com.github.nfers.message.AssignPublisherMessage;
import com.github.nfers.service.domain.dto.SubscriptionMessageDTO;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PublisherService {

  private final AssignPublisherMessage assignPublisherMessage;

  public PublisherService(AssignPublisherMessage assignPublisherMessage) {
    this.assignPublisherMessage = assignPublisherMessage;
  }

  public void publishMessage(SubscriptionMessageDTO dto) {
    if (isBlank(dto.getNotificationType()) || dto.getSubscription().isBlank()) {
      throw new NullPointerException("Published message cannot be null/empty");
    }

    assignPublisherMessage.publishMessage(dto);
  }
}
