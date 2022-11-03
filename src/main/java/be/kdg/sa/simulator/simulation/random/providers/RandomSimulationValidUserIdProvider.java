package be.kdg.sa.simulator.simulation.random.providers;

import be.kdg.sa.simulator.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RandomSimulationValidUserIdProvider {
	
	private final UserService userService;
	private final Logger logger = LoggerFactory.getLogger (RandomSimulationValidUserIdProvider.class);
	
	public RandomSimulationValidUserIdProvider (UserService userService) {
		this.userService = userService;
	}
	
	public List<Integer> getValidSimulationUserIds () {
		try {
			return userService.getValidSimulationUserIds ();
		} catch (Exception e) {
			logger.error (e.getMessage (), e);
		}
		return List.of ();
	}
	
}
