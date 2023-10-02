package com.github.nfers.message;

import com.github.nfers.service.domain.dto.SubscriptionMessageDTO;

public interface MessagePublisher {
    void publishMessage(SubscriptionMessageDTO message);
}
