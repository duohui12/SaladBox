package com.chunjae.saladbox.member.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

interface VerificationCodeSpringDataRepository extends JpaRepository<VerificationCodeEntity, String> {
    Optional<VerificationCodeEntity> findByCode(String code);
}
