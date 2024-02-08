package fem.member.framework.kafkaadpater;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import fem.member.application.outputport.ClearOverdueMemberOutput;
import fem.member.domain.model.event.EventOverdueClearResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ClearOverdueMemberEventProducer implements ClearOverdueMemberOutput {
    private static final String TOPIC = "TOPIC_OVERDUE_CLEAR_RESULT";
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper;

    @Override
    public void occurEvent(EventOverdueClearResult eventOverdueClearResult) {
        try {
            kafkaTemplate.send(TOPIC, objectMapper.writeValueAsString(eventOverdueClearResult));
        } catch (JsonProcessingException e) {
            log.error("json 변환 에러 ", e);
            throw new IllegalStateException(e);
        }
    }
}
