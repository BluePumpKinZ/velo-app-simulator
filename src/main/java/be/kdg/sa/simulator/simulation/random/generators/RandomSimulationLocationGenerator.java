package be.kdg.sa.simulator.simulation.random.generators;

import be.kdg.sa.simulator.simulation.random.settings.RandomSimulationSettings;
import org.springframework.stereotype.Component;

import static be.kdg.sa.simulator.utils.RandomUtils.getRandomDoubleRanged;

@Component
public class RandomSimulationLocationGenerator {
	
	public double generateLatitude (RandomSimulationSettings randomSimulationSettings) {
		return getRandomDoubleRanged (randomSimulationSettings.getMinLatitude (), randomSimulationSettings.getMaxLatitude ());
	}
	
	public double generateLongitude (RandomSimulationSettings randomSimulationSettings) {
		return getRandomDoubleRanged (randomSimulationSettings.getMinLongitude (), randomSimulationSettings.getMaxLongitude ());
	}
	
}
