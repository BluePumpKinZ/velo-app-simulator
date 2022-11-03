package be.kdg.sa.simulator.simulation.random.providers;

import be.kdg.sa.simulator.services.VehicleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RandomSimulationValidVehicleIdProvider {
	
	private final VehicleService vehicleService;
	private final Logger logger = LoggerFactory.getLogger (RandomSimulationValidVehicleIdProvider.class);
	
	public RandomSimulationValidVehicleIdProvider (VehicleService vehicleService) {
		this.vehicleService = vehicleService;
	}
	
	public List<Integer> getValidSimulationVehicleIds () {
		try {
			return vehicleService.getValidSimulationVehicleIds ();
		} catch (Exception e) {
			logger.error (e.getMessage (), e);
		}
		return List.of ();
	}
	
}
