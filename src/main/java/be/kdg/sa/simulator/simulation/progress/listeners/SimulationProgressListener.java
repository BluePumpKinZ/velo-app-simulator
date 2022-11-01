package be.kdg.sa.simulator.simulation.progress.listeners;

import be.kdg.sa.simulator.simulation.progress.SimulationUpdate;

/**
 * Jonas Leijzen
 * 1/11/2022
 */
public interface SimulationProgressListener {
	void updateProgress(SimulationUpdate update);
}
