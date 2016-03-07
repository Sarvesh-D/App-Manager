package com.ds.appmanager.services.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ds.appmanager.model.ApplicationView;
import com.ds.appmanager.services.exceptions.ApplicationException;
import com.ds.appmanager.services.service.ApplicationService;

@Controller
public class ApplicationController {
	
	@Autowired
	@Qualifier("applicationServiceImpl")
	private ApplicationService applicationService;
	
	private static final Logger logger = LoggerFactory.getLogger(ApplicationController.class);
	
	/**
	 * Add application in the system
	 * @param applicationView
	 * @return
	 */
	@RequestMapping(value="/applications" , method=RequestMethod.POST)
	public @ResponseBody ApplicationView addApplication(@RequestBody ApplicationView applicationView) {
		applicationService.addApplication(applicationView);
		return applicationView;
	}
	
	/**
	 * Get all applications from the system
	 * @return
	 * @throws ApplicationException
	 */
	@RequestMapping(value="/applications" , method=RequestMethod.GET)
	public @ResponseBody List<ApplicationView> getAllApplications() throws ApplicationException {
		return applicationService.getAllApplications();
	}
	
	/**
	 * Get a particular application from the system
	 * @param applicationId
	 * @return
	 */
	@RequestMapping(value="/applications/{applicationId}" , method=RequestMethod.GET)
	public @ResponseBody ApplicationView getApplication(@PathVariable int applicationId) {
		return applicationService.getApplication(applicationId);
	}
	
	/**
	 * Update application in the system
	 * @param applicationView
	 * @return
	 */
	@RequestMapping(value="/applications" , method=RequestMethod.PUT)
	public @ResponseBody ApplicationView updateApplication(@RequestBody ApplicationView applicationView) {
		applicationService.updateApplication(applicationView);
		return applicationView;
	}
	
	/**
	 * Delete application in the system
	 * @param applicationView
	 * @return
	 */
	@RequestMapping(value="/applications/{applicationId}" , method=RequestMethod.DELETE)
	public @ResponseBody void deleteApplication(@PathVariable int applicationId) {
		applicationService.deleteApplication(applicationId);
	}
	
	/**
	 * Map users to applications
	 * @param applicationId
	 * @param userIds
	 */
	@RequestMapping(value="/app/users" , method=RequestMethod.PUT)
	public @ResponseBody void mapApplicationToUsers(@RequestBody ApplicationView applicationView) {
		applicationService.updateApplication(applicationView);
	}
	
	// TODO FOR TESTING ERROR.JSP NOT RENDERING ISSUE
	/*@RequestMapping(value="/get")
	public String getAllApplications() throws ApplicationException {
		throw new ApplicationException("Testing error page...");
		//return applicationService.getAllApplications();
	}*/
	
	@ExceptionHandler(value = Exception.class)
	public static ModelAndView redirectOnException(Exception e) {
		logger.error("Following error occured while processing request : {}",e.getMessage());
		ModelAndView mv = new ModelAndView("error");
		mv.addObject("errMsg", e.getMessage());
		return mv;
	}
	
	/* TODO FOR TESTING ERROR.JSP NOT RENDERING ISSUE
	@RequestMapping(value="/error")
	public String displayErrorPage() {
		return "error";
	}*/

}
