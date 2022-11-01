package be.kdg.sa.simulator.services;

import be.kdg.sa.simulator.settings.SettingValues;
import be.kdg.sa.simulator.settings.SettingValuesFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class SettingService {

	private static SettingValues settingValues;
	@Value ("${settings.file}")
	private String defaultSettingsPath = "default.simsettings";
	
	public SettingValues getSettingValues () {
		if (settingValues == null)
			settingValues = SettingValuesFactory.fromFile(defaultSettingsPath);
		
		return settingValues;
	}
	
	public void setSettingValues (SettingValues settingValues) {
		SettingService.settingValues = settingValues;
	}
	

}
