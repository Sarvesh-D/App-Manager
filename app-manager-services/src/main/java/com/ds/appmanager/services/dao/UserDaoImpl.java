package com.ds.appmanager.services.dao;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.ds.appmanager.services.domain.User;

@Repository
public class UserDaoImpl implements UserDao {
	
	@Autowired
	private HibernateTemplate hibernateTemplate;
	
	private static final Logger logger = LoggerFactory.getLogger(UserDaoImpl.class);

	@Override
	public List<User> getAllUsers() {
		final String query = "FROM User";
		return (List<User>) hibernateTemplate.find(query);
	}

}
