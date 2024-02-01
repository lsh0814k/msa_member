package fem.member.framework.jpadapter;

import fem.member.application.outputport.MemberOutputPort;
import fem.member.domain.model.Member;
import fem.member.domain.model.vo.IDName;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class MemberJpaAdapter implements MemberOutputPort {
    private final MemberRepository memberRepository;

    @Override
    public Member findById(long memberNo) {
        return memberRepository.findById(memberNo)
                .orElseThrow(() -> new IllegalArgumentException("존재 하지 않는 회원입니다."));
    }

    @Override
    public Optional<Member> findByIdName(IDName idName) {
        return memberRepository.findByIdName(idName);
    }

    @Override
    public Member save(Member member) {
        return memberRepository.save(member);
    }
}
