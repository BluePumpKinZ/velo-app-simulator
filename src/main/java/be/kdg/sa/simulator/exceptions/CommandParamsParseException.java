package be.kdg.sa.simulator.exceptions;

public class CommandParamsParseException extends RuntimeException {
	public CommandParamsParseException (String commandName, String params) {
		super (String.format ("Could not parse params for command %s: %s", commandName, params));
	}
}
