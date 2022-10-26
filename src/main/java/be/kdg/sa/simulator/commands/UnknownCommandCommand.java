package be.kdg.sa.simulator.commands;

import org.springframework.stereotype.Component;


public final class UnknownCommandCommand extends SimulatorCommand<UnknownCommandCommand.UnknownCommandParams> {
	
	public static class UnknownCommandParams implements SimulatorCommandParams {
		
		@Override
		public SimulatorCommandParams setParams (String paramString) {
			return this;
		}
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