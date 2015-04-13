package com.bil.ams.database_manager;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseManagement extends SQLiteOpenHelper {

	private static DatabaseManagement mDatabaseManagement;
	private final static String DB_NAME = "AMS.db";
	private String mDbPath;
	private Context mContext;
	private static final int DATABASE_VERSION = 1;

	// Singleton
	public static synchronized DatabaseManagement getInstance(Context context) {
		if (mDatabaseManagement == null) {
			mDatabaseManagement = new DatabaseManagement(context);
		}
		return mDatabaseManagement;
	}

	public DatabaseManagement(Context context) {
		super(context, DB_NAME, null, DATABASE_VERSION);
		mContext = context;
		mDbPath = context.getDatabasePath(DB_NAME).toString();
	}

	/**
	 * Creates a empty database on the system and rewrites it with your own
	 * database.
	 * */
	public void createDataBase() throws IOException {

		boolean dbExist = checkDataBase();

		if (!dbExist) {
			// do nothing - database already exist
			// By calling this method and empty database will be created into
			// the default system path
			// of your application so we are gonna be able to overwrite that
			// database with our database.
			this.getReadableDatabase();

			try {
				copyDataBase();

			} catch (IOException e) {

				throw new Error("Error copying database");

			}
		}

	}

	/**
	 * Check if the database already exist to avoid re-copying the file each
	 * time you open the application.
	 * 
	 * @return true if it exists, false if it doesn't
	 */
	private boolean checkDataBase() {

		SQLiteDatabase checkDB = null;

		try {
			// String myPath = DB_PATH;
			checkDB = SQLiteDatabase.openDatabase(mDbPath, null,
					SQLiteDatabase.OPEN_READONLY);

		} catch (SQLiteException e) {

			// database does't exist yet.
		}

		if (checkDB != null) {
			checkDB.close();
		}
		return checkDB != null ? true : false;
	}

	/**
	 * Copies your database from your local assets-folder to the just created
	 * empty database in the system folder, from where it can be accessed and
	 * handled. This is done by transfering bytestream.
	 * */
	private void copyDataBase() throws IOException {

		// Open your local db as the input stream
		InputStream myInput = mContext.getAssets().open(DB_NAME);

		// Path to the just created empty db
		String outFileName = mDbPath;

		// Open the empty db as the output stream
		OutputStream myOutput = new FileOutputStream(outFileName);

		// transfer bytes from the inputfile to the outputfile
		byte[] buffer = new byte[1024];
		int length;
		while ((length = myInput.read(buffer)) > 0) {
			myOutput.write(buffer, 0, length);
		}

		// Close the streams
		myOutput.flush();
		myOutput.close();
		myInput.close();

	}

	// method for executing query and returns cursor
	public Cursor executeQuery(String query) {
		Cursor cursor = null;
		try {
			SQLiteDatabase sqliteDatabase = this.getWritableDatabase();
			cursor = sqliteDatabase.rawQuery(query, null);
		} catch (SQLiteException sqle) {
			cursor = null;
		}
		return cursor;
	}

	// method to close cursor
	public void closeCursor(Cursor cursor) {
		if (cursor != null) {
			cursor.close();
		}
	}

	/*
	 * updates the given value in to the database
	 * 
	 * @param tableName: name of the table
	 * 
	 * @param contentValues: valued to be updated in Database
	 * 
	 * @param whereCondition: where condition to be applied for query
	 */
	public int updateDataBase(String tableName, ContentValues contentValues,
			String whereCondition) {

		SQLiteDatabase database = this.getWritableDatabase();
		int ret = database.update(tableName, contentValues, whereCondition,
				null);
		return ret;

	}

	// method to insert data
	public long insertIntoDatabase(ContentValues contentValues, String tableName) {
		SQLiteDatabase database = this.getWritableDatabase();
		long l = database.insert(tableName, null, contentValues);
		// database.close();
		return l;
	}

	// method for deleting data
	public int deleteFromDatabase(String tableName, String whereCondition) {
		SQLiteDatabase database = this.getWritableDatabase();
		return database.delete(tableName, whereCondition, null);
	}

	// check whether row exists in DB before trying to insert

	/**
	 * @param table
	 *            :name of the table
	 * @param whereCondition
	 *            : condition to check
	 * @param column
	 *            : on coloum
	 * 
	 * 
	 * */
	public boolean checkDataExistsInTable(String table, String whereCondition,
			String column) {
		SQLiteDatabase database = this.getReadableDatabase();

		Cursor cursor = database
				.rawQuery(" Select count(" + column + ") as count from "
						+ table + " where " + whereCondition, null);
		// Cursor cursor = database.rawQuery("select * from "+table, null);
		int totalCount = 0;
		if (cursor.moveToFirst()) {
			totalCount = cursor.getInt(cursor.getColumnIndex("count"));
		}
		Log.v("exits", table + "  : " + totalCount);
		closeCursor(cursor);
		if (totalCount > 0) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub

	}

	// return sys Id of the table name passed
	// public int getSysId(String tableName, String coulmnName) {
	// int sysId = 0;
	// String query = "SELECT " + coulmnName + " FROM " + tableName
	// + " WHERE ROWID=(SELECT MAX(ROWID) FROM " + tableName + ");";
	//
	// Cursor cursor = this.executeQuery(query);
	//
	// // looping through all rows and adding to list
	// if (cursor.moveToFirst()) {
	// do {
	// sysId = Integer.parseInt(cursor.getString(0) == null ? "0"
	// : cursor.getString(0));
	// sysId++;
	//
	// } while (cursor.moveToNext());
	// }
	// closeCursor(cursor);
	//
	// return sysId;
	// }

}
