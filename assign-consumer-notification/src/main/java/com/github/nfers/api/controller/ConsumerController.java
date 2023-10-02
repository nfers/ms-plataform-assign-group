package com.github.nfers.api.controller;

import com.github.nfers.api.contract.ConsumerAPI;
import com.github.nfers.service.domain.dto.MessageReceivedDTO;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ConsumerController implements ConsumerAPI {

  @Override
  public MessageReceivedDTO getMessage() {
    return ConsumerAPI.super.getMessage();
  }
}
