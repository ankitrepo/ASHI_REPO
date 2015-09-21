package com.mandi5;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.httpclient.NameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.Settings.Secure;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.AdapterView.OnItemSelectedListener;

import com.devsmart.android.ui.HorizontalListView;
import com.google.gson.Gson;
import com.mandi5.adapter.ExpandableListAdapter;
import com.mandi5.adapter.HorizontalListAdapter;
import com.mandi5.adapter.ViewPagerAdapter;
import com.mandi5.bean.Banner;
import com.mandi5.bean.Child;
import com.mandi5.bean.HorizCategory;
import com.mandi5.bean.LocationBean;
import com.mandi5.bean.Parent;
import com.mandi5.utils.AppConfig;
import com.mandi5.utils.AppConstants;
import com.mandi5.utils.BaseNetworkTask;
import com.mandi5.utils.NetworkManager;
import com.mandi5.utils.SessionManager;
import com.mandi5.utils.Singleton;
import com.mandi5.utils.Utils;

public class MainActivity extends Activity implements NetworkManager {
	private ProgressDialog progressDialog;
	private Context context;
	private MainActivity activity;
	private String device_id;
	private SessionManager sessionManager;
	private Utils utilsSingleObject;
	private String from;
	private Spinner location_spin;
	private Spinner category_spin;
	private String[] childCategoryString = { "cat1", "cat2" };
	private String[] locationString = { "loc1", "loc2" };
	private Button buttonSetting;
	private Button mandi5_screen_back_btn;
	private DrawerLayout drawer_layout;
	private ExpandableListView lvExp;
	private List<Parent> parentList;
	private List<Banner> bannerList;
	private ViewPager view_Pager;
	private com.mandi5.circleIndicator.PageIndicator mIndicator;
	private List<Child> childCategoryList;
	private List<LocationBean> locationList;
	private List<HorizCategory> horizList;
	private HorizontalListView mandi5_category_horizontal_scroll;
	private Intent intent;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		context = this;
		activity = MainActivity.this;
		setContentView(R.layout.main_screen);
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
		MainActivityNetworkCall();
		MainActivityDrawerNetworkCall();
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
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

	private void initSessionData() {
		// TODO Auto-generated method stub
		device_id = Secure.getString(getContentResolver(), Secure.ANDROID_ID);
		sessionManager = Singleton.getInstance().getSessionManager(context);
		utilsSingleObject = Singleton.getInstance().getUtils();
	}

	private void initView() {
		// TODO Auto-generated method stub
		category_spin = (Spinner) findViewById(R.id.mandi5_category_spinner);
		location_spin = (Spinner) findViewById(R.id.mandi5_location_spinner);
		buttonSetting = (Button) findViewById(R.id.mandi5_setting_btn);
		mandi5_screen_back_btn = (Button) findViewById(R.id.mandi5_screen_back_btn);
		;
		drawer_layout = (DrawerLayout) findViewById(R.id.drawer_layout);
		lvExp = (ExpandableListView) findViewById(R.id.lvExp);
		view_Pager = (ViewPager) findViewById(R.id.view_pager);
		mandi5_category_horizontal_scroll = (HorizontalListView)findViewById(R.id.mandi5_category_horizontal_scroll);
		
		lvExp.setOnChildClickListener(new OnChildClickListener() {
			
			@Override
			public boolean onChildClick(ExpandableListView parent, View v,
					int groupPosition, int childPosition, long id) {
				// TODO Auto-generated method stub
				
				String subChildId = parentList.get(groupPosition)
						.getChildCategoryList().get(childPosition).getId();

				
				intent = new Intent(activity, SubChildListActivity.class);
				intent.putExtra("SUB_CHILD_ID", subChildId);
				startActivity(intent);
				
				return false;
			}
		});
	}

