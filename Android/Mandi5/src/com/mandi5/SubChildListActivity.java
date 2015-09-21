package com.mandi5;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.httpclient.NameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.google.gson.Gson;
import com.mandi5.adapter.ExpandableListAdapter;
import com.mandi5.adapter.LazyAdapter;
import com.mandi5.bean.Parent;
import com.mandi5.bean.SubChild;
import com.mandi5.utils.AppConfig;
import com.mandi5.utils.AppConstants;
import com.mandi5.utils.BaseNetworkTask;
import com.mandi5.utils.Singleton;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class SubChildListActivity extends Activity{
	private String id;
	private ListView lstView;
	private ProgressDialog progressDialog;
	private Context context;
	private Activity activity;
	private List<SubChild> subChildListing;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.subchild_list_layout);
		context = this;
		activity = this;
		id = getIntent().getStringExtra("SUB_CHILD_ID");
		
		initView();
		
		
		
		
	}
	private void initView() {
		// TODO Auto-generated method stub
		
		lstView = (ListView)findViewById(R.id.listView);
		subChildListNetworkCall();
		
		lstView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				startActivity(new Intent(SubChildListActivity.this, SellerListActivity.class));
			}
		});
		
	}

	public void subChildListNetworkCall(){
		
		NameValuePair[] loginPostNVPair = {
				new NameValuePair("app_id", AppConfig.APP_ID),
				new NameValuePair("category_id", id) };

		try {
			if (Singleton.getInstance().getUtils().haveInternet(context))
				new SubChildListNetworkCall(context, AppConfig.BASE_URL
						+ AppConfig.SUB_CATEGORY_LIST, loginPostNVPair, null)
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
	
	
	class SubChildListNetworkCall extends BaseNetworkTask<String>{	
		
		public SubChildListNetworkCall(Context context, String requestUrl,
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
//				String Msg = obj_err.getString("resultMsg");
				if (error_code.equalsIgnoreCase("200")) {

					JSONObject obj_result = obj.getJSONObject("result");
					JSONArray obj_array = obj_result.getJSONArray("categories");

					Gson gson = new Gson();

					// gson.toJson(obj_array, Parent.class);

					subChildListing = Arrays.asList(gson.fromJson(
							obj_array.toString(), SubChild[].class));

					LazyAdapter adapter = new LazyAdapter(activity, subChildListing);
					lstView.setAdapter(adapter);

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
}
