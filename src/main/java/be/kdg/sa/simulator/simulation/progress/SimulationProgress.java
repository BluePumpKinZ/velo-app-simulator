package be.kdg.sa.simulator.simulation.progress;

import be.kdg.sa.simulator.simulation.progress.listeners.SimulationProgressListener;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * Jonas Leijzen
 * 1/11/2022
 */
public class SimulationProgress {

	private final List<Consumer<SimulationUpdate>> updateListeners = new ArrayList<> ();
	private final List<SimulationUpdate> updates = new ArrayList<> ();
	
	public void updateProgress(SimulationUpdate update) {
		updates.add (update);
		updateListeners.forEach (listener -> listener.accept (update));
	}
	
	public void addUpdateListener(SimulationProgressListener listener) {
		updateListeners.add (listener::updateProgress);
	}
	
	public List<SimulationUpdate> getUpdates() {
		return updates;
	}
	
	public void reset() {
		updateListeners.clear ();
		updates.clear ();
	}

}
