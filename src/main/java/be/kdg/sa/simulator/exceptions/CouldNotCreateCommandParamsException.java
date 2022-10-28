package be.kdg.sa.simulator.exceptions;

public class CouldNotCreateCommandParamsException extends RuntimeException {
	public CouldNotCreateCommandParamsException (Class<?> commandParamsClass, Exception e) {
		super ("Could not create command params of type " + commandParamsClass.getName(), e);
	}
}
