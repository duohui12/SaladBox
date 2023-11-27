package com.chunjae.saladbox.member.application.service;

import com.chunjae.saladbox.member.application.port.SaveVerificationCodePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.security.NoSuchAlgorithmException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;


@Nested
@DisplayName("CreateVerificationCodeService 클래스")
class CreateVerificationCodeServiceTest {

    private CreateVerificationCodeService createVerificationCodeService;

    @Nested
    @DisplayName("createVerificationCode 메서드는")
    class Describe_createVerificationCode{

        @Nested
        @DisplayName("메서드가 호출되면")
        class Context_when_called{

            private String verificationCode;

            @BeforeEach
            void setup() throws NoSuchAlgorithmException {
                createVerificationCodeService = new CreateVerificationCodeService();
                verificationCode = createVerificationCodeService.createVerificationCode();
            }

            @Test
            @DisplayName("6자리 숫자 인증코드를 생성해서 리턴한다.")
            void it_returns_new_verificationCode(){
                assertThat(verificationCode.length()).isEqualTo(6);
                assertDoesNotThrow(() -> Integer.parseInt(verificationCode));
            }

        }

    }
}
