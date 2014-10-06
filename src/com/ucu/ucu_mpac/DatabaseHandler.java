package com.ucu.ucu_mpac;

import java.sql.SQLException;
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
	private static String DATABASE_NAME = "dbBook";
	// TABLE
	private static String TABLE_COLLECTION = "tblCollection";
	private static String TABLE_FAVORITES = "tblFavorites";
	private static String TABLE_HISTORY = "tblHistory";
	// COLUMNS
	private static String ID = "id";
	private static String DATE = "date";
	private static String ACCESSNO = "accessno";
	private static String TITLE = "title";
	private static String AUTHOR = "author";
	private static String PUBLISHER = "publisher";
	private static String EDITION = "edition";
	private static String VOLUME = "volume";
	private static String PAGES = "pages";
	private static String CYEAR = "cyear";
	private static String CSECTION = "csection";
	private static String COPIES = "copies";
	private static String BABARCODE = "babarcode";
	private static String COMPLETECN = "completecn";
	private static String FORMAT = "format";
	
	private static final String collection = "CREATE TABLE " + TABLE_COLLECTION
			+ "(" + ID + " INTEGER, " + ACCESSNO + " TEXT PRIMARY KEY, " + TITLE
			+ " TEXT, " + AUTHOR + " TEXT, " + PUBLISHER + " TEXT, " + EDITION
			+ " TEXT, " + VOLUME + " TEXT, " + PAGES + " TEXT, " + CYEAR
			+ " TEXT, " + CSECTION + " TEXT, " + COPIES + " TEXT, " + BABARCODE
			+ " TEXT, " + COMPLETECN + " TEXT, " + FORMAT + " TEXT " + ")";
	
	private static final String favorites = "CREATE TABLE " + TABLE_FAVORITES
			+ "(" + ID + " INTEGER PRIMARY KEY, " + ACCESSNO + " TEXT, " + TITLE
			+ " TEXT, " + AUTHOR + " TEXT, " + PUBLISHER + " TEXT, " + EDITION
			+ " TEXT, " + VOLUME + " TEXT, " + PAGES + " TEXT, " + CYEAR
			+ " TEXT, " + CSECTION + " TEXT, " + COPIES + " TEXT, " + BABARCODE
			+ " TEXT, " + COMPLETECN + " TEXT, " + FORMAT + " TEXT " + ")";

	private static final String history = "CREATE TABLE " + TABLE_HISTORY
			+ "(" + ID + " INTEGER PRIMARY KEY, " + DATE + " TEXT, " + ACCESSNO + " TEXT, " + TITLE
			+ " TEXT, " + AUTHOR + " TEXT, " + PUBLISHER + " TEXT, " + EDITION
			+ " TEXT, " + VOLUME + " TEXT, " + PAGES + " TEXT, " + CYEAR
			+ " TEXT, " + CSECTION + " TEXT, " + COPIES + " TEXT, " + BABARCODE
			+ " TEXT, " + COMPLETECN + " TEXT, " + FORMAT + " TEXT " + ")";

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
	
	void DropTable() {
		SQLiteDatabase db = this.getWritableDatabase();
		onUpgrade(db, 1, 1);
	}
	
	// INSERT TO COLLECTION
	void addCollection(Collection c) {
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues cv = new ContentValues();

		cv.put(ID, c.getId());
		cv.put(ACCESSNO, c.getAccessno());
		cv.put(TITLE, c.getTitle());
		cv.put(AUTHOR, c.getAuthor());
		cv.put(PUBLISHER, c.getPublisher());
		cv.put(EDITION, c.getEdition());
		cv.put(VOLUME, c.getVolume());
		cv.put(PAGES, c.getPages());
		cv.put(CYEAR, c.getCyear());
		cv.put(CSECTION, c.getCsection());
		cv.put(COPIES, c.getCopies());
		cv.put(BABARCODE, c.getBabarcode());
		cv.put(COMPLETECN, c.getCompletecn());
		cv.put(FORMAT, c.getFormat());

		db.insert(TABLE_COLLECTION, null, cv);
		db.close();
	}
	
	public Collection search(String title) {

		String selectQuery = "SELECT  * FROM " + TABLE_COLLECTION + " WHERE " + TITLE + " = " + title;

		SQLiteDatabase db = this.getReadableDatabase();
		Cursor c = db.rawQuery(selectQuery, null);

		if (c != null)
			c.moveToFirst();

			Collection col = new Collection();
			
			col.setId(Integer.parseInt(c.getString(0)));
			col.setAccessno(c.getString(1));
			col.setTitle(c.getString(2));
			col.setAuthor(c.getString(3));
			col.setPublisher(c.getString(4));
			col.setEdition(c.getString(5));
			col.setVolume(c.getString(6));
			col.setPages(c.getString(7));
			col.setCyear(c.getString(8));
			col.setCsection(c.getString(9));
			col.setCopies(c.getString(10));
			col.setBabarcode(c.getString(11));
			col.setCompletecn(c.getString(12));
			col.setFormat(c.getString(13));

		return col;
	}
	
	public List<Collection> getAllCollection() {
		List<Collection> list = new ArrayList<Collection>();
		String query = "SELECT * FROM " + TABLE_COLLECTION + " ORDER BY " + TITLE + " COLLATE NOCASE";

		SQLiteDatabase db = this.getWritableDatabase();
		Cursor c = db.rawQuery(query, null);

		if (c.moveToFirst()) {

			do {

				Collection col = new Collection();

				col.setId(Integer.parseInt(c.getString(0)));
				col.setAccessno(c.getString(1));
				col.setTitle(c.getString(2));
				col.setAuthor(c.getString(3));
				col.setPublisher(c.getString(4));
				col.setEdition(c.getString(5));
				col.setVolume(c.getString(6));
				col.setPages(c.getString(7));
				col.setCyear(c.getString(8));
				col.setCsection(c.getString(9));
				col.setCopies(c.getString(10));
				col.setBabarcode(c.getString(11));
				col.setCompletecn(c.getString(12));
				col.setFormat(c.getString(13));

				list.add(col);

			} while (c.moveToNext());

		}

		return list;

	}
	
	// INSERT TO FAVORITES
	void addFavorites(Favorites f) {
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues cv = new ContentValues();

		cv.put(ACCESSNO, f.getAccessno());
		cv.put(TITLE, f.getTitle());
		cv.put(AUTHOR, f.getAuthor());
		cv.put(PUBLISHER, f.getPublisher());
		cv.put(EDITION, f.getEdition());
		cv.put(VOLUME, f.getVolume());
		cv.put(PAGES, f.getPages());
		cv.put(CYEAR, f.getCyear());
		cv.put(CSECTION, f.getCsection());
		cv.put(COPIES, f.getCopies());
		cv.put(BABARCODE, f.getBabarcode());
		cv.put(COMPLETECN, f.getCompletecn());
		cv.put(FORMAT, f.getFormat());

		db.insert(TABLE_FAVORITES, null, cv);
		db.close();
	}

	// Deleting single favorites
	public void deleteFavorites(Favorites f) {
		SQLiteDatabase db = this.getWritableDatabase();
		db.delete(TABLE_FAVORITES, ID + " = ?", new String[] { String.valueOf(f.getId()) });
		db.close();
	}
	
	// SELECT COMMAND
	public Cursor getAccessno(String accessno) throws SQLException {
		SQLiteDatabase db = this.getWritableDatabase();
		Cursor c = db.query(true, TABLE_FAVORITES, new String[] { ACCESSNO,
					TITLE, AUTHOR, PUBLISHER, EDITION, VOLUME, PAGES, CYEAR,
					CSECTION, COPIES, BABARCODE, COMPLETECN, FORMAT }, ACCESSNO
					+ "='" + accessno + "' ", null, null, null, null, null);
		return c;
	}

	public List<Favorites> getAllFavorites() {
		List<Favorites> list = new ArrayList<Favorites>();
		String query = "SELECT * FROM " + TABLE_FAVORITES + " ORDER BY " + TITLE + " COLLATE NOCASE";

		SQLiteDatabase db = this.getWritableDatabase();
		Cursor c = db.rawQuery(query, null);

		if (c.moveToFirst()) {

			do {

				Favorites fav = new Favorites();

				fav.setId(Integer.parseInt(c.getString(0)));
				fav.setAccessno(c.getString(1));
				fav.setTitle(c.getString(2));
				fav.setAuthor(c.getString(3));
				fav.setPublisher(c.getString(4));
				fav.setEdition(c.getString(5));
				fav.setVolume(c.getString(6));
				fav.setPages(c.getString(7));
				fav.setCyear(c.getString(8));
				fav.setCsection(c.getString(9));
				fav.setCopies(c.getString(10));
				fav.setBabarcode(c.getString(11));
				fav.setCompletecn(c.getString(12));
				fav.setFormat(c.getString(13));

				list.add(fav);

			} while (c.moveToNext());

		}

		return list;

	}

	// INSERT TO HISTORY
	void addHistory(History h) {
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues cv = new ContentValues();

		cv.put(DATE, h.getDate());
		cv.put(ACCESSNO, h.getAccessno());
		cv.put(TITLE, h.getTitle());
		cv.put(AUTHOR, h.getAuthor());
		cv.put(PUBLISHER, h.getPublisher());
		cv.put(EDITION, h.getEdition());
		cv.put(VOLUME, h.getVolume());
		cv.put(PAGES, h.getPages());
		cv.put(CYEAR, h.getCyear());
		cv.put(CSECTION, h.getCsection());
		cv.put(COPIES, h.getCopies());
		cv.put(BABARCODE, h.getBabarcode());
		cv.put(COMPLETECN, h.getCompletecn());
		cv.put(FORMAT, h.getFormat());

		db.insert(TABLE_HISTORY, null, cv);
		db.close();
	}

	public List<History> getAllHistory() {
		List<History> list = new ArrayList<History>();
		String query = "SELECT * FROM " + TABLE_HISTORY + " ORDER BY " + ID + " DESC";

		SQLiteDatabase db = this.getWritableDatabase();
		Cursor c = db.rawQuery(query, null);

		if (c.moveToFirst()) {

			do {

				History his = new History();

				his.setId(Integer.parseInt(c.getString(0)));
				his.setDate(c.getString(1));
				his.setAccessno(c.getString(2));
				his.setTitle(c.getString(3));
				his.setAuthor(c.getString(4));
				his.setPublisher(c.getString(5));
				his.setEdition(c.getString(6));
				his.setVolume(c.getString(7));
				his.setPages(c.getString(8));
				his.setCyear(c.getString(9));
				his.setCsection(c.getString(10));
				his.setCopies(c.getString(11));
				his.setBabarcode(c.getString(12));
				his.setCompletecn(c.getString(13));
				his.setFormat(c.getString(14));

				list.add(his);

			} while (c.moveToNext());

		}

		return list;

	}
}
