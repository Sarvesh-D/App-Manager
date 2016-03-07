package com.ds.appmanager.services.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="AUDIT")
public class Audit {
	
	@Id
	@Column(name="ID")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	@Column(name="FIELD_NAME")
	private String fieldName;
	
	@Column(name="OLD_VALUE")
	private String oldValue;
	
	@Column(name="NEW_VALUE")
	private String newValue;
	
	@Column(name="UPDATE_BY")
	private String updatedBy;
	
	@Column(name="UPDATED_TS")
	private Date updateTs;
	
	public Audit() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public String getOldValue() {
		return oldValue;
	}

	public void setOldValue(String oldValue) {
		this.oldValue = oldValue;
	}

	public String getNewValue() {
		return newValue;
	}

	public void setNewValue(String newValue) {
		this.newValue = newValue;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Date getUpdateTs() {
		return updateTs;
	}

	public void setUpdateTs(Date updateTs) {
		this.updateTs = updateTs;
	}

	@Override
	public String toString() {
		return "Audit [id=" + id + ", fieldName=" + fieldName + ", oldValue=" + oldValue + ", newValue=" + newValue
				+ ", updatedBy=" + updatedBy + ", updateTs=" + updateTs + "]";
	}
	
}
