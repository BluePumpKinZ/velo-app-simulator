package be.kdg.sa.simulator.commands;

import be.kdg.sa.simulator.commands.commands.UnknownCommandCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Collection;

@Component
public class SimulatorCommandExecuter {
	
	private final Collection<SimulatorCommand<?>> commands;
	private final Logger logger = LoggerFactory.getLogger (SimulatorCommandExecuter.class);
	
	public SimulatorCommandExecuter (Collection<SimulatorCommand<?>> commands) {
		this.commands = commands;
	}
	
	public String executeCommand (String commandName, String params) {
		try {
			return "SUCCES: " + commands.stream()
					.filter(command -> command.getCommandName().equalsIgnoreCase (commandName))
					.findFirst()
					.orElse(new UnknownCommandCommand (commandName))
					.execute(params);
		} catch (IOException e) {
			logger.error (e.getMessage(), e);
			return "ERROR: " + e.getMessage();
		}
	}
	
}
