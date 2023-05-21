package com.geekster.expense.tracker.constants;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ExpenseTrackerConstants {

	// User
	public static final String USER_TABLE_NAME = "user";
	public static final String USER_ID = "id";
	public static final String USER_NAME = "name";
	public static final String USER_EMAIL = "email";
	public static final String USER_PASSWORD = "password";
	public static final String FIELD_USER = "user";

	public static final String USER_UPDATE_RESPONSE = "User updated successfully!";
	public static final String USER_DELETE_RESPONSE = "User deleted successfully!";
	public static final String USER_GET_RESPONSE = "User retrieved successfully!";
	public static final String INVALID_USER_RESPONSE = "Invalid user password/email!";
	public static final String USER_ALREADY_EXISTS_RESPONSE = "User already exists!...sign in instead";
	public static final String USER_REGISTRATION_RESPONSE = "User registerd successfully";
	public static final String USER_ENTER_STRONG_PASSWORD_RESPONSE = "Please enter a strong password!";
	public static final String USER_NOT_REGISTRED_RESPONSE = "User not registered!...please sign up";
	public static final String USER_SIGNUP_RESPONSE = "User registerd successfully!";
	public static final String INVALID_USER_PASSWORD_RESPONSE = "User password is Invalid!";
	public static final String USER_AUTHENTICATION_RESPONSE = "Authentication Successfull!";

	// Authentication
	public static final String AUTHENTICATION_TABLE_NAME = "authentication";
	public static final String AUTHENTICATION_ID = "id";
	public static final String AUTHENTICATION_TOKEN = "name";
	public static final String AUTHENTICATION_CREATED_DATE = "created_date";
	public static final String AUTHENTICATION_USER = "user_id";

	// Product
	public static final String PRODUCT_TABLE_NAME = "product";
	public static final String PRODUCT_ID = "id";
	public static final String PRODUCT_TITLE = "title";
	public static final String PRODUCT_DESCRIPTION = "description";
	public static final String PRODUCT_PRICE = "price";
	public static final String PRODUCT_DATE = "date";
	public static final String PRODUCT_TIME = "time";
	public static final String PRODUCT_USER = "user_id";
	
	public static final String PRODUCT_CREATE_RESPONSE = "Product added successfully!";
	public static final String PRODUCT_UPDATE_RESPONSE = "Product updated successfully!";
	public static final String PRODUCT_DELETE_RESPONSE = "Product deleted successfully!";
	public static final String PRODUCT_GET_RESPONSE = "Product retrieved successfully!";
	public static final String PRODUCT_GET_ALL_RESPONSE = "Products retrieved successfully!";
	public static final String PRODUCT_MONTHLY_REPORT_RESPONSE = "Monthly report generated successfully!";
	public static final String PRODUCT_NOT_FOUND_RESPONSE = "Product not found! / Invalid Product Id!";
	public static final String PRODUCT_GET_TOTAL_EXPENSE_RESPONSE = "Selected month's total expense retrieved successfully!";
	public static final String NO_PRODUCTS_FOUND_RESPONSE = "No products found for the selected month/date!";
	
}
