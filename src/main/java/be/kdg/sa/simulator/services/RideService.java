package be.kdg.sa.simulator.services;

import be.kdg.sa.simulator.api.di.VeloApiContainer;
import be.kdg.sa.simulator.api.velo.RideVeloApi;
import be.kdg.sa.simulator.exceptions.CommandExecutionError;
import be.kdg.sa.simulator.models.vehicles.calls.LockDockedVehicleCall;
import be.kdg.sa.simulator.models.vehicles.calls.LockUndockedVehicleCall;
import be.kdg.sa.simulator.models.vehicles.calls.UnlockDockedVehicleCall;
import be.kdg.sa.simulator.models.vehicles.calls.UnlockUndockedVehicleCall;
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
	
	public Integer startDockedRide (int stationId, int userId) throws IOException {
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
	
	public void startUndockedRide (int vehicleId, int userId, double latitude, double longitude) throws IOException {
		var event = new UnlockUndockedVehicleCall (vehicleId, userId, latitude, longitude);
		var call = rideVeloApi.startUndockedRide (event);
		
		var response = call.execute ();
		if (response.isSuccessful ())
			return;
		
		assert response.errorBody () != null;
		String message = response.errorBody ().string ();
		logger.error (message);
		throw new CommandExecutionError (message);
	}
	
	public void endDockedRide (int vehicleId, int userId, boolean defect, int lockId) throws IOException {
		var event = new LockDockedVehicleCall (vehicleId, userId, defect, lockId);
		var call = rideVeloApi.endDockedRide (event);
		
		var response = call.execute ();
		if (response.isSuccessful ())
			return;
		
		assert response.errorBody () != null;
		String message = response.errorBody ().string ();
		logger.error (message);
		throw new CommandExecutionError (message);
	}
	
	public void endUndockedRide (int vehicleId, int userId, boolean defect, double latitude, double longitude) throws IOException {
		var event = new LockUndockedVehicleCall (vehicleId, userId, defect, latitude, longitude);
		var call = rideVeloApi.endUndockedRide (event);
		
		var response = call.execute ();
		if (response.isSuccessful ())
			return;
		
		assert response.errorBody () != null;
		String message = response.errorBody ().string ();
		logger.error (message);
		throw new CommandExecutionError (message);
	}
	
}
