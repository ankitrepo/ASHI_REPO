package com.mandi5;

import com.mandi5.utils.SessionManager;
import com.mandi5.utils.Singleton;
import com.mandi5.utils.Utils;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
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

public class UserAccountActivity extends Activity
{
	private static final String[] categoryStr = {"a","b","c"};
	private String[] districtStr={"d","e","f"};
	private ProgressDialog progressDialog;
	private RegistrationActivity registration_activty;
	private EditText edit_firstName;
	private EditText edit_lastName;
	private EditText edit_mob;
	private EditText edit_email;
	private EditText edit_zip;
	private EditText edit_pass;
	private EditText edit_confirPass;
	private Button btn_submit;
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
	private Context context;
	private UserAccountActivity user_account;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		context=this;
		user_account=UserAccountActivity.this;
		setContentView(R.layout.user_account_screen);
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
		edit_firstName=(EditText)findViewById(R.id.account_info_first_name);
		edit_lastName=(EditText)findViewById(R.id.account_info_last_name);
		edit_mob=(EditText)findViewById(R.id.account_info_mobile);
		edit_email=(EditText)findViewById(R.id.account_info_email);
		edit_zip=(EditText)findViewById(R.id.account_info_zip);
		btn_submit =(Button)findViewById(R.id.account_info_button);
		CategorySpinner=(Spinner)findViewById(R.id.mandi5_registration_category_spinner);
		districtSpinner=(Spinner)findViewById(R.id.mandi5_registration_district_spinner);
	}

	private void initData() {
		// TODO Auto-generated method stub
		
	}

	private void initBundleData() {
		// TODO Auto-generated method stub
		
	}

	private void initListener() {
		// TODO Auto-generated method stub
		onCategorySpinnerClick();
		onDistrictSpinnerClick();
		btn_submit.setOnClickListener(click);
	}

OnClickListener click = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			switch (v.getId()) {
			case R.id.register_button:
				
				break;
			}
		}
	};
	private void initResponseData() {
		// TODO Auto-generated method stub
		
	}
	private void onDistrictSpinnerClick() {
		// TODO Auto-generated method stub

		ArrayAdapter<String> searchByDistrictArrayAdapter = new ArrayAdapter<String>(
				this, android.R.layout.simple_spinner_item, districtStr) {
			@Override
			public TextView getView(int position, View convertView,
					ViewGroup parent) {
				TextView spinnerItemDistrict = (TextView) super.getView(
						position, convertView, parent);
				Singleton.getInstance().getUtils()
						.setSpinnerItemTextStyle(context, spinnerItemDistrict);
				return spinnerItemDistrict;
			}

			public TextView getDropDownView(int position, View convertView,
					ViewGroup parent) {
				TextView spinnerItemDistrict = (TextView) super.getView(
						position, convertView, parent);
				Singleton.getInstance().getUtils()
						.setSpinnerItemTextStyle(context, spinnerItemDistrict);
				return spinnerItemDistrict;
			}
		};
		searchByDistrictArrayAdapter
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		districtSpinner.setAdapter(searchByDistrictArrayAdapter);
		districtSpinner.setOnItemSelectedListener(spinnerDistrictClick);
	
	}
	

	AdapterView.OnItemSelectedListener spinnerDistrictClick = new AdapterView.OnItemSelectedListener() {

		@Override
		public void onItemSelected(AdapterView<?> parent, View view, int pos,
				long id) {
			switch (parent.getId()) {
			case R.id.mandi5_registration_district_spinner
			:
				
				break;
			}
		}

		@Override
		public void onNothingSelected(AdapterView<?> parent) {
		}
	};
	

	private void onCategorySpinnerClick() {
		// TODO Auto-generated method stub

		ArrayAdapter<String> searchByCategoryArrayAdapter = new ArrayAdapter<String>(
				this, android.R.layout.simple_spinner_item, categoryStr) {
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
		CategorySpinner.setAdapter(searchByCategoryArrayAdapter);
		CategorySpinner.setOnItemSelectedListener(spinnerCategoryClick);
	
	}
	

	AdapterView.OnItemSelectedListener spinnerCategoryClick = new AdapterView.OnItemSelectedListener() {

		@Override
		public void onItemSelected(AdapterView<?> parent, View view, int pos,
				long id) {
			switch (parent.getId()) {
			case R.id.mandi5_registration_category_spinner:
				
				break;
			}
		}

		@Override
		public void onNothingSelected(AdapterView<?> parent) {
		}
	};
}
