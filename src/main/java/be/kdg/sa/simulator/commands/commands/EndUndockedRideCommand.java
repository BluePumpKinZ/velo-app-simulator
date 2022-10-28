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
	
	public static class EndUndockedRideCommandParams implements SimulatorCommandParams {
		
		private static final Pattern PATTERN = Pattern.compile ("^\\(vehicleId: *(.*), *userId: *(.*), *defect: *(.*), *longitude: *(.*), *latitude: *(.*)\\)$");
		
		public int vehicleId;
		public int userId;
		public boolean defect;
		public double latitude;
		public double longitude;
		
		@Override
		public void setParams (String paramString) {
			var matcher = PATTERN.matcher (paramString);
			if (!matcher.find ())
				throw new CommandParamsParseException ("END_UNDOCKED_RIDE", paramString);
			
			vehicleId = Integer.parseInt (matcher.group (1));
			userId = Integer.parseInt (matcher.group (2));
			defect = Boolean.parseBoolean (matcher.group (3));
			latitude = Double.parseDouble (matcher.group (4));
			longitude = Double.parseDouble (matcher.group (5));
		}
	}
	
	@Override
	protected String execute (EndUndockedRideCommand.EndUndockedRideCommandParams params) throws IOException {
		rideService.endUndockedRide (params.vehicleId, params.userId, params.defect, params.latitude, params.longitude);
		return "Ended undocked ride";
	}
	
}
