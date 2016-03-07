package com.ds.appmanager.services.util;

import org.springframework.stereotype.Service;

import com.ds.appmanager.services.domain.User;

@Service
public class UserContextHolder {
	
	// Not Thread safe, need to make it thread safe, or else same user will be returned each time
	private static User user;

	public static User getUser() {
		if(null == user)
			user = new User("SYSTEM");
		return user;
	}

	public static void setUser(User user) {
		UserContextHolder.user = user;
	}
	
	

}
