package fem.member.domain.model.event;

import fem.member.domain.model.vo.IDName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static lombok.AccessLevel.PRIVATE;
import static lombok.AccessLevel.PROTECTED;

@Getter
@NoArgsConstructor(access = PROTECTED)
@AllArgsConstructor(access = PRIVATE)
public class EventOverdueClearResult {
    private IDName idName;
    private long point;
    private boolean isSuccess;

    public static EventOverdueClearResult create(IDName idName, long point, boolean isSuccess) {
        return new EventOverdueClearResult(idName, point, isSuccess);
    }
}
