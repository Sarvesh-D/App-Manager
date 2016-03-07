/**
 * 
 */
package com.ds.appmanager.services.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.dozer.Mapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.ds.appmanager.model.ApplicationView;
import com.ds.appmanager.model.UserView;
import com.ds.appmanager.services.dao.ApplicationDao;
import com.ds.appmanager.services.domain.Application;
import com.ds.appmanager.services.domain.User;

/**
 * @author Sarvesh
 *
 */
@Service
public class ApplicationServiceImpl implements ApplicationService {

	@Autowired
	@Qualifier("applicationDaoImpl")
	private ApplicationDao applicationDao;

	@Autowired
	private Mapper mapper;

	private static final Logger logger = LoggerFactory.getLogger(ApplicationServiceImpl.class);

	@Override
	public boolean addApplication(ApplicationView applicationView) {
		Application application = mapper.map(applicationView, Application.class);
		return applicationDao.addApplication(application);
		//throw new IllegalArgumentException("Testing exception....");
	}

	@Override
	public boolean updateApplication(ApplicationView applicationView) {
		Application application = mapper.map(applicationView, Application.class);
		return applicationDao.updateApplication(application);
	}

	@Override
	public boolean deleteApplication(int applicationId) {
		//Application application = mapper.map(applicationView, Application.class);
		return applicationDao.deleteApplication(applicationId);
	}

	@Override
	public List<ApplicationView> getAllApplications() {
		final List<ApplicationView> allApplicationsList = new ArrayList<ApplicationView>();
		List<Application> applications = applicationDao.getAllApplications();
		if(!applications.isEmpty()) {
			for (Application application : applications) {
				allApplicationsList.add(mapper.map(application, ApplicationView.class));
			}
		}
		return allApplicationsList;
	}

	@Override
	public ApplicationView getApplication(int applicationId) {
		if(null != applicationDao.getApplication(applicationId)) {
			return mapper.map(applicationDao.getApplication(applicationId), ApplicationView.class);
		} else {
			return null;
		}
	}

}
