package com.ds.appmanager.services.service;

import java.util.List;

import com.ds.appmanager.model.UserView;
import com.ds.appmanager.services.domain.User;

public interface UserService {
	
	List<UserView> getAllUsers();

	void setLoggedInUser(User user);
	
}
