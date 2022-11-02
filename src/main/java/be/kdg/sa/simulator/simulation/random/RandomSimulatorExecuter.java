package be.kdg.sa.simulator.simulation.random;

import be.kdg.sa.simulator.simulation.random.actions.RandomSimulationAction;
import be.kdg.sa.simulator.simulation.settings.SimulationSettings;
import be.kdg.sa.simulator.simulation.progress.SimulationProgress;
import be.kdg.sa.simulator.simulation.progress.listeners.SimulationProgressListener;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RandomSimulatorExecuter {
	
	private static SimulationProgress progress = new SimulationProgress ();
	
	private final List<SimulationProgressListener> simulationProgressListeners;
	private final RandomSimulatorActionExecuter randomSimulatorActionExecuter;
	
	public RandomSimulatorExecuter (List<SimulationProgressListener> simulationProgressListeners, RandomSimulatorActionExecuter randomSimulatorActionExecuter) {
		this.simulationProgressListeners = simulationProgressListeners;
		this.randomSimulatorActionExecuter = randomSimulatorActionExecuter;
	}
	
	public void executeSimulation(SimulationSettings simulationSettings) {
		
		if (progress != null) {
			throw new IllegalStateException ("Simulation is already running");
		}
		
		progress = new SimulationProgress ();
		simulationProgressListeners.forEach (progress::addUpdateListener);
		
		new Thread (() -> executeSimulationThread (simulationSettings, progress)).start ();
		
	}
	
	private void onSimulationFinished () {
		progress = null;
	}
	
	void executeSimulationThread (SimulationSettings simulationSettings, SimulationProgress progress) {
		var context = new RandomSimulationContext (simulationSettings);
		progress.updateProgress ("Starting simulation", context.getPercentage ());
		
		while (context.canExecute ()) {
			randomSimulatorActionExecuter.executeRandomAction (context);
		}
		
		
		progress.updateProgress ("Simulation finished", context.getPercentage ());
		onSimulationFinished ();
	}
	
}
