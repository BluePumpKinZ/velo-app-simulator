package be.kdg.sa.simulator.services;

import be.kdg.sa.simulator.api.di.VeloApiContainer;
import be.kdg.sa.simulator.api.velo.RideVeloApi;
import be.kdg.sa.simulator.exceptions.CommandExecutionError;
import be.kdg.sa.simulator.models.vehicles.calls.UnlockDockedVehicleCall;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class RideService {
	
	private final RideVeloApi rideVeloApi;
	private final Logger logger = LoggerFactory.getLogger (RideService.class);
	
	public RideService (VeloApiContainer veloApiContainer) {
		this.rideVeloApi = veloApiContainer.getVeloApi (RideVeloApi.class);
	}
	
	public Integer startRide (int stationId, int userId) throws IOException {
		var event = new UnlockDockedVehicleCall (userId, stationId);
		var call = rideVeloApi.startDockedRide (event);
		
		var response = call.execute ();
		if (response.isSuccessful ())
			return response.body ();
		
		assert response.errorBody () != null;
		String message = response.errorBody ().string ();
		logger.error (message);
		throw new CommandExecutionError (message);
	}
	
}
