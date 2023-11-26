package com.chunjae.saladbox.member.persistence;


import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

interface MemberSpringDataRepository extends JpaRepository<MemberEntity, String> {
    Optional<MemberEntity> findByEmail(String email);
}
