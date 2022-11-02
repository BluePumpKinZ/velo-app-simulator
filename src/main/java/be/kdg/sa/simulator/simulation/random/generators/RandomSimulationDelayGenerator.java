package be.kdg.sa.simulator.simulation.random.generators;

import be.kdg.sa.simulator.simulation.settings.SimulationSettings;
import org.springframework.stereotype.Component;

@Component
public class RandomSimulationDelayGenerator {
	
	public final class RandomSimulationDelay {
		
		private final long delayMillis;
		
		public RandomSimulationDelay (long delayMillis) {
			this.delayMillis = delayMillis;
		}
		
		public void awaitDelay () {
			try {
				Thread.sleep (delayMillis);
			} catch (InterruptedException e) {
				e.printStackTrace ();
			}
		}
		
	}
	
	public RandomSimulationDelay getRandomSimulationDelay (SimulationSettings simulationSettings) {
		var delayMillis = getRandomDelayMillis (simulationSettings);
		return new RandomSimulationDelay (delayMillis);
	}
	
	private long getRandomDelayMillis (SimulationSettings simulationSettings) {
		var minDelayMillis = simulationSettings.getDelayDuration () - simulationSettings.getDelayVariation ();
		var maxDelayMillis = simulationSettings.getDelayDuration () + simulationSettings.getDelayVariation ();
		var delayMillis = Math.round (Math.random () * (maxDelayMillis - minDelayMillis) + minDelayMillis);
		return Math.max (delayMillis, 0);
	}
	
}
