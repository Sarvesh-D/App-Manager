package com.ds.appmanager.services.util;

import org.hibernate.envers.RevisionListener;

import com.ds.appmanager.services.domain.CustomRevEntity;

public class CustomRevEntityListener implements RevisionListener {

	@Override
	public void newRevision(Object revisionEntity) {
		CustomRevEntity revEntity = (CustomRevEntity) revisionEntity;
		revEntity.setUserName(UserContextHolder.getUser().getUserName());
	}

}
