package com.chunjae.saladbox.member.application.port;

import com.chunjae.saladbox.member.domain.Member;

public interface SaveMemberPort {
    Member save(Member member);
}
