package be.kdg.sa.simulator.simulation.random.generators;

import be.kdg.sa.simulator.simulation.random.settings.RandomSimulationSettings;
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
	
	public RandomSimulationDelay getRandomSimulationDelay (RandomSimulationSettings randomSimulationSettings) {
		var delayMillis = getRandomDelayMillis (randomSimulationSettings);
		return new RandomSimulationDelay (delayMillis);
	}
	
	private long getRandomDelayMillis (RandomSimulationSettings randomSimulationSettings) {
		var minDelayMillis = randomSimulationSettings.getDelayDuration () - randomSimulationSettings.getDelayVariation ();
		var maxDelayMillis = randomSimulationSettings.getDelayDuration () + randomSimulationSettings.getDelayVariation ();
		var delayMillis = Math.round (Math.random () * (maxDelayMillis - minDelayMillis) + minDelayMillis);
		return Math.max (delayMillis, 0);
	}
	
}
