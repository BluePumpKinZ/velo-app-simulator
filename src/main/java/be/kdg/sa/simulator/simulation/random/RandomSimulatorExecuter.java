package be.kdg.sa.simulator.simulation.random;

import be.kdg.sa.simulator.simulation.random.generators.RandomSimulationDelayGenerator;
import be.kdg.sa.simulator.simulation.random.providers.RandomSimulationValidUserIdProvider;
import be.kdg.sa.simulator.simulation.random.providers.RandomSimulationValidVehicleIdProvider;
import be.kdg.sa.simulator.simulation.settings.SimulationSettings;
import be.kdg.sa.simulator.simulation.progress.SimulationProgress;
import be.kdg.sa.simulator.simulation.progress.listeners.SimulationProgressListener;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RandomSimulatorExecuter {
	
	private static SimulationProgress progress;
	
	private final List<SimulationProgressListener> simulationProgressListeners;
	private final RandomSimulatorActionExecuter randomSimulatorActionExecuter;
	private final RandomSimulationDelayGenerator randomSimulationDelayGenerator;
	private final RandomSimulationFinalizer randomSimulationFinalizer;
	private final RandomSimulationValidUserIdProvider randomSimulationValidUserIdProvider;
	private final RandomSimulationValidVehicleIdProvider randomSimulationValidVehicleIdProvider;
	
	public RandomSimulatorExecuter (List<SimulationProgressListener> simulationProgressListeners, RandomSimulatorActionExecuter randomSimulatorActionExecuter, RandomSimulationDelayGenerator randomSimulationDelayGenerator, RandomSimulationFinalizer randomSimulationFinalizer, RandomSimulationValidUserIdProvider randomSimulationValidUserIdProvider, RandomSimulationValidVehicleIdProvider randomSimulationValidVehicleIdProvider) {
		this.simulationProgressListeners = simulationProgressListeners;
		this.randomSimulatorActionExecuter = randomSimulatorActionExecuter;
		this.randomSimulationDelayGenerator = randomSimulationDelayGenerator;
		this.randomSimulationFinalizer = randomSimulationFinalizer;
		this.randomSimulationValidUserIdProvider = randomSimulationValidUserIdProvider;
		this.randomSimulationValidVehicleIdProvider = randomSimulationValidVehicleIdProvider;
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
		try {
			var validUserIds = randomSimulationValidUserIdProvider.getValidSimulationUserIds ();
			var validVehicleIds = randomSimulationValidVehicleIdProvider.getValidSimulationVehicleIds ();
			var context = new RandomSimulationContext (simulationSettings, validVehicleIds, validUserIds);
			progress.updateProgress ("Starting simulation", context.getPercentage ());
			
			while (context.canExecute ()) {
				var actionOutput = randomSimulatorActionExecuter.executeRandomAction (context);
				progress.updateProgress (actionOutput, context.getPercentage ());
				randomSimulationDelayGenerator.getRandomSimulationDelay (simulationSettings).awaitDelay ();
			}
			
			randomSimulationFinalizer.finalizeSimulation (context);
			
			progress.updateProgress ("Simulation finished", context.getPercentage ());
		} finally {
			onSimulationFinished ();
		}
	}
	
}
