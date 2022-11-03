package be.kdg.sa.simulator.simulation.file;

import java.io.IOException;
import java.io.InputStream;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class FileSimulationContext {
	
	private final LocalDateTime startTime;
	private final Integer totalLines;
	private final Queue<String> commands;
	
	public FileSimulationContext (List<String> commands) {
		startTime = LocalDateTime.now();
		this.commands = new LinkedList<> (commands);
		totalLines = commands.size ();
	}
	
	public boolean canExecute () {
		return !commands.isEmpty ();
	}
	
	public String getNextCommand () {
		return commands.poll ();
	}
	
	public int getPercentage () {
		return 100 * (totalLines - commands.size ()) / totalLines;
	}
}
