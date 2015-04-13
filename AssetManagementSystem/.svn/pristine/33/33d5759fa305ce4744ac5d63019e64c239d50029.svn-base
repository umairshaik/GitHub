package com.bil.ams.controller;

import java.io.IOException;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.bil.ams.database_manager.DatabaseManagement;
import com.bil.ams.models.AMSUser;
import com.bil.ams.models.AMSUserDBFields;
import com.bil.ams.models.Settings;

public class AMSController {

	// loads AFR database
	public static void loadAFRDatabase(Activity context) {
		DatabaseManagement databaseManagement = DatabaseManagement
				.getInstance(context);
		try {
			databaseManagement.createDataBase();
		} catch (IOException ioe) {
		}
	}

	// save setting vlaues into database
	public static long insertOrUpdateSettingsDataIntoDataBase(Context context,
			Settings settings) {
		DatabaseManagement databaseManagement = DatabaseManagement
				.getInstance(context);
		long rowsEffected;
		ContentValues contentValues = new ContentValues();

		contentValues.put(Settings.COLOUMN_SCHEDULER_ENABLED,
				settings.getmEnableScheduler());

		contentValues.put(Settings.COLOUMN_START_TIME,
				settings.getmStart_time());

		contentValues.put(Settings.COLOUMN_END_TIME, settings.getmEnd_ime());

		contentValues.put(Settings.COLOUMN_INTERVAL, settings.getmSync_time());

		contentValues.put(Settings.COLOUMN_PUSH_NOTIFICATION,
				settings.getmPush_notification());

		contentValues.put(Settings.COLOUMN_GEO_LOCATION,
				settings.getmGeo_location());

		contentValues.put(Settings.COLOUMN_ENABLE_FINANCIAL_INFO,
				settings.getmFinancial_info_capture());

		contentValues.put(Settings.COLOUMN_ENABLE_CONTRACT_INFO,
				settings.getmContract_info_capture());

		contentValues.put(Settings.COLOUMN_URL, settings.getmUrl());

		contentValues.put(Settings.COLOUMN_PATH, settings.getmPath());

		// check if data exits
		// if (!databaseManagement.checkDataExistsInTable(Settings.table_name,
		// "rowid=1", Settings.coloumn_settings_id)) {
		//
		// rowsEffected = databaseManagement.insertIntoDatabase(contentValues,
		// Settings.table_name);
		//
		// } else { // update database table
		rowsEffected = databaseManagement.updateDataBase(Settings.TABLE_NAME,
				contentValues, "rowid=1");

		// }
		return rowsEffected;
	}

	// fetching Settings from DB
	public static Settings getSettingsFromDatabase(Context context) {
		DatabaseManagement databaseManagement = DatabaseManagement
				.getInstance(context);
		Settings settings = new Settings();
		String query = "select * from " + Settings.TABLE_NAME;
		Cursor cursor = databaseManagement.executeQuery(query);
		if (cursor.moveToNext()) {

			settings.setmEnableScheduler(cursor.getInt(cursor
					.getColumnIndex(Settings.COLOUMN_SCHEDULER_ENABLED)));

			settings.setmStart_time(cursor.getString(cursor
					.getColumnIndex(Settings.COLOUMN_START_TIME)));

			settings.setmEnd_time(cursor.getString(cursor
					.getColumnIndex(Settings.COLOUMN_END_TIME)));

			settings.setmSync_time(cursor.getString(cursor
					.getColumnIndex(Settings.COLOUMN_INTERVAL)));

			settings.setmPush_notification(cursor.getInt(cursor
					.getColumnIndex(Settings.COLOUMN_PUSH_NOTIFICATION)));

			settings.setmFinancial_info_capture(cursor.getInt(cursor
					.getColumnIndex(Settings.COLOUMN_ENABLE_FINANCIAL_INFO)));

			settings.setmGeo_location(cursor.getInt(cursor
					.getColumnIndex(Settings.COLOUMN_GEO_LOCATION)));

			settings.setmContract_info_capture(cursor.getInt(cursor
					.getColumnIndex(Settings.COLOUMN_ENABLE_CONTRACT_INFO)));

			settings.setmUrl(cursor.getString(cursor
					.getColumnIndex(Settings.COLOUMN_URL)));

			settings.setmPath(cursor.getString(cursor
					.getColumnIndex(Settings.COLOUMN_PATH)));

		}
		databaseManagement.closeCursor(cursor);
		return settings;
	}

	public static AMSUser getAMSUsersListFromDabatase(Context context,
			String loginID, String password) {
		DatabaseManagement databaseManagement = DatabaseManagement
				.getInstance(context);
		AMSUser amsUser = null;
		String query = "SELECT  * FROM " + AMSUserDBFields.TABLE_NAME
				+ " where " + AMSUserDBFields.COLUMN_USER_ID + "='" + loginID
				+ "' and " + AMSUserDBFields.COLUMN_PASSWORD + "='"
				+ password + "';";
		Cursor cursor = databaseManagement.executeQuery(query);

		System.out.println(query);

		while (cursor.moveToNext()) {

			// do {

			try {
				amsUser = new AMSUser();

				amsUser.setId(cursor.getString(cursor
						.getColumnIndex(AMSUserDBFields.COLUMN_ID)));
				// amsUser.setPassword(cursor.getString(cursor
				// .getColumnIndex(AMSUser_DB_Fields.COLUMN_PASSWORD)));

				amsUser.setUser_id(cursor.getString(cursor
						.getColumnIndex(AMSUserDBFields.COLUMN_USER_ID)));

				amsUser.setFirst_name(cursor.getString(cursor
						.getColumnIndex(AMSUserDBFields.COLUMN_FIRST_NAME)));

				amsUser.setLast_name(cursor.getString(cursor
						.getColumnIndex(AMSUserDBFields.COLUMN_LAST_NAME)));

				amsUser.setDob(cursor.getString(cursor
						.getColumnIndex(AMSUserDBFields.COLUMN_DOB)));

				amsUser.setLast_login_date(cursor.getString(cursor
						.getColumnIndex(AMSUserDBFields.COLUMN_LAST_LOGIN_DATE)));

				amsUser.setCreated_by(cursor.getString(cursor
						.getColumnIndex(AMSUserDBFields.COLUMN_CREATED_BY)));

				amsUser.setCreated_on(cursor.getString(cursor
						.getColumnIndex(AMSUserDBFields.COLUMN_CREATED_ON)));

				amsUser.setUpdated_by(cursor.getString(cursor
						.getColumnIndex(AMSUserDBFields.COLUMN_UPDATED_BY)));

				amsUser.setUpdated_on(cursor.getString(cursor
						.getColumnIndex(AMSUserDBFields.COLUMN_UPDATED_ON)));

			} catch (NullPointerException n) {
				n.printStackTrace();
			}
		}
		return amsUser;
	}
}
