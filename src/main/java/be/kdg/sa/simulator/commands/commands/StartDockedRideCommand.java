package be.kdg.sa.simulator.commands.commands;

import be.kdg.sa.simulator.commands.SimulatorCommand;
import be.kdg.sa.simulator.commands.SimulatorCommandParams;
import be.kdg.sa.simulator.exceptions.CommandParamsParseException;
import be.kdg.sa.simulator.services.RideService;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.regex.Pattern;


@Component
public class StartDockedRideCommand extends SimulatorCommand<StartDockedRideCommand.StartDockedRideCommandParams> {
	
	private final RideService rideService;
	
	protected StartDockedRideCommand (RideService rideService) {
		super ("START_DOCKED_RIDE");
		this.rideService = rideService;
	}
	
	public static class StartDockedRideCommandParams implements SimulatorCommandParams {
		private static final Pattern PATTERN = Pattern.compile ("^\\(stationId: *(.*), *userId: *(.*)\\)$");
		public int stationId;
		public int userId;
		
		@Override
		public void setParams (String paramString) {
			var matcher = PATTERN.matcher (paramString);
			if (!matcher.find ())
				throw new CommandParamsParseException ("START_DOCKED_RIDE", paramString);
			
			stationId = Integer.parseInt (matcher.group (1));
			userId = Integer.parseInt (matcher.group (2));
		}
	}
	
	@Override
	protected String execute (StartDockedRideCommandParams params) throws IOException {
		var lockId = rideService.startDockedRide (params.stationId, params.userId);
		return "Started docked ride from lock: " + lockId;
	}
	
	
}
