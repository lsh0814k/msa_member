package fem.member.domain.model;

import fem.member.MemberFactory;
import fem.member.domain.model.vo.*;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.*;

class MemberTest {
    @Test
    @DisplayName("멤버 등록")
    void registerMember() {
        Member member = Member.registerMember(
                IDName.createIDName("id", "홍길동"),
                Password.createPassword("123456", "123456"),
                Email.create("email@naver.com")
        );

        assertThat(member.getIdName()).isEqualTo(IDName.createIDName("id", "홍길동"));
        assertThat(member.getAuthorities().size()).isEqualTo(1);
        assertThat(member.getAuthorities().get(0).getRoleName()).isEqualTo(UserRole.USER);
    }

    @Test
    @DisplayName("포인트 적립")
    void savePoint() {
        Member member = MemberFactory.create("id", "홍길동");

        member.savePoint(10);

        assertThat(member.getPoint()).isEqualTo(Point.create(10));
    }

    @Test
    @DisplayName("잘못된 포인트 적립")
    void savePoint_wrong_point() {
        Member member = MemberFactory.create("id", "홍길동");

        assertThatThrownBy(() -> member.savePoint(-10))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("포인트 차감_보유 금액보다 차감 포인트가 큰 경우")
    void usePoint_over_point() {
        Member member = MemberFactory.create("id", "홍길동");
        assertThatThrownBy(() -> member.usePoint(10))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("포인트 차감")
    void usePoint() {
        Member member = MemberFactory.create("id", "홍길동");
        member.savePoint(10);
        member.usePoint(10);

        assertThat(member.getPoint()).isEqualTo(Point.create(0));
    }

    @Test
    @DisplayName("포인트 차감 - 포인트 부족")
    void usePoint_not_enough() {
        Member member = MemberFactory.create("id", "홍길동");
        member.savePoint(10);

        assertThatThrownBy(() -> member.usePoint(20))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("권한 추가_중복된 권한")
    void addAuthority_duplicate() {
        Member member = MemberFactory.create("id", "홍길동");
        assertThatThrownBy(() -> member.addAuthority(UserRole.USER))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("권한 추가")
    void addAuthority() {
        Member member = MemberFactory.create("id", "홍길동");
        member.addAuthority(UserRole.ADMIN);

        assertThat(member.getAuthorities().size()).isEqualTo(2);
        assertThat(member.getAuthorities().stream()
                .map(Authority::getRoleName)
                .toList().contains(UserRole.ADMIN)).isTrue();
    }
}