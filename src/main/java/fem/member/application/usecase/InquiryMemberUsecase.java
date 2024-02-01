package fem.member.application.usecase;

import fem.member.framework.web.dto.MemberOutputDTO;

public interface InquiryMemberUsecase {
    MemberOutputDTO findMember(long memberNo);
}
