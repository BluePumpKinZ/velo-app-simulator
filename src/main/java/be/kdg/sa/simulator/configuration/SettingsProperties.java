package be.kdg.sa.simulator.configuration;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties (prefix = "settings")
@Getter
@Setter
@NoArgsConstructor
public class SettingsProperties {
	private String file;
}

