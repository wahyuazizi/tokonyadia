package com.enigmacamp.tokonyadia.service;

import com.enigmacamp.tokonyadia.entity.Member;

import java.util.List;
import java.util.UUID;

public interface MemberService {
    Member saveMember(Member member);
    List<Member> saveAllMembers(List<Member> members);
    List<Member> getMembers();
    Member getMemberById(UUID id);
    void deleteMember(UUID id);
    Member updateMember(Member member);
    Boolean findByUsername(String username);
}
