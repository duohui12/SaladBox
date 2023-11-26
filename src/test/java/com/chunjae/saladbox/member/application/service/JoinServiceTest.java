package com.chunjae.saladbox.member.application.service;

import com.chunjae.saladbox.member.application.port.SaveMemberPort;
import com.chunjae.saladbox.member.domain.Member;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;


@Slf4j
@DisplayName("JoinService 클래스")
class JoinServiceTest {

    private static JoinService joinService;
    private static SaveMemberPort saveMemberPort;

    @BeforeAll
    static void setup(){
        saveMemberPort = mock(SaveMemberPort.class);
        joinService = new JoinService(saveMemberPort);
    }

    @Nested
    @DisplayName("join 메서드는")
    class Describe_join{

        private Member member;

        @Nested
        @DisplayName("멤버가 주어지면")
        class Context_with_member{

            @BeforeEach
            void setup(){
                member = new Member("dh1234@gmail.com","1111","dahye");
                given(saveMemberPort.save(any(Member.class))).willReturn(member);
            }

            @Test
            @DisplayName("멤버를 저장하고, 저장된 멤버를 반환한다.")
            void it_saves_member_and_return_saved_member(){
                Member savedMember = joinService.join(member);

                assertThat(savedMember.getEmail()).isEqualTo("dh1234@gmail.com");
                assertThat(savedMember.getName()).isEqualTo("dahye");
                assertThat(savedMember.getPassword()).isEqualTo("1111");

                verify(saveMemberPort).save(member);
            }
        }

    }

}
