package be.kdg.sa.simulator.simulation.random;

import be.kdg.sa.simulator.exceptions.NoValidIdsAvailableException;
import be.kdg.sa.simulator.simulation.settings.SimulationSettings;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class RandomSimulationContext {
	
	private final LocalDateTime startTime;
	private final SimulationSettings settings;
	private final List<RandomSimulationRide> rides;
	private int currentRideId;
	
	private final List<Integer> validVehicleIds;
	private final List<Integer> validUserIds;
	
	
	public RandomSimulationContext (SimulationSettings settings, List<Integer> validVehicleIds, List<Integer> validUserIds) {
		this.settings = settings;
		this.validVehicleIds = validVehicleIds;
		this.validUserIds = validUserIds;
		startTime = LocalDateTime.now ();
		rides = new ArrayList<> ();
	}
	
	public SimulationSettings getSettings () {
		return settings;
	}
	
	public boolean canExecute () {
		return startTime.plusSeconds (settings.getSeconds ()).isAfter (LocalDateTime.now ());
	}
	
	public int getPercentage () {
		return 100 * (LocalDateTime.now ().getSecond () - startTime.getSecond ()) / settings.getSeconds ();
	}
	
	public int getRideCount () {
		return rides.size ();
	}
	
	public RandomSimulationRide getNewRide () {
		var validVehicleId = validVehicleIds.stream ()
				.filter (vehicleId -> rides.stream ().noneMatch (ride -> ride.getVehicleId () == vehicleId))
				.findFirst ().orElseThrow (()
						-> new NoValidIdsAvailableException ("No valid vehicle ids available. Consider lowering the amount of concurrent rides"));
		var validUserId = validUserIds.stream ()
				.filter (userId -> rides.stream ().noneMatch (ride -> ride.getUserId () == userId))
				.findFirst ().orElseThrow (()
						-> new NoValidIdsAvailableException ("No valid user ids available. Consider lowering the amount of concurrent rides"));
		var randomSimulationRide = new RandomSimulationRide (currentRideId++, validVehicleId, validUserId);
		rides.add (randomSimulationRide);
		return randomSimulationRide;
	}
	
	public RandomSimulationRide getRandomRide () {
		return rides.get ((int) (Math.random () * rides.size ()));
	}
	
	public void removeRide (RandomSimulationRide ride) {
		rides.remove (ride);
	}
	
}
