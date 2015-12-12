package com.ds.appmanager.services.webservices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.ds.appmanager.model.ApplicationView;
import com.ds.appmanager.services.service.ApplicationService;

@Component
/*@WebService(endpointInterface="com.sarvesh.ams.webservices.ApplicationManagementWS" , serviceName="ApplicationManagerService" , portName="ApplicationManagerPort")*/
public class ApplicationManagementWSImpl extends SpringBeanAutowiringSupport {
	
	@Autowired
	@Qualifier("applicationServiceImpl")
	private ApplicationService applicationService;

	public boolean addApplication(ApplicationView application) {
		return applicationService.addApplication(application);
	}

}
