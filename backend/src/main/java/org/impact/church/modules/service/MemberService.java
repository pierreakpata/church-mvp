package org.impact.church.modules.service;


import org.impact.church.modules.model.Member;

import java.util.List;
import java.util.Map;

public interface MemberService {

    Member createMember(Member member);

    Member updateMember(Long id, Map<String, Object> updates);

    Member getMember(Long id);

    List<Member> getAllMembers();

    void deleteMember(Long id);
}
