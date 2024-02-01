package fem.member.domain.model;

import fem.member.MemberFactory;
import fem.member.domain.model.vo.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class MemberTest {

    @Test
    @DisplayName("Member 생성")
    void register() {
        Member member = MemberFactory.create("id", "홍길동");

        assertThat(member.getPoint()).isEqualTo(Point.create(0));
        assertThat(member.getAuthorities().size()).isEqualTo(1);
        assertThat(member.getAuthorities().get(0).getRoleName()).isEqualTo(UserRole.USER);
        assertThat(member.getAuthorities().get(0).getMember()).isNotNull();
    }

    @Test
    @DisplayName("포인트 적립")
    void savePoint() {
        Member member = MemberFactory.create("id", "홍길동");

        member.savePoint(10);

        assertThat(member.getPoint()).isEqualTo(Point.create(10));
    }

    @Test
    @DisplayName("포인트 적립_마이너스 포인트")
    void savePoint_minus_point() {
        Member member = MemberFactory.create("id", "홍길동");

        assertThatThrownBy(() -> member.savePoint(-10))
                .isInstanceOf(IllegalArgumentException.class)
                .message()
                .isEqualTo("적립 포인트는 0 이거나 0 보다 작을 수 없습니다.");
    }

    @Test
    @DisplayName("포인트 사용")
    void usePoint() {
        Member member = MemberFactory.create("id", "홍길동");
        member.savePoint(10);

        member.usePoint(10);

        assertThat(member.getPoint()).isEqualTo(Point.create(0));
    }

    @Test
    @DisplayName("포인트 사용_보유 포인트를 초과하는 경우")
    void usePoint_over() {
        Member member = MemberFactory.create("id", "홍길동");

        assertThatThrownBy(() -> member.usePoint(10))
                .isInstanceOf(IllegalArgumentException.class)
                .message()
                .isEqualTo("기존 보유 Point보다 적어 차감할 수 없습니다.");
    }
}
