package com.github.nfers.service.domain.service;

import com.github.nfers.service.domain.entity.EventHistoryEntity;
import com.github.nfers.service.domain.entity.StatusEntity;
import com.github.nfers.service.domain.entity.SubscriptionEntity;
import com.github.nfers.service.domain.entity.enums.NotificationTypeEnum;
import com.github.nfers.service.domain.entity.enums.StatusEnum;
import io.quarkus.test.InjectMock;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.internal.matchers.Not;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@QuarkusTest
@Transactional
class EventHistoryServiceTest {
    private static final String subscriptionId = "123456";
    private static final String subscriptionIdNotFound = "0101";

    @InjectMock
    SubscriptionService subscriptionService;
    @Inject
    EventHistoryService eventHistoryService;

    @BeforeEach
    @Transactional
    public void setup() {
        StatusEntity statusEntityActive = new StatusEntity();
        statusEntityActive.setName(StatusEnum.ACTIVE);
        statusEntityActive.persist();

        SubscriptionEntity subscriptionEntity = new SubscriptionEntity();
        subscriptionEntity.setId(subscriptionId);
        subscriptionEntity.setStatusId(statusEntityActive.getId());
        subscriptionEntity.setStatus(statusEntityActive);
        subscriptionEntity.persist();
    }

    @Test
    public void testSave() {
        when(subscriptionService.checkExists(subscriptionId)).thenReturn(true);


       var id = eventHistoryService.save(subscriptionId, "SUBSCRIPTION_PURCHASED");

        verify(subscriptionService, times(1)).checkExists(eq(subscriptionId));

        EventHistoryEntity result = EventHistoryEntity.find("id = ?1", id)
                .firstResult();

        assertNotNull(result);
        assertEquals(result.getSubscriptionId(), subscriptionId);
        assertEquals(result.getType(), NotificationTypeEnum.SUBSCRIPTION_PURCHASED);

    }

    @Test
    public void testSaveSubscriptionDoesNotExist() {
        assertThrows(RuntimeException.class, () -> {
            eventHistoryService.save(subscriptionIdNotFound, "SUBSCRIPTION_PURCHASED");
        });
    }

}