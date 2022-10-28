package be.kdg.sa.simulator.services;

import be.kdg.sa.simulator.commands.SimulatorCommandExecuter;
import org.springframework.stereotype.Component;

import java.util.regex.Pattern;


@Component
public class CommandService {
	
	private final SimulatorCommandExecuter commandExecuter;
	private final Pattern commandPattern = Pattern.compile ("^([a-zA-Z_]+); ?(\\(.*\\)?|[0-9a-zA-Z]+)$");
	
	public CommandService (SimulatorCommandExecuter commandExecuter) {
		this.commandExecuter = commandExecuter;
	}
	
	public String executeCommand (String command) {
		command = command.trim ();
		var matcher = commandPattern.matcher (command);
		if (!matcher.find ())
			throw new IllegalArgumentException ("Invalid command: '" + command + "'");
		String commandName = matcher.group (1);
		String params = matcher.group (2);
		return commandExecuter.executeCommand(commandName, params);
	}
	
}
