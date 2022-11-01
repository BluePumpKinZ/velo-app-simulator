package be.kdg.sa.simulator.settings;

import be.kdg.sa.simulator.services.SettingService;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class SettingValuesStartupLoader {
	
	private final SettingService settingService;
	
	public SettingValuesStartupLoader (SettingService settingService) {
		this.settingService = settingService;
	}
	
	@PostConstruct
	public void loadSettings () {
		settingService.getSettingValues ();
	}
}
