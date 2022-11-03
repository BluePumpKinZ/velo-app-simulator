package be.kdg.sa.simulator.simulation.progress.listeners;

import be.kdg.sa.simulator.controllers.websockets.SimulationUpdateWebSocketController;
import be.kdg.sa.simulator.simulation.progress.SimulationUpdate;
import org.springframework.stereotype.Component;

@Component
public class WebSocketProgressListener implements SimulationProgressListener {
	private final SimulationUpdateWebSocketController simulationUpdateWebSocketController;

	public WebSocketProgressListener(SimulationUpdateWebSocketController simulationUpdateWebSocketController) {
		this.simulationUpdateWebSocketController = simulationUpdateWebSocketController;
	}
	
	@Override
	public void updateProgress (SimulationUpdate update) {
		simulationUpdateWebSocketController.sendSimulationUpdate (update);
	}
}
