package fem.member;

import fem.member.framework.web.dto.MemberInfoDTO;

public class MemberInfoDTOFactory {
    public static MemberInfoDTO create() {
        return MemberInfoDTO.create("id", "홍길동", "123456", "email@naver.com");
    }
}
