package com.chunjae.saladbox.member.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@AllArgsConstructor
public class VerificationCode {
    private String code;
    private Boolean isValidated;
}
