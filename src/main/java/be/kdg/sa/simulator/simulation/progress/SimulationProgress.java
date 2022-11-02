package be.kdg.sa.simulator.simulation.progress;

import be.kdg.sa.simulator.simulation.progress.listeners.SimulationProgressListener;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class SimulationProgress {

	private final List<Consumer<SimulationUpdate>> updateListeners = new ArrayList<> ();
	private final List<SimulationUpdate> updates = new ArrayList<> ();
	private final LocalDateTime startTime = LocalDateTime.now ();
	
	public void updateProgress (String message, int progressPercentage) {
		var duration = Duration.between (startTime, LocalDateTime.now ());
		var update = new SimulationUpdate (message, duration, progressPercentage);
		updateProgress (update);
	}
	
	private void updateProgress(SimulationUpdate update) {
		updates.add (update);
		updateListeners.forEach (listener -> listener.accept (update));
	}
	
	public void addUpdateListener(SimulationProgressListener listener) {
		updateListeners.add (listener::updateProgress);
	}
	
	public List<SimulationUpdate> getUpdates() {
		return updates;
	}

}
