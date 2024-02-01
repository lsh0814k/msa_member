package fem.member.application.inputport;

import fem.member.MemberFactory;
import fem.member.MemberInfoDTOFactory;
import fem.member.domain.model.Member;
import fem.member.framework.jpadapter.MemberRepository;
import fem.member.framework.web.dto.MemberOutputDTO;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
class RegisterMemberInputPortTest {
    @Autowired private RegisterMemberInputPort registerMemberInputPort;
    @Autowired private MemberRepository memberRepository;

    @BeforeEach
    void init() {
        memberRepository.deleteAll();
    }

    @Test
    @DisplayName("회원 생성")
    void registerMember() {
        MemberOutputDTO memberOutputDTO = registerMemberInputPort.registerMember(MemberInfoDTOFactory.create());

        Optional<Member> memberOptional = memberRepository.findById(memberOutputDTO.getMemberNo());
        assertThat(memberOptional.isPresent()).isTrue();
        assertThat(memberOptional.get().getCreatedDate()).isNotNull();
        assertThat(memberOptional.get().getLastModifiedDate()).isNotNull();
    }

    @Test
    @DisplayName("동일한 IdName")
    void register_sameIdName() {
        memberRepository.save(MemberFactory.create("id", "홍길동"));
        assertThatThrownBy(() -> registerMemberInputPort.registerMember(MemberInfoDTOFactory.create()))
                .isInstanceOf(IllegalArgumentException.class)
                .message()
                .isEqualTo("동일한 id와 name이 존재합니다.");
    }
}