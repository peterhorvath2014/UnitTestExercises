package com.epam.torpedo.communication.ui.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.epam.torpedo.communication.ui.service.StartTorpedoClientService;
import com.epam.torpedo.communication.ui.service.StartTorpedoServerService;
import com.epam.torpedo.config.GameConfiguration;

@RestController
public class TorpedoStarterController {
	private static final Logger logger = LogManager.getLogger();

	@Autowired
	StartTorpedoServerService startTorpedoServerService;

	@Autowired
	StartTorpedoClientService startTorpedoClientService;

	@RequestMapping(value = "/server", method = RequestMethod.POST, produces = "application/json")
	public GameConfiguration startTorpedoServer(@RequestBody GameConfiguration gameConfiguration) {
		logger.debug("gameConfiguration: " + gameConfiguration);
		return startTorpedoServerService.startTorpedoServer(gameConfiguration);
	}

	@RequestMapping(value = "/client", method = RequestMethod.POST, produces = "application/json")
	public GameConfiguration startTorpedoClient(@RequestBody GameConfiguration gameConfiguration) {
		logger.debug("gameConfiguration: " + gameConfiguration);
		return startTorpedoClientService.startTorpedoClient(gameConfiguration);
	}
}
