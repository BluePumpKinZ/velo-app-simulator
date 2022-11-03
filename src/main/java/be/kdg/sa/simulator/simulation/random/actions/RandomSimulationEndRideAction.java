package be.kdg.sa.simulator.simulation.random.actions;

import be.kdg.sa.simulator.services.RideService;
import be.kdg.sa.simulator.simulation.random.generators.RandomSimulationLocationGenerator;
import be.kdg.sa.simulator.simulation.random.RandomSimulationContext;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class RandomSimulationEndRideAction implements RandomSimulationAction {
	
	private final RideService rideService;
	private final RandomSimulationLocationGenerator randomSimulationLocationGenerator;
	
	public RandomSimulationEndRideAction (RideService rideService, RandomSimulationLocationGenerator randomSimulationLocationGenerator) {
		this.rideService = rideService;
		this.randomSimulationLocationGenerator = randomSimulationLocationGenerator;
	}
	
	@Override
	public int getWeight () {
		return 1;
	}
	
	@Override
	public boolean doesApply (RandomSimulationContext context) {
		return context.getRideCount () > 0;
	}
	
	@Override
	public String execute (RandomSimulationContext context) {
		var ride = context.getRandomRide ();
		try {
			rideService.endUndockedRide (ride.getVehicleId (), ride.getUserId (), false,
					randomSimulationLocationGenerator.generateLatitude (context.getSettings ()),
					randomSimulationLocationGenerator.generateLongitude (context.getSettings ()));
			context.removeRide (ride);
			return String.format ("Ended ride with vehicle %s and user %s", ride.getVehicleId (), ride.getUserId ());
		} catch (IOException e) {
			return String.format ("Failed to end ride with vehicle %s and user %s", ride.getVehicleId (), ride.getUserId ());
		}
	}
}
