package be.kdg.sa.simulator.services;

import be.kdg.sa.simulator.commands.SimulatorCommandExecuter;
import org.springframework.stereotype.Component;


@Component
public class CommandService {
	
	private final SimulatorCommandExecuter commandExecuter;
	
	public CommandService (SimulatorCommandExecuter commandExecuter) {
		this.commandExecuter = commandExecuter;
	}
	
	public String executeCommand (String commandName, String params) {
		return commandExecuter.executeCommand(commandName, params);
	}
	
}
