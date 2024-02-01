package fem.member.application.outputport;

import fem.member.domain.model.Member;
import fem.member.domain.model.vo.IDName;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberOutputPort {
    Member findById(long memberNo);
    Optional<Member> findByIdName(IDName idName);
    Member save(Member member);
}
