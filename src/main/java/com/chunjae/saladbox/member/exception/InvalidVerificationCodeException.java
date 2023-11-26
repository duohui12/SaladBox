package com.chunjae.saladbox.member.exception;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class InvalidVerificationCodeException extends RuntimeException{
    public InvalidVerificationCodeException(String code) {
        super("Invalid verification code " + code);
    }
}
