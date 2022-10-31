package be.kdg.sa.simulator.commands;


import be.kdg.sa.simulator.exceptions.CommandParamsParseException;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.regex.Pattern;

public abstract class SimulatorCommandParams {
	
	@SuppressWarnings ("RegExpRedundantEscape")
	private final Pattern paramsPattern = Pattern.compile ("^([A-Za-z0-9]+|\\(([A-Za-z]+: *[A-Za-z\\.0-9]+, *)+[A-Za-z]+: *[A-Za-z\\.0-9]+\\))$");
	
	private final Map<Class<?>, Function<String, ?>> paramParsers = Map.of (
			String.class, (Function<String, String>) s -> s,
			Integer.class, Integer::parseInt,
			int.class, Integer::parseInt,
			Long.class, Long::parseLong,
			long.class, Long::parseLong,
			Double.class, Double::parseDouble,
			double.class, Double::parseDouble,
			Boolean.class, Boolean::parseBoolean,
			boolean.class, Boolean::parseBoolean
	);
	
	protected void setParams (String paramString) {
	
		var matcher = paramsPattern.matcher (paramString);
		if (!matcher.matches ()) {
			throw new IllegalArgumentException ("Invalid parameter format");
		}
		
		if (paramString.startsWith ("(")) {
			paramString = paramString.substring (1, paramString.length () - 1);
			var params = paramString.split (",");
			for (var param : params) {
				var keyValue = param.split (":");
				setParam (keyValue[0].trim (), keyValue[1].trim ());
			}
		} else {
			setParam (paramString);
		}
	
	}
	
	private void setParam (String paramValue) {
		setParam (this.getClass ().getDeclaredFields ()[0], paramValue);
	}
	
	private void setParam (String paramName, String paramValue) {
		try {
			var field = this.getClass ().getDeclaredField (paramName);
			setParam (field, paramValue);
		} catch (NoSuchFieldException e) {
			throw new CommandParamsParseException (String.format ("Invalid parameter name '%s'", paramName));
		}
	}
	
	private void setParam (Field paramField, String paramValue) {
		paramField.setAccessible (true);
		var paramType = paramField.getType ();
		paramParsers.forEach ((type, parser) -> {
			if (paramType.isAssignableFrom (type)) {
				try {
					paramField.set (this, parser.apply (paramValue));
				} catch (IllegalAccessException e) {
					throw new CommandParamsParseException (String.format ("Invalid parameter value '%s'", paramValue));
				}
			}
		});
		paramField.setAccessible (false);
	}
	
}
