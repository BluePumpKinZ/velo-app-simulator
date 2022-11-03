package be.kdg.sa.simulator.controllers.mvc;

import be.kdg.sa.simulator.simulation.file.FileSimulationExecuter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping ("file-simulation")
public class MvcFileSimulationController {
	
	private final FileSimulationExecuter fileSimulationExecuter;
	private final Logger logger = LoggerFactory.getLogger (MvcFileSimulationController.class);
	
	public MvcFileSimulationController (FileSimulationExecuter fileSimulationExecuter) {
		this.fileSimulationExecuter = fileSimulationExecuter;
	}
	
	@GetMapping
	public String fileSimulation () {
		return "file-simulation";
	}
	
	@PostMapping ("/simulate")
	public String executeSimulation (@RequestParam ("file")MultipartFile file) {
		try {
			fileSimulationExecuter.executeSimulation (file.getInputStream ());
			logger.info ("Simulation started from file: " + file.getOriginalFilename ());
		} catch (Exception e) {
			logger.error ("Error while executing simulation", e);
		}
		
		return "redirect:/file-simulation";
	}
}
