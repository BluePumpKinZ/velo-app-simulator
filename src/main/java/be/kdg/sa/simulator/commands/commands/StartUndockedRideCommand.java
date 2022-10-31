package be.kdg.sa.simulator.commands.commands;

import be.kdg.sa.simulator.commands.SimulatorCommand;
import be.kdg.sa.simulator.commands.SimulatorCommandParams;
import be.kdg.sa.simulator.exceptions.CommandParamsParseException;
import be.kdg.sa.simulator.services.RideService;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.regex.Pattern;

@Component
public class StartUndockedRideCommand extends SimulatorCommand<StartUndockedRideCommand.StartUndockedRideCommandParams> {
	
	private final RideService rideService;
	
	protected StartUndockedRideCommand (RideService rideService) {
		super ("START_UNDOCKED_RIDE");
		this.rideService = rideService;
	}
	
	public static class StartUndockedRideCommandParams extends SimulatorCommandParams {
		
		public int userId;
		public int vehicleId;
		public double latitude;
		public double longitude;
		
	}
	
	@Override
	protected String execute (StartUndockedRideCommandParams params) throws IOException {
		rideService.startUndockedRide (params.vehicleId, params.userId, params.latitude, params.longitude);
		return "Started undocked ride";
	}
	
}