	private void initData() {
		// TODO Auto-generated method stub

	}

	
	private void onSpinnerClickListener(Spinner spinnerFor, 
			final ArrayList<String> strArray, int setItem) {
		ArrayAdapter<String> searchByCategoryArrayAdapter = new ArrayAdapter<String>(
				MainActivity.this, android.R.layout.simple_spinner_item,
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
//		spinnerFor.setOnItemSelectedListener(spinnerCategoryClick);

		// spinnerFor.setOnItemSelectedListener(spinnerClick);
		//
		spinnerFor.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				switch (arg0.getId()) {
				case R.id.mandi5_category_spinner:

					break;
				case R.id.mandi5_location_spinner:

					break;
				}

			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub

			}
		});
	}



	private void initBundleData() {
		// TODO Auto-generated method stub

	}

	private void initListener() {
		// TODO Auto-generated method stub

		buttonSetting.setOnClickListener(click);
		mandi5_screen_back_btn.setOnClickListener(click);
	}

	OnClickListener click = new OnClickListener() {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			switch (v.getId()) {
			case R.id.mandi5_setting_btn:
				Singleton
						.getInstance()
						.getUtils()
						.headerSetting(activity, context, "Settings",
								AppConstants.settingArrLogin);
				break;
			case R.id.mandi5_screen_back_btn:
				if (drawer_layout.isDrawerOpen(lvExp)) {
					drawer_layout.closeDrawer(lvExp);
				} else {
					drawer_layout.openDrawer(lvExp);
				}

			}
		}
	};

	private void initResponseData() {
		// TODO Auto-generated method stub

	}

	private void MainActivityNetworkCall() {
		// TODO Auto-generated method stub
		from = "home_screen";

		NameValuePair[] loginPostNVPair = {
				new NameValuePair("app_id", AppConfig.APP_ID),
				new NameValuePair("device_id", device_id) };

		try {
			if (Singleton.getInstance().getUtils().haveInternet(context))
				new HomeAsyncTask(context, AppConfig.BASE_URL
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

	private void MainActivityDrawerNetworkCall() {
		// TODO Auto-generated method stub
		from = "Drawer_Category";
		NameValuePair[] loginPostNVPair = {
				new NameValuePair("app_id", AppConfig.APP_ID),
				new NameValuePair("device_id", device_id) };

		try {
			if (Singleton.getInstance().getUtils().haveInternet(context))
				new DrawerAsyncTask(context, AppConfig.BASE_URL
						+ AppConfig.DRAWER_CATEGORY, loginPostNVPair, null)
						.execute("");
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

	private class HomeAsyncTask extends BaseNetworkTask<String> {

		public HomeAsyncTask(Context context, String requestUrl,
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
						JSONArray obj_banner_array = obj_result
								.getJSONArray("banner");
						JSONArray obj_childCate_array = obj_result
								.getJSONArray("child_cat_dropdown");
						JSONArray obj_location_array = obj_result
								.getJSONArray("location_dropdown");
						
						JSONArray obj_horiz_array = obj_result
								.getJSONArray("slider_category");

						Gson gson = new Gson();

						// gson.toJson(obj_array, Parent.class);
						horizList = Arrays.asList(gson.fromJson(
								obj_horiz_array.toString(), HorizCategory[].class));
						HorizontalListAdapter horzAdapter = new HorizontalListAdapter(context,horizList);
						
						mandi5_category_horizontal_scroll.setAdapter(horzAdapter);

						bannerList = Arrays.asList(gson.fromJson(
								obj_banner_array.toString(), Banner[].class));

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

//						onCategorySpinnerClick();
//						onLocationSpinnerClick();

						setBannerView(bannerList);

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

	public void setBannerView(List bannerList) {
		ViewPagerAdapter mAdapter = new ViewPagerAdapter(context, bannerList);
		view_Pager.setAdapter(mAdapter);
		com.mandi5.circleIndicator.CirclePageIndicator indicator = (com.mandi5.circleIndicator.CirclePageIndicator) findViewById(R.id.indicator);
		mIndicator = indicator;
		indicator.setViewPager(view_Pager);
		final float density = getResources().getDisplayMetrics().density;
		indicator.setBackgroundColor(Color.TRANSPARENT);
		indicator.setRadius(4 * density);
		indicator.setPageColor(getResources().getColor(R.color.white));
		indicator.setFillColor(getResources().getColor(R.color.gray));
	}

	private class DrawerAsyncTask extends BaseNetworkTask<String> {

		public DrawerAsyncTask(Context context, String requestUrl,
				NameValuePair[] request, Type type) {
			super(context, requestUrl, request, type);
		}

		@Override
		protected void onPreExecute() {
			/*
			 * progressDialog = ProgressDialog.show(context, "",
			 * AppConstants.PLEASE_WAIT);
			 */
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
						JSONArray obj_array = obj_result.getJSONArray("parent");

						Gson gson = new Gson();

						// gson.toJson(obj_array, Parent.class);

						parentList = Arrays.asList(gson.fromJson(
								obj_array.toString(), Parent[].class));

						ExpandableListAdapter exAdapter = new ExpandableListAdapter(
								activity, parentList);
						lvExp.setGroupIndicator(null);
						lvExp.setAdapter(exAdapter);

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

	@Override
	public void success() {
		// TODO Auto-generated method stub

	}

	@Override
	public void failure() {
		// TODO Auto-generated method stub

	}

}