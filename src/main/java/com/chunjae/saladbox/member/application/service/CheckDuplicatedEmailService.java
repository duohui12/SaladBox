package com.chunjae.saladbox.member.application.service;

import com.chunjae.saladbox.member.application.port.GetMemberPort;
import com.chunjae.saladbox.member.application.usecase.CheckDuplicatedEmailUseCase;
import com.chunjae.saladbox.member.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
class CheckDuplicatedEmailService implements CheckDuplicatedEmailUseCase {

    private final GetMemberPort getMemberPort;

    @Override
    public boolean isDuplicatedEmail(String email) {
        Optional<Member> member = getMemberPort.findByEmail(email);
        return member.isPresent();
    }

}


