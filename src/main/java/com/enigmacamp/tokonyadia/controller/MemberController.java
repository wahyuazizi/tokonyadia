package com.enigmacamp.tokonyadia.controller;

import com.enigmacamp.tokonyadia.entity.Member;
import com.enigmacamp.tokonyadia.service.Implementation.MemberServiceImpl;
import com.enigmacamp.tokonyadia.utils.constant.ApiUrlConstant;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(ApiUrlConstant.MEMBER)
public class MemberController {
    private final MemberServiceImpl memberService;
    public MemberController(MemberServiceImpl memberService) {
        this.memberService = memberService;
    }

    @PostMapping
    public Member createMember(@RequestBody Member member) {
        return memberService.saveMember(member);
    }

    @PostMapping("/batch")
    public List<Member> saveMembers(@RequestBody List<Member> members) {
        return memberService.saveAllMembers(members);
    }

    @GetMapping
    public List<Member> getAllMembers() {
        return memberService.getMembers();
    }

    @GetMapping("/{id}")
    public Member getMemberById(@PathVariable UUID id) {
        return memberService.getMemberById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteMemberById(@PathVariable UUID id) {
        getMemberById(id);
        memberService.deleteMember(id);
    }

    @PutMapping
    public Member updateMember(@RequestBody Member member) {
        getMemberById(member.getId());
        return createMember(member);
    }



}
