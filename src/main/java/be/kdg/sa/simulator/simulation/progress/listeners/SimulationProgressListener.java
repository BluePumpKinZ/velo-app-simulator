package be.kdg.sa.simulator.simulation.progress.listeners;

import be.kdg.sa.simulator.simulation.progress.SimulationUpdate;

public interface SimulationProgressListener {
	void updateProgress(SimulationUpdate update);
}
