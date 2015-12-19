package com.ds.appmanager.batch.validator;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.ds.appmanager.model.ApplicationView;

public class ItemValidator implements BaseValidator<ApplicationView> {
	
	private List<String> errors;
	
	private static ItemValidator itemValidator;

	@Override
	public boolean validate(ApplicationView obj) {
		errors = new ArrayList<>();
		if(StringUtils.isBlank(obj.getApplicationName())) {
			getErrors().add("Application Name cannot be blank");
		}
		if(null == obj.getApplicationLaunch()) {
			getErrors().add("Launch Date is mandatory");
		}
		if(obj.getApplicationLaunch().after(new Date())) {
			getErrors().add("Launch Date cannot be future date");
		}
		return getErrors().isEmpty();
	}

	@Override
	public List<String> getErrors() {
		return errors;
	}
	
	public static ItemValidator getValidator() {
		return null == itemValidator ? new ItemValidator() : itemValidator;
	}


}
