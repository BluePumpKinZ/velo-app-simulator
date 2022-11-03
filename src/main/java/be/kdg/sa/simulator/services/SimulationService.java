package be.kdg.sa.simulator.services;

import be.kdg.sa.simulator.simulation.random.RandomSimulatorExecuter;
import org.springframework.stereotype.Service;

@Service
public class SimulationService {
	
	private final RandomSimulatorExecuter randomSimulatorExecuter;
	private final RandomSimulatorSettingService randomSimulatorSettingService;
	
	public SimulationService (RandomSimulatorExecuter randomSimulatorExecuter, RandomSimulatorSettingService randomSimulatorSettingService) {
		this.randomSimulatorExecuter = randomSimulatorExecuter;
		this.randomSimulatorSettingService = randomSimulatorSettingService;
	}
	
	public void startSimulation () {
		randomSimulatorExecuter.executeSimulation (randomSimulatorSettingService.getSimulationSettings ());
	}
}
