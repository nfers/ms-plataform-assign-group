package com.github.nfers.message.exception;

public class MessageProducerException extends RuntimeException {

  public MessageProducerException(String message) {
    super(message);
  }

  public MessageProducerException(String message, Throwable cause) {
    super(message, cause);
  }
}
