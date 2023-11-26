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

    private final SaveVerificationCodePort saveVerificationCode;

    /*
    인증코드 이메일 전송
    1. 인증코드 난수 생성
    2. 데이터베이스에 이메일+인증코드 저장
    3. 사용자 이메일로 인증코드 발송
     */
    @Transactional
    @Override
    public String sendVerificationCode(String toEmail){

        try {

            String code = createVerificationCode();
            saveVerificationCode.save(toEmail+code);
            SimpleMailMessage emailForm = createEmailForm(toEmail, code);
            javaMailSender.send(emailForm);
            return code;

        } catch (NoSuchAlgorithmException e) {
           return null;
        }
    }


    //인증메일 생성
    private SimpleMailMessage createEmailForm(String toEmail, String code) {

        SimpleMailMessage message = new SimpleMailMessage();

        message.setTo(toEmail);
        message.setSubject("[샐러드박스] 이메일 인증번호 안내");
        message.setText(code);

        return message;
    }


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


