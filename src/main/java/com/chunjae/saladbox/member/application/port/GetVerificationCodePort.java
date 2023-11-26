package com.chunjae.saladbox.member.application.port;

import com.chunjae.saladbox.member.domain.VerificationCode;

import java.util.Optional;

public interface GetVerificationCodePort {
    Optional<VerificationCode> findByCode(String code);
}
