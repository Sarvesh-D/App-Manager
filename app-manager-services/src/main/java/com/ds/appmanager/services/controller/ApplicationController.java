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
	
	@RequestMapping(value="/index" , method=RequestMethod.GET)
	public String login() {
		return "redirect:/html/index.html";
	}
	
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
	public @ResponseBody ApplicationView getApplication(@PathVariable Integer applicationId) {
		return applicationService.getApplication(applicationId);
	}
	
	// TODO FOR TESTING ERROR.JSP NOT RENDERING ISSUE
	/*@RequestMapping(value="/get")
	public String getAllApplications() throws ApplicationException {
		throw new ApplicationException("Testing error page...");
		//return applicationService.getAllApplications();
	}*/
	
	@ExceptionHandler(value = Exception.class)
	public static ModelAndView redirectOnException(Exception e) {
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
