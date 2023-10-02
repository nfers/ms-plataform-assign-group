package com.github.nfers.service.domain.dto;

import java.util.Objects;

public class MessageReceivedDTO {

    private String notificationType;
    private String subscription;

    public MessageReceivedDTO() {
    }

    public MessageReceivedDTO(String notificationType, String subscription) {
        this.notificationType = notificationType;
        this.subscription = subscription;
    }

    public String getNotificationType() {
        return notificationType;
    }

    public MessageReceivedDTO setNotificationType(String notificationType) {
        this.notificationType = notificationType;
        return this;
    }

    public String getSubscription() {
        return subscription;
    }

    public MessageReceivedDTO setSubscription(String subscription) {
        this.subscription = subscription;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MessageReceivedDTO)) return false;
        MessageReceivedDTO that = (MessageReceivedDTO) o;
        return Objects.equals(getNotificationType(),
                that.getNotificationType())
                && Objects.equals(getSubscription(),
                that.getSubscription());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getNotificationType(), getSubscription());
    }
}
