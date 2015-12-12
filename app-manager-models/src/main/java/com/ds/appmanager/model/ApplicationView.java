package com.ds.appmanager.model;

import java.util.Date;


public class ApplicationView {

	private Integer applicationId;
	private String applicationName;
	private String applicationDesc;
	private Date applicationLaunch;
	private boolean live;

	public ApplicationView() {
	}

	public ApplicationView(Integer applicationId, String applicationName) {
		super();
		this.applicationId = applicationId;
		this.applicationName = applicationName;
	}

	public Integer getApplicationId() {
		return applicationId;
	}

	public void setApplicationId(Integer applicationId) {
		this.applicationId = applicationId;
	}

	public String getApplicationName() {
		return applicationName;
	}

	public void setApplicationName(String applicationName) {
		this.applicationName = applicationName;
	}

	public String getApplicationDesc() {
		return applicationDesc;
	}

	public void setApplicationDesc(String applicationDesc) {
		this.applicationDesc = applicationDesc;
	}

	public Date getApplicationLaunch() {
		return applicationLaunch;
	}

	public void setApplicationLaunch(Date applicationLaunch) {
		this.applicationLaunch = applicationLaunch;
	}

	public boolean isLive() {
		return live;
	}

	public void setLive(boolean live) {
		this.live = live;
	}

	@Override
	public String toString() {
		return "ApplicationView [applicationId=" + applicationId
				+ ", applicationName=" + applicationName + ", applicationDesc="
				+ applicationDesc + ", applicationLaunch=" + applicationLaunch
				+ ", live=" + live + "]";
	}

}
