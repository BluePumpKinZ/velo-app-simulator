package be.kdg.sa.simulator.settings;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SettingValues {
	
	public int seconds;
	public int concurrentRides;
	public int delayDuration;
	public int delayVariation;
	public double minLatitude;
	public double maxLatitude;
	public double minLongitude;
	public double maxLongitude;
	
	public SettingValues() {
	}
	
}
