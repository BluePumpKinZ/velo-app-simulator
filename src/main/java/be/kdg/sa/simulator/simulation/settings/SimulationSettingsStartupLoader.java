package be.kdg.sa.simulator.simulation.settings;

import be.kdg.sa.simulator.services.SettingService;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class SimulationSettingsStartupLoader {
	
	private final SettingService settingService;
	
	public SimulationSettingsStartupLoader (SettingService settingService) {
		this.settingService = settingService;
	}
	
	@PostConstruct
	public void loadSettings () {
		settingService.getSettingValues ();
	}
}
