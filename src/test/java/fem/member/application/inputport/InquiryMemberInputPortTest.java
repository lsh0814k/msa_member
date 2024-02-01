package fem.member.application.inputport;

import fem.member.MemberFactory;
import fem.member.MemberInfoDTOFactory;
import fem.member.application.usecase.RegisterMemberUsecase;
import fem.member.domain.model.Member;
import fem.member.framework.jpadapter.MemberRepository;
import fem.member.framework.web.dto.MemberInfoDTO;
import fem.member.framework.web.dto.MemberOutputDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.InvalidDataAccessApiUsageException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
class InquiryMemberInputPortTest {
    @Autowired private InquiryMemberInputPort inquiryMemberInputPort;
    @Autowired private MemberRepository memberRepository;

    @BeforeEach
    void init() {
        memberRepository.deleteAll();
    }

    @Test
    @DisplayName("멤버 조회_존재하지 않는 멤버")
    void findMember_non_exist() {
        assertThatThrownBy(() -> inquiryMemberInputPort.findMember(1))
                .isInstanceOf(InvalidDataAccessApiUsageException.class)
                .message()
                .isEqualTo("존재 하지 않는 회원입니다.");
    }

    @Test
    @DisplayName("멤버 조회")
    void findMember() {
        Member member = MemberFactory.create("id", "홍길동");
        memberRepository.save(member);

        MemberOutputDTO memberOutputDTO = inquiryMemberInputPort.findMember(member.getMemberNo());
        assertThat(memberOutputDTO.getId()).isEqualTo("id");
        assertThat(memberOutputDTO.getName()).isEqualTo("홍길동");
    }
}