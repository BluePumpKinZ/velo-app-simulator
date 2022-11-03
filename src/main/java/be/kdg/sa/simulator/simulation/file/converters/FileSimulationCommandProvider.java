package be.kdg.sa.simulator.simulation.file.converters;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Component
public class FileSimulationCommandProvider {
	
	private final Logger logger = LoggerFactory.getLogger (FileSimulationCommandProvider.class);
	
	public List<String> getCommands (InputStream inputStream) {
		var commands = new BufferedReader (
				new InputStreamReader (inputStream, StandardCharsets.UTF_8))
				.lines ().toList ();
		
		logger.info (String.format ("Read %d commands from file", commands.size ()));
		return commands;
	}
	
}
