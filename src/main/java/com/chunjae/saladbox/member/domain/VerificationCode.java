package com.chunjae.saladbox.member.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class VerificationCode {
    private String code;
    private Boolean isValidated;

    public boolean checkValidation(){
        if(!isValidated){  //이미 인증된 번호인 경우 -> 만료
            return false;
        }else{             //아직 인증되지 않은 번호인 경우 -> 인증처리
            isValidated = true;
            return true;
        }
    }
}
