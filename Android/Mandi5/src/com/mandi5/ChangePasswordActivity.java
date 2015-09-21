package com.mandi5;

import java.lang.reflect.Type;

import org.apache.commons.httpclient.NameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import com.mandi5.utils.AppConfig;
import com.mandi5.utils.AppConstants;
import com.mandi5.utils.BaseNetworkTask;
import com.mandi5.utils.SessionManager;
import com.mandi5.utils.Singleton;
import com.mandi5.utils.Utils;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.provider.Settings.Secure;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ChangePasswordActivity extends Activity
{
	private Context context;
	private ChangePasswordActivity change_pass_activity;
	private EditText oldPass;
	private EditText newPass;
	private EditText confirmPass;
	private Button submitBtn;
	private Button cancelBtn;
	private String oldPassVal;
	private String newPassVal;
	private String confirmPassVal;
	private String device_id;
	private SessionManager sessionManager;
	private Utils utilsSingleObject;
	private String from;
	private ProgressDialog progressDialog;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		context=this;
		change_pass_activity=ChangePasswordActivity.this;
		setContentView(R.layout.changepass_screen);
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
		oldPass=(EditText)findViewById(R.id.user_old_pass);
		newPass=(EditText)findViewById(R.id.user_new_password);
		confirmPass=(EditText)findViewById(R.id.user_confirm_password);
		submitBtn=(Button)findViewById(R.id.change_pass_submit_button);
		cancelBtn=(Button)findViewById(R.id.change_pass_cancel_button);
	}
	private void initData() {
		// TODO Auto-generated method stub
		
	}
	private void initBundleData() {
		// TODO Auto-generated method stub
		
	}
	private void initListener() {
		// TODO Auto-generated method stub
		submitBtn.setOnClickListener(click);
		cancelBtn.setOnClickListener(click);
	}
	
	OnClickListener click = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			switch (v.getId()) {
			case R.id.change_pass_submit_button:
				changePassSubmitCall();
				break;
			case R.id.change_pass_cancel_button:
				
				break;
			}
		}
	};
	
	
	private void initResponseData() {
		// TODO Auto-generated method stub
		
	}
	protected void changePassSubmitCall() {
		// TODO Auto-generated method stub
		oldPassVal=oldPass.getText().toString().trim();
		newPassVal=newPass.getText().toString().trim();
		confirmPassVal=confirmPass.getText().toString().trim();
		if(strValidation(oldPassVal,AppConstants.OLD_PASS) && strValidation(newPassVal,AppConstants.NEW_PASS) && strValidation(confirmPassVal,AppConstants.CONFIRM_PASS))
		{
			changePassNetowrkCall();
		}
	}
	
	private void changePassNetowrkCall() {
		// TODO Auto-generated method stub
		from="change_password_Screen_Response";
		NameValuePair[] registration_PostNVPair = {
				
				new NameValuePair("app_id", AppConfig.APP_ID),
				new NameValuePair("device_id", device_id),
				new NameValuePair("old_pass", oldPassVal),
				new NameValuePair("new_pass", newPassVal),
				new NameValuePair("confirm_pass", confirmPassVal)};
		try {
			if (Singleton.getInstance().getUtils().haveInternet(context))
				new changePassAsyncTask(context, AppConfig.BASE_URL
						+ AppConfig.CHANGE_PASS,
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
	private boolean strValidation(String val, String msg)
	{

		// TODO Auto-generated method stub
		if (val.equalsIgnoreCase("")
				|| val.equalsIgnoreCase(null)) {
			Toast.makeText(getApplicationContext(),
					msg,
					Toast.LENGTH_SHORT).show();
			return false;
		}
		return true;
	
	}
	private class changePassAsyncTask extends BaseNetworkTask<String> {

		

		public changePassAsyncTask(Context context, String requestUrl,
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
			if(from.equalsIgnoreCase("change_password_Screen_Response"))
			{
				try {
					JSONObject obj = new JSONObject(result);
					JSONObject obj_err = obj.getJSONObject("error");
					String error_code = obj_err.getString("errorCode");
					String Msg = obj_err.getString("resultMsg");
					if (error_code.equalsIgnoreCase("200")) 
					{
						Singleton
						.getInstance()
						.getUtils()
						.passwordChangeDialog(change_pass_activity, context, Msg,
								false);
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
