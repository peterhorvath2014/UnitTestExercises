package com.epam.torpedo.communication.ui.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController {  
	private static final Logger logger = LogManager.getLogger();
    @RequestMapping(value = "/", method = RequestMethod.GET)  
    public String getHomePage() {
    	logger.debug("Generating index.jsp...");;
        return "index";  
    }  
}