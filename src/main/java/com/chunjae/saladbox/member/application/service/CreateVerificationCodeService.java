package com.chunjae.saladbox.member.application.service;

import com.chunjae.saladbox.member.application.usecase.CreateVerificationCodeUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Random;

@Service
@RequiredArgsConstructor
class CreateVerificationCodeService implements CreateVerificationCodeUseCase {

    //이메일 인증번호 생성
    public String createVerificationCode() throws NoSuchAlgorithmException {

        int len = 6;

        //Random random = new Random();
        Random random = SecureRandom.getInstanceStrong();

        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < len; i++) {
            builder.append(random.nextInt(10));
        }
        return builder.toString();

    }

}


