package com.cvgenerator.exceptions;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public class ErrorMessage {

    private int errorCode;
    private String errorMessage;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm:ss")
    private LocalDateTime timestamp;
    private String developerMessage;

    private ErrorMessage(Builder builder) {
        this.errorCode          = builder.errorCode;
        this.errorMessage       = builder.errorMessage;
        this.timestamp          = builder.timestamp;
        this.developerMessage   = builder.developerMessage;
    }

    private static class Builder{

        private int errorCode;
        private String errorMessage;
        private LocalDateTime timestamp;
        private String developerMessage;

        public Builder setErrorCode(int errorCode) {
            this.errorCode = errorCode;
            return this;
        }

        public Builder setErrorMessage(String errorMessage) {
            this.errorMessage = errorMessage;
            return this;
        }

        public Builder setTimestamp(LocalDateTime timestamp) {
            this.timestamp = timestamp;
            return this;
        }

        public Builder setDeveloperMessage(String developerMessage) {
            this.developerMessage = developerMessage;
            return this;
        }

        public ErrorMessage buildErrorMessage(){
            return new ErrorMessage(this);
        }
    }

}
