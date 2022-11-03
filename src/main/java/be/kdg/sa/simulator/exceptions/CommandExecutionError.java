package be.kdg.sa.simulator.exceptions;

public class CommandExecutionError extends RuntimeException {
	public CommandExecutionError (String message) {
		super(message);
	}
}
