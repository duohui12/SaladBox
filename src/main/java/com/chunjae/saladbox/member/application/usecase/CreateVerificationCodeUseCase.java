package com.chunjae.saladbox.member.application.usecase;

import java.security.NoSuchAlgorithmException;

public interface CreateVerificationCodeUseCase {

    String createVerificationCode() throws NoSuchAlgorithmException;

}
