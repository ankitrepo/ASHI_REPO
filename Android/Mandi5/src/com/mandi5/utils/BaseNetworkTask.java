package com.mandi5.utils;
import java.lang.reflect.Type;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpClientParams;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

/**
 * @author B0073698
 * 
 */
public abstract class BaseNetworkTask<T> extends AsyncTask<String, Integer, String> {

	private Context context;
	private String requestUrl;
	private NameValuePair[] request;
	private Type type;
	String resp = "";

	/**
	 * @param context
	 * @param requestUrl
	 * @param request
	 */
	public BaseNetworkTask(Context context, String requestUrl, NameValuePair[] request, Type type) {
		super();
		this.context = context;
		this.requestUrl = requestUrl;
		this.request = request;
		this.type = type;
	}

	@Override
	protected String doInBackground(String... params) {

		PostMethod post = new PostMethod(requestUrl);
		if (request != null) {
			post.setRequestBody(request);
			Log.i("gcm",request.toString());
		}
		HttpClient client = new HttpClient();
		HttpClientParams httpParams= client.getParams();
		httpParams.setSoTimeout(50000);
		httpParams.setConnectionManagerTimeout(30000);
		//client.setConnectionTimeout(500);
		try {
			client.executeMethod(post);
			if (post.getStatusCode() == 200) {
				resp = post.getResponseBodyAsString();
				return resp;
			} else if(post.getStatusCode() == 408){
				resp = AppConstants.SERVER_TIMEOUT_ERROR;
				return resp;
			}else if(post.getStatusCode() == 500){
				resp = AppConstants.INTERNAL_SERVER_ERROR;
				return resp;
			}
			else
			{
				resp = AppConstants.SERVER_ERROR;
				return resp;
			}
		} catch (Exception e) {
			resp = AppConstants.SERVER_ERROR;
			e.printStackTrace();
			return resp;			
		}
	}

	protected void onPostExecute(String result) {
		super.onPostExecute(result);
		
		

	}
}
