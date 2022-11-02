package be.kdg.sa.simulator.simulation.random;

import be.kdg.sa.simulator.simulation.settings.SimulationSettings;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
public class RandomSimulationContext {
	
	private final LocalDateTime startTime;
	private final SimulationSettings settings;
	private final List<RandomSimulationRide> rides;
	private int currentRideId;
	
	public RandomSimulationContext (SimulationSettings settings) {
		this.settings = settings;
		startTime = LocalDateTime.now();
		rides = new ArrayList<> ();
	}
	
	public boolean canExecute () {
		return startTime.plusSeconds(settings.getSeconds()).isAfter(LocalDateTime.now());
	}
	
	public int getPercentage () {
		return 100 * (LocalDateTime.now().getSecond() - startTime.getSecond()) / settings.getSeconds();
	}
	
	public int getRideCount () {
		return rides.size();
	}
	
	public RandomSimulationRide addRide (RandomSimulationRide ride) {
		rides.add (ride);
		ride.setId (currentRideId++);
		return ride;
	}
	
	public RandomSimulationRide getRandomRide () {
		return rides.get ((int) (Math.random () * rides.size ()));
	}
	
	public void removeRide (RandomSimulationRide ride) {
		rides.remove (ride);
	}

}
