package com.chunjae.saladbox.member.application.service;

import com.chunjae.saladbox.member.application.port.SaveMemberPort;
import com.chunjae.saladbox.member.application.usecase.JoinUseCase;
import com.chunjae.saladbox.member.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JoinService implements JoinUseCase {

    private final SaveMemberPort saveMemberPort;

    @Override
    public Member join(Member member) {
       return saveMemberPort.save(member);
    }

}
