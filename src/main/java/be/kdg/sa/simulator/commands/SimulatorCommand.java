package be.kdg.sa.simulator.commands;

import be.kdg.sa.simulator.commands.commands.UnknownCommandCommand;
import be.kdg.sa.simulator.exceptions.CouldNotCreateCommandParamsException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public abstract class SimulatorCommand<TParams extends SimulatorCommandParams> {
	
	private final String commandName;
	private final Logger logger = LoggerFactory.getLogger (SimulatorCommand.class);
	
	protected SimulatorCommand (String commandName) {
		this.commandName = commandName;
	}
	
	String getCommandName () {
		return commandName;
	}
	
	public String execute(String paramString) throws IOException {
		var params = createParams();
		if (!(params instanceof UnknownCommandCommand.UnknownCommandParams))
			params.setParams (paramString);
		return execute (params);
	}
	
	protected abstract String execute (TParams params) throws IOException;
	
	@SuppressWarnings ("unchecked")
	private TParams createParams () {
		// Create a new instance of the generic type TParams
		Type type = ((ParameterizedType)this.getClass ().getGenericSuperclass ()).getActualTypeArguments ()[0];
		try {
			//return (TParams) type.getClass ().getConstructor ().newInstance ();
			var clazz = (Class<TParams>) type;
			var constructor = clazz.getConstructor ();
			var instance = constructor.newInstance ();
			return (TParams) instance;
		} catch (InstantiationException | NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
			logger.error ("Could not create instance of type " + type.getTypeName () + " while creating command arguments", e);
			throw new CouldNotCreateCommandParamsException (type.getClass (), e);
		}
	}
	
}
