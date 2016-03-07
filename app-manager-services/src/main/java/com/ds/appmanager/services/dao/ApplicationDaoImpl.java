package com.ds.appmanager.services.dao;

import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.ds.appmanager.services.domain.Application;
import com.ds.appmanager.services.domain.User;


/**
 * @author Sarvesh
 *
 */
@Repository
public class ApplicationDaoImpl implements ApplicationDao {
	
	@Autowired
	private HibernateTemplate hibernateTemplate;
	
	private static final Logger logger = LoggerFactory.getLogger(ApplicationDaoImpl.class);

	@Override
	public boolean addApplication(Application application) {
		try {
			addUsersToApplication(application, application.getUsers());
			hibernateTemplate.save(application);
			return true;
		} catch (DataAccessException e) {
			return false;
		}
	}

	@Override
	public boolean updateApplication(Application application) {
		try {
			addUsersToApplication(application, application.getUsers());
			hibernateTemplate.update(application);
			return true;
		} catch (DataAccessException e) {
			return false;
		}
	}

	@Override
	public boolean deleteApplication(int applicationId) {
		try {
			hibernateTemplate.delete(hibernateTemplate.get(Application.class, applicationId));
			return true;
		} catch (DataAccessException e) {
			return false;
		}
	}

	@Override
	public List<Application> getAllApplications() {
		final String query = "FROM Application";
		return (List<Application>) hibernateTemplate.find(query);
	}

	@Override
	public Application getApplication(int applicationId) {
		return hibernateTemplate.get(Application.class, applicationId);
	}
	
	private boolean addUsersToApplication(Application application, Set<User> users) {
		if(null != users) {
			application.setUsers(users);
			for (User user : users) {
				user.setApplication(application);;
			}
		}
		return true;
	}

}
