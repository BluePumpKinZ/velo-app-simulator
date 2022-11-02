package be.kdg.sa.simulator.simulation.random.actions;

import be.kdg.sa.simulator.simulation.random.RandomSimulationContext;
import org.springframework.stereotype.Component;

@Component
public class RandomSimulationStartRideAction implements RandomSimulationAction {
	
	@Override
	public int getWeight () {
		return 1;
	}
	
	@Override
	public boolean doesApply (RandomSimulationContext context) {
		return false;
	}
	
	@Override
	public String execute (RandomSimulationContext context) {
		return null;
	}
}
