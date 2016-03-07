package com.ds.appmanager.services.domain;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.envers.Audited;

@Entity
@Table(name="USER_DETAILS")
@Audited
public class User {
	
	@Id
	@Column(name="USER_ID")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer userId;
	
	@Column(name="USER_NAME")
	private String userName;
	
	@ManyToOne
	@JoinColumn(name="APPLICATION_ID")
	private Application application;
	
	public User() {
	}
	
	public User(String userName) {
		this.userName = userName;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public Application getApplication() {
		return application;
	}

	public void setApplication(Application application) {
		this.application = application;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", userName=" + userName + "]";
	}

}
