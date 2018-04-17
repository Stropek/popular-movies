package com.example.pscurzytek.popularmovies.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MovieDbHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "movies.db";
    private static final int DATABASE_VERSION = 1;

    MovieDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        final String createMoviesEntriesTable = "CREATE TABLE " +
                MovieContract.MovieEntry.TABLE_NAME + "(" +
                MovieContract.MovieEntry._ID + " INTEGER PRIMARY KEY," +
                MovieContract.MovieEntry.COLUMN_TITLE + " TEXT NOT NULL," +
                MovieContract.MovieEntry.COLUMN_SYNOPSIS + " TEXT," +
                MovieContract.MovieEntry.COLUMN_POSTER + " TEXT," +
                MovieContract.MovieEntry.COLUMN_RELEASE_DATE + " TEXT," +
                MovieContract.MovieEntry.COLUMN_RATING + " INT" +
                ");";

        db.execSQL(createMoviesEntriesTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//        if (oldVersion < 2) {
//            db.execSQL(DATABASE_ALTER_TABLE_1)
//        }
    }
}
