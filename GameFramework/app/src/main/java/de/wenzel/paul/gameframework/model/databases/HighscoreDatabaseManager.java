package com.dreede.spaceinvaders.model.databases;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


/**
 * Die Klasse {@link HighscoreDatabaseManager} [...]
 * 
 * 
 * @author Paul Wenzel
 *
 */
public class HighscoreDatabaseManager extends SQLiteOpenHelper {
	
/////////////////////////////////////////////////Datenfelder/////////////////////////////////////////////////
	
	private static final String DB_NAME = "highscore.db";
	public static final String HIGHSCORE_TABLE_NAME = "highscore";
	public static final String POINTS_ROW_NAME = "points";
	public static final String WAVE_ROW_NAME = "wave";
	private static final int DB_VERSION = 1;
	
	private static final String CREATE_DATABASE = "CREATE TABLE " +  HIGHSCORE_TABLE_NAME + " ( " +
													"id INTEGER PRIMARY KEY AUTOINCREMENT, " +
													POINTS_ROW_NAME + " INTEGER NOT NULL, " +
													WAVE_ROW_NAME + " INTEGER NOT NULL" +
											      " )";
	private static final String DROP_DATABASE = "DROP TABLE IF EXISTS" + HIGHSCORE_TABLE_NAME;
	
/////////////////////////////////////////////////Konstruktor/////////////////////////////////////////////////
	
	/**
	 * Der Konstruktor der Klasse {@link HighscoreDatabaseManager}. 
	 */
	public HighscoreDatabaseManager(Context context) {
		super(context, DB_NAME, null, DB_VERSION);
		
		// Datenfelder initialisieren
		
	}
	
//////////////////////////////////////////////Getter und Setter//////////////////////////////////////////////
	
	
	
	
	
	
///////////////////////////////////////////////geerbte Methoden//////////////////////////////////////////////
	
	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(CREATE_DATABASE);
	}
	
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// Wenn die Datenbank ein neues Schema z.B. erhalten soll kann man hier genau definieren, wie die
		// alten bestehenden Daten in das neue Datenbankschema übertragen werden sollen!
		db.execSQL(DROP_DATABASE);
		onCreate(db);
	}
	
//////////////////////////////////////////////////Methoden///////////////////////////////////////////////////
	
	
	
	
///////////////////////////////////////////////Innere Klassen////////////////////////////////////////////////	
	
	
	
	
}
