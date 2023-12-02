package com.chunjae.saladbox.member.web;
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
        Member member = mapper.map(joinRequestDto,Member.class);
        return mapper.map(joinUseCase.join(member), JoinResponseDto.class);
    }

}

//Dozer is a Java Bean to Java Bean mapper that recursively copies data from one object to another, attribute by attribute.
//자바빈패턴 : 매개변수가 없는 생성자로 객체를 생성한 후에, setter 메서드를 이용해 필드의 초기값을 설정하는 방식


//default class => 동일 패키지 내에서 접근할 수 있다.
//public protected(같은 패키지 + 상속) default(같은 패키지) private
//not empty (must not be null nor empty) " " okay
//not blank (must not be null and must contain at least one non-whitespace character) "a"
//not null (must not be null) "" " " 이면 okay
@Valid
@Setter
@NoArgsConstructor
@AllArgsConstructor
class JoinRequestDto {

    @Email
    private String email;

    @NotBlank
    private String password;

    @NotBlank
    private String password_check;

    @NotBlank
    @Size(min=1, max=10)
    private String name;

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

