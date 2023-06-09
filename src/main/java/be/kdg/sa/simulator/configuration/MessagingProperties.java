package be.kdg.sa.simulator.configuration;

import be.kdg.sa.simulator.messaging.senders.SenderQueueConfig;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;


@Configuration
@ConfigurationProperties(prefix = "messaging")
@Getter
@Setter
@NoArgsConstructor
public class MessagingProperties implements SenderQueueConfig {
	private String routingKey;
	
	@Override
	public String getSenderQueueName () {
		return routingKey;
	}
}
