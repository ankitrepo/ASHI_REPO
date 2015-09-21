package com.mandi5.utils;

import java.lang.reflect.Type;

import org.apache.commons.httpclient.NameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import com.mandi5.LoginActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.View;




public class SessionManager
{
	private Editor editor;
	private static SharedPreferences pref;
	private Context context;
	private SharedPreferences prefOrder;
	private Editor editorOrder;
	private static String userType="";
	private static String userName="";
	private static String userLoginId="";
	private static boolean userLogedIn;
	private ProgressDialog progressDialog;
	
	private boolean userHasBeenLogoutFromServer=false;
	

	private String userMobile="";
	
	public SessionManager()
    {
    	
    }

    // Constructor
    public SessionManager(Context context){
        this.context = context;
        pref = context.getSharedPreferences(AppConstants.SHARED_PREF_NAME, Context.MODE_PRIVATE);
        editor = pref.edit();
    }

    public void createLoginSession(String user_mobile, boolean userIsLogedIn){
        // Storing login value as TRUE
        editor.putBoolean(AppConstants.IS_LOGIN, userIsLogedIn);
        // Storing name in pref
        editor.putString(AppConstants.USER_MOBILE, user_mobile);    
        editor.commit();
    } 
	
	public boolean isLoggedIn(){
        return pref.getBoolean(AppConstants.IS_LOGIN, userLogedIn);
    }
	
	public String getUserMobileNum()
	{
		return pref.getString(AppConstants.USER_MOBILE, userMobile);
	}
	/*
	public String getLoginUserName()
	{
		return pref.getString(AppConstants.LOGIN_USERNAME, userName);
	}
	
	
	public String getLoginUserId()
	{
		return pref.getString(AppConstants.LOGIN_USER_ID, userLoginId);
	}
	*/
	
    public void logoutUser(Activity activity){
        // Clearing all data from Shared Preferences	
    		editor.clear();
	        editor.commit();
	       
	        // After logout redirect user to Loing Activity
	        Intent i = new Intent(context, LoginActivity.class);
	        i.putExtra("finish", true);
	        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);    
	        context.startActivity(i);
	        activity.finish();
       
    }



}


