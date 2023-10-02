package com.github.nfers.service;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

import com.github.nfers.message.AssignPublisherMessage;
import com.github.nfers.service.domain.dto.SubscriptionMessageDTO;
import io.quarkus.test.InjectMock;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;

@QuarkusTest
class PublisherServiceTest {

  @Inject PublisherService publisherService;

  @InjectMock private AssignPublisherMessage assignPublisherMessage;

  @Test
  public void testPublishMessage() {

    var dto = new SubscriptionMessageDTO("SUBSCRIPTION_CANCELED", "123");
    publisherService.publishMessage(dto);

    verify(assignPublisherMessage, times(1)).publishMessage(eq(dto));
  }

  @Test
  public void testPublishMessageWithNullableFields() {

    var dto = new SubscriptionMessageDTO();

    assertThrows(NullPointerException.class,
        () -> { publisherService.publishMessage(dto);
    });
  }
}
