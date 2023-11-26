package com.chunjae.saladbox.member.exception;

public class DuplicatedEmailException extends RuntimeException {

    public DuplicatedEmailException(String email) {
        super("Duplicated Email " + email);
    }

}
