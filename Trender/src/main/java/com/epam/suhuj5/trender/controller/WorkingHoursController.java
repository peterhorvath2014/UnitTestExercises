package com.epam.suhuj5.trender.controller;

import java.util.List;

import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.epam.suhuj5.trender.converter.PeriodReadConverter;
import com.epam.suhuj5.trender.repository.WorkingHours;
import com.epam.suhuj5.trender.service.WorkingHoursService;

@RestController
public class WorkingHoursController {
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private WorkingHoursService workingHoursService;

	@RequestMapping(value = "/wh", method = RequestMethod.GET, produces = "application/json")
	public List<WorkingHours> getWorkingHoursList() {
		List<WorkingHours> listWorkingHours = workingHoursService.listWorkingHours();
		log.debug("listWorkingHours: " + listWorkingHours);
		return listWorkingHours;
	}

	@RequestMapping(value = "/wh", method = RequestMethod.POST, produces = "application/json")
	public List<WorkingHours> processImport(@RequestBody String importParam) {
		log.debug(importParam);
		String[] importLines = importParam.split("\n");
		for (String importLine : importLines) {
			String[] importValues = importLine.split(";");
			log.debug("Date: " + importValues[0]);
			log.debug("inOffice: " + importValues[1]);
			log.debug("outOfOffice: " + importValues[2]);
			WorkingHours workingHours = new WorkingHours();
			workingHours.setDate(new DateTime(importValues[0]));
			PeriodReadConverter periodConverter = new PeriodReadConverter();
			workingHours.setInoffice(periodConverter.convert(importValues[1]));
			workingHours.setOutofoffice(periodConverter.convert(importValues[2]));
			workingHoursService.addWorkingHours(workingHours);
		}
		return getWorkingHoursList();
	}

	/*
	 * @RequestMapping(value = "/workinghours/save", method =
	 * RequestMethod.POST) public View createWorkingHours(@ModelAttribute
	 * WorkingHours workingHours, ModelMap model) {
	 * if(StringUtils.hasText(workingHours.getId())) {
	 * workingHoursService.updateWorkingHours(workingHours); } else {
	 * workingHoursService.addWorkingHours(workingHours); } return new
	 * RedirectView("/Trender/workinghours"); }
	 * 
	 * @RequestMapping(value = "/workinghours/delete", method =
	 * RequestMethod.GET) public View deletePerson(@ModelAttribute WorkingHours
	 * workingHours, ModelMap model) {
	 * workingHoursService.deleteWorkingHours(workingHours); return new
	 * RedirectView("/Trender/workinghours"); }
	 */
}