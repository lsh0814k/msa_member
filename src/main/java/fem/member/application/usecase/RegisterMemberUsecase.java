package fem.member.application.usecase;

import fem.member.framework.web.dto.MemberOutputDTO;
import fem.member.framework.web.dto.MemberInfoDTO;

public interface RegisterMemberUsecase {
    MemberOutputDTO registerMember(MemberInfoDTO memberInfoDTO);
}
