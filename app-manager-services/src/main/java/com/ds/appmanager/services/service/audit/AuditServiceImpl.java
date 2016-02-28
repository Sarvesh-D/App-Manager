package com.ds.appmanager.services.service.audit;

import java.util.ArrayList;
import java.util.List;

import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.ds.appmanager.model.ApplicationView;
import com.ds.appmanager.services.dao.AuditDao;
import com.ds.appmanager.services.domain.Application;

@Service
public class AuditServiceImpl implements AuditService {
	
	@Autowired
	@Qualifier("auditDaoImpl")
	private AuditDao auditDao;
	
	@Autowired
	private Mapper mapper;

	@Override
	public List<ApplicationView> getApplicationAudit(int applicationId) {
		final List<ApplicationView> allApplicationRevisionsList = new ArrayList<ApplicationView>();
		List<Application> applications = auditDao.getApplicationAudit(applicationId);
		if(!applications.isEmpty()) {
			for (Application application : applications) {
				allApplicationRevisionsList.add(mapper.map(application, ApplicationView.class));
			}
		}
		return allApplicationRevisionsList;
	}

}
