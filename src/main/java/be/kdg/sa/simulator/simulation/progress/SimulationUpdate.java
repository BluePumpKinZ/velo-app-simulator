package be.kdg.sa.simulator.simulation.progress;

import java.time.Duration;
import java.time.LocalDateTime;

public record SimulationUpdate(String message, LocalDateTime timestamp, Duration duration, int progressPercentage) {
	
	@Override
	public String toString () {
		return String.format ("%s: %s (%s) - %d%%", timestamp, message, duration, progressPercentage);
	}
}
