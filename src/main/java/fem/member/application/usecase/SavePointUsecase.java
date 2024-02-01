package fem.member.application.usecase;

import fem.member.domain.model.vo.IDName;
import fem.member.framework.web.dto.MemberOutputDTO;

public interface SavePointUsecase {
    MemberOutputDTO savePoint(IDName idName, long point);
}
