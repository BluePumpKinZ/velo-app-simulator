package be.kdg.sa.simulator.simulation.random.actions;

import be.kdg.sa.simulator.services.RideService;
import be.kdg.sa.simulator.simulation.random.RandomSimulationContext;
import be.kdg.sa.simulator.simulation.random.generators.RandomSimulationLocationGenerator;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class RandomSimulationStartRideAction implements RandomSimulationAction {
	
	private final RideService rideService;
	private final RandomSimulationLocationGenerator randomSimulationLocationGenerator;
	
	public RandomSimulationStartRideAction (RideService rideService, RandomSimulationLocationGenerator randomSimulationLocationGenerator) {
		this.rideService = rideService;
		this.randomSimulationLocationGenerator = randomSimulationLocationGenerator;
	}
	
	@Override
	public int getWeight () {
		return 1;
	}
	
	@Override
	public boolean doesApply (RandomSimulationContext context) {
		return context.getRideCount () < context.getSettings ().getConcurrentRides ();
	}
	
	@Override
	public String execute (RandomSimulationContext context) {
		var newRide = context.getNewRide ();
		try {
			rideService.startUndockedRide (newRide.getVehicleId (), newRide.getUserId (),
					randomSimulationLocationGenerator.generateLatitude (context.getSettings ()),
					randomSimulationLocationGenerator.generateLongitude (context.getSettings ()));
			return String.format ("Started ride for user %s with vehicle %s", newRide.getUserId (), newRide.getVehicleId ());
		} catch (IOException e) {
			return String.format ("Failed to start ride for user %s with vehicle %s", newRide.getUserId (), newRide.getVehicleId ());
		}
	}
}
