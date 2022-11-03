package be.kdg.sa.simulator.controllers.websockets;

import be.kdg.sa.simulator.simulation.progress.SimulationUpdate;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class SimulationUpdateWebSocketController {
	
	private final SimpMessagingTemplate template;
	
	public SimulationUpdateWebSocketController (SimpMessagingTemplate template) {
		this.template = template;
	}
	
	@MessageMapping ("/simulationupdates")
	@SendTo ("/topic/simulationupdates")
	public SimulationUpdate sendSimulationUpdate (SimulationUpdate simulationUpdate) {
		this.template.convertAndSend ("/topic/simulationupdates", simulationUpdate);
		return simulationUpdate;
	}
	
}
