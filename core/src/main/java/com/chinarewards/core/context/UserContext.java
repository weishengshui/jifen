package com.chinarewards.core.context;

/**
 * Defines the interface which contains authenticated user information. This
 * interface should contain authenticated (logged in) user related information
 * only.
 * 
 */
public interface UserContext {

	/**
	 * Returns the user ID.
	 * 
	 * @return
	 */
	public String getUserId();

	/**
	 * Returns the use sessionId
	 * 
	 * @return
	 */
	public String getSessionId();

	/**
	 * Copy a new instance with same properties as previous one
	 * 
	 * @return
	 */
	public UserContext copyNewInstance();

	/**
	 * 
	 * 
	 * @return
	 */
	public String getIp();

}
