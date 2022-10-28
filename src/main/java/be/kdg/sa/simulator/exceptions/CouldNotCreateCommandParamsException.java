package be.kdg.sa.simulator.exceptions;

import be.kdg.sa.simulator.commands.SimulatorCommandParams;

public class CouldNotCreateCommandParamsException extends RuntimeException {
	public CouldNotCreateCommandParamsException (Class<?> commandParamsClass, Exception e) {
		super ("Could not create command params of type " + commandParamsClass.getName(), e);
	}
}
