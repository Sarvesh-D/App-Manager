package com.ds.appmanager.batch.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="BATCH_DETAILS")
public class BatchDetails {
	
	@Id
	@Column(name="SR_NO")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int srNo;

	@Column(name="APP_NAME")
	private String appName;
	
	@Column(name="CREATED_BY")
	private String createdBy;
	
	@Column(name="CREATED_TS")
	private Date createdTs;
	
	@Column(name="RECORD_STATUS")
	private String recordStatus;
	
	public BatchDetails() {
	}

	public BatchDetails(String appName, String createdBy, Date createdTs, String appStatus) {
		super();
		this.appName = appName;
		this.createdBy = createdBy;
		this.createdTs = createdTs;
		this.recordStatus = appStatus;
	}

	@Override
	public String toString() {
		return "BatchDetails [srNo=" + srNo + ", appName=" + appName + ", createdBy=" + createdBy + ", createdTs="
				+ createdTs + ", recordStatus=" + recordStatus + "]";
	}

	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedTs() {
		return createdTs;
	}

	public void setCreatedTs(Date createdTs) {
		this.createdTs = createdTs;
	}

	public String getAppStatus() {
		return recordStatus;
	}

	public void setAppStatus(String recordStatus) {
		this.recordStatus = recordStatus;
	}
	
}
