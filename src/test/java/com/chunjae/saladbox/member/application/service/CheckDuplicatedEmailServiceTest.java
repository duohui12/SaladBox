package com.chunjae.saladbox.member.application.service;

import com.chunjae.saladbox.member.application.port.GetMemberPort;
import com.chunjae.saladbox.member.domain.Member;
import org.junit.jupiter.api.*;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

@SpringBootTest
@DisplayName("CheckDuplicatedEmailService 클래스")
class CheckDuplicatedEmailServiceTest {

    private static CheckDuplicatedEmailService checkDuplicatedEmailService;
    private static GetMemberPort getMemberPort;
    private String duplicatedEmail = "dh1234@gmail.com";
    private String newEmail = "duohui12@gmail.com";
    private Member member = new Member(duplicatedEmail,"1111","dahye");

    @BeforeAll
    static void setup(){
        getMemberPort = mock(GetMemberPort.class);
        checkDuplicatedEmailService = new CheckDuplicatedEmailService(getMemberPort);
    }

    @Nested
    @DisplayName("isDuplicatedEmail 메서드는")
    class Describe_isDuplicatedEmail{

        @Nested
        @DisplayName("이미 등록된 이메일이 주어지면")
        class Context_with_duplicated_email{

            @BeforeEach
            void setup(){
                given(getMemberPort.findByEmail(duplicatedEmail)).willReturn(Optional.of(member));
            }

            @Test
            @DisplayName("true를 리턴한다")
            void it_returns_true(){
                assertThat(checkDuplicatedEmailService.isDuplicatedEmail(duplicatedEmail))
                        .isTrue();
            }

        }

        @Nested
        @DisplayName("신규 이메일이 주어지면")
        class Context_with_new_email{

            @BeforeEach
            void setup(){
                given(getMemberPort.findByEmail(newEmail)).willReturn(Optional.ofNullable(null));
            }

            @Test
            @DisplayName("false를 리턴한다")
            void ir_returns_false(){
                assertThat(checkDuplicatedEmailService.isDuplicatedEmail(duplicatedEmail))
                        .isFalse();
            }

        }
    }

}


//1.check 로직 작성할 때 service에서는 boolean을 리턴해서 컨트롤러에서 exception을 던질지
//2.service에서 바로 exception을 던지고 service에서 void 를 리턴할지

//1번으로 하면 컨트롤러만 보고도 서비스 메서드에서 어떤 작업을 할지 예상할 수 있다.
//2번으로 하면 컨트롤러를 보고 check로직을 알수 없어서 서비스 메서드를 타고 들어가야한다.

//Optional.of, Optional.ofNullable => 자바책 공부 + 포스팅
//위 내용 포스팅
//테스트 다 짜기
//@BeforeEach, @Before
