package com.github.nfers.service.domain.service;

import com.github.nfers.service.domain.entity.StatusEntity;
import com.github.nfers.service.domain.entity.SubscriptionEntity;
import com.github.nfers.service.domain.entity.enums.NotificationTypeEnum;
import com.github.nfers.service.domain.entity.enums.StatusEnum;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@QuarkusTest
@Transactional
class SubscriptionServiceTest {

    private static final String subscriptionId = "123456";
    private static StatusEntity statusEntityActive = null;
    private static StatusEntity statusEntityInactive = null;

    @Inject
    SubscriptionService subscriptionService;

    @BeforeEach
    @Transactional
    public void setup() {
        statusEntityActive = new StatusEntity();
        statusEntityActive.setName(StatusEnum.ACTIVE);
        statusEntityActive.persist();

        statusEntityInactive = new StatusEntity();
        statusEntityInactive.setName(StatusEnum.INACTIVE);
        statusEntityInactive.persist();
    }


    @Test
    public void testSaveInactive() {


        subscriptionService.save(NotificationTypeEnum.SUBSCRIPTION_CANCELED.name(), subscriptionId);

        SubscriptionEntity subscriptionEntityDoCreate = new SubscriptionEntity();

        subscriptionEntityDoCreate.setId(subscriptionId);
        subscriptionEntityDoCreate.setStatusId(statusEntityInactive.getId());
        subscriptionEntityDoCreate.setStatus(statusEntityInactive);

        SubscriptionEntity subscriptionEntityFind = SubscriptionEntity.find("id = ?1", subscriptionId)
                .firstResult();

        assertEquals(subscriptionEntityDoCreate, subscriptionEntityFind);
    }

    @Test
    public void testUpdateSubscriptionStatus() {
        SubscriptionEntity subscriptionEntity = new SubscriptionEntity();
        subscriptionEntity.setId(subscriptionId);
        subscriptionEntity.setStatusId(statusEntityInactive.getId());
        subscriptionEntity.setStatus(statusEntityInactive);
        subscriptionEntity.persist();

        SubscriptionEntity subscriptionEntityFind = SubscriptionEntity.find("id = ?1", subscriptionId)
                .firstResult();

        subscriptionEntityFind.setStatusId(statusEntityActive.getId());
        subscriptionEntity.setStatus(statusEntityActive);
        subscriptionEntity.persist();

        SubscriptionEntity updatedSubscription = SubscriptionEntity.findById(subscriptionId);

        assertNotNull(updatedSubscription);
        assertEquals(subscriptionEntityFind.getId(), updatedSubscription.getId());
        assertEquals(StatusEnum.ACTIVE, updatedSubscription.getStatus().getName());
    }


}

