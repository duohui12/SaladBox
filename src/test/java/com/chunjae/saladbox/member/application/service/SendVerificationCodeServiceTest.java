package com.chunjae.saladbox.member.application.service;

import com.chunjae.saladbox.member.application.port.SaveVerificationCodePort;
import com.chunjae.saladbox.member.config.EmailConfig;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.test.context.ContextConfiguration;

import java.security.NoSuchAlgorithmException;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;


@SpringBootTest
@DisplayName("SendVerificationCodeService 클래스")
class SendVerificationCodeServiceTest {

    @Autowired
    private JavaMailSender javaMailSender;

    private SendVerificationCodeService sendVerificationCodeService;
    private SaveVerificationCodePort saveVerificationCodePort;

    @Nested
    @DisplayName("sendVerificationCode 메서드는")
    class Describe_sendVerificationCode{

        @Nested
        @DisplayName("유효한 이메일이 주어지면")
        class Context_with_valid_email{

            private String email = "duohui12@gmail.com";
            private String validVerificationCode = "123456";

            @BeforeEach
            void setup() {
                saveVerificationCodePort = mock(SaveVerificationCodePort.class);
                sendVerificationCodeService = new SendVerificationCodeService(javaMailSender, saveVerificationCodePort);

                given(saveVerificationCodePort.save(email+validVerificationCode,false)).willReturn(email+validVerificationCode);
            }

            @Test
            @DisplayName("인증코드를 생성해서 DB에 저장한다")
            void it_creates_verification_code_and_save() {
                assertThat(sendVerificationCodeService.sendVerificationCode(email, validVerificationCode))
                        .isEqualTo(validVerificationCode);

                verify(saveVerificationCodePort).save(email+validVerificationCode, false);
            }

            @Test
            @DisplayName("사용자 이메일로 인증코드를 발송한다")
            void it_sends_verification_code_to_user_email()  {

            }

        }

    }

}
