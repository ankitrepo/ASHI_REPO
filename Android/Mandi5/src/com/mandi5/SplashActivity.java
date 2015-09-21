package com.mandi5;

import java.util.Timer;
import java.util.TimerTask;

import com.mandi5.utils.SessionManager;
import com.mandi5.utils.Singleton;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

public class SplashActivity extends Activity 
{
	
	private Context context;
	private SplashActivity splash_screen;
	private SessionManager sessionManager;
	private boolean isLogedIn;
	private String userMobileNo;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		context=this;
		splash_screen=SplashActivity.this;
		setContentView(R.layout.splash_screen);
		TimerTask timertask = new TimerTask() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				try {
					sessionManager = Singleton.getInstance()
							.getSessionManager(context);
					isLogedIn = sessionManager.isLoggedIn();
					userMobileNo=sessionManager.getUserMobileNum();
					if(isLogedIn==true)
					{
						
					}else
					{
						
					}
				} catch (Exception e) {
					// TODO: handle exception
				}	
				Intent i = new Intent(splash_screen,
						MainActivity.class);
				startActivity(i);
				finish();
			}
		};
		Timer timer = new Timer();
		timer.schedule(timertask, 2000);
	}
}
