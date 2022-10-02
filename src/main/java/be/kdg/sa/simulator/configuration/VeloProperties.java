package be.kdg.sa.simulator.configuration;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Jonas Leijzen
 * 2/10/2022
 */
@Configuration
@ConfigurationProperties (prefix = "velo")
@Getter
@Setter
@NoArgsConstructor
public class VeloProperties {
	private String url;
}
