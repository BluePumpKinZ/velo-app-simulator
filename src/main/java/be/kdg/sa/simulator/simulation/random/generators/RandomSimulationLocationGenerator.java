package be.kdg.sa.simulator.simulation.random.generators;

import be.kdg.sa.simulator.simulation.settings.SimulationSettings;
import org.springframework.stereotype.Component;

import static be.kdg.sa.simulator.utils.RandomUtils.getRandomDoubleRanged;

@Component
public class RandomSimulationLocationGenerator {
	
	public double generateLatitude (SimulationSettings simulationSettings) {
		return getRandomDoubleRanged (simulationSettings.getMinLatitude (), simulationSettings.getMaxLatitude ());
	}
	
	public double generateLongitude (SimulationSettings simulationSettings) {
		return getRandomDoubleRanged (simulationSettings.getMinLongitude (), simulationSettings.getMaxLongitude ());
	}
	
}
