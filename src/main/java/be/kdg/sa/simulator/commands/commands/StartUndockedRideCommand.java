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
	
	public static class StartUndockedRideCommandParams implements SimulatorCommandParams {
		
		private static final Pattern PATTERN = Pattern.compile ("^\\(userId: *(.*), *vehicleId: *(.*), *latitude: *(.*), *longitude: *(.*)\\)$");
		
		public int userId;
		public int vehicleId;
		public double latitude;
		public double longitude;
		
		@Override
		public void setParams (String paramString) {
			var matcher = PATTERN.matcher (paramString);
			if (!matcher.find ())
				throw new CommandParamsParseException ("START_UNDOCKED_RIDE", paramString);
			
			userId = Integer.parseInt (matcher.group (1));
			vehicleId = Integer.parseInt (matcher.group (2));
			latitude = Double.parseDouble (matcher.group (3));
			longitude = Double.parseDouble (matcher.group (4));
		}
	}
	
	@Override
	protected String execute (StartUndockedRideCommandParams params) throws IOException {
		rideService.startUndockedRide (params.userId, params.vehicleId, params.latitude, params.longitude);
		return "Started undocked ride";
	}
	
}
