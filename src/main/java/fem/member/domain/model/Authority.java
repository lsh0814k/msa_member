package fem.member.domain.model;

import fem.member.domain.model.vo.CreatedTime;
import fem.member.domain.model.vo.UserRole;
import jakarta.persistence.*;
import lombok.*;

import static jakarta.persistence.EnumType.STRING;
import static jakarta.persistence.FetchType.*;
import static lombok.AccessLevel.PRIVATE;
import static lombok.AccessLevel.PROTECTED;

@Entity
@Getter
@NoArgsConstructor(access = PROTECTED)
@AllArgsConstructor(access = PRIVATE)
@Builder(access = PRIVATE)
@EqualsAndHashCode(of = "roleName")
public class Authority extends CreatedTime {
    @Id @Column(name = "authority_id")
    @GeneratedValue
    private Long id;
    @Enumerated(value = STRING)
    private UserRole roleName;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "member_no")
    private Member member;

    public static Authority create(UserRole userRole, Member member) {
        return Authority.builder()
                .roleName(userRole)
                .member(member)
                .build();
    }
}
