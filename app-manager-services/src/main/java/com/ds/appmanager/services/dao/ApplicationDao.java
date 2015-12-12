/**
 * 
 */
package com.ds.appmanager.services.dao;

import java.util.List;

import com.ds.appmanager.services.domain.Application;
import com.ds.appmanager.services.exceptions.ApplicationException;

/**
 * Interface that provides the functionality to connect to database
 * and perform operations for Application_Management_System
 * @author Sarvesh
 * @version 1.0
 */
public interface ApplicationDao {
	
	/**
	 * Method that adds a new application to the system
	 * @param application to be added
	 * @return boolean depending on whether the application was successfully added to the system or not
	 * @throws ApplicationException if the application is already present in the system
	 */
	boolean addApplication(Application application) ;
	
	/**
	 * Method to update an existing application
	 * @param application to be updated
	 * @return boolean depending on whether the application was updated or not
	 * @throws ApplicationException 
	 */
	boolean updateApplication(Application application) ;
	
	/**
	 * Method to delete an existing application
	 * @param application to be deleted
	 * @return boolean depending on whether the application was delted or not
	 */
	boolean deleteApplication(Application application);

	List<Application> getAllApplications();
	
	Application getApplication(Integer applicationId);

}
