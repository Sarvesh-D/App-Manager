package com.ds.appmanager.services.service.audit;

import java.util.List;

import com.ds.appmanager.model.ApplicationView;

public interface AuditService {
	
	List<ApplicationView> getApplicationAudit(int applicationId);

}
