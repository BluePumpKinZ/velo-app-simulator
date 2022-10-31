package be.kdg.sa.simulator.commands.commands;


import be.kdg.sa.simulator.commands.SimulatorCommand;
import be.kdg.sa.simulator.commands.SimulatorCommandParams;

public final class UnknownCommandCommand extends SimulatorCommand<UnknownCommandCommand.UnknownCommandParams> {
	
	public static class UnknownCommandParams extends SimulatorCommandParams {
	
	}
	
	private final String unknownCommandName;
	
	public UnknownCommandCommand () {
		super ("unknown");
		unknownCommandName = "";
	}
	
	public UnknownCommandCommand (String unknownCommandName) {
		super ("unknown");
		this.unknownCommandName = unknownCommandName;
	}
	
	@Override
	public String execute (UnknownCommandParams params) {
		return String.format ("Unknown command: %s", unknownCommandName);
	}
	
}