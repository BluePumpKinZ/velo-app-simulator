package be.kdg.sa.simulator.simulation.random;

import be.kdg.sa.simulator.simulation.random.actions.RandomSimulationEndRideAction;
import org.springframework.stereotype.Component;

import java.util.stream.Stream;

@Component
public class RandomSimulationFinalizer {
	
	private final RandomSimulationEndRideAction endRideAction;
	
	public RandomSimulationFinalizer (RandomSimulationEndRideAction endRideAction) {
		this.endRideAction = endRideAction;
	}
	
	public void finalizeSimulation (RandomSimulationContext context) {
		endAllOpenRides (context);
	}
	
	private void endAllOpenRides (RandomSimulationContext context) {
		Stream.generate (() -> endRideAction)
				.limit (context.getRideCount ())
				.forEach (action -> action.execute (context));
	}
	
}
