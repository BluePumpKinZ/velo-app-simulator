package be.kdg.sa.simulator.simulation.progress;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
@Setter
@EqualsAndHashCode
public final class SimulationUpdate {
	private String message;
	private LocalDateTime timestamp;
	private Duration duration;
	private int progressPercentage;
	
	public SimulationUpdate () {
	
	}
	
	public SimulationUpdate (String message, Duration duration, int progressPercentage) {
		this.message = message;
		this.timestamp = LocalDateTime.now ();
		this.duration = duration;
		this.progressPercentage = Math.min (Math.max (progressPercentage, 0), 100);
	}
	
	@Override
	public String toString () {
		return String.format ("%s: %s (%ss) - %d%%", timestamp.format (DateTimeFormatter.ofPattern ("yyyy-MM-dd HH-mm-ss")), message, duration.getSeconds (), progressPercentage);
	}
	
	
}
