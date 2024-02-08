package fem.member.framework.kafkaadpater;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import fem.member.application.outputport.ClearOverdueMemberOutput;
import fem.member.application.usecase.UsePointUsecase;
import fem.member.domain.model.event.EventOverdueClearResult;
import fem.member.domain.model.event.OverdueCleared;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ClearOverdueMemberEventConsumer {
    private final ObjectMapper objectMapper;
    private final UsePointUsecase usePointUsecase;
    private final ClearOverdueMemberOutput clearOverdueMemberOutput;
    private static final String TOPIC = "TOPIC_OVERDUE_CLEAR";

    @KafkaListener(topics = TOPIC, groupId = "${spring.kafka.consumer.group-id}")
    public void consumeClear(ConsumerRecord<String, String> record) {
        OverdueCleared overdueCleared = null;
        EventOverdueClearResult eventOverdueClearResult = null;
        try {
            overdueCleared = objectMapper.readValue(record.value(), OverdueCleared.class);
            usePointUsecase.usePoint(overdueCleared.getIdName(), overdueCleared.getPoint());
            eventOverdueClearResult = createResult(overdueCleared, true);
        } catch (JsonProcessingException e) {
            log.error("json 변환 에러 : ", e);
            throw new IllegalStateException(e);
        } catch (IllegalArgumentException e) {
            log.info("포인트 부족 오류", e);
            eventOverdueClearResult = createResult(overdueCleared, false);
        } catch (Exception e) {
            log.info("예상치 못한 포인트 차감 오류");
            eventOverdueClearResult = createResult(overdueCleared, false);
        }

        clearOverdueMemberOutput.occurEvent(eventOverdueClearResult);
    }

    private EventOverdueClearResult createResult(OverdueCleared overdueCleared, boolean isSuccess) {
        return EventOverdueClearResult.create(overdueCleared.getIdName(), overdueCleared.getPoint(), isSuccess);
    }
}
