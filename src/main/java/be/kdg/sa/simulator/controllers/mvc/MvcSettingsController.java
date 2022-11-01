package be.kdg.sa.simulator.controllers.mvc;

import be.kdg.sa.simulator.services.SettingService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping (path = "/settings")
public class MvcSettingsController {

	public final SettingService settingService;
	
	public MvcSettingsController (SettingService settingService) {
		this.settingService = settingService;
	}
	
	@GetMapping ()
	public String settings (Model model) {
		model.addAttribute ("simSettings", settingService.getSettingValues ());
		return "settings";
	}

}
