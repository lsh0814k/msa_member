package fem.member.application.inputport;

import fem.member.application.outputport.MemberOutputPort;
import fem.member.application.usecase.InquiryMemberUsecase;
import fem.member.framework.web.dto.MemberOutputDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class InquiryMemberInputPort implements InquiryMemberUsecase {

    private final MemberOutputPort memberOutputPort;
    @Override
    public MemberOutputDTO findMember(long memberNo) {
        return MemberOutputDTO.mapToDTO(memberOutputPort.findById(memberNo));
    }
}
