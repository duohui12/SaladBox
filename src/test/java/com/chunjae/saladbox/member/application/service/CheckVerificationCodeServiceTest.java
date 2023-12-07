package com.chunjae.saladbox.member.application.service;

import com.chunjae.saladbox.member.application.port.GetVerificationCodePort;
import com.chunjae.saladbox.member.application.port.SaveVerificationCodePort;
import com.chunjae.saladbox.member.domain.VerificationCode;
import org.junit.jupiter.api.*;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

@DisplayName("CheckVerificationCodeService 클래스")
class CheckVerificationCodeServiceTest {

    private static CheckVerificationCodeService checkVerificationCodeService;
    private static GetVerificationCodePort getVerificationCodePort;
    private static SaveVerificationCodePort saveVerificationCodePort;

    @BeforeAll
    static void setup(){
        getVerificationCodePort = mock(GetVerificationCodePort.class);
        saveVerificationCodePort = mock(SaveVerificationCodePort.class);
        checkVerificationCodeService =
                new CheckVerificationCodeService(getVerificationCodePort,saveVerificationCodePort);
    }

    @Nested
    @DisplayName("isValidCode 메서드는")
    class Describe_isValidCode{

        @Nested
        @DisplayName("유효한 인증코드가 주어지면")
        class Context_with_valid_code{

            private String validCode = "dh1234@gmail.com0123456789";
            private VerificationCode verificationCode = new VerificationCode(validCode,false);

            @BeforeEach
            void setup(){
                given(getVerificationCodePort.findByCode(validCode))
                        .willReturn(Optional.of(verificationCode));
            }

            @Test
            @DisplayName("true를 리턴한다")
            void it_returns_true(){
                assertThat(checkVerificationCodeService.isValidCode(validCode)).isTrue();
            }

        }

        @Nested
        @DisplayName("유효하지 않은 인증코드가 주어지면")
        class Context_with_invalid_code{

            private String invalidCode = "dh1234@gmail.com0000000000";

            @BeforeEach
            void setup(){
                given(getVerificationCodePort.findByCode(invalidCode))
                        .willReturn(Optional.ofNullable(null));
            }

            @Test
            @DisplayName("false를 리턴한다")
            void it_returns_false(){
                assertThat(checkVerificationCodeService.isValidCode(invalidCode)).isFalse();
            }

        }

    }
}
