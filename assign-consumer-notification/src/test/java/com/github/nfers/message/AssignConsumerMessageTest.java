package com.github.nfers.message;

import com.github.nfers.message.exception.MessageConsumerException;
import com.github.nfers.service.domain.dto.MessageReceivedDTO;
import com.github.nfers.service.domain.service.ConsumerService;
import io.quarkus.test.InjectMock;
import io.quarkus.test.junit.QuarkusTest;
import io.vertx.core.json.JsonObject;
import jakarta.inject.Inject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@QuarkusTest
public class AssignConsumerMessageTest {

    @InjectMock
    ConsumerService consumerService;
    @Inject
    AssignConsumerMessage assignConsumerMessage;

    @BeforeEach
    public void setUp() {
        Mockito.reset(consumerService);
    }

    @Test
    public void testReceiveMessageSuccess() {
        JsonObject message = new JsonObject()
                .put("notification-type", "TEST_NOTIFICATION")
                .put("subscription", "123456");

        MessageReceivedDTO dto = new MessageReceivedDTO(message.getString("notificationType"),
                message.getString("subscription"));

        assignConsumerMessage.receiveMessage(message);

        verify(consumerService, times(1))
                .saveMessageInEntity(eq(dto));
    }

    @Test
    public void testReceiveMessageError() {
        JsonObject message = new JsonObject()
                .put("notificationType", "TEST_NOTIFICATION")
                .put("subscription", "123456");

        MessageReceivedDTO dto = new MessageReceivedDTO(
                message.getString("notificationType"),
                message.getString("subscription"));

        doThrow(new RuntimeException("Error"))
                .when(consumerService)
                .saveMessageInEntity(eq(dto));
        assertThrows(MessageConsumerException.class,
                () -> assignConsumerMessage.receiveMessage(message)
        );

        verify(consumerService, times(1))
                .saveMessageInEntity(eq(dto));
    }

    @Test
    public void testReceiveMessageNull() {
        assertThrows(MessageConsumerException.class,
                () -> assignConsumerMessage.receiveMessage(null)
        );

        verify(consumerService, never()).saveMessageInEntity(any());
    }

}
