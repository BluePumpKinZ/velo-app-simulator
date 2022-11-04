package be.kdg.sa.simulator.simulation.progress;

import org.springframework.stereotype.Component;

/**
 * The purpose of this class is to wait for a certain amount of time before starting the simulation
 * so that the webpage and therefore the websocket is loaded on the client.
 */
@Component
public class SimulationLoadDelay {
	
	private final int DELAY = 1000;
	
	public void delay () {
		try {
			Thread.sleep (DELAY);
		} catch (InterruptedException ignored) {}
	}
	
}
