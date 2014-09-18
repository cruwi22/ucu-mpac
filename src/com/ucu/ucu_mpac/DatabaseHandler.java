package com.ucu.ucu_mpac;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHandler extends SQLiteOpenHelper {

	// DATABASE VERSION
	private static int DATABASE_VERSION = 1;
	// DATABASE
	private static String DATABASE_NAME = "dbCollection";
	// TABLE
	private static String TABLE_NAME = "tblBook";
	// COLUMNS	
	private static String COL_ACCESSNO = "accessno";
	private static String COL_TITLE = "title";
	private static String COL_AUTHOR = "author";
	private static String COL_PUBLISHER = "publisher";
	private static String COL_EDITION = "edition";
	private static String COL_VOLUME = "volume";
	private static String COL_PAGES = "pages";
	private static String COL_CYEAR = "cyear";
	private static String COL_CSECTION = "csection";
	private static String COL_COPIES = "copies";
	private static String COL_BABARCODE = "babarcode";
	private static String COL_COMPLETECN = "completecn";
	private static String COL_FORMAT = "format";

	public DatabaseHandler(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		String query = "CREATE TABLE " + TABLE_NAME + "(" + COL_ACCESSNO
				+ " TEXT PRIMARY KEY, " + COL_TITLE + " TEXT, " + COL_AUTHOR
				+ " TEXT, " + COL_PUBLISHER + " TEXT, " + COL_EDITION
				+ " TEXT, " + COL_VOLUME + " TEXT, " + COL_PAGES + " TEXT, "
				+ COL_CYEAR + " TEXT, " + COL_CSECTION + " TEXT, " + COL_COPIES
				+ " TEXT, " + COL_BABARCODE + " TEXT, " + COL_COMPLETECN
				+ " TEXT, " + COL_FORMAT + " TEXT " + ")";
		db.execSQL(query);

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int arg1, int arg2) {
		String query = "DROP TABLE IF EXISTS " + TABLE_NAME;
		db.execSQL(query);
		onCreate(db);

	}

	void addFavorites(Favorites f) {
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues cv = new ContentValues();

		cv.put(COL_ACCESSNO, f.getAccessno());
		cv.put(COL_TITLE, f.getTitle());
		cv.put(COL_AUTHOR, f.getAuthor());
		cv.put(COL_PUBLISHER, f.getPublisher());
		cv.put(COL_EDITION, f.getEdition());
		cv.put(COL_VOLUME, f.getVolume());
		cv.put(COL_PAGES, f.getPages());
		cv.put(COL_CYEAR, f.getCyear());
		cv.put(COL_CSECTION, f.getCsection());
		cv.put(COL_COPIES, f.getCopies());
		cv.put(COL_BABARCODE, f.getBabarcode());
		cv.put(COL_COMPLETECN, f.getCompletecn());
		cv.put(COL_FORMAT, f.getFormat());

		db.insert(TABLE_NAME, null, cv);
		db.close();
	}

	public List<Favorites> getAllFavorites() {
		List<Favorites> list = new ArrayList<Favorites>();
		String query = "SELECT * FROM " + TABLE_NAME;

		SQLiteDatabase db = this.getWritableDatabase();
		Cursor c = db.rawQuery(query, null);

		if (c.moveToFirst()) {

			do {

				Favorites fav = new Favorites();
				
				fav.setAccessno(c.getString(0));
				fav.setTitle(c.getString(1));
				fav.setAuthor(c.getString(2));
				fav.setPublisher(c.getString(3));
				fav.setEdition(c.getString(4));
				fav.setVolume(c.getString(5));
				fav.setPages(c.getString(6));
				fav.setCyear(c.getString(7));
				fav.setCsection(c.getString(8));
				fav.setCopies(c.getString(9));
				fav.setBabarcode(c.getString(10));
				fav.setCompletecn(c.getString(11));
				fav.setFormat(c.getString(12));
				
				list.add(fav);

			} while (c.moveToNext());

		}

		return list;

	}

}
