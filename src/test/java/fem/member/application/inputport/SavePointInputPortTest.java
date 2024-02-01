package fem.member.application.inputport;

import fem.member.MemberFactory;
import fem.member.MemberInfoDTOFactory;
import fem.member.application.usecase.RegisterMemberUsecase;
import fem.member.domain.model.Member;
import fem.member.domain.model.vo.IDName;
import fem.member.domain.model.vo.Point;
import fem.member.framework.jpadapter.MemberRepository;
import fem.member.framework.web.dto.MemberOutputDTO;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class SavePointInputPortTest {
    @Autowired private SavePointInputPort savePointInputPort;
    @Autowired private MemberRepository memberRepository;

    @BeforeEach
    void init() {
        memberRepository.deleteAll();
    }

    @Test
    @DisplayName("포인트 적립")
    void savePoint() {
        // given
        Member member = memberRepository.save(MemberFactory.create("id", "홍길동"));
        memberRepository.save(member);

        // when
        savePointInputPort.savePoint(member.getIdName(), 10);

        // then
        Member findMember = memberRepository.findById(member.getMemberNo()).get();
        assertThat(findMember.getPoint()).isEqualTo(Point.create(10));
    }

    @Test
    @DisplayName("존재 하지 않는 회원")
    void savePoint_non_exist_member() {
        assertThatThrownBy(() -> savePointInputPort.savePoint(IDName.createIDName("11", ""), 10))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("잘못된 포인트 적립")
    void savePoint_wrong_point() {
        // given
        Member member = memberRepository.save(MemberFactory.create("id", "홍길동"));
        memberRepository.save(member);

        //expected
        assertThatThrownBy(() -> savePointInputPort.savePoint(member.getIdName(), -10))
                .isInstanceOf(IllegalArgumentException.class);
    }
}