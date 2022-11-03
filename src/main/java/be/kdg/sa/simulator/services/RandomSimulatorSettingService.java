package be.kdg.sa.simulator.services;

import be.kdg.sa.simulator.configuration.SettingsProperties;
import be.kdg.sa.simulator.simulation.random.settings.RandomSimulationSettings;
import be.kdg.sa.simulator.simulation.random.settings.RandomSimulationSettingsFactory;
import org.springframework.stereotype.Service;

@Service
public class RandomSimulatorSettingService {

	private final SettingsProperties settingsProperties;
	private static RandomSimulationSettings randomSimulationSettings;
	
	public RandomSimulatorSettingService (SettingsProperties settingsProperties) {
		this.settingsProperties = settingsProperties;
	}
	
	public RandomSimulationSettings getSimulationSettings () {
		if (randomSimulationSettings == null)
			randomSimulationSettings = RandomSimulationSettingsFactory.fromFile(settingsProperties.getFile ());
		
		return randomSimulationSettings;
	}
	
	public void setSimulationSettings (RandomSimulationSettings simulationSettingValues) {
		RandomSimulatorSettingService.randomSimulationSettings = simulationSettingValues;
	}
	

}
