package com.dogdam.shop.admin;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "admin")
public class AdminConfig {
	
	final static public String ATTRIBUTE_NAME = "adminConfig";

	final static public String BASIC_PATH = "/admin/member";
	final static public String BASIC_VIEW_PATH = "admin/member";
	
	final static public String CREATE_ACCOUNT_FORM = "/create_account_form";
	final static public String CREATE_ACCOUNT_CONFIRM = "/create_account_confirm";
	final static public String CREATE_ACCOUNT_RESULT = "/create_account_result";
	final static public String ID_VALUE_TEST = "/id_value_test";
	final static public String SIGN_UP_RESULT = "createAccountResult";
	
	final static public String LOGIN_FORM = "/login_form";
	final static public String LOGIN_CONFIRM = "/login_confirm";
	final static public String LOGIN_RESULT = "/login_result";
	final static public String SIGN_IN_RESULT = "signInResult";
	
	final static public String LOGOUT_CONFIRM = "/logout_confirm";
	
	final static public String BASIC_MGM_PATH = "/admin/mgm";
	final static public String BASIC_MGM_VIEW_PATH = "admin/member/mgm";
	
	final static public String LIST_AND_MGM = "/list_and_mgm";
	final static public String APPROVAL_UPDATE = "/updateApproval";
	final static public String WAITING_FOR_ARV_LIST = "/wating_for_arv_list";
	final static public String APPROVED_LIST = "/approved_list";
	
	final static public String USER_LIST_MGM = "/user_list_mgm";
	final static public String TOOLS = "/admin_tools";
	

	final static public int CREATE_ACCOUNT_SUCCESS = 3;
	final static public int CREATE_ACCOUNT_FAIL = -3;
	final static public int CREATE_ACCOUNT_ERROR = -88;
	
	final static public int LOGIN_SUCCESS = 2;
	final static public int LOGIN_FAIL = 4;
	
	
	
	static public String generateViewPath(String viewName) {
		
		return BASIC_VIEW_PATH + viewName;
	}
	
	static public String requestToControllerPath(String viewName) {
		
		return BASIC_PATH + viewName;
	}
	
	
	static public String managementViewPath(String viewName) {
		return BASIC_MGM_VIEW_PATH + viewName;
	}
	
	static public String requestMgmControllerPath(String viewName) {
		return BASIC_MGM_PATH + viewName;
	}
	
	
}
