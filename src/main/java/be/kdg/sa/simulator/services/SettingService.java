package be.kdg.sa.simulator.services;

import be.kdg.sa.simulator.configuration.SettingsProperties;
import be.kdg.sa.simulator.simulation.settings.SimulationSettings;
import be.kdg.sa.simulator.simulation.settings.SimulationSettingsFactory;
import org.springframework.stereotype.Service;

@Service
public class SettingService {

	private final SettingsProperties settingsProperties;
	private static SimulationSettings simulationSettings;
	
	public SettingService (SettingsProperties settingsProperties) {
		this.settingsProperties = settingsProperties;
	}
	
	public SimulationSettings getSimulationSettings () {
		if (simulationSettings == null)
			simulationSettings = SimulationSettingsFactory.fromFile(settingsProperties.getFile ());
		
		return simulationSettings;
	}
	
	public void setSimulationSettings (SimulationSettings simulationSettingValues) {
		SettingService.simulationSettings = simulationSettingValues;
	}
	

}
