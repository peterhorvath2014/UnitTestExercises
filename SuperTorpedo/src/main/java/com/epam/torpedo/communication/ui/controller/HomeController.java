package com.epam.torpedo.communication.ui.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController {  
    
    @RequestMapping(value = "/", method = RequestMethod.GET)  
    public String getHomePage() {  
        return "index";  
    }  
}