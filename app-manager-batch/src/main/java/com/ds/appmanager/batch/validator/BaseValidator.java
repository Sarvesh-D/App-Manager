package com.ds.appmanager.batch.validator;

import java.util.List;

public interface BaseValidator<T> {
	
	boolean validate(T obj);
	
	List<String> getErrors();
	
}
