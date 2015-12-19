package com.ds.appmanager.batch.listeners;

import org.springframework.batch.core.SkipListener;

import com.ds.appmanager.batch.exceptions.InvalidRecordException;
import com.ds.appmanager.model.ApplicationView;

public class ItemSkipListner implements SkipListener<ApplicationView, ApplicationView> {

	@Override
	public void onSkipInRead(Throwable t) {
		if(t instanceof InvalidRecordException) {
			//throw new InvalidRecordException("");
		}
		
	}

	@Override
	public void onSkipInWrite(ApplicationView item, Throwable t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onSkipInProcess(ApplicationView item, Throwable t) {
		// TODO Auto-generated method stub
		
	}

}
