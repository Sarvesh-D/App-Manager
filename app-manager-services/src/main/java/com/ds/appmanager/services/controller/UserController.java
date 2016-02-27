package com.ds.appmanager.services.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ds.appmanager.model.UserView;
import com.ds.appmanager.services.exceptions.ApplicationException;
import com.ds.appmanager.services.service.UserService;

@Controller
public class UserController {
	
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	@Qualifier("userServiceImpl")
	private UserService userService;
	
	/**
	 * Get all uses from the system
	 * @return
	 * @throws ApplicationException
	 */
	@RequestMapping(value="/users" , method=RequestMethod.GET)
	public @ResponseBody List<UserView> getAllUsers() throws ApplicationException {
		return userService.getAllUsers();
	}

}
