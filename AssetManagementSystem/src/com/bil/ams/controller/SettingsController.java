package com.bil.ams.controller;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.bil.ams.database_manager.DatabaseManagement;
import com.bil.ams.models.Settings;

public class SettingsController {

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

		contentValues.put(Settings.COLOUMN_SYNC_BATTERY_STATUS,
				settings.getSync_batter_status());

		contentValues.put(Settings.COLOUMN_ENABLE_FINANCIAL_INFO,
				settings.getmFinancial_info_capture());

		contentValues.put(Settings.COLOUMN_ENABLE_CONTRACT_INFO,
				settings.getmContract_info_capture());

		contentValues.put(Settings.COLOUMN_URL, settings.getmUrl());

		contentValues.put(Settings.COLOUMN_PATH, settings.getmPath());

		contentValues.put(Settings.COLOUMN_GEO_LOCTION,
				settings.getGeo_status());

		// check if data exits
		// if (!databaseManagement.checkDataExistsInTable(Settings.table_name,
		// "rowid=1", Settings.coloumn_settings_id)) {
		//
		// rowsEffected = databaseManagement.insertIntoDatabase(contentValues,
		// Settings.table_name);
		//
		// } else { // update database table
		rowsEffected = databaseManagement.updateDataBase(Settings.TABLE_NAME,
				contentValues, Settings.COLOUMN_SETTINGS_ID + "=1");

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

			settings.setSync_batter_status(cursor.getInt(cursor
					.getColumnIndex(Settings.COLOUMN_SYNC_BATTERY_STATUS)));

			settings.setmContract_info_capture(cursor.getInt(cursor
					.getColumnIndex(Settings.COLOUMN_ENABLE_CONTRACT_INFO)));

			settings.setmUrl(cursor.getString(cursor
					.getColumnIndex(Settings.COLOUMN_URL)));

			settings.setmPath(cursor.getString(cursor
					.getColumnIndex(Settings.COLOUMN_PATH)));
			settings.setGeo_status(cursor.getInt(cursor
					.getColumnIndex(Settings.COLOUMN_GEO_LOCTION)));

		}
		databaseManagement.closeCursor(cursor);
		return settings;
	}
}
