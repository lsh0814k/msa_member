package fem.member.framework.kafkaadpater;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import fem.member.application.usecase.UsePointUsecase;
import fem.member.domain.model.event.PointCommand;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UsePointMemberEventConsumer {
    private static final String TOPIC = "TOPIC_POINT_USE";
    private final ObjectMapper objectMapper;
    private final UsePointUsecase usePointUsecase;

    @KafkaListener(topics = TOPIC, groupId = "${spring.kafka.consumer.group-id}")
    public void consume(ConsumerRecord<String, String> record) {
        try {
            PointCommand pointCommand = objectMapper.readValue(record.value(), PointCommand.class);
            usePointUsecase.usePoint(pointCommand.getIdName(), pointCommand.getPoint());
        } catch (JsonProcessingException e) {
            log.error("json 변환 에러 : ", e);
            throw new IllegalStateException(e);
        }
    }
}
