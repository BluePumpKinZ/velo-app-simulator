package be.kdg.sa.simulator.simulation.random;

import be.kdg.sa.simulator.simulation.progress.SimulationLoadDelay;
import be.kdg.sa.simulator.simulation.random.generators.RandomSimulationDelayGenerator;
import be.kdg.sa.simulator.simulation.random.providers.RandomSimulationValidUserIdProvider;
import be.kdg.sa.simulator.simulation.random.providers.RandomSimulationValidVehicleIdProvider;
import be.kdg.sa.simulator.simulation.random.settings.RandomSimulationSettings;
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
	private final SimulationLoadDelay simulationLoadDelay;
	
	public RandomSimulatorExecuter (List<SimulationProgressListener> simulationProgressListeners, RandomSimulatorActionExecuter randomSimulatorActionExecuter, RandomSimulationDelayGenerator randomSimulationDelayGenerator, RandomSimulationFinalizer randomSimulationFinalizer, RandomSimulationValidUserIdProvider randomSimulationValidUserIdProvider, RandomSimulationValidVehicleIdProvider randomSimulationValidVehicleIdProvider, SimulationLoadDelay simulationLoadDelay) {
		this.simulationProgressListeners = simulationProgressListeners;
		this.randomSimulatorActionExecuter = randomSimulatorActionExecuter;
		this.randomSimulationDelayGenerator = randomSimulationDelayGenerator;
		this.randomSimulationFinalizer = randomSimulationFinalizer;
		this.randomSimulationValidUserIdProvider = randomSimulationValidUserIdProvider;
		this.randomSimulationValidVehicleIdProvider = randomSimulationValidVehicleIdProvider;
		this.simulationLoadDelay = simulationLoadDelay;
	}
	
	public void executeSimulation(RandomSimulationSettings randomSimulationSettings) {
		
		if (progress != null) {
			throw new IllegalStateException ("Simulation is already running");
		}
		
		progress = new SimulationProgress ();
		simulationProgressListeners.forEach (progress::addUpdateListener);
		
		new Thread (() -> executeSimulationThread (randomSimulationSettings, progress)).start ();
		
	}
	
	private void onSimulationFinished () {
		progress = null;
	}
	
	void executeSimulationThread (RandomSimulationSettings randomSimulationSettings, SimulationProgress progress) {
		try {
			
			simulationLoadDelay.delay ();
			
			var validUserIds = randomSimulationValidUserIdProvider.getValidSimulationUserIds ();
			var validVehicleIds = randomSimulationValidVehicleIdProvider.getValidSimulationVehicleIds ();
			var context = new RandomSimulationContext (randomSimulationSettings, validVehicleIds, validUserIds);
			progress.updateProgress ("Starting simulation", context.getPercentage ());
			
			while (context.canExecute ()) {
				try {
					var actionOutput = randomSimulatorActionExecuter.executeRandomAction (context);
					progress.updateProgress (actionOutput, context.getPercentage ());
					randomSimulationDelayGenerator.getRandomSimulationDelay (randomSimulationSettings).awaitDelay ();
				} catch (Exception e) {
					progress.updateProgress (e.getMessage (), context.getPercentage ());
				}
			}
			
			randomSimulationFinalizer.finalizeSimulation (context);
			
			progress.updateProgress ("Simulation finished", context.getPercentage ());
		} finally {
			onSimulationFinished ();
		}
	}
	
}
