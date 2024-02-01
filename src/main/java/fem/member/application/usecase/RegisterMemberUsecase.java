package fem.member.application.usecase;

import fem.member.framework.web.dto.MemberInfoDTO;
import fem.member.framework.web.dto.MemberOutputDTO;

public interface RegisterMemberUsecase {
    MemberOutputDTO registerMember(MemberInfoDTO memberInfoDTO);
}
