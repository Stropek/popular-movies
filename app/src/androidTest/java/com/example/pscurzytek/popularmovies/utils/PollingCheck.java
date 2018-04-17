package com.example.pscurzytek.popularmovies.utils;

import junit.framework.Assert;

import java.util.concurrent.Callable;

import kotlin.jvm.Throws;

abstract class PollingCheck {

    private static final Long TIME_SLICE = 50L;
    private Long _timeout;

    PollingCheck(Long timeout) {
        _timeout = timeout;
    }

    protected abstract Boolean check();

    public void run() {
        if (check()) {
            return;
        }

        Long timeout = _timeout;
        while (timeout > 0) {
            try {
                Thread.sleep(TIME_SLICE);
            } catch (InterruptedException e) {
                Assert.fail("Notification error, unexpected InterruptedException");
            }

            if (check()) {
                return;
            }

            timeout -= TIME_SLICE;
        }

        Assert.fail("Notification not set, unexpected timeout");
    }

    @Throws(exceptionClasses = Exception.class)
    public static void check(CharSequence message, Long timeout, Callable<Boolean> condition) throws Exception {
        while (timeout > 0) {
            if (condition.call()) {
                return;
            }

            Thread.sleep(TIME_SLICE);
            timeout -= TIME_SLICE;
        }

        Assert.fail(message.toString());
    }
}
