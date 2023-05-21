package com.geekster.expense.tracker.constants;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ApiPath {

	public static final String BASE_API = "api/v1/expense-tracker";

	// User
	public static final String USER_API_SIGNUP = "/signup";
	public static final String USER_API_SIGNIN = "/signin";
	public static final String FIELD_USER_ID = "userId";
	public static final String USER_API_USER_ID_PATH_VARIABLE = "/user/{" + FIELD_USER_ID + "}";

	// Product
	public static final String FIELD_PRODUCT_ID = "productId";
	public static final String FIELD_PRODUCT_MONTH = "month";
	public static final String FIELD_PRODUCT_DATE = "date";
	public static final String FIELD_TOKEN = "token";
	public static final String PRODUCT_API = "/product/{" + FIELD_TOKEN + "}";
	public static final String PRODUCT_API_PRODUCT_ID_PATH_VARIABLE = PRODUCT_API + "/{" + FIELD_PRODUCT_ID + "}";
	public static final String PRODUCT_API_MONTH_PATH_VARIABLE = PRODUCT_API + "/month/{" + FIELD_PRODUCT_MONTH + "}";
	public static final String PRODUCT_API_DATE_PATH_VARIABLE = PRODUCT_API + "/date/{" + FIELD_PRODUCT_DATE + "}";
	public static final String PRODUCT_API_GENERATE_REPORT_MONTH_PATH_VARIABLE = PRODUCT_API_MONTH_PATH_VARIABLE + "/report";

}
