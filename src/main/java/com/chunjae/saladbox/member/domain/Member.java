package com.chunjae.saladbox.member.domain;

import lombok.*;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Member {
    private String email;
    private String password;
    private String name;
}



