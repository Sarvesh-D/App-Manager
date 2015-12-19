package com.ds.appmanager.batch.listeners;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.ItemReadListener;

import com.ds.appmanager.model.ApplicationView;

public class AfterAppReadListener implements ItemReadListener<ApplicationView> {
	
	private static final Logger LOG = LoggerFactory.getLogger(AfterAppReadListener.class);

	@Override
	public void beforeRead() {
		// TODO Auto-generated method stub

	}

	@Override
	public void afterRead(ApplicationView item) {
		
	}

	@Override
	public void onReadError(Exception ex) {
		// TODO Auto-generated method stub

	}

}