package be.kdg.sa.simulator.controllers;

import be.kdg.sa.simulator.exceptions.CommandExecutionError;
import be.kdg.sa.simulator.services.CommandService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Jonas Leijzen
 * 26/10/2022
 */

@RestController
@RequestMapping (path = "/api/v1/commands")
public class CommandController {
	
	private final CommandService commandService;
	private final Logger logger = LoggerFactory.getLogger (CommandController.class);
	
	public CommandController (CommandService commandService) {
		this.commandService = commandService;
	}
	
	@GetMapping ("/execute")
	public ResponseEntity<String> executeCommand (@RequestBody String command) {
		try {
			return new ResponseEntity<> (commandService.executeCommand (command), HttpStatus.OK);
		} catch (Exception e) {
			String message = String.format ("Error while executing command '%s': %s", command, e.getMessage ());
			logger.error (message, e);
			return new ResponseEntity<> (message, e instanceof CommandExecutionError
					? HttpStatus.BAD_REQUEST
					: HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
}
