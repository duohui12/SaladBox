package com.chunjae.saladbox.member.web;
import com.chunjae.saladbox.member.application.usecase.CheckDuplicatedEmailUseCase;
import com.chunjae.saladbox.member.application.usecase.CheckVerificationCodeUseCase;
import com.chunjae.saladbox.member.application.usecase.JoinUseCase;
import com.chunjae.saladbox.member.domain.Member;
import com.github.dozermapper.core.Mapper;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
@RequiredArgsConstructor
public class JoinController {

    private final JoinUseCase joinUseCase;
    private final CheckDuplicatedEmailUseCase checkDuplicatedEmailUseCase;
    private final CheckVerificationCodeUseCase checkVerificationCodeUseCase;
    private final Mapper mapper;

    //회원가입 페이지 리턴
    @GetMapping("/join")
    public String joinForm() {
        return "member/join";
    }

    //회원가입 프로세스
    @PostMapping("/join")
    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    JoinResponseDto join(@ModelAttribute @Valid JoinRequestDto joinRequestDto) {

        //비밀번호 같은지 체크
        if(!joinRequestDto.checkPassword()){
            //do something
        }

        //중복된 이메일 아닌지 체크
        if(checkDuplicatedEmailUseCase.isDuplicatedEmail(joinRequestDto.getEmail())){
            //do something
        }

        //인증된 이메일인지 체크
        if(!checkVerificationCodeUseCase.isValidCode(joinRequestDto.getEmail()+joinRequestDto.getVerificationCode())){
            //do something
        }


        Member member = mapper.map(joinRequestDto,Member.class);
        return mapper.map(joinUseCase.join(member), JoinResponseDto.class);
    }

}

//Dozer is a Java Bean to Java Bean mapper that recursively copies data from one object to another, attribute by attribute.
//자바빈패턴 : 매개변수가 없는 생성자로 객체를 생성한 후에, setter 메서드를 이용해 필드의 초기값을 설정하는 방식


//public protected(같은 패키지 + 상속) default(같은 패키지) private
//not blank (must not be null and must contain at least one non-whitespace character) "a"(0) , " "(x), ""(x)
//not empty (must not be null nor empty) " " okay
//not null (must not be null) "" " " 이면 okay
@Valid
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
class JoinRequestDto {

    @Email
    private String email;

    @NotBlank
    private String verificationCode;

    @NotBlank
    private String password;

    @NotBlank
    private String password_check;

    @NotBlank
    @Size(min=1, max=10)
    private String name;

    public boolean checkPassword(){
        return password.equals(password_check);
    }
}


@Valid
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
class JoinResponseDto{

    @NotBlank
    private String email;

    @NotBlank
    private String name;

}

