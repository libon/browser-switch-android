package com.braintreepayments.browserswitch;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.VisibleForTesting;

/**
 * <a href="https://developer.android.com/guide/topics/manifest/activity-element.html#lmode">singleTask</a>
 * Activity used to receive the response from a browser switch. This Activity contains no UI and
 * finishes during {@link Activity#onCreate(Bundle)}.
 */
public class BrowserSwitchActivity extends Activity {

    @VisibleForTesting
    // TODO: Refactor browser-switch-android to allow injection of a custom url scheme
    // via manifest. Ref: https://developer.android.com/studio/build/manifest-build-variables
    BrowserSwitchClient browserSwitchClient = BrowserSwitchClient.newInstance(null);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        browserSwitchClient.captureResult(getIntent(), this);
        finish();
    }
}
