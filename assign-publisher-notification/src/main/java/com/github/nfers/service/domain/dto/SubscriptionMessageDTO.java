package com.github.nfers.service.domain.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import jakarta.validation.constraints.NotEmpty;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

public class SubscriptionMessageDTO {

  @Schema(
      name = "notification-type",
      description = "The string value is a new status",
      example = "SUBSCRIPTION_CREATED")
  @NotEmpty(message = "notification-type is required")
  @JsonAlias({"notificationType", "notification-type"})
  private String notificationType;

  @Schema(
      name = "subscription",
      description = "The string value is a subscriptionId",
      example = "5793cf6b3fd833521db8c420955e6f01")
  @NotEmpty(message = "subscription is required")
  private String subscription;

  public SubscriptionMessageDTO() {
    //
  }

  public SubscriptionMessageDTO(String notificationType, String subscription) {
    this.notificationType = notificationType;
    this.subscription = subscription;
  }

  public String getNotificationType() {
    return notificationType;
  }

  public void setNotificationType(String notificationType) {
    this.notificationType = notificationType;
  }

  public String getSubscription() {
    return subscription;
  }

  public void setSubscription(String subscription) {
    this.subscription = subscription;
  }

  @Override
  public String toString() {
    return "SubscriptionMessage{"
        + "notificationType='"
        + notificationType
        + '\''
        + ", subscription='"
        + subscription
        + '\''
        + '}';
  }
}
