package com.chunjae.saladbox.member.application.service;

import com.chunjae.saladbox.member.application.port.GetVerificationCodePort;
import com.chunjae.saladbox.member.application.usecase.CheckVerificationCodeUseCase;
import com.chunjae.saladbox.member.domain.VerificationCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
class CheckVerificationCodeService implements CheckVerificationCodeUseCase {

    private final GetVerificationCodePort getVerificationCodePort;

    @Override
    public boolean isValidCode(String code) {
        Optional<VerificationCode> verificationCode = getVerificationCodePort.findByCode(code);
        return verificationCode.isPresent() && verificationCode.get().checkValidation();
    }

}



