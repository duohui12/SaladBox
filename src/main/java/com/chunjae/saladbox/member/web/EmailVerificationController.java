package com.chunjae.saladbox.member.web;

import com.chunjae.saladbox.member.application.usecase.CheckDuplicatedEmailUseCase;
import com.chunjae.saladbox.member.application.usecase.CheckVerificationCodeUseCase;
import com.chunjae.saladbox.member.application.usecase.CreateVerificationCodeUseCase;
import com.chunjae.saladbox.member.application.usecase.SendVerificationCodeUseCase;
import com.chunjae.saladbox.member.exception.BusinessLogicException;
import com.chunjae.saladbox.member.exception.DuplicatedEmailException;
import com.chunjae.saladbox.member.exception.InvalidVerificationCodeException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;

@RestController
@RequestMapping("/email")
@RequiredArgsConstructor
public class EmailVerificationController {

    private final CheckDuplicatedEmailUseCase checkDuplicatedEmailUseCase;
    private final SendVerificationCodeUseCase sendVerificationCodeUseCase;
    private final CheckVerificationCodeUseCase checkVerificationCodeUseCase;
    private final CreateVerificationCodeUseCase createVerificationCodeUseCase;

    //이메일 중복체크
    @GetMapping("/checkDup")
    void checkDup(String email) {
       if(checkDuplicatedEmailUseCase.isDuplicatedEmail(email)){
           throw new DuplicatedEmailException(email);
       }
    }

    //이메일 인증코드 발급
    @PostMapping("/verificationCode")
    @ResponseStatus(HttpStatus.CREATED)
    void createVerificationCode(String email) {

        String verificationCode;

        //1. 이메일 인증코드 발급
        try {
            verificationCode = createVerificationCodeUseCase.createVerificationCode();
        } catch (NoSuchAlgorithmException e) {
            throw new BusinessLogicException(e.getMessage());
        }

        //2. 인증코드 디비에 저장하고, 메일 발송
        sendVerificationCodeUseCase.sendVerificationCode(email, verificationCode);

    }

    //이메일 인증
    @GetMapping("/verify")
    void verify(String verificationCode) {
        if(!checkVerificationCodeUseCase.isValidCode(verificationCode)){
            throw new InvalidVerificationCodeException(verificationCode);
        }
    }

}
