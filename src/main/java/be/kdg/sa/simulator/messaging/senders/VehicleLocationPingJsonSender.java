package be.kdg.sa.simulator.messaging.senders;

import be.kdg.sa.simulator.models.vehicles.messages.VehicleLocationPingMessage;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
public class VehicleLocationPingJsonSender extends JsonSender<VehicleLocationPingMessage, SenderQueueConfig> {
	
	public VehicleLocationPingJsonSender (RabbitTemplate rabbitTemplate, ObjectMapper objectMapper, SenderQueueConfig senderQueueConfig) {
		super (rabbitTemplate, objectMapper, senderQueueConfig, LoggerFactory.getLogger (VehicleLocationPingJsonSender.class));
	}
}
