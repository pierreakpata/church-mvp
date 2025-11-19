package org.impact.church.modules.service;

import lombok.RequiredArgsConstructor;
import org.impact.church.modules.model.Member;
import org.impact.church.modules.repository.MemberRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService{

    private final MemberRepository memberRepository;


    public Member createMember(Member member) {
        return memberRepository.save(member);
    }

    public Member updateMember(Long id, Map<String, Object> updates) {
        Member existing = memberRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Member not found"));

        updates.forEach((key, value) -> {
            Field field = ReflectionUtils.findField(Member.class, key);
            if (field != null) {
                field.setAccessible(true);
                ReflectionUtils.setField(field, existing, value);
            }
        });

        return memberRepository.save(existing);
    }

    public Member getMember(Long id) {
        return memberRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Member not found"));
    }

    public List<Member> getAllMembers() {
        return memberRepository.findAll();
    }

    public void deleteMember(Long id) {
        memberRepository.deleteById(id);
    }
}
