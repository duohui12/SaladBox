package com.chunjae.saladbox.member.application.port;

public interface SaveVerificationCodePort {
    String save(String code, boolean isValidated);
}
