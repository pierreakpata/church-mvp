package org.impact.church.modules.controller;

import lombok.RequiredArgsConstructor;
import org.impact.church.modules.dto.MemberDTO;
import org.impact.church.modules.model.Member;
import org.impact.church.modules.service.MemberService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/members")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/create")
    public MemberDTO saveMember(@RequestBody Member member) {
        return new MemberDTO(memberService.createMember(member));
    }

    @PutMapping("/update/{id}")
    public MemberDTO updateMember(@PathVariable Long id, @RequestBody Map<String, Object> updates) {
        return new MemberDTO(memberService.updateMember(id, updates));
    }

    @GetMapping("/{id}")
    public MemberDTO findMember(@PathVariable Long id) {
        return new MemberDTO(memberService.getMember(id));
    }

    @GetMapping
    public List<MemberDTO> getMembers() {
        return memberService.getAllMembers().stream()
                .map(MemberDTO::new).toList();
    }

    @DeleteMapping("/delete/{id}")
    public void deleteMember(@PathVariable Long id) {
        memberService.deleteMember(id);
    }
}
