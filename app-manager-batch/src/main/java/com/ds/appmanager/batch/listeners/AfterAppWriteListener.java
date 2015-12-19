package com.ds.appmanager.batch.listeners;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.ItemWriteListener;
import org.springframework.orm.hibernate3.HibernateTemplate;

import com.ds.appmanager.batch.domain.BatchDetails;
import com.ds.appmanager.model.ApplicationView;

public class AfterAppWriteListener implements ItemWriteListener<ApplicationView> {
	
	private HibernateTemplate hibernateTemplate;
	
	private static final Logger LOG = LoggerFactory.getLogger(AfterAppWriteListener.class);

	@Override
	public void beforeWrite(List<? extends ApplicationView> items) {
		// TODO Auto-generated method stub

	}

	@Override
	public void afterWrite(List<? extends ApplicationView> items) {
		for (ApplicationView applicationView : items) {
			LOG.debug("Writing item to BATCH_DETAILS table {}",applicationView);
			updateBatchDetails(applicationView);
		}
	}

	@Override
	public void onWriteError(Exception exception, List<? extends ApplicationView> items) {
		// TODO Auto-generated method stub

	}
	
	private void updateBatchDetails(ApplicationView applicationView) {
		hibernateTemplate.save(new BatchDetails(applicationView.getApplicationName(), "BATCH", new Date() , "VALID"));
	}
	
	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}
	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}

}
