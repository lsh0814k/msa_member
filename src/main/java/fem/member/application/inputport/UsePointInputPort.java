package fem.member.application.inputport;

import fem.member.application.outputport.MemberOutputPort;
import fem.member.application.usecase.UsePointUsecase;
import fem.member.domain.model.Member;
import fem.member.domain.model.vo.IDName;
import fem.member.framework.web.dto.MemberOutputDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class UsePointInputPort implements UsePointUsecase {
    private final MemberOutputPort memberOutputPort;

    @Override
    public MemberOutputDTO usePoint(IDName idName, long point) {
        Member member = memberOutputPort.findByIdName(idName)
                .orElseThrow(() -> new IllegalArgumentException("존재 하지 않는 회원입니다."));
        member.usePoint(point);
        return MemberOutputDTO.mapToDTO(member);
    }
}
