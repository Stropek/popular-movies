package com.example.pscurzytek.popularmovies.data;

import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Context;
import android.content.pm.PackageManager;
import android.content.pm.ProviderInfo;
import android.database.ContentObserver;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.example.pscurzytek.popularmovies.utils.TestUtilities;

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
    public void query_with_unknown_uri_should_throw_unsupported_operation_exception() {
        // given
        Uri unknownUri = MovieContract.BASE_CONTENT_URI.buildUpon().appendPath("unknown").build();
        setObservedUriOnContentResolver(_context.getContentResolver(), unknownUri, TestUtilities.getTestContentObserver());

        // when
        _context.getContentResolver().query(unknownUri, null, null, null, null);
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
}
