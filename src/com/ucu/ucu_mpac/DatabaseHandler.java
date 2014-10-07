package com.ucu.ucu_mpac;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class DatabaseHandler extends SQLiteOpenHelper {

	private static int DATABASE_VERSION = 1;
	private static String DATABASE_NAME = "dbCollection";
	private static String TABLE_COLLECTION = "tblCollection";
	private static String COLLECTION_ID = "id";
	private static String COLLECTION_ACCESSNO = "accessno";
	private static String COLLECTION_TITLE = "title";
	private static String COLLECTION_AUTHOR = "author";
	private static String COLLECTION_PUBLISHER = "publisher";
	private static String COLLECTION_EDITION = "edition";
	private static String COLLECTION_VOLUME = "volume";
	private static String COLLECTION_PAGES = "pages";
	private static String COLLECTION_CYEAR = "cyear";
	private static String COLLECTION_CSECTION = "csection";
	private static String COLLECTION_COPIES = "copies";
	private static String COLLECTION_BABARCODE = "babarcode";
	private static String COLLECTION_COMPLETECN = "completecn";
	private static String COLLECTION_FORMAT = "format";

	private static String TABLE_FAVORITES = "tblFavorites";
	private static String FAVORITES_ID = "id";
	private static String FAVORITES_ACCESSNO = "accessno";
	private static String FAVORITES_TITLE = "title";
	private static String FAVORITES_AUTHOR = "author";
	private static String FAVORITES_PUBLISHER = "publisher";
	private static String FAVORITES_EDITION = "edition";
	private static String FAVORITES_VOLUME = "volume";
	private static String FAVORITES_PAGES = "pages";
	private static String FAVORITES_CYEAR = "cyear";
	private static String FAVORITES_CSECTION = "csection";
	private static String FAVORITES_COPIES = "copies";
	private static String FAVORITES_BABARCODE = "babarcode";
	private static String FAVORITES_COMPLETECN = "completecn";
	private static String FAVORITES_FORMAT = "format";
	
	private static String TABLE_HISTORY = "tblHistory";
	private static String HISTORY_ID = "id";
	private static String HISTORY_DATE = "date";
	private static String HISTORY_ACCESSNO = "accessno";
	private static String HISTORY_TITLE = "title";
	private static String HISTORY_AUTHOR = "author";
	private static String HISTORY_PUBLISHER = "publisher";
	private static String HISTORY_EDITION = "edition";
	private static String HISTORY_VOLUME = "volume";
	private static String HISTORY_PAGES = "pages";
	private static String HISTORY_CYEAR = "cyear";
	private static String HISTORY_CSECTION = "csection";
	private static String HISTORY_COPIES = "copies";
	private static String HISTORY_BABARCODE = "babarcode";
	private static String HISTORY_COMPLETECN = "completecn";
	private static String HISTORY_FORMAT = "format";

	private static final String collection = "CREATE TABLE " + TABLE_COLLECTION
			+ "(" + COLLECTION_ID + " INTEGER  PRIMARY KEY, " + COLLECTION_ACCESSNO + " TEXT, "
			+ COLLECTION_TITLE + " TEXT, " + COLLECTION_AUTHOR + " TEXT, " + COLLECTION_PUBLISHER + " TEXT, "
			+ COLLECTION_EDITION + " TEXT, " + COLLECTION_VOLUME + " TEXT, " + COLLECTION_PAGES + " TEXT, "
			+ COLLECTION_CYEAR + " TEXT, " + COLLECTION_CSECTION + " TEXT, " + COLLECTION_COPIES + " TEXT, "
			+ COLLECTION_BABARCODE + " TEXT, " + COLLECTION_COMPLETECN + " TEXT, " + COLLECTION_FORMAT + " TEXT " + ")";

	private static final String favorites = "CREATE TABLE " + TABLE_FAVORITES
			+ "(" + FAVORITES_ID + " INTEGER PRIMARY KEY, " + FAVORITES_ACCESSNO + " TEXT, "
			+ FAVORITES_TITLE + " TEXT, " + FAVORITES_AUTHOR + " TEXT, " + FAVORITES_PUBLISHER + " TEXT, "
			+ FAVORITES_EDITION + " TEXT, " + FAVORITES_VOLUME + " TEXT, " + FAVORITES_PAGES + " TEXT, "
			+ FAVORITES_CYEAR + " TEXT, " + FAVORITES_CSECTION + " TEXT, " + FAVORITES_COPIES + " TEXT, "
			+ FAVORITES_BABARCODE + " TEXT, " + FAVORITES_COMPLETECN + " TEXT, " + FAVORITES_FORMAT + " TEXT " + ")";

	private static final String history = "CREATE TABLE " + TABLE_HISTORY + "("
			+ HISTORY_ID + " INTEGER PRIMARY KEY, " + HISTORY_DATE + " TEXT, " + HISTORY_ACCESSNO + " TEXT, "
			+ HISTORY_TITLE + " TEXT, " + HISTORY_AUTHOR + " TEXT, " + HISTORY_PUBLISHER + " TEXT, "
			+ HISTORY_EDITION + " TEXT, " + HISTORY_VOLUME + " TEXT, " + HISTORY_PAGES + " TEXT, "
			+ HISTORY_CYEAR + " TEXT, " + HISTORY_CSECTION + " TEXT, " + HISTORY_COPIES + " TEXT, "
			+ HISTORY_BABARCODE + " TEXT, " + HISTORY_COMPLETECN + " TEXT, " + HISTORY_FORMAT + " TEXT " + ")";

	public DatabaseHandler(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(collection);
		db.execSQL(favorites);
		db.execSQL(history);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_COLLECTION);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_FAVORITES);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_HISTORY);
		onCreate(db);
	}
}
