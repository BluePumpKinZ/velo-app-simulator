package be.kdg.sa.simulator.services;

import be.kdg.sa.simulator.configuration.SettingsProperties;
import be.kdg.sa.simulator.simulation.settings.SimulationSettings;
import be.kdg.sa.simulator.simulation.settings.SimulationSettingsFactory;
import org.springframework.stereotype.Service;

@Service
public class SettingService {

	private final SettingsProperties settingsProperties;
	private static SimulationSettings simulationSettingValues;
	
	public SettingService (SettingsProperties settingsProperties) {
		this.settingsProperties = settingsProperties;
	}
	
	public SimulationSettings getSettingValues () {
		if (simulationSettingValues == null)
			simulationSettingValues = SimulationSettingsFactory.fromFile(settingsProperties.getFile ());
		
		return simulationSettingValues;
	}
	
	public void setSettingValues (SimulationSettings simulationSettingValues) {
		SettingService.simulationSettingValues = simulationSettingValues;
	}
	

}
