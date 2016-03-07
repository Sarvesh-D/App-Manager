package com.ds.appmanager.services.domain;

import java.util.Collection;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.envers.Audited;


@Entity
@Table(name="APPLICATION_DETAILS")
@Audited
public class Application {
	
	@Id
	@Column(name="APPLICATION_ID")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer applicationId;
	
	@Column(name="APPLICATION_NAME")
	private String applicationName;
	
	@Column(name="APPLICATION_DESC")
	private String applicationDesc;
	
	@Column(name="APPLICATION_LAUNCH")
	private Date applicationLaunch;
	
	@Column(name="LIVE")
	private boolean live;
	
	@OneToMany(cascade=CascadeType.ALL, mappedBy="application")
	private Set<User> users;
	
	public Application() {
	}

	public Application(Integer applicationId, String applicationName,
			String applicationDesc, Date applicationLaunch, boolean live) {
		super();
		this.applicationId = applicationId;
		this.applicationName = applicationName;
		this.applicationDesc = applicationDesc;
		this.applicationLaunch = applicationLaunch;
		this.live = live;
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
	
	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((applicationId == null) ? 0 : applicationId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Application other = (Application) obj;
		if (applicationId == null) {
			if (other.applicationId != null)
				return false;
		} else if (!applicationId.equals(other.applicationId))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Application [applicationId=" + applicationId
				+ ", applicationName=" + applicationName + ", applicationDesc="
				+ applicationDesc + ", applicationLaunch=" + applicationLaunch
				+ ", live=" + live + "]";
	}

	
	
}
