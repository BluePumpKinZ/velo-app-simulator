package be.kdg.sa.simulator.commands;

import org.springframework.stereotype.Component;

import java.util.Collection;


@Component
public class SimulatorCommandExecuter {
	
	private final Collection<SimulatorCommand<?>> commands;
	
	public SimulatorCommandExecuter (Collection<SimulatorCommand<?>> commands) {
		this.commands = commands;
	}
	
	public String executeCommand (String commandName, String params) {
		return commands.stream()
				.filter(command -> command.getCommandName().equalsIgnoreCase (commandName))
				.findFirst()
				.orElse(new UnknownCommandCommand(commandName))
				.execute(params);
	}
	
}
