package fem.member;

import fem.member.domain.model.Member;
import fem.member.domain.model.vo.Email;
import fem.member.domain.model.vo.IDName;
import fem.member.domain.model.vo.Password;

public class MemberFactory {
    public static Member create(String id, String name) {
        return Member.registerMember(
                IDName.createIDName(id, name),
                Password.createPassword("123456", "123456"),
                Email.create("email@naver.com")
        );
    }
}
