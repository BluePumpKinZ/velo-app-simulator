package be.kdg.sa.simulator.controllers.mvc;

import be.kdg.sa.simulator.services.SettingService;
import be.kdg.sa.simulator.services.SimulationService;
import be.kdg.sa.simulator.simulation.settings.SimulationSettings;
import be.kdg.sa.simulator.simulation.settings.SimulationSettingsFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Objects;

@Controller
@RequestMapping (path = "/random-simulation")
public class MvcRandomSimulationController {

	private final SettingService settingService;
	private final SimulationService randomSimulationService;
	private final Logger logger = LoggerFactory.getLogger (MvcRandomSimulationController.class);
	
	private final Path uploadResourceDir = Paths.get ("static/upload");
	private final Path uploadDir = Paths.get ("src/main/resources", uploadResourceDir.toString ());
	
	public MvcRandomSimulationController (SettingService settingService, SimulationService randomSimulationService) {
		this.settingService = settingService;
		this.randomSimulationService = randomSimulationService;
	}
	
	@GetMapping ()
	public String randomSimulation (Model model) {
		model.addAttribute ("simSettings", settingService.getSimulationSettings ());
		return "random-simulation";
	}
	
	@PostMapping (path = "/updatesettings/file")
	public String updateSettingsFromFile (@RequestParam("file")MultipartFile file) {
		String fileName = StringUtils.cleanPath (Objects.requireNonNull (file.getOriginalFilename ()));
		
		try {
			settingService.setSimulationSettings (SimulationSettingsFactory.fromInputStream (file.getInputStream ()));
			logger.info ("Settings updated from file: " + fileName);
		} catch (IOException e) {
			logger.error ("Error while reading file: " + fileName, e);
		}
		
		return "redirect:/random-simulation";
	}
	
	@PostMapping (path = "/updatesettings/values")
	public String executeSimulation (@ModelAttribute("simSettings") SimulationSettings simSettings, BindingResult result, Model model) {
		if (result.hasErrors ())
			return "random-simulation";
		settingService.setSimulationSettings (simSettings);
		randomSimulationService.startSimulation ();
		return "redirect:/random-simulation";
	}

}
