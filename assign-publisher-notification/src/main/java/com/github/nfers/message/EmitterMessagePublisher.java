package com.github.nfers.message;

import com.github.nfers.service.domain.dto.SubscriptionMessageDTO;
import jakarta.inject.Inject;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;

public class EmitterMessagePublisher implements MessagePublisher {
  private final Emitter<SubscriptionMessageDTO> emitter;

  @Inject
  public EmitterMessagePublisher(@Channel("assign") Emitter<SubscriptionMessageDTO> emitter) {
    this.emitter = emitter;
  }

  @Override
  public void publishMessage(SubscriptionMessageDTO message) {
    emitter.send(message);
  }
}
