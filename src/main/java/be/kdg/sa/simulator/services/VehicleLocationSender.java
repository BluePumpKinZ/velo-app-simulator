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
	private final MessagingProperties configProperties;
	
	public VehicleLocationSender (RabbitTemplate rabbitTemplate, MessagingProperties configProperties) {
		this.rabbitTemplate = rabbitTemplate;
		this.configProperties = configProperties;
	}
	
	public void send (String message) {
		rabbitTemplate.convertAndSend (configProperties.getRoutingKey (), message);
	}
}
