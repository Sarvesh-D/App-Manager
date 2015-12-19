package com.ds.appmanager.batch.listeners;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.ItemProcessListener;
import org.springframework.orm.hibernate3.HibernateTemplate;

import com.ds.appmanager.batch.domain.BatchDetails;
import com.ds.appmanager.batch.exceptions.InvalidRecordException;
import com.ds.appmanager.model.ApplicationView;

public class AfterAppProcessListener implements ItemProcessListener<ApplicationView, ApplicationView> {

	private HibernateTemplate hibernateTemplate;

	private static final Logger LOG = LoggerFactory.getLogger(AfterAppProcessListener.class);
	
	@Override
	public void beforeProcess(ApplicationView item) {
		
	}

	@Override
	public void afterProcess(ApplicationView item, ApplicationView result) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onProcessError(ApplicationView item, Exception e) {
		if(e instanceof InvalidRecordException) {
			LOG.error("Invalid Record : {}",item);
			LOG.error(e.getMessage());
			LOG.debug("Writing item to BATCH_DETAILS table {}",item);
			hibernateTemplate.save(new BatchDetails(item.getApplicationName(), "BATCH", new Date(), "INVALID"));
		}
	}

	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}

	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}
	
	

}
