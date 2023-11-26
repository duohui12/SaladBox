package com.chunjae.saladbox.member.web;//package com.chunjae.saladbox.member.web;
//
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.http.HttpStatus;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.*;
//
//import javax.validation.Valid;
//import javax.validation.constraints.Email;
//import javax.validation.constraints.NotBlank;
//import javax.validation.constraints.Size;
//
//@Controller
//public class JoinController {
//
//    //회원가입 페이지 리턴
//    @GetMapping("/join")
//    public String joinForm() {
//        return "member/join";
//    }
//
//    //회원가입 프로세스
//    @PostMapping("/join")
//    @ResponseBody
//    @ResponseStatus(HttpStatus.CREATED)
//    String join(@ModelAttribute @Valid JoinRequestDto joinRequestDto) {
//        return "success";
//    }
//
//}
//
////default class => 동일 패키지 내에서 접근할 수 있다.
////public protected(같은 패키지 + 상속) default(같은 패키지) private
////not empty (must not be null nor empty) " " okay
////not blank (must not be null and must contain at least one non-whitespace character) "a"
////not null (must not be null) "" " " 이면 okay
//@Valid
//class JoinRequestDto {
//
//    JoinRequestDto(String email, String password, String password_check, String name){
//        this.email = email;
//        this.password = password;
//        this.password_check = password_check;
//        this.name = name;
//    }
//
//    @Email
//    private String email;
//
//    @NotBlank
//    private String password;
//
//    @NotBlank
//    private String password_check;
//
//    @NotBlank
//    @Size(min=1, max=10)
//    private String name;
//
//    @Override
//    public String toString() {
//        return String.format("email : %s, password : %s, password_check : %s, name : %s"
//                ,email, password, password_check, name);
//    }
//
//}
//
//
//
