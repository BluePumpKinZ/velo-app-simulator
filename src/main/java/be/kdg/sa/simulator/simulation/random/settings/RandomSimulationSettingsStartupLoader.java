package be.kdg.sa.simulator.simulation.random.settings;

import be.kdg.sa.simulator.services.RandomSimulatorSettingService;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class RandomSimulationSettingsStartupLoader {
	
	private final RandomSimulatorSettingService randomSimulatorSettingService;
	
	public RandomSimulationSettingsStartupLoader (RandomSimulatorSettingService randomSimulatorSettingService) {
		this.randomSimulatorSettingService = randomSimulatorSettingService;
	}
	
	@PostConstruct
	public void loadSettings () {
		randomSimulatorSettingService.getSimulationSettings ();
	}
}
