package com.ds.appmanager.batch.processor;

import org.springframework.batch.item.ItemProcessor;

import com.ds.appmanager.model.ApplicationView;

public class AppProcessor implements ItemProcessor<ApplicationView, ApplicationView> {

	@Override
	public ApplicationView process(ApplicationView item) throws Exception {
		ApplicationView processedItem = item;
		if("" != item.getApplicationName()) {
			processedItem.setApplicationName(item.getApplicationName().toUpperCase());
		} else {
			processedItem.setApplicationName("BLANK");
		}
		return processedItem;
	}

}
