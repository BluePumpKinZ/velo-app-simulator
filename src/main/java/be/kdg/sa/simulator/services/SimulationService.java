package be.kdg.sa.simulator.services;

import be.kdg.sa.simulator.simulation.random.RandomSimulatorExecuter;
import org.springframework.stereotype.Service;

@Service
public class SimulationService {
	
	private final RandomSimulatorExecuter randomSimulatorExecuter;
	
	public SimulationService (RandomSimulatorExecuter randomSimulatorExecuter) {
		this.randomSimulatorExecuter = randomSimulatorExecuter;
	}
}
