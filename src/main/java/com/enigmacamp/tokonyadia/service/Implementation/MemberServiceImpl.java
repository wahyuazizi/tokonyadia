package com.enigmacamp.tokonyadia.service.Implementation;

import com.enigmacamp.tokonyadia.entity.Member;
import com.enigmacamp.tokonyadia.repository.MemberRepository;
import com.enigmacamp.tokonyadia.service.MemberService;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public Member saveMember(Member member) {
        return memberRepository.save(member);
    }

    @Override
    public List<Member> saveAllMembers(List<Member> members) {
        return memberRepository.saveAll(members);
    }

    @Override
    public List<Member> getMembers() {
        return memberRepository.findAll();
    }

    @Override
    public Member getMemberById(UUID id) {
        try {
            if (!memberRepository.existsById(id)){
                throw new ChangeSetPersister.NotFoundException();
            }
            return memberRepository.getReferenceById(id);

        } catch (ChangeSetPersister.NotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void deleteMember(UUID id) {
        getMemberById(id);
        memberRepository.deleteById(id);
    }

    @Override
    public Member updateMember(Member member) {
        getMemberById(member.getId());
        return saveMember(member);
    }

    @Override
    public Optional<Member> findByUsername(String username) {
        return memberRepository.findByUsername(username);
    }

    @Override
    public Boolean existsByUsername(String username) {
        return memberRepository.existsByUsername(username);
    }


}
