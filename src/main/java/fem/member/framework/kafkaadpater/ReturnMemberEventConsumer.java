package fem.member.framework.kafkaadpater;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import fem.member.application.usecase.SavePointUsecase;
import fem.member.domain.model.event.ItemRented;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ReturnMemberEventConsumer {
    private final ObjectMapper objectMapper;
    private final SavePointUsecase savePointUsecase;
    private static final String TOPIC = "TOPIC_RETURN";

    @KafkaListener(topics = TOPIC, groupId = "${spring.kafka.consumer.group-id}")
    public void consumeReturn(ConsumerRecord<String, String> record) {
        try {
            ItemRented itemReturned = objectMapper.readValue(record.value(), ItemRented.class);
            savePointUsecase.savePoint(itemReturned.getIdName(), itemReturned.getPoint());
        } catch (JsonProcessingException e) {
            log.error("json 변환 에러 : ", e);
            throw new IllegalStateException(e);
        }
    }
}
