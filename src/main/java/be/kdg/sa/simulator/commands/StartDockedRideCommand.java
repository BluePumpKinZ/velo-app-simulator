package be.kdg.sa.simulator.commands;

import be.kdg.sa.simulator.exceptions.CommandParamsParseException;
import be.kdg.sa.simulator.services.RideService;
import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

/**
 * Jonas Leijzen
 * 24/10/2022
 */
@Component
public class StartDockedRideCommand extends SimulatorCommand<StartDockedRideCommand.StartDockedRideCommandParams> {
	
	private final RideService rideService;
	
	protected StartDockedRideCommand (RideService rideService) {
		super ("START_DOCKED_RIDE");
		this.rideService = rideService;
	}
	
	public static class StartDockedRideCommandParams implements SimulatorCommandParams {
		public int stationId;
		public int userId;
		
		private static final Pattern PATTERN = Pattern.compile ("^\\(stationId: *(.*), *userId: *(.*)\\)$");
		// (stationId: 5, userId: 7)
		
		@Override
		public SimulatorCommandParams setParams (String paramString) {
			var matcher = PATTERN.matcher (paramString);
			if (!matcher.find ())
				throw new CommandParamsParseException ("START_DOCKED_RIDE", paramString);
			
			stationId = Integer.parseInt (matcher.group (1));
			userId = Integer.parseInt (matcher.group (2));
			return this;
		}
	}
	
	@Override
	protected String execute (StartDockedRideCommandParams params) {
		var lockId = rideService.startRide (params.stationId, params.userId);
		return "Started ride with from lock: " + lockId;
	}
	
	
}
