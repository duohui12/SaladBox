package com.chunjae.saladbox.member.application.service;

import com.chunjae.saladbox.member.application.port.SaveVerificationCodePort;
import com.chunjae.saladbox.member.application.usecase.SendVerificationCodeUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Random;

@Service
@RequiredArgsConstructor
class SendVerificationCodeService implements SendVerificationCodeUseCase {

    private final JavaMailSender javaMailSender;
    private final SaveVerificationCodePort saveVerificationCodePort;


    @Transactional
    @Override
    public String sendVerificationCode(String toEmail, String verificationCode) {

        /*
        인증코드 이메일 전송
        1. 인증코드 난수 생성
        2. 데이터베이스에 이메일+인증코드 저장
        3. 사용자 이메일로 인증코드 발송
         */

        saveVerificationCodePort.save(toEmail+verificationCode);
        SimpleMailMessage emailForm = createEmailForm(toEmail, verificationCode);
        javaMailSender.send(emailForm);
        return verificationCode;
    }


    //인증메일 생성
    private SimpleMailMessage createEmailForm(String toEmail, String verificationCode) {

        SimpleMailMessage message = new SimpleMailMessage();

        message.setTo(toEmail);
        message.setSubject("[샐러드박스] 이메일 인증번호 안내");
        message.setText(verificationCode);

        return message;
    }

}


