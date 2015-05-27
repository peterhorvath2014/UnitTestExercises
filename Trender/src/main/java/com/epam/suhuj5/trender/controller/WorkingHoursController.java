package com.epam.suhuj5.trender.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.epam.suhuj5.trender.service.WorkingHoursService;

@RestController
public class WorkingHoursController {  
    
    @Autowired
    private WorkingHoursService workingHoursService;
     
    @RequestMapping(value = "/wh", method = RequestMethod.GET)  
    public String getWorkingHoursList() {  
        return "string";  
    }  
     
    /*@RequestMapping(value = "/workinghours/save", method = RequestMethod.POST)  
    public View createWorkingHours(@ModelAttribute WorkingHours workingHours, ModelMap model) {
        if(StringUtils.hasText(workingHours.getId())) {
            workingHoursService.updateWorkingHours(workingHours);
        } else {
            workingHoursService.addWorkingHours(workingHours);
        }
        return new RedirectView("/Trender/workinghours");  
    }
         
    @RequestMapping(value = "/workinghours/delete", method = RequestMethod.GET)  
    public View deletePerson(@ModelAttribute WorkingHours workingHours, ModelMap model) {  
        workingHoursService.deleteWorkingHours(workingHours);
        return new RedirectView("/Trender/workinghours");  
    }  */ 
}