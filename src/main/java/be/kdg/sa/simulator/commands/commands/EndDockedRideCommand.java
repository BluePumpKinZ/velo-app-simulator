package be.kdg.sa.simulator.commands.commands;

import be.kdg.sa.simulator.commands.SimulatorCommand;
import be.kdg.sa.simulator.commands.SimulatorCommandParams;
import be.kdg.sa.simulator.exceptions.CommandParamsParseException;
import be.kdg.sa.simulator.services.RideService;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.regex.Pattern;

@Component
public class EndDockedRideCommand extends SimulatorCommand<EndDockedRideCommand.EndDockedRideCommandParams> {
	
	private final RideService rideService;
	
	protected EndDockedRideCommand (RideService rideService) {
		super ("END_DOCKED_RIDE");
		this.rideService = rideService;
	}
	
	public static class EndDockedRideCommandParams implements SimulatorCommandParams {
		private static final Pattern PATTERN = Pattern.compile ("^\\(vehicleId: *(.*), *userId: *(.*), *defect: *(.*), *lockId: *(.*)\\)$");
		
		public int vehicleId;
		public int userId;
		public boolean defect;
		public int lockId;
		
		@Override
		public void setParams (String paramString) {
			var matcher = PATTERN.matcher (paramString);
			if (!matcher.find ())
				throw new CommandParamsParseException ("END_DOCKED_RIDE", paramString);
			
			vehicleId = Integer.parseInt (matcher.group (1));
			userId = Integer.parseInt (matcher.group (2));
			defect = Boolean.parseBoolean (matcher.group (3));
			lockId = Integer.parseInt (matcher.group (4));
		}
	}
	
	@Override
	protected String execute (EndDockedRideCommandParams params) throws IOException {
		rideService.endDockedRide (params.vehicleId, params.userId, params.defect, params.lockId);
		return "Ended docked ride";
	}
	
}
