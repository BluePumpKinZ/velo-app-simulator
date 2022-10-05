package be.kdg.sa.simulator.services;

import be.kdg.sa.simulator.configuration.MessagingProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

/**
 * Jonas Leijzen
 * 2/10/2022
 */
@Component
public class VehicleLocationSender {
	private final RabbitTemplate rabbitTemplate;
	private final MessagingProperties messagingProperties;
	
	public VehicleLocationSender (RabbitTemplate rabbitTemplate, MessagingProperties messagingProperties) {
		this.rabbitTemplate = rabbitTemplate;
		this.messagingProperties = messagingProperties;
	}
	
	public void send (String message) {
		rabbitTemplate.convertAndSend (messagingProperties.getRoutingKey (), message);
	}
}
