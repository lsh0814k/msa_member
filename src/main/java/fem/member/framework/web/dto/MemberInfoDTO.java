package fem.member.framework.web.dto;

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
public class MemberInfoDTO {
    private String id;
    private String name;
    private String password;
    private String email;

    public static MemberInfoDTO create(String id, String name, String password, String email) {
        return MemberInfoDTO
                .builder()
                .id(id)
                .name(name)
                .password(password)
                .email(email)
                .build();
    }
}
