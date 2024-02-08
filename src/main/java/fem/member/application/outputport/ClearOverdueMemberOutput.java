package fem.member.application.outputport;

import fem.member.domain.model.event.EventOverdueClearResult;

public interface ClearOverdueMemberOutput {
    void occurEvent(EventOverdueClearResult eventOverdueClearResult);
}
