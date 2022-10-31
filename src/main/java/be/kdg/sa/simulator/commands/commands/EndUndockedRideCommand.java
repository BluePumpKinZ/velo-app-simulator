package be.kdg.sa.simulator.commands.commands;

import be.kdg.sa.simulator.commands.SimulatorCommand;
import be.kdg.sa.simulator.commands.SimulatorCommandParams;
import be.kdg.sa.simulator.exceptions.CommandParamsParseException;
import be.kdg.sa.simulator.services.RideService;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.regex.Pattern;

@Component
public class EndUndockedRideCommand extends SimulatorCommand<EndUndockedRideCommand.EndUndockedRideCommandParams> {
	
	private final RideService rideService;
	
	public EndUndockedRideCommand (RideService rideService) {
		super ("END_UNDOCKED_RIDE");
		this.rideService = rideService;
	}
	
	public static class EndUndockedRideCommandParams extends SimulatorCommandParams {
		
		public int vehicleId;
		public int userId;
		public boolean defect;
		public double latitude;
		public double longitude;
		
	}
	
	@Override
	protected String execute (EndUndockedRideCommand.EndUndockedRideCommandParams params) throws IOException {
		rideService.endUndockedRide (params.vehicleId, params.userId, params.defect, params.latitude, params.longitude);
		return "Ended undocked ride";
	}
	
}
