package be.kdg.sa.simulator.simulation.random.actions;

import be.kdg.sa.simulator.models.vehicles.messages.VehicleLocationPingMessage;
import be.kdg.sa.simulator.services.VehicleService;
import be.kdg.sa.simulator.simulation.random.generators.RandomSimulationLocationGenerator;
import be.kdg.sa.simulator.simulation.random.RandomSimulationContext;
import org.springframework.stereotype.Component;

@Component
public class RandomSimulationVehicleLocationPingAction implements RandomSimulationAction {
	
	private final VehicleService vehicleService;
	private final RandomSimulationLocationGenerator randomSimulationLocationGenerator;
	
	public RandomSimulationVehicleLocationPingAction (VehicleService vehicleService, RandomSimulationLocationGenerator randomSimulationLocationGenerator) {
		this.vehicleService = vehicleService;
		this.randomSimulationLocationGenerator = randomSimulationLocationGenerator;
	}
	
	@Override
	public int getWeight () {
		return 3;
	}
	
	@Override
	public boolean doesApply (RandomSimulationContext context) {
		return context.getRideCount () > 0;
	}
	
	@Override
	public String execute (RandomSimulationContext context) {
		var randomRide = context.getRandomRide ();
		var vehicleLocationPingMessage = new VehicleLocationPingMessage (randomRide.getVehicleId (),
				randomSimulationLocationGenerator.generateLatitude (context.getSettings ()),
				randomSimulationLocationGenerator.generateLongitude (context.getSettings ()));
		vehicleService.updateVehicleLocation (vehicleLocationPingMessage);
		return String.format ("Vehicle %s pinged at %s, %s", vehicleLocationPingMessage.getVehicleId (),
				vehicleLocationPingMessage.getLatitude (), vehicleLocationPingMessage.getLongitude ());
	}
}
