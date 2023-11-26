package com.chunjae.saladbox.member.application.port;

import com.chunjae.saladbox.member.domain.Member;

import java.util.Optional;

public interface GetMemberPort {
    Optional<Member> findByEmail(String email);
}
