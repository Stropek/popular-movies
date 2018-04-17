package com.example.pscurzytek.popularmovies.data;

import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.pm.PackageManager;
import android.content.pm.ProviderInfo;
import android.database.ContentObserver;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.example.pscurzytek.popularmovies.utils.TestContentObserver;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.fail;

@RunWith(AndroidJUnit4.class)
public class MovieDataProviderTests {

    private final Context _context = InstrumentationRegistry.getTargetContext();

    @Before
    public void setUp() {
        MovieDbHelper dbHelper = new MovieDbHelper(_context);
        SQLiteDatabase database = dbHelper.getWritableDatabase();

        database.delete(MovieContract.MovieEntry.TABLE_NAME, null, null);
    }

    @Test
    public void provider_is_registered_correctly() {
        String packageName = _context.getPackageName();
        String movieProviderClassName = MovieDataProvider.class.getName();
        ComponentName componentName = new ComponentName(packageName, movieProviderClassName);

        try {
            ProviderInfo providerInfo = _context.getPackageManager().getProviderInfo(componentName, 0);
            String actualAuthority = providerInfo.authority;

            assertEquals("Error: content provider registered with authority " + actualAuthority + " instead of expected " + packageName,
                    packageName, actualAuthority);
        }
        catch (PackageManager.NameNotFoundException ex) {
            fail("Error: content provider not registered at " + packageName);
        }
    }

    @Test(expected = UnsupportedOperationException.class)
    public void insert_with_unknown_uri_should_throw_unsupported_operation_exception() {
        // given
        Uri unknownUri = MovieContract.BASE_CONTENT_URI.buildUpon().appendPath("unknown").build();
        setObservedUriOnContentResolver(_context.getContentResolver(), unknownUri, TestContentObserver.getTestContentObserver());

        // when
        _context.getContentResolver().insert(unknownUri, new ContentValues());
    }
    @Test
    public void insert_to_movies_with_valid_parameters_should_succeed() {
        // given
        ContentResolver contentResolver = _context.getContentResolver();
        TestContentObserver contentObserver = TestContentObserver.getTestContentObserver();
        Uri uri = MovieContract.MovieEntry.CONTENT_URI;

        setObservedUriOnContentResolver(contentResolver, uri, contentObserver);

        // when
        Uri expectedUri = uri.buildUpon().appendPath("1").build();
        Uri actualUri = insertMovie(contentResolver, uri, "test title");

        // then
        assertEquals("Unable to insert item through provider", expectedUri, actualUri);

        contentObserver.waitForNotificationOrFail();
        contentResolver.unregisterContentObserver(contentObserver);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void query_with_unknown_uri_should_throw_unsupported_operation_exception() {
        // given
        Uri unknownUri = MovieContract.BASE_CONTENT_URI.buildUpon().appendPath("unknown").build();
        setObservedUriOnContentResolver(_context.getContentResolver(), unknownUri, TestContentObserver.getTestContentObserver());

        // when
        _context.getContentResolver().query(unknownUri, null, null, null, null);
    }
    @Test
    public void query_with_movies_content_uri_returns_all_movies() {
        // given
        ContentResolver contentResolver = _context.getContentResolver();
        ContentObserver contentObserver = TestContentObserver.getTestContentObserver();
        Uri uri = MovieContract.MovieEntry.CONTENT_URI;

        setObservedUriOnContentResolver(contentResolver, uri, contentObserver);

        for (int i = 0; i < 10; i++) {
            insertMovie(contentResolver, uri, "movie " + i);
        }

        // when
        Cursor movies = contentResolver.query(uri, null, null, null, null);

        // then
        assertEquals("Unexpected number of movies", movies.getCount(), 10);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void delete_with_unknown_uri_should_throw_unsupported_operation_exception() {
        // given
        Uri unknownUri = MovieContract.BASE_CONTENT_URI.buildUpon().appendPath("unknown").build();
        setObservedUriOnContentResolver(_context.getContentResolver(), unknownUri, TestContentObserver.getTestContentObserver());

        // when
        _context.getContentResolver().delete(unknownUri, null, null);
    }
    @Test
    public void delete_from_movies_with_existing_id_should_succeed() {
        // given
        ContentResolver contentResolver = _context.getContentResolver();
        TestContentObserver contentObserver = TestContentObserver.getTestContentObserver();
        Uri uri = MovieContract.MovieEntry.CONTENT_URI;
        Uri existingUri = uri.buildUpon().appendPath("1").build();

        setObservedUriOnContentResolver(contentResolver, uri, contentObserver);

        insertMovie(contentResolver, uri, "test movie");

        // when
        int deleted = contentResolver.delete(existingUri, null, null);

        // then
        assertEquals(1, deleted);
        Cursor movies = contentResolver.query(uri, null, null, null, null);
        assertEquals(0, movies.getCount());
    }

    private void setObservedUriOnContentResolver(ContentResolver contentResolver, Uri uri, ContentObserver contentObserver) {
        contentResolver.registerContentObserver(
                /* URI that we would like to observe changes to */
                uri,
                /* Whether or not to notify us if descendants of this URI change */
                true,
                /* The observer to register (that will receive notifyChange callbacks) */
                contentObserver);
    }

    private Uri insertMovie(ContentResolver contentResolver, Uri uri, String title) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(MovieContract.MovieEntry.COLUMN_TITLE, title);

        return contentResolver.insert(uri, contentValues);
    }
}
