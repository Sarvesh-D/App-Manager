package com.ds.appmanager.services.domain;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.envers.DefaultRevisionEntity;
import org.hibernate.envers.RevisionEntity;

import com.ds.appmanager.services.util.CustomRevEntityListener;

@Entity
@Table(name="CUSTOM_REV_INFO")
@RevisionEntity(CustomRevEntityListener.class)
public class CustomRevEntity extends DefaultRevisionEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String userName;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	

}
