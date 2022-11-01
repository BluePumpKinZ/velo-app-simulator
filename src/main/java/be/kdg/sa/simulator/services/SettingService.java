package be.kdg.sa.simulator.services;

import be.kdg.sa.simulator.configuration.SettingsProperties;
import be.kdg.sa.simulator.settings.SettingValues;
import be.kdg.sa.simulator.settings.SettingValuesFactory;
import org.springframework.stereotype.Service;

@Service
public class SettingService {

	private final SettingsProperties settingsProperties;
	private static SettingValues settingValues;
	
	public SettingService (SettingsProperties settingsProperties) {
		this.settingsProperties = settingsProperties;
	}
	
	public SettingValues getSettingValues () {
		if (settingValues == null)
			settingValues = SettingValuesFactory.fromFile(settingsProperties.getFile ());
		
		return settingValues;
	}
	
	public void setSettingValues (SettingValues settingValues) {
		SettingService.settingValues = settingValues;
	}
	

}
