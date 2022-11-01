package be.kdg.sa.simulator.simulation.progress.listeners;

import be.kdg.sa.simulator.simulation.progress.SimulationUpdate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class LoggerSimulationProgressListener implements SimulationProgressListener {

	private final Logger logger = LoggerFactory.getLogger (LoggerSimulationProgressListener.class);
	
	@Override
	public void updateProgress (SimulationUpdate update) {
		logger.info (update.toString ());
	}
}
