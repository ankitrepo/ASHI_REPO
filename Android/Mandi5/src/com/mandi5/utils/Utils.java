package com.mandi5.utils;
import com.mandi5.LoginActivity;
import com.mandi5.R;
import com.mandi5.RegistrationActivity;
import com.mandi5.UserAccountActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.AlertDialog.Builder;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.sax.StartElementListener;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class Utils {

	private Context context;
	private ProgressDialog progressDialog;

	private Activity act;

	public void setSpinnerItemTextStyle(Context context, TextView textView) {
		textView.setTextSize(AppConstants.spinnerItemTextSize);
		textView.setPadding(10, 7, 20, 7);
		textView.setTextColor(Color.BLACK);
	}

	public static Typeface getRonniaBasicBold(Context context) {
		return Typeface.createFromAsset(context.getAssets(),
				"fonts/ronniabasicbold.otf");
	}

	public static float getDip(Context context) {
		return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
				(float) 1, context.getResources().getDisplayMetrics());
	}

	public boolean haveInternet(Context context) {
		boolean flag = false;
		NetworkInfo info;
		try {
			info = (NetworkInfo) ((ConnectivityManager) context
					.getSystemService(Context.CONNECTIVITY_SERVICE))
					.getActiveNetworkInfo();
			if (info != null && info.isAvailable() && info.isConnected()) {
				flag = true;
				return flag;
			} else {
				flag = false;
				return flag;
			}
		} catch (NullPointerException e) {
			e.printStackTrace();
			return flag;
		} catch (Exception exception) {
			exception.printStackTrace();
			return flag;
		}
	}

	public void showToast(Context context, String message, int duration) {
		duration = duration * 1000;
		/*
		 * Toast toast = Toast.makeText(context, message, Toast.LENGTH_SHORT);
		 * toast.setGravity(Gravity.CENTER, 0, 0); toast.show();
		 */

		Toast toast = new Toast(context);
		View layout = null;
		try {
			LayoutInflater inflater = (LayoutInflater) context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			layout = inflater.inflate(R.layout.toast_layout, null, false);
			TextView textView = (TextView) layout.findViewById(R.id.toast_text);
			// textView.setTypeface(Utils.getRonniaBasic(context));
			textView.setText(message);
		} catch (Exception e) {
			e.printStackTrace();
		}
		toast.setGravity(Gravity.CENTER, 0, 0);
		toast.setDuration(Toast.LENGTH_SHORT);
		if (layout != null) {
			try {
				toast.setView(layout);
			} catch (Exception e) {
				e.printStackTrace();
			}
			toast.show();
		}
	}

	public boolean isValidString(String str) {
		return str != null && !str.equals(null) && !str.equals("")
				&& !str.trim().equals("");
	}

	public void VersionUpdateDialog(final Activity activity,
			final Context context, String message, final boolean finishActivity) {
		this.context = context;
		act = activity;
		Builder builder = new AlertDialog.Builder(context);
		builder.setMessage(message);
		builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				Intent intent = new Intent(Intent.ACTION_VIEW);
				intent.setData(Uri
						.parse("https://play.google.com/store/apps/details?id=com.java.offing.activity&hl=en"));
				context.startActivity(intent);
				dialog.dismiss();

			}

		});
		builder.setNegativeButton("Cancel",
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						dialog.dismiss();

					}
				});
		Dialog dialog = builder.create();
		dialog.show();
	}

	public void errorDialog(final Activity activity, final Context context,
			String message, final boolean finishActivity) {
		this.context = context;
		act = activity;
		Builder builder = new AlertDialog.Builder(context);
		builder.setMessage(message);
		builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				//logoutNetworkCall(activity, context);
				dialog.dismiss();

			}

		});
		builder.setNegativeButton("Cancel",
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						dialog.dismiss();

					}
				});
		Dialog dialog = builder.create();
		dialog.show();
	}

	public void RegistrationDialog(final Activity activity,
			final Context context, String message) {
		Builder builder = new AlertDialog.Builder(context);
		builder.setMessage(message);
		builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
				activity.finish();
			}
		});
		Dialog dialog = builder.create();
		dialog.show();
	}

	public void passwordChangeDialog(final Activity activity,
			final Context context, String message, final boolean finishActivity) {
		Builder builder = new AlertDialog.Builder(context);
		builder.setMessage(message);
		builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				Singleton.getInstance().getSessionManager(context)
						.logoutUser(activity);
				dialog.dismiss();
				if (finishActivity)
					activity.finish();
			}
		});
		Dialog dialog = builder.create();
		dialog.show();
	}

	public void NetworkAvalability(final Activity activity,
			final Context context, String message) {
		this.context = context;
		Builder builder = new AlertDialog.Builder(context);
		builder.setMessage(message);
		builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
				activity.finish();
			}
		});
		Dialog dialog = builder.create();
		dialog.show();
	}

/*	private void logoutNetworkCall(Activity actv, Context context) {
		this.context = context;

		SessionManager sessionManager = Singleton.getInstance()
				.getSessionManager(context);

		String userLoginId = sessionManager.getLoginUserId();

		if (!userLoginId.equalsIgnoreCase("") && userLoginId != null) {
			NameValuePair[] loginPostNVPair = {

			new NameValuePair("gcm_login_id", userLoginId),
					new NameValuePair("app_id", AppConfig.APP_ID) };
			try {
				if (Singleton.getInstance().getUtils().haveInternet(context))
					new LoginAsyncTask(context, AppConfig.BASE_URL
							+ AppConfig.USER_LOGOUT, loginPostNVPair, null)
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
		} else {
			Singleton
					.getInstance()
					.getUtils()
					.showToast(context, AppConstants.EMPTY_STR_MSG,
							AppConstants.TOAST_SECONDS);
		}

	}*/

	public void paymentDetailAlert(final Activity activity,
			final Context context, String message) {
		Builder builder = new AlertDialog.Builder(context);
		builder.setMessage(message);
		builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {

				dialog.dismiss();

			}
		});
		Dialog dialog = builder.create();
		dialog.show();
	}

	
	public void headerSetting(final Activity activity, final Context context,
			final String message, String[]str) {
		

		Builder builder = new AlertDialog.Builder(context);
		builder.setTitle("Settings");
		builder.setItems(str,
				new DialogInterface.OnClickListener() {

					public void onClick(DialogInterface dialog, int item) {
						
						switch (item) {
						case 0:
							Intent login_intent=new Intent(context, LoginActivity.class);
							context.startActivity(login_intent);
							activity.finish();
							break;
						case 1:
							Intent myAccount_intent=new Intent(context, UserAccountActivity.class);
							context.startActivity(myAccount_intent);
							activity.finish();
							break;
						case 2:
							Intent register_intent=new Intent(context, RegistrationActivity.class);
							context.startActivity(register_intent);
							activity.finish();
							break;
						case 3:
							
							break;
						case 4:
							
							break;

						}
					}
				});
		AlertDialog alert = builder.create();
		alert.show();
	}
}
