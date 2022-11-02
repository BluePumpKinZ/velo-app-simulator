package be.kdg.sa.simulator.simulation.random.actions;

import be.kdg.sa.simulator.simulation.random.RandomSimulationContext;

public interface RandomSimulationAction {
	
	int getWeight ();
	
	boolean doesApply(RandomSimulationContext context);
	
	String execute(RandomSimulationContext context);
	
}
