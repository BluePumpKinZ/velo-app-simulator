package be.kdg.sa.simulator.simulation.progress;

import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
@EqualsAndHashCode
public final class SimulationUpdate {
	private final String message;
	private final LocalDateTime timestamp;
	private final Duration duration;
	private final int progressPercentage;
	
	public SimulationUpdate (String message, Duration duration, int progressPercentage) {
		this.message = message;
		this.timestamp = LocalDateTime.now ();
		this.duration = duration;
		this.progressPercentage = progressPercentage;
	}
	
	@Override
	public String toString () {
		return String.format ("%s: %s (%ss) - %d%%", timestamp.format (DateTimeFormatter.ofPattern ("yyyy-MM-dd HH-mm-ss")), message, duration.getSeconds (), progressPercentage);
	}
	
	
}
