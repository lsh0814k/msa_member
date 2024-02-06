package fem.member.framework.kafkaadpater;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import fem.member.application.usecase.UsePointUsecase;
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
    private static final String TOPIC = "TOPIC_OVERDUE_CLEAR";

    @KafkaListener(topics = TOPIC, groupId = "${spring.kafka.consumer.group-id}")
    public void consumeClear(ConsumerRecord<String, String> record) {
        try {
            OverdueCleared overdueCleared = objectMapper.readValue(record.value(), OverdueCleared.class);
            usePointUsecase.usePoint(overdueCleared.getIdName(), overdueCleared.getPoint());
        } catch (JsonProcessingException e) {
            log.error("json 변환 에러 : ", e);
            throw new IllegalStateException(e);
        }
    }
}
