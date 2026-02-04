package com.enigmacamp.tokonyadia.repository;

import com.enigmacamp.tokonyadia.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface MemberRepository extends JpaRepository<Member, UUID> {
    Optional<Member> findByUsername(String username);
    Boolean existsByUsername(String username);
//    Member finByUsername(String username);
}
