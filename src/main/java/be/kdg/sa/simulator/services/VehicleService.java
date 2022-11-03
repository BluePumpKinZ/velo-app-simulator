package be.kdg.sa.simulator.services;

import be.kdg.sa.simulator.api.di.VeloApiContainer;
import be.kdg.sa.simulator.api.velo.VehicleVeloApi;
import be.kdg.sa.simulator.messaging.senders.VehicleLocationPingJsonSender;
import be.kdg.sa.simulator.models.vehicles.messages.VehicleLocationPingMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class VehicleService {
	
	private final VehicleLocationPingJsonSender vehicleLocationPingJsonSender;
	private final VehicleVeloApi vehicleVeloApi;
	private final Logger logger = LoggerFactory.getLogger (VehicleService.class);
	
	public VehicleService (VehicleLocationPingJsonSender vehicleLocationPingJsonSender, VeloApiContainer veloApiContainer) {
		this.vehicleLocationPingJsonSender = vehicleLocationPingJsonSender;
		this.vehicleVeloApi = veloApiContainer.getVeloApi (VehicleVeloApi.class);
	}
	
	public void updateVehicleLocation (VehicleLocationPingMessage pingMessage) {
		vehicleLocationPingJsonSender.send (pingMessage);
	}
	
	public List<Integer> getValidSimulationVehicleIds () throws IOException {
		var call = vehicleVeloApi.getValidSimulationVehicleIds ();
		
		var response = call.execute ();
		if (response.isSuccessful ())
			return response.body ();
		
		assert response.errorBody () != null;
		String message = response.errorBody ().string ();
		logger.error (message);
		throw new IOException (message);
	}
}
