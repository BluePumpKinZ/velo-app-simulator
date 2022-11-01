package be.kdg.sa.simulator.simulation.settings;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SimulationSettings {
	
	public int seconds;
	public int concurrentRides;
	public int delayDuration;
	public int delayVariation;
	public double minLatitude;
	public double maxLatitude;
	public double minLongitude;
	public double maxLongitude;
	
	public SimulationSettings () {
	}
	
}
