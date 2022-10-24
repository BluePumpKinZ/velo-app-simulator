package be.kdg.sa.simulator.commands;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * Jonas Leijzen
 * 24/10/2022
 */
public abstract class SimulatorCommand<TParams extends SimulatorCommandParams> {
	
	private final String commandName;
	private final Logger logger = LoggerFactory.getLogger (SimulatorCommand.class);
	
	protected SimulatorCommand (String commandName) {
		this.commandName = commandName;
	}
	
	String getCommandName () {
		return commandName;
	}
	
	public String execute(String paramString) {
		var params = createParams();
		return execute (params);
	}
	
	protected abstract String execute (TParams params);
	
	@SuppressWarnings ("unchecked")
	private TParams createParams () {
		// Create a new instance of the generic type TParams
		Type type = ((ParameterizedType)this.getClass ().getGenericSuperclass ()).getActualTypeArguments ()[0];
		try {
			return (TParams) type.getClass ().getConstructor ().newInstance ();
		} catch (InstantiationException | NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
			logger.error ("Could not create instance of type " + type.getTypeName () + " while creating command arguments", e);
		}
		return null;
	}
	
}
