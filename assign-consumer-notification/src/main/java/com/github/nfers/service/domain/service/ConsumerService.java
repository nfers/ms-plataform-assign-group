package com.github.nfers.service.domain.service;

import com.github.nfers.service.domain.dto.MessageReceivedDTO;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class ConsumerService {

    private final SubscriptionService subscriptionService;
    private final EventHistoryService eventHistoryService;


    public ConsumerService(SubscriptionService subscriptionService, EventHistoryService eventHistoryService) {
        this.subscriptionService = subscriptionService;
        this.eventHistoryService = eventHistoryService;
    }

    @Transactional
    public void saveMessageInEntity(MessageReceivedDTO messageReceivedDTO) {
        try {
            subscriptionService.save(messageReceivedDTO.getNotificationType(), messageReceivedDTO.getSubscription());
            eventHistoryService.save(messageReceivedDTO.getSubscription(), messageReceivedDTO.getNotificationType());
        } catch (Exception e) {
            throw new RuntimeException("Failed to persist data in the database", e);
        }

    }
}