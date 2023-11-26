package com.chunjae.saladbox.member.persistence;

import com.chunjae.saladbox.member.application.port.GetVerificationCodePort;
import com.chunjae.saladbox.member.application.port.SaveVerificationCodePort;
import com.chunjae.saladbox.member.domain.VerificationCode;
import com.github.dozermapper.core.Mapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
class VerificationCodePersistenceAdapter implements SaveVerificationCodePort
                                                    , GetVerificationCodePort {

    private final VerificationCodeSpringDataRepository verificationCodeSpringDataRepository;
    private final Mapper mapper;

    @Override
    public String save(String code) {
        VerificationCodeEntity entity = new VerificationCodeEntity(code);
        return verificationCodeSpringDataRepository.save(entity).getCode();
    }

    @Override
    public Optional<VerificationCode> findByCode(String code) {
        Optional<VerificationCodeEntity> verificationCodeEntity = verificationCodeSpringDataRepository.findByCode(code);
        VerificationCode verificationCode = verificationCodeEntity.isPresent() ? mapper.map(verificationCodeEntity, VerificationCode.class) : null;
        return Optional.of(verificationCode);
    }

}
