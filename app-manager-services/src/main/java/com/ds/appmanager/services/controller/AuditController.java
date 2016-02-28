package com.ds.appmanager.services.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ds.appmanager.model.ApplicationView;
import com.ds.appmanager.services.service.audit.AuditService;

@Controller
public class AuditController {
	
	@Autowired
	@Qualifier("auditServiceImpl")
	private AuditService auditService;
	
	@RequestMapping("/audit/{appId}")
	public @ResponseBody List<ApplicationView> getAuditData(@PathVariable("appId") int applicationId) {
		return auditService.getApplicationAudit(applicationId);
	}

}
