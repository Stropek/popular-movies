package com.example.pscurzytek.popularmovies.data;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

public class MovieDataProvider extends ContentProvider {

    private Context _context;

    private static MovieDbHelper _db;
    private static UriMatcher _uriMatcher = buildUriMatcher();

    private static final int MOVIE_ENTRIES = 100;
    private static final int MOVIE_ENTRY_WITH_ID = 101;

    @Override
    public boolean onCreate() {
        _context = getContext();
        _db = new MovieDbHelper(_context);
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        SQLiteDatabase db = _db.getReadableDatabase();
        Cursor cursor;

        switch (_uriMatcher.match(uri)) {
            case MOVIE_ENTRIES:
                cursor = db.query(MovieContract.MovieEntry.TABLE_NAME,
                        projection,
                        selection,
                        selectionArgs,
                        null,
                        null,
                        sortOrder);
                break;
            default:
                throw new UnsupportedOperationException("Unknown operation URI: " + uri);
        }

        if (cursor != null) {
            if (_context != null) {
                cursor.setNotificationUri(_context.getContentResolver(), uri);
            }
        }
        return cursor;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        Uri returnUri;
        SQLiteDatabase db = _db.getWritableDatabase();

        switch (_uriMatcher.match(uri)) {
            case MOVIE_ENTRIES:
                long movieId = db.insert(MovieContract.MovieEntry.TABLE_NAME, null, values);
                if (movieId > -1) {
                    returnUri = uri.buildUpon().appendPath(String.format("%s", movieId)).build();
                } else {
                    throw new SQLException("Failed to insert a new movie into: " + uri);
                }
                break;
            default:
                throw new UnsupportedOperationException("Unknown operation URI: " + uri);
        }

        _context.getContentResolver().notifyChange(uri, null);
        return returnUri;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        int deleted;
        SQLiteDatabase db = _db.getWritableDatabase();

        switch (_uriMatcher.match(uri)) {
            case MOVIE_ENTRY_WITH_ID:
                String id = uri.getLastPathSegment();
                deleted = db.delete(MovieContract.MovieEntry.TABLE_NAME, MovieContract.MovieEntry._ID + "=?", new String[] {id});
                break;
            default:
                throw new UnsupportedOperationException("Unknown operation URI: " + uri);
        }

        return deleted;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    private static UriMatcher buildUriMatcher() {
        UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

        uriMatcher.addURI(MovieContract.AUTHORITY, MovieContract.PATH_MOVIES, MOVIE_ENTRIES);
        uriMatcher.addURI(MovieContract.AUTHORITY, MovieContract.PATH_MOVIES + "/#", MOVIE_ENTRY_WITH_ID);

        return uriMatcher;
    }
}
