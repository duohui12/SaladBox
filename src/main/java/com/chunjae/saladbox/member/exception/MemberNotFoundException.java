package com.chunjae.saladbox.member.exception;

public class MemberNotFoundException extends RuntimeException {

    public MemberNotFoundException(String email) {
        super("Member not found " + email);
    }

}
