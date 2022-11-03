package be.kdg.sa.simulator.services;

import be.kdg.sa.simulator.simulation.random.RandomSimulatorExecuter;
import org.springframework.stereotype.Service;

@Service
public class SimulationService {
	
	private final RandomSimulatorExecuter randomSimulatorExecuter;
	private final SettingService settingService;
	
	public SimulationService (RandomSimulatorExecuter randomSimulatorExecuter, SettingService settingService) {
		this.randomSimulatorExecuter = randomSimulatorExecuter;
		this.settingService = settingService;
	}
	
	public void startSimulation () {
		randomSimulatorExecuter.executeSimulation (settingService.getSimulationSettings ());
	}
}
