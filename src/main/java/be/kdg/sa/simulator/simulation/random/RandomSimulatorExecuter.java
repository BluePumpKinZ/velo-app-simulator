package be.kdg.sa.simulator.simulation.random;

import be.kdg.sa.simulator.simulation.settings.SimulationSettings;
import be.kdg.sa.simulator.simulation.progress.SimulationProgress;
import be.kdg.sa.simulator.simulation.progress.listeners.SimulationProgressListener;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RandomSimulatorExecuter {
	
	private static final SimulationProgress progress = new SimulationProgress ();
	
	private final List<SimulationProgressListener> simulationProgressListeners;
	
	public RandomSimulatorExecuter (List<SimulationProgressListener> simulationProgressListeners) {
		this.simulationProgressListeners = simulationProgressListeners;
	}
	
	public void executeSimulation(SimulationSettings simulationSettings) {
		
		if (progress.getUpdates ().size () > 0) {
			throw new IllegalStateException ("Simulation is already running");
		}
		
		SimulationProgress progress = new SimulationProgress ();
		simulationProgressListeners.forEach (progress::addUpdateListener);
		
		
		
	}
	
	void executeSimulationThread (SimulationProgress progress) {
	
		progress.reset ();
		
	}
	
}
