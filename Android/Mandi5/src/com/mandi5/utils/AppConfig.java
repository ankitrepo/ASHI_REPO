package com.mandi5.utils;

public class AppConfig {
	
	public static String APP_ID = "1";
	public static String BASE_URL="http://mandi5app.webits.in/service/api.php?";
	
	public static String HOME_SCREEN_URL="request=home_page_product_list";
	public static String DRAWER_CATEGORY="request=get_all_categories";
	public static String REGISTERATION="request=register";
	public static String USER_LOGOUT="";
	public static String SUB_CATEGORY_LIST = "request=get_sub_categories";
	public static String LOGIN="request=login";//input parameter: mobile_num, password, quote_id
	public static String FORGOT_PASS="request=forgot_password";//input parameter: mobile_num
	public static String MY_ACCOUNT="request=my_profile";//input parameter: user_id
	public static String CHANGE_PASS="request=change_password";// input parameter: user_id, old_password, new_password, confirm_password
	public static String SUB_CATEGORY="request=get_sub_categories";//input parameters:  category_id//category_id=9
	public static String VIEW_PRODUCT="request=view_category_product ";//input parameter: sub_child_id
	

	
}


