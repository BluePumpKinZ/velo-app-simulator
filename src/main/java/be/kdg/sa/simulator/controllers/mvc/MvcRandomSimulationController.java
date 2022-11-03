package be.kdg.sa.simulator.controllers.mvc;

import be.kdg.sa.simulator.services.SettingService;
import be.kdg.sa.simulator.services.SimulationService;
import be.kdg.sa.simulator.simulation.settings.SimulationSettings;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping (path = "/random-simulation")
public class MvcRandomSimulationController {

	private final SettingService settingService;
	private final SimulationService randomSimulationService;
	
	public MvcRandomSimulationController (SettingService settingService, SimulationService randomSimulationService) {
		this.settingService = settingService;
		this.randomSimulationService = randomSimulationService;
	}
	
	@GetMapping ()
	public String randomSimulation (Model model) {
		model.addAttribute ("simSettings", settingService.getSimulationSettings ());
		return "random-simulation";
	}
	
	@PostMapping (path = "/update")
	public String executeSimulation (@ModelAttribute("simSettings") SimulationSettings simSettings, BindingResult result, Model model) {
		if (result.hasErrors ())
			return "random-simulation";
		settingService.setSimulationSettings (simSettings);
		randomSimulationService.startSimulation ();
		return "redirect:/random-simulation";
	}

}
