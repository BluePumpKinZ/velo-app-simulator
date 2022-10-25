package be.kdg.sa.simulator.services;

import be.kdg.sa.simulator.api.di.VeloApiContainer;
import be.kdg.sa.simulator.api.velo.RideVeloApi;
import be.kdg.sa.simulator.models.vehicles.calls.UnlockDockedVehicleCall;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Service;

/**
 * Jonas Leijzen
 * 25/10/2022
 */
@Service
public class RideService {

	private final RideVeloApi rideVeloApi;
	private final Logger logger = LoggerFactory.getLogger (RideService.class);
	
	public RideService (VeloApiContainer veloApiContainer) {
		this.rideVeloApi = veloApiContainer.getVeloApi (RideVeloApi.class);
	}
	
	public int startRide (int stationId, int userId) {
		var event = new UnlockDockedVehicleCall (stationId, userId);
		var call = rideVeloApi.startDockedRide (event);
		try {
			var response = call.execute ();
			if (response.isSuccessful ()) {
				return response.body ();
			}
			logger.warn (response.errorBody ().string ());
		} catch (Exception e) {
			logger.error (e.getMessage ());
		}
		return 0;
	}

}
