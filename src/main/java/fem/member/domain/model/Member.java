package fem.member.domain.model;

import fem.member.domain.model.vo.*;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

import static lombok.AccessLevel.PRIVATE;
import static lombok.AccessLevel.PROTECTED;


@Entity
@Getter
@NoArgsConstructor(access = PROTECTED)
@AllArgsConstructor(access = PRIVATE)
@Builder(access = PRIVATE)
@EqualsAndHashCode(of = "memberNo")

public class Member extends BaseTime {

    @Id @GeneratedValue
    private Long memberNo;
    @Embedded
    private IDName idName;
    @Embedded
    private Password password;
    @Embedded
    private Email email;

    @OneToMany(mappedBy = "member")
    private List<Authority> authorities = new ArrayList<>();
    @Embedded
    private Point point;

    public static Member registerMember(IDName idName, Password pwd, Email email) {
        Member member = Member
                .builder()
                .idName(idName)
                .password(pwd)
                .email(email)
                .point(Point.create(0))
                .authorities(new ArrayList<>())
                .build();
        member.addAuthority(UserRole.USER);

        return member;
    }

    public void addAuthority(UserRole userRole) {
        Authority authority = Authority.create(userRole, this);
        if (authorities.contains(authority)) {
            throw new IllegalArgumentException("권한이 중복되었습니다.");
        }

        authorities.add(authority);
    }

    public void savePoint(long point) {
        this.point = this.point.addPoint(point);
    }

    public void usePoint(long point) {
        if (this.point.getPoint() < point) {
            throw new IllegalArgumentException("포인트가 부족합니다.");
        }
        this.point = this.point.removePoint(point);
    }
}
