package com.chunjae.saladbox.member.web;

import com.chunjae.saladbox.member.application.usecase.JoinUseCase;
import com.chunjae.saladbox.member.domain.Member;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(JoinController.class)
@DisplayName("JoinController 클래스")
class JoinControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private JoinUseCase joinUseCase;


    private final String email = "duohui12@gmail.com";
    private final String name = "dahye";
    private final String password = "1111";
    private final String passwordCheck = "1111";


    @Nested
    @DisplayName("join 메서드는")
    class Describe_join{

        @Nested
        @DisplayName("올바른 가입 정보가 주어지면")
        class Context_with_valid_join_data{

            private Member member;

            @BeforeEach
            void setup(){
                member = new Member(email,password,name);
                given(joinUseCase.join(any(Member.class))).willReturn(member);
            }

            @Test
            @DisplayName("주어진 가입정보를 디비에 저장하고, email과 name을 리턴한다.")
            void it_saves_data_and_returns_email_and_name() throws Exception {
                mockMvc.perform(post("/join")
                                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                                .param("email", email)
                                .param("password", password)
                                .param("password_check", passwordCheck)
                                .param("name",name))
                        .andExpect(status().isCreated())
                        .andExpect(jsonPath("email").value(email))
                        .andExpect(jsonPath("name").value(name))
                        .andExpect(jsonPath("password").doesNotExist())
                        .andExpect(jsonPath("passsword_check").doesNotExist())
                        .andDo(print());

                verify(joinUseCase).join(any(Member.class));
            }

        }

        @Nested
        @DisplayName("유요하지 않은 이메일이 주어지면")
        class Context_with_invalid_email{

            private final String invalidEmail = "duohui12";

            @Test
            @DisplayName("상태코드 400을 리턴한다.")
            void it_returns_400() throws Exception {
                mockMvc.perform(post("/join")
                                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                                .param("email", invalidEmail)
                                .param("password", password)
                                .param("password_check", passwordCheck)
                                .param("name",name))
                        .andExpect(status().isBadRequest());
            }

        }

        @Nested
        @DisplayName("유효하지 않은 비밀번호가 주어지면")
        class Context_with_invalid_password{

            private final String invalidPassword = " ";

            @Test
            @DisplayName("상태코드 400을 리턴한다.")
            void it_returns_400() throws Exception {
                mockMvc.perform(post("/join")
                                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                                .param("email", email)
                                .param("password", invalidPassword)
                                .param("password_check", passwordCheck)
                                .param("name",name))
                        .andExpect(status().isBadRequest());
            }

        }

        @Nested
        @DisplayName("필수 정보를 넘겨주지 않으면")
        class Context_without_required_data{

            @Test
            @DisplayName("상태코드 400을 리턴한다.")
            void it_returns_400() throws Exception {
                mockMvc.perform(post("/join")
                                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                                .param("email", email)
                                .param("name",name))
                        .andExpect(status().isBadRequest());
            }

        }

        //TODO
        //인증을 진행한 이메일이 아니면??

        //위 세가지 조건은 사실 /join 을 호출하기 전에 체크한 로직이다 (위 세가지 조건을 만족했을 때만 join 버튼이 활성화 된다.)
        //하지만,, 프론트에서 조건을 체크하지 않고 강제로 join을 호출하는 경우에는??
        //join 작업 수행전에 위 조건 체크를 서버에서 한번 더 진행해야 하는건지???

    }
}
