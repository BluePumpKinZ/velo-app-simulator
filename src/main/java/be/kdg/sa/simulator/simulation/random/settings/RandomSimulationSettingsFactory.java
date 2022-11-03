package be.kdg.sa.simulator.simulation.random.settings;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ResourceUtils;

import java.io.*;
import java.util.Properties;

public class RandomSimulationSettingsFactory {
	
	private static final Logger logger = LoggerFactory.getLogger (RandomSimulationSettingsFactory.class);
	
	public static RandomSimulationSettings fromFile (String settingsPath) {
		try {
			File file = ResourceUtils.getFile (String.format ("classpath:%s", settingsPath));
			var simulationSettingValues = fromInputStream (new FileInputStream (file));
			logger.info (String.format ("Settings loaded from %s", settingsPath));
			return simulationSettingValues;
		} catch (IOException e) {
			logger.error ("Error while reading settings file", e);
			return new RandomSimulationSettings ();
		}
	}
	
	public static RandomSimulationSettings fromInputStream (InputStream inputStream) throws IOException {
		var simulationSettingValues = new RandomSimulationSettings ();
		Properties properties = new Properties ();
		properties.load (inputStream);
		loadPropertiesIntoSettingValues (simulationSettingValues, properties);
		inputStream.close ();
		return simulationSettingValues;
	}
	
	private static void loadPropertiesIntoSettingValues (RandomSimulationSettings simulationSettingValues, Properties properties) {
		simulationSettingValues.seconds = Integer.parseInt (properties.getProperty ("seconds"));
		simulationSettingValues.concurrentRides = Integer.parseInt (properties.getProperty ("concurrentRides"));
		simulationSettingValues.delayDuration = Integer.parseInt (properties.getProperty ("delayDuration"));
		simulationSettingValues.delayVariation = Integer.parseInt (properties.getProperty ("delayVariation"));
		simulationSettingValues.minLatitude = Double.parseDouble (properties.getProperty ("minLatitude"));
		simulationSettingValues.maxLatitude = Double.parseDouble (properties.getProperty ("maxLatitude"));
		simulationSettingValues.minLongitude = Double.parseDouble (properties.getProperty ("minLongitude"));
		simulationSettingValues.maxLongitude = Double.parseDouble (properties.getProperty ("maxLongitude"));
	}
	
}
