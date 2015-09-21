package com.mandi5;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.httpclient.NameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.google.gson.Gson;
import com.mandi5.adapter.HorizontalListAdapter;
import com.mandi5.bean.Banner;
import com.mandi5.bean.Child;
import com.mandi5.bean.HorizCategory;
import com.mandi5.bean.LocationBean;
import com.mandi5.utils.AppConfig;
import com.mandi5.utils.AppConstants;
import com.mandi5.utils.BaseNetworkTask;
import com.mandi5.utils.SessionManager;
import com.mandi5.utils.Singleton;
import com.mandi5.utils.Utils;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.Settings.Secure;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;

public class RegistrationActivity extends Activity
{
	private static final String[] categoryStr = {"a","b","c"};
	private String[] districtStr={"d","e","f"};
	private Context context;
	private ProgressDialog progressDialog;
	private RegistrationActivity registration_activty;
	private EditText edit_firstName;
	private EditText edit_lastName;
	private EditText edit_mob;
	private EditText edit_email;
	private EditText edit_zip;
	private EditText edit_pass;
	private EditText edit_confirPass;
	private Button btn_register;
	private String firstNameVal;
	private String lastNameVal;
	private String mobVal;
	private String emailVal;
	private String zipVal;
	private String passVal;
	private String confirmPassVal;
	private String device_id;
	private SessionManager sessionManager;
	private Utils utilsSingleObject;
	private Spinner districtSpinner;
	private Spinner CategorySpinner;
	private List<Child> childCategoryList;
	private List<LocationBean> locationList;
	private String from="";
	private Spinner location_spin;
	private Spinner category_spin;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		context=this;
		registration_activty=RegistrationActivity.this;
		setContentView(R.layout.registration_screen);
		initSessionData();
		initView();
		initData();
		initBundleData();
		initListener();
		initResponseData();
	}
	
	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		categoryNetworkCall();
	}
	private void categoryNetworkCall() {
		// TODO Auto-generated method stub
		from = "Category_District_Service";

		NameValuePair[] loginPostNVPair = {
				new NameValuePair("app_id", AppConfig.APP_ID),
				new NameValuePair("device_id", device_id) };

		try {
			if (Singleton.getInstance().getUtils().haveInternet(context))
				new CategoryDistrictTask(context, AppConfig.BASE_URL
						+ AppConfig.HOME_SCREEN_URL, loginPostNVPair, null)
						.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, "");
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

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
	}
	private void initSessionData() {
		// TODO Auto-generated method stub
		device_id = Secure.getString(getContentResolver(), Secure.ANDROID_ID);
		sessionManager = Singleton.getInstance().getSessionManager(context);
		utilsSingleObject = Singleton.getInstance().getUtils();
	}
	private void initView() {
		// TODO Auto-generated method stub
		edit_firstName=(EditText)findViewById(R.id.register_first_name);
		edit_lastName=(EditText)findViewById(R.id.register_last_name);
		edit_mob=(EditText)findViewById(R.id.register_mobile);
		edit_email=(EditText)findViewById(R.id.register_email);
		edit_zip=(EditText)findViewById(R.id.register_zip);
		edit_pass=(EditText)findViewById(R.id.register_password);
		edit_confirPass=(EditText)findViewById(R.id.register_confirm_pass);
		btn_register=(Button)findViewById(R.id.register_button);
		category_spin=(Spinner)findViewById(R.id.mandi5_registration_category_spinner);
		location_spin=(Spinner)findViewById(R.id.mandi5_registration_district_spinner);
	}
	private void initData() {
		// TODO Auto-generated method stub
		
	}
	private void initBundleData() {
		// TODO Auto-generated method stub
		
	}
	private void initListener() {
		// TODO Auto-generated method stub
		/*onCategorySpinnerClick();
		onDistrictSpinnerClick();*/
		btn_register.setOnClickListener(click);
	}
	OnClickListener click = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			switch (v.getId()) {
			case R.id.register_button:
				Registeration();
				break;
			}
		}
	};
	
	
	private void initResponseData() {
		// TODO Auto-generated method stub
	
	}
	protected void Registeration() {
		// TODO Auto-generated method stub
		firstNameVal=edit_firstName.getText().toString().trim();
		lastNameVal=edit_lastName.getText().toString().trim();
		mobVal=edit_mob.getText().toString().trim();
		emailVal=edit_email.getText().toString().trim();
		zipVal=edit_zip.getText().toString().trim();
		passVal=edit_pass.getText().toString().trim();
		confirmPassVal=edit_confirPass.getText().toString().trim();
		if (validateFirstName() && validateLastName() && validateEmail(emailVal) && validateMob() && validateZip() 
				&& validatePass() && validateConfrimPass()) {
			RegistratonNetworkCall();
		}
	}

	private void RegistratonNetworkCall() {
		// TODO Auto-generated method stub
		
		String from="Registration_Screen";
		NameValuePair[] registration_PostNVPair = {
				
				new NameValuePair("app_id", AppConfig.APP_ID),
				new NameValuePair("device_id", device_id),
				new NameValuePair("first_name", firstNameVal),
				new NameValuePair("last_name", lastNameVal),
				new NameValuePair("email_id", emailVal),
				new NameValuePair("mobile_num", mobVal),
				new NameValuePair("zip_code", zipVal),
				new NameValuePair("cat_id", "5"),
				new NameValuePair("city_name", "akola"),
				new NameValuePair("password", passVal),
				new NameValuePair("user_type", "seller")};
		try {
			if (Singleton.getInstance().getUtils().haveInternet(context))
				new RegistrationAsyncTask(context, AppConfig.BASE_URL
						+ AppConfig.REGISTERATION,
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

	private boolean validateFirstName() {
		// TODO Auto-generated method stub
		if (firstNameVal.equalsIgnoreCase("")
				|| firstNameVal.equalsIgnoreCase(null)) {
			Toast.makeText(getApplicationContext(),
					AppConstants.FIRST_NAME_VALIDATION,
					Toast.LENGTH_SHORT).show();
			return false;
		}
		return true;
	}

	private boolean validateLastName() {
		// TODO Auto-generated method stub
		if (lastNameVal.equalsIgnoreCase("")
				|| lastNameVal.equalsIgnoreCase(null)) {
			Toast.makeText(getApplicationContext(),
					AppConstants.LAST_NAME_VALIDATION,
					Toast.LENGTH_SHORT).show();
			return false;
		}
		return true;
	}

	private boolean validateEmail(String email) {
		String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
				+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

		Pattern pattern = Pattern.compile(EMAIL_PATTERN);
		Matcher matcher = pattern.matcher(email);
		boolean isValid = matcher.matches();
		if(!isValid){
			Toast.makeText(getApplicationContext(), AppConstants.REGISTATION_EMAIL_PATERN_VALIDATION,
					Toast.LENGTH_SHORT).show();
			return false;
		}
		return true;
	}

	private boolean validateMob() {
		// TODO Auto-generated method stub
		if (mobVal.equalsIgnoreCase("")
				|| mobVal.equalsIgnoreCase(null)) {
			Toast.makeText(getApplicationContext(),
					AppConstants.MOB_VALIDATION,
					Toast.LENGTH_SHORT).show();
			return false;
		}
		return true;
	}

	private boolean validateZip() {
		// TODO Auto-generated method stub
		if (zipVal.equalsIgnoreCase("")
				|| zipVal.equalsIgnoreCase(null)) {
			Toast.makeText(getApplicationContext(),
					AppConstants.ZIP_VALIDATION,
					Toast.LENGTH_SHORT).show();
			return false;
		}
		return true;
	}

	private boolean validatePass() {
		// TODO Auto-generated method stub
		if (passVal.equalsIgnoreCase("")
				|| passVal.equalsIgnoreCase(null)) {
			Toast.makeText(getApplicationContext(),
					AppConstants.PASS_VALIDATION,
					Toast.LENGTH_SHORT).show();
			return false;
		}
		return true;
	}

	private boolean validateConfrimPass() {
		// TODO Auto-generated method stub
		if (confirmPassVal.equalsIgnoreCase("")
				|| confirmPassVal.equalsIgnoreCase(null)) {
			Toast.makeText(getApplicationContext(),
					AppConstants.CONFIRM_PASS_VALIDATION,
					Toast.LENGTH_SHORT).show();
			return false;
		}
		return true;
	}
	
	
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
	}
	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
	}
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}
	
	private class RegistrationAsyncTask extends BaseNetworkTask<String> {

		public RegistrationAsyncTask(Context context, String requestUrl,
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
	
	
	private class CategoryDistrictTask extends BaseNetworkTask<String> {

		public CategoryDistrictTask(Context context, String requestUrl,
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
			{

				try {
					JSONObject obj = new JSONObject(result);
					JSONObject obj_err = obj.getJSONObject("error");
					String error_code = obj_err.getString("errorCode");
					String Msg = obj_err.getString("resultMsg");
					if (error_code.equalsIgnoreCase("200")) {

						JSONObject obj_result = obj.getJSONObject("result");
						JSONArray obj_childCate_array = obj_result
								.getJSONArray("child_cat_dropdown");
						JSONArray obj_location_array = obj_result
								.getJSONArray("location_dropdown");

						Gson gson = new Gson();

						childCategoryList = Arrays.asList(gson.fromJson(
								obj_childCate_array.toString(), Child[].class));

						locationList = Arrays.asList(gson.fromJson(
								obj_location_array.toString(),
								LocationBean[].class));
						
						ArrayList<String> dropDownChildlisting = new ArrayList<String>();
						for(int i=0;i<childCategoryList.size();i++){
							dropDownChildlisting.add(childCategoryList.get(i).getName());
						}
						
						ArrayList<String> dropDownLocationlisting = new ArrayList<String>();
						for(int i=0;i<locationList.size();i++){
							dropDownLocationlisting.add(locationList.get(i).getName());
						}
						
						onSpinnerClickListener(category_spin, 
								dropDownChildlisting,9);

						onSpinnerClickListener(location_spin, 
								dropDownLocationlisting,9);

					} else {
						Singleton
								.getInstance()
								.getUtils()
								.showToast(context, AppConstants.LOGIN_ERROR,
										AppConstants.TOAST_SECONDS);
					}

				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		}
	}


	private void onSpinnerClickListener(Spinner spinnerFor, 
			final ArrayList<String> strArray, int setItem) {
		ArrayAdapter<String> searchByCategoryArrayAdapter = new ArrayAdapter<String>(
				registration_activty, android.R.layout.simple_spinner_item,
				strArray) {
			@Override
			public TextView getView(int position, View convertView,
					ViewGroup parent) {

				TextView spinnerItemCategory = (TextView) super.getView(
						position, convertView, parent);
				Singleton.getInstance().getUtils()
						.setSpinnerItemTextStyle(context, spinnerItemCategory);
				return spinnerItemCategory;

			}

			public TextView getDropDownView(int position, View convertView,
					ViewGroup parent) {
				TextView spinnerItemCategory = (TextView) super.getView(
						position, convertView, parent);
				Singleton.getInstance().getUtils()
						.setSpinnerItemTextStyle(context, spinnerItemCategory);
				return spinnerItemCategory;
			}
		};
		searchByCategoryArrayAdapter
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinnerFor.setAdapter(searchByCategoryArrayAdapter);

		spinnerFor.setOnItemSelectedListener(new OnItemSelectedListener() {

			private long cab;

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				switch (arg0.getId()) {
				case R.id.mandi5_registration_category_spinner:
					String s=String.valueOf(arg0.getSelectedItem());
					break;
				case R.id.mandi5_registration_district_spinner:

					break;
				}

			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub

			}
		});
	}

}
