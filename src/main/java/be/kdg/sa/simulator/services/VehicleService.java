package be.kdg.sa.simulator.services;

import be.kdg.sa.simulator.messaging.senders.VehicleLocationPingJsonSender;
import be.kdg.sa.simulator.models.vehicles.messages.VehicleLocationPingMessage;
import org.springframework.stereotype.Service;

@Service
public class VehicleService {
	
	private final VehicleLocationPingJsonSender vehicleLocationPingJsonSender;
	
	public VehicleService (VehicleLocationPingJsonSender vehicleLocationPingJsonSender) {
		this.vehicleLocationPingJsonSender = vehicleLocationPingJsonSender;
	}
	
	public void updateVehicleLocation (VehicleLocationPingMessage pingMessage) {
		vehicleLocationPingJsonSender.send (pingMessage);
	}
}
