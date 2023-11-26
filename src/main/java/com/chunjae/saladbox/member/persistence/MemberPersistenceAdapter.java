package com.chunjae.saladbox.member.persistence;

import com.chunjae.saladbox.member.application.port.GetMemberPort;
import com.chunjae.saladbox.member.application.port.SaveMemberPort;
import com.chunjae.saladbox.member.domain.Member;
import com.github.dozermapper.core.Mapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class MemberPersistenceAdapter implements GetMemberPort
                                                    , SaveMemberPort {

    private final MemberSpringDataRepository memberSpringDataRepository;
    private final Mapper mapper;

    @Override
    public Optional<Member> findByEmail(String email) {
        Optional<MemberEntity> memberEntity = memberSpringDataRepository.findByEmail(email);
        Member member = memberEntity.isPresent() ? mapper.map(memberEntity.get(),Member.class) : null;
        return Optional.ofNullable(member);
    }

    @Override
    public Member save(Member member) {
        MemberEntity memberEntity = memberSpringDataRepository.save(mapper.map(member, MemberEntity.class));
        return mapper.map(memberEntity, Member.class);
    }
}
