package fem.member.framework.web.dto;

import fem.member.domain.model.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static lombok.AccessLevel.PRIVATE;
import static lombok.AccessLevel.PROTECTED;

@Getter
@AllArgsConstructor(access = PRIVATE)
@NoArgsConstructor(access = PROTECTED)
@Builder(access = PRIVATE)
public class MemberOutputDTO {
    private Long memberNo;
    private String id;
    private String name;
    private String email;
    private long point;

    public static MemberOutputDTO mapToDTO(Member member) {
        return MemberOutputDTO
                .builder()
                .memberNo(member.getMemberNo())
                .id(member.getIdName().getId())
                .name(member.getIdName().getName())
                .email(member.getEmail().getAddress())
                .point(member.getPoint().getPoint())
                .build();
    }
}
