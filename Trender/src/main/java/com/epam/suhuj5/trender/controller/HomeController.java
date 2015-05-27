package com.epam.suhuj5.trender.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.epam.suhuj5.trender.service.WorkingHoursService;

@Controller
public class HomeController {  
    
    @Autowired
    private WorkingHoursService workingHoursService;
     
    @RequestMapping(value = "/", method = RequestMethod.GET)  
    public String getWorkingHoursList() {  
        return "index";  
    }  
}