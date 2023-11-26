package com.chunjae.saladbox.member.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Member {
    private String email;
    private String password;
    private String name;
}
