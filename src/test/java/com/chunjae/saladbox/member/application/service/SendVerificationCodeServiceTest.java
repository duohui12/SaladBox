package com.chunjae.saladbox.member.application.service;

import com.chunjae.saladbox.member.application.port.SaveVerificationCodePort;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.javamail.JavaMailSender;

import java.security.NoSuchAlgorithmException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

//@SpringBootTest
@DisplayName("SendVerificationCodeService 클래스")
class SendVerificationCodeServiceTest {

    //@Autowired
    //private JavaMailSender javaMailSender;

    private static SendVerificationCodeService sendVerificationCodeService;
    private static SaveVerificationCodePort saveVerificationCodePort;

    @BeforeAll
    static void setup(){
        saveVerificationCodePort = mock(SaveVerificationCodePort.class);
        sendVerificationCodeService =
                mock(SendVerificationCodeService.class);
    }

    @Nested
    @DisplayName("sendVerificationCode 메서드는")
    class Describe_sendVerificationCode{

        private String email = "duohui12@gmail.com";
        private String verificationCode = "123456";

        @Nested
        @DisplayName("유효한 이메일이 주어지면")
        class Context_with_valid_email{

            @BeforeEach
            void setup() throws NoSuchAlgorithmException {
                given(sendVerificationCodeService.createVerificationCode()).willReturn(verificationCode);
                given(saveVerificationCodePort.save(any(String.class))).willReturn(email+verificationCode);
            }

            @Test
            @DisplayName("인증코드를 생성해서 DB에 저장한다")
            void it_creates_verification_code_and_save() throws NoSuchAlgorithmException {
                assertThat(sendVerificationCodeService.createVerificationCode()).isEqualTo(verificationCode);
            }

            @Test
            @DisplayName("인증코드를 주어진 이메일로 발송한다")
            void it_sends_verification_code_to_given_email(){

            }

        }

    }

}
