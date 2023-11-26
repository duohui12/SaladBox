package com.chunjae.saladbox.member.exception;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BusinessLogicException extends RuntimeException{
    public BusinessLogicException(String errorMsg) {
    }
}
