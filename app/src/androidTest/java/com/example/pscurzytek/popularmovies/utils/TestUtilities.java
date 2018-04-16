package com.example.pscurzytek.popularmovies.utils;

import android.database.ContentObserver;
import android.net.Uri;
import android.os.Handler;
import android.os.HandlerThread;

public class TestUtilities {

    public static TestContentObserver getTestContentObserver() {
        return TestContentObserver.getTestContentObserver();
    }

    static class TestContentObserver extends ContentObserver {
        Boolean _contentChanged = false;
        HandlerThread _handlerThread;

        private TestContentObserver(HandlerThread handlerThread) {
            super(new Handler(handlerThread.getLooper()));
            _handlerThread = handlerThread;
        }

        @Override
        public void onChange(boolean selfChange) {
            onChange(selfChange, null);
        }

        @Override
        public void onChange(boolean selfChange, Uri uri) {
            _contentChanged = true;
        }

        public void waitForNotificationOrFail() {
            new PollingCheck(5000L) {
                @Override
                public Boolean check() {
                    return _contentChanged;
                }
            }.run();

            _handlerThread.quit();
        }

            static TestContentObserver getTestContentObserver()
            {
                HandlerThread ht = new HandlerThread("ContentObserverThread");
                ht.start();
                return new TestContentObserver(ht);
            }
    }
}
