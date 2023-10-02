package com.github.nfers.message;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

import com.github.nfers.service.domain.dto.SubscriptionMessageDTO;
import io.quarkus.test.junit.QuarkusTest;
import org.eclipse.microprofile.reactive.messaging.Emitter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

@QuarkusTest
public class AssignPublisherMessageTest {
  private Emitter<SubscriptionMessageDTO> emitter;
  private MessagePublisher messagePublisher;

  @BeforeEach
  public void setUp() {
    emitter = mock(Emitter.class);
    messagePublisher = new EmitterMessagePublisher(emitter);
  }

  @Test
  public void publishMessage() {

    var dto = new SubscriptionMessageDTO("SUBSCRIPTION_CANCELED", "123");
    messagePublisher.publishMessage(dto);

    verify(emitter, times(1)).send(eq(dto));
  }

  @Test
  public void publishMessageWithError() {
    when(emitter.send(any(SubscriptionMessageDTO.class))).thenThrow(new RuntimeException("Erro ao publicar a mensagem"));

    var dto = new SubscriptionMessageDTO("SUBSCRIPTION_CANCELED", "123");

    assertThrows(RuntimeException.class, () -> {
      messagePublisher.publishMessage(dto);
    });

    verify(emitter, times(1)).send(eq(dto));
  }

}
