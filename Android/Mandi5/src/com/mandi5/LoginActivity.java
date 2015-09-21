package com.mandi5;

import java.lang.reflect.Type;

import org.apache.commons.httpclient.NameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings.Secure;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.mandi5.utils.AppConfig;
import com.mandi5.utils.AppConstants;
import com.mandi5.utils.BaseNetworkTask;
import com.mandi5.utils.SessionManager;
import com.mandi5.utils.Singleton;
import com.mandi5.utils.Utils;

public class LoginActivity extends Activity
{
	private LinearLayout loginLayout;
	private LinearLayout forgotLayout;
	private EditText userMobile;
	private EditText userPassword;
	private Button signInBtn;
	private Button registerBtn;
	private TextView forgotPassTxt;
	private EditText forgotUserMobile;
	private Button forgotSubmitBtn;
	private Button forgotCancelBtn;
	private String device_id;
	private SessionManager sessionManager;
	private Utils utilsSingleObject;
	private Context context;
	private LoginActivity login_activity;
	private ProgressDialog progressDialog;
	private String from;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		context=this;
		login_activity=LoginActivity.this;
		setContentView(R.layout.login_screen);
		initSessionData();
		initView();
		initData();
		initBundleData();
		initListener();
		initResponseData();
	}

	private void initSessionData() {
		// TODO Auto-generated method stub
		device_id = Secure.getString(getContentResolver(), Secure.ANDROID_ID);
		sessionManager = Singleton.getInstance().getSessionManager(context);
		utilsSingleObject = Singleton.getInstance().getUtils();
	}

	private void initView() {
		// TODO Auto-generated method stub
		loginLayout=(LinearLayout)findViewById(R.id.login_layout);
		forgotLayout=(LinearLayout)findViewById(R.id.forgot_layout);;
		userMobile=(EditText)findViewById(R.id.user_mobile);
		userPassword=(EditText)findViewById(R.id.user_pass);
		signInBtn=(Button)findViewById(R.id.signin_button);
		registerBtn=(Button)findViewById(R.id.register_button);
		forgotPassTxt=(TextView)findViewById(R.id.forgotPassTxt);
		forgotUserMobile=(EditText)findViewById(R.id.forgot_user_mobile);
		forgotSubmitBtn=(Button)findViewById(R.id.forgot_submit_button);
		forgotCancelBtn=(Button)findViewById(R.id.forgot_cancel_button);
	}

	private void initData() {
		// TODO Auto-generated method stub
		
	}

	private void initBundleData() {
		// TODO Auto-generated method stub
		
	}

	private void initListener() {
		// TODO Auto-generated method stub
		signInBtn.setOnClickListener(click);
		registerBtn.setOnClickListener(click);
		forgotPassTxt.setOnClickListener(click);
		forgotSubmitBtn.setOnClickListener(click);
		forgotCancelBtn.setOnClickListener(click);
	}
	OnClickListener click = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			switch (v.getId()) {
			case R.id.signin_button:
				siginCall();
				break;
			case R.id.register_button:
				Intent register_intent = new Intent(LoginActivity.this, RegistrationActivity.class);
				startActivity(register_intent);
				break;
			case R.id.forgotPassTxt:
				loginLayout.setVisibility(View.GONE);
				forgotLayout.setVisibility(View.VISIBLE);
				break;
			case R.id.forgot_submit_button:
				forgotPassCall();
				break;
			case R.id.forgot_cancel_button:
				loginLayout.setVisibility(View.VISIBLE);
				forgotLayout.setVisibility(View.GONE);
				break;
				
			}
		}
	};
	private String userMobileVal;
	private String userPassVal;

	private void initResponseData() {
		// TODO Auto-generated method stub
		
	}

	protected void forgotPassCall() {
		// TODO Auto-generated method stub

		// TODO Auto-generated method stub
		userMobileVal=userMobile.getText().toString().trim();
		if (validateMobileNum())
		{
			ForgotPassNetworkCall();
		}
		
	
	}

	private void ForgotPassNetworkCall() {
		// TODO Auto-generated method stub
		from="Forgot_Screen_Response";
		NameValuePair[] registration_PostNVPair = {
				
				new NameValuePair("app_id", AppConfig.APP_ID),
				new NameValuePair("device_id", device_id),
				new NameValuePair("first_name", userMobileVal) };
		try {
			if (Singleton.getInstance().getUtils().haveInternet(context))
				new LoginAsyncTask(context, AppConfig.BASE_URL
						+ AppConfig.FORGOT_PASS,
						registration_PostNVPair, null).execute("");
			else
				Singleton
						.getInstance()
						.getUtils()
						.showToast(context, AppConstants.NETWORK_MSG,
								AppConstants.TOAST_SECONDS);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	protected void siginCall() {
		// TODO Auto-generated method stub
		userMobileVal=userMobile.getText().toString().trim();
		userPassVal=userPassword.getText().toString().trim();
		if (validateMobileNum()  && validatePass())
		{
			LoginNetworkCall();
		}
		
	}

	private void LoginNetworkCall() {
		// TODO Auto-generated method stub
		from="Login_Screen_Response";
		NameValuePair[] registration_PostNVPair = {
				
				new NameValuePair("app_id", AppConfig.APP_ID),
				new NameValuePair("device_id", device_id),
				new NameValuePair("mobile_num", userMobileVal),
				new NameValuePair("password", userPassVal) };
		try {
			if (Singleton.getInstance().getUtils().haveInternet(context))
				new LoginAsyncTask(context, AppConfig.BASE_URL
						+ AppConfig.LOGIN,
						registration_PostNVPair, null).execute("");
			else
				Singleton
						.getInstance()
						.getUtils()
						.showToast(context, AppConstants.NETWORK_MSG,
								AppConstants.TOAST_SECONDS);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private boolean validatePass() {
		// TODO Auto-generated method stub
		if (userPassVal.equalsIgnoreCase("")
				|| userPassVal.equalsIgnoreCase(null)) {
			Toast.makeText(getApplicationContext(),
					AppConstants.PASS_VALIDATION,
					Toast.LENGTH_SHORT).show();
			return false;
		}
		return true;
	}

	private boolean validateMobileNum() {
		// TODO Auto-generated method stub
		if (userMobileVal.equalsIgnoreCase("")
				|| userMobileVal.equalsIgnoreCase(null)) {
			Toast.makeText(getApplicationContext(),
					AppConstants.MOB_VALIDATION,
					Toast.LENGTH_SHORT).show();
			return false;
		}
		return true;
	}
	private class LoginAsyncTask extends BaseNetworkTask<String> {

		public LoginAsyncTask(Context context, String requestUrl,
				NameValuePair[] request, Type type) {
			super(context, requestUrl, request, type);
		}

		@Override
		protected void onPreExecute() {
			progressDialog = ProgressDialog.show(context, "",
					AppConstants.PLEASE_WAIT);
		}

		@Override
		protected void onPostExecute(String result) {
			if (progressDialog != null)
				if (progressDialog.isShowing())
					progressDialog.dismiss();
			super.onPostExecute(result);
			if(from.equalsIgnoreCase("Login_Screen_Response"))
			{
				try {
					JSONObject obj = new JSONObject(result);
					JSONObject obj_err = obj.getJSONObject("error");
					String error_code = obj_err.getString("errorCode");
					String Msg = obj_err.getString("resultMsg");
					if (error_code.equalsIgnoreCase("200")) {
						sessionManager.createLoginSession(userMobileVal,true);
						Toast.makeText(context, "User has been successfully login.", Toast.LENGTH_LONG).show();
						finish();
					} else {
						Singleton
								.getInstance()
								.getUtils()
								.showToast(context, Msg, AppConstants.TOAST_SECONDS);
					}

				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}else if(from.equalsIgnoreCase("Forgot_Screen_Response"))
			{
				try {
					JSONObject obj = new JSONObject(result);
					JSONObject obj_err = obj.getJSONObject("error");
					String error_code = obj_err.getString("errorCode");
					String Msg = obj_err.getString("resultMsg");
					if (error_code.equalsIgnoreCase("200")) {
						
					} else {
						Singleton
								.getInstance()
								.getUtils()
								.showToast(context, Msg, AppConstants.TOAST_SECONDS);
					}

				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		}
	}
}
