package be.kdg.sa.simulator.simulation.file;

import be.kdg.sa.simulator.services.CommandService;
import be.kdg.sa.simulator.simulation.file.converters.FileSimulationCommandProvider;
import be.kdg.sa.simulator.simulation.progress.SimulationLoadDelay;
import be.kdg.sa.simulator.simulation.progress.SimulationProgress;
import be.kdg.sa.simulator.simulation.progress.listeners.SimulationProgressListener;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.util.List;

@Component
public class FileSimulationExecuter {
	
	private static SimulationProgress progress;
	
	private final List<SimulationProgressListener> simulationProgressListeners;
	private final CommandService commandService;
	private final FileSimulationCommandProvider fileSimulationCommandProvider;
	private final SimulationLoadDelay simulationLoadDelay;
	
	public FileSimulationExecuter (CommandService commandService, List<SimulationProgressListener> simulationProgressListeners, FileSimulationCommandProvider fileSimulationCommandProvider, SimulationLoadDelay simulationLoadDelay) {
		this.commandService = commandService;
		this.simulationProgressListeners = simulationProgressListeners;
		this.fileSimulationCommandProvider = fileSimulationCommandProvider;
		this.simulationLoadDelay = simulationLoadDelay;
	}
	
	public void executeSimulation (InputStream fileInputStream) {
		
		if (progress != null) {
			throw new IllegalStateException ("Simulation is already running");
		}
		
		progress = new SimulationProgress ();
		simulationProgressListeners.forEach (progress::addUpdateListener);
		
		new Thread (() -> executeSimulationThread (fileInputStream, progress)).start ();
		
	}
	
	private void onSimulationFinished () {
		progress = null;
	}
	
	void executeSimulationThread (InputStream fileInputStream, SimulationProgress progress) {
		try {
			
			simulationLoadDelay.delay ();
			
			var allCommands = fileSimulationCommandProvider.getCommands (fileInputStream);
			var context = new FileSimulationContext (allCommands);
			progress.updateProgress ("Starting simulation", context.getPercentage ());
			
			while (context.canExecute ()) {
				try {
					var command = context.getNextCommand ();
					var commandResult = commandService.executeCommand (command);
					progress.updateProgress (commandResult, context.getPercentage ());
				} catch (Exception e) {
					progress.updateProgress (e.getMessage (), context.getPercentage ());
				}
			}
			
			progress.updateProgress ("Finished simulation", context.getPercentage ());
		} finally {
			onSimulationFinished ();
		}
	}
	
}
