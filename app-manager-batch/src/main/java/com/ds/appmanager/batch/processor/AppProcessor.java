package com.ds.appmanager.batch.processor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

import com.ds.appmanager.batch.exceptions.InvalidRecordException;
import com.ds.appmanager.batch.validator.ItemValidator;
import com.ds.appmanager.model.ApplicationView;

public class AppProcessor implements ItemProcessor<ApplicationView, ApplicationView> {
	
	private ItemValidator validator = ItemValidator.getValidator();
	
	private static final Logger LOG = LoggerFactory.getLogger(AppProcessor.class);

	@Override
	public ApplicationView process(ApplicationView item) throws Exception {
		if(validator.validate(item)) {
			LOG.debug("Valid Record : {}",item);
			LOG.debug("In Processor : Formatting application name : original = {} , processed = {}",
					item.getApplicationName(), item.getApplicationName().toUpperCase());
			item.setApplicationName(item.getApplicationName().toUpperCase());
			return item;
		} else {
			throw new InvalidRecordException("Record contains following errors :" +validator.getErrors());
		}
	}

}
