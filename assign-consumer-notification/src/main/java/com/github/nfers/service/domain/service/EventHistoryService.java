package com.github.nfers.service.domain.service;

import com.github.nfers.service.domain.entity.EventHistoryEntity;
import com.github.nfers.service.domain.entity.enums.NotificationTypeEnum;
import io.quarkus.logging.Log;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class EventHistoryService {

    private final SubscriptionService subscriptionService;

    public EventHistoryService(SubscriptionService subscriptionService) {
        this.subscriptionService = subscriptionService;
    }


    @Transactional
    public Long save(String id, String notificationType) {

        var subscriptionExists = subscriptionService.checkExists(id);

        if (Boolean.FALSE.equals(subscriptionExists)) {
            throw new RuntimeException("Subscription: " + id + " not found");
        }
        Log.infof("EventHistory Created by subscription %s ", id);
        EventHistoryEntity eventHistoryEntity = new EventHistoryEntity();
        eventHistoryEntity.setSubscriptionId(id);
        eventHistoryEntity.setType(NotificationTypeEnum.valueOf(notificationType));
        eventHistoryEntity.persistAndFlush();

        return eventHistoryEntity.getId();
    }
}
