package fem.member.framework.jpadapter;


import fem.member.domain.model.Member;
import fem.member.domain.model.vo.IDName;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByIdName(IDName idName);
}
