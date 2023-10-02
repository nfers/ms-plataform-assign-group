package com.github.nfers.message.exception;

public class MessageConsumerException extends RuntimeException {

        public MessageConsumerException(String message) {
            super(message);
        }

        public MessageConsumerException(String message, Throwable cause) {
            super(message, cause);
        }
}
