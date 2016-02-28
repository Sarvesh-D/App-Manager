package com.ds.appmanager.services.service;

import java.util.ArrayList;
import java.util.List;

import org.dozer.Mapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.ds.appmanager.model.UserView;
import com.ds.appmanager.services.dao.UserDao;
import com.ds.appmanager.services.domain.User;
import com.ds.appmanager.services.util.UserContextHolder;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	@Qualifier("userDaoImpl")
	private UserDao userDao;

	@Autowired
	private Mapper mapper;
	
	private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

	@Override
	public List<UserView> getAllUsers() {
		final List<UserView> allUsersList = new ArrayList<UserView>();
		List<User> users = userDao.getAllUsers();
		if(!users.isEmpty()) {
			for (User user : users) {
				allUsersList.add(mapper.map(user, UserView.class));
			}
		}
		return allUsersList;
	}

	@Override
	public void setLoggedInUser(User user) {
		UserContextHolder.setUser(user);
	}

}
