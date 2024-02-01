package fem.member.domain.model.vo;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static lombok.AccessLevel.PRIVATE;
import static lombok.AccessLevel.PROTECTED;

@Getter
@Embeddable
@NoArgsConstructor(access = PROTECTED)
@AllArgsConstructor(access = PRIVATE)
public class Password {
    private String presentPWD;
    private String pastPWD;

    public static Password createPassword(String presentPWD, String pastPWD) {
        return new Password(presentPWD, pastPWD);
    }

}
