package be.kdg.sa.simulator.settings;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ResourceUtils;

import java.io.*;
import java.nio.file.Files;
import java.util.Properties;

/**
 * Jonas Leijzen
 * 1/11/2022
 */
public class SettingValuesFactory {
	
	private static final Logger logger = LoggerFactory.getLogger (SettingValuesFactory.class);
	
	public static SettingValues fromFile (String settingsPath) {
		SettingValues settingValues = new SettingValues ();
		try {
			File file = ResourceUtils.getFile (String.format ("classpath:%s", settingsPath));
			InputStream inputStream = new FileInputStream (file);
			Properties properties = new Properties ();
			properties.load (inputStream);
			loadPropertiesIntoSettingValues (settingValues, properties);
			inputStream.close ();
			logger.info (String.format ("Settings loaded from %s", settingsPath));
		} catch (IOException e) {
			logger.error ("Error while reading settings file", e);
		}
		return settingValues;
	}
	
	private static void loadPropertiesIntoSettingValues (SettingValues settingValues, Properties properties) {
		settingValues.seconds = Integer.parseInt (properties.getProperty ("seconds"));
		settingValues.concurrentRides = Integer.parseInt (properties.getProperty ("concurrentRides"));
		settingValues.delayDuration = Integer.parseInt (properties.getProperty ("delayDuration"));
		settingValues.delayVariation = Integer.parseInt (properties.getProperty ("delayVariation"));
		settingValues.minLatitude = Double.parseDouble (properties.getProperty ("minLatitude"));
		settingValues.maxLatitude = Double.parseDouble (properties.getProperty ("maxLatitude"));
		settingValues.minLongitude = Double.parseDouble (properties.getProperty ("minLongitude"));
		settingValues.maxLongitude = Double.parseDouble (properties.getProperty ("maxLongitude"));
	}
	
}
