package com.chunjae.saladbox.member.application.usecase;

import java.security.NoSuchAlgorithmException;

public interface SendVerificationCodeUseCase {

    String sendVerificationCode(String toEmail) throws NoSuchAlgorithmException;

}
