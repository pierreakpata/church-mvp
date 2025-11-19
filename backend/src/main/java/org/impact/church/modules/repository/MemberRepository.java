package org.impact.church.modules.repository;

import org.impact.church.modules.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
