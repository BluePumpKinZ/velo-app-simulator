package be.kdg.sa.simulator.controllers.mvc;

import be.kdg.sa.simulator.services.RandomSimulatorSettingService;
import be.kdg.sa.simulator.services.SimulationService;
import be.kdg.sa.simulator.simulation.random.settings.RandomSimulationSettings;
import be.kdg.sa.simulator.simulation.random.settings.RandomSimulationSettingsFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

@Controller
@RequestMapping (path = "/random-simulation")
public class MvcRandomSimulationController {

	private final RandomSimulatorSettingService randomSimulatorSettingService;
	private final SimulationService randomSimulationService;
	private final Logger logger = LoggerFactory.getLogger (MvcRandomSimulationController.class);
	
	public MvcRandomSimulationController (RandomSimulatorSettingService randomSimulatorSettingService, SimulationService randomSimulationService) {
		this.randomSimulatorSettingService = randomSimulatorSettingService;
		this.randomSimulationService = randomSimulationService;
	}
	
	@GetMapping ()
	public String randomSimulation (Model model) {
		model.addAttribute ("simSettings", randomSimulatorSettingService.getSimulationSettings ());
		return "random-simulation";
	}
	
	@PostMapping (path = "/updatesettings/file")
	public String updateSettingsFromFile (@RequestParam("file")MultipartFile file) {
		String fileName = StringUtils.cleanPath (Objects.requireNonNull (file.getOriginalFilename ()));
		
		try {
			randomSimulatorSettingService.setSimulationSettings (RandomSimulationSettingsFactory.fromInputStream (file.getInputStream ()));
			logger.info ("Settings updated from file: " + fileName);
		} catch (IOException e) {
			logger.error ("Error while reading file: " + fileName, e);
		}
		
		return "redirect:/random-simulation";
	}
	
	@PostMapping (path = "/updatesettings/values")
	public String executeSimulation (@ModelAttribute("simSettings") RandomSimulationSettings simSettings, BindingResult result, Model model) {
		if (result.hasErrors ())
			return "random-simulation";
		randomSimulatorSettingService.setSimulationSettings (simSettings);
		randomSimulationService.startSimulation ();
		return "redirect:/random-simulation";
	}

}
