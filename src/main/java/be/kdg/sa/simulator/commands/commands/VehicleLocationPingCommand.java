package be.kdg.sa.simulator.commands.commands;

import be.kdg.sa.simulator.commands.SimulatorCommand;
import be.kdg.sa.simulator.commands.SimulatorCommandParams;
import be.kdg.sa.simulator.models.vehicles.messages.VehicleLocationPingMessage;
import be.kdg.sa.simulator.services.VehicleService;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class VehicleLocationPingCommand extends SimulatorCommand<VehicleLocationPingCommand.VehicleLocationPingCommandParams> {
	
	private final VehicleService vehicleService;
	
	public VehicleLocationPingCommand (VehicleService vehicleService) {
		super("VEHICLE_LOCATION_PING");
		this.vehicleService = vehicleService;
	}
	
	public static class VehicleLocationPingCommandParams extends SimulatorCommandParams {
		
		public int vehicleId;
		public double latitude;
		public double longitude;
		
	}
	
	@Override
	protected String execute (VehicleLocationPingCommandParams params) throws IOException {
		var pingMessage = new VehicleLocationPingMessage (params.vehicleId, params.latitude, params.longitude);
		vehicleService.updateVehicleLocation(pingMessage);
		return "Vehicle location ping sent";
	}
	
}
