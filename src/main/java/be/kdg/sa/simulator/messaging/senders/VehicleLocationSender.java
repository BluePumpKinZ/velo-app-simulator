package be.kdg.sa.simulator.messaging.senders;

import be.kdg.sa.simulator.configuration.MessagingProperties;
import be.kdg.sa.simulator.models.vehicles.messages.VehicleLocationPingMessage;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;


@Component
public class VehicleLocationSender {
	private final RabbitTemplate rabbitTemplate;
	private final MessagingProperties messagingProperties;
	private final ObjectMapper objectMapper;
	private final Logger logger = LoggerFactory.getLogger (VehicleLocationSender.class);
	
	public VehicleLocationSender (RabbitTemplate rabbitTemplate, MessagingProperties messagingProperties, ObjectMapper objectMapper) {
		this.rabbitTemplate = rabbitTemplate;
		this.messagingProperties = messagingProperties;
		this.objectMapper = objectMapper;
	}
	
	public void sendVehicleLocation (VehicleLocationPingMessage message) {
		try {
			var strMessage = serializeVehicleLocation (message);
			send (strMessage);
		} catch (JsonProcessingException e) {
			logger.error ("Could not parse " + message, e);
		}
	}
	
	private void send (String message) {
		rabbitTemplate.convertAndSend (messagingProperties.getRoutingKey (), message);
	}
	
	private String serializeVehicleLocation (VehicleLocationPingMessage message) throws JsonProcessingException {
		return objectMapper.writeValueAsString (message);
	}
}
