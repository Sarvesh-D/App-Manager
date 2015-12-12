/**
 * 
 */
package com.ds.appmanager.services.service;

import java.util.List;

import com.ds.appmanager.model.ApplicationView;

/**
 * Interface that provides the functionality of Application_Management_System
 * @author Sarvesh
 * @version 1.0
 */
public interface ApplicationService {
	
	/**
	 * Method that adds a new application to the system
	 * @param applicationView to be added
	 * @return boolean depending on whether the application was successfully added to the system or not
	 */
	boolean addApplication(ApplicationView applicationView);
	
	/**
	 * Method to update an existing application
	 * @param application to be updated
	 * @return boolean depending on whether the application was updated or not
	 */
	boolean updateApplication(ApplicationView application);
	
	/**
	 * Method to delete an existing application
	 * @param application to be deleted
	 * @return boolean depending on whether the application was delted or not
	 */
	boolean deleteApplication(ApplicationView application);

	List<ApplicationView> getAllApplications();
	
	ApplicationView getApplication(Integer applicationId);

}
