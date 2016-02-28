package com.ds.appmanager.services.dao;

import java.util.List;

import com.ds.appmanager.services.domain.Application;

public interface AuditDao {
	
	List<Application> getApplicationAudit(int applicationId);

}
