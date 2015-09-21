package com.mandi5.utils;


import android.content.Context;


public class Singleton {

	private static Singleton instance;
	private Utils utils;
	private SessionManager sessionManager;

	private Singleton() {
	}
	
	/**
	 * This function is used to return the instance of this class.
	 * @return Singleton class object.
	 */
	public static Singleton getInstance()
	{
		if(instance == null)
			instance = new Singleton();
		return instance;
	}
	
	public Utils getUtils()
	{
		if(utils == null)
			utils = new Utils();
		return utils;
	}
	
	public SessionManager getSessionManager(Context context)
	{
		if(sessionManager == null)
			sessionManager = new SessionManager(context);
		return sessionManager;
	}
}
