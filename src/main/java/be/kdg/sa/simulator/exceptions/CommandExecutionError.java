package be.kdg.sa.simulator.exceptions;

/**
 * Jonas Leijzen
 * 26/10/2022
 */
public class CommandExecutionError extends RuntimeException {
	public CommandExecutionError (String message) {
		super(message);
	}
}
