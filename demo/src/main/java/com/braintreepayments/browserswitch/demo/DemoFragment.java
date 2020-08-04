package com.braintreepayments.browserswitch.demo;

import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.braintreepayments.browserswitch.BrowserSwitchFragment;
import com.braintreepayments.browserswitch.BrowserSwitchResult;

public class DemoFragment extends BrowserSwitchFragment implements View.OnClickListener {

    private TextView mResult;
    private TextView mReturnUrl;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.demo_fragment, container, false);

        Button browserSwitchButton = view.findViewById(R.id.browser_switch);
        browserSwitchButton.setOnClickListener(this);

        mResult = view.findViewById(R.id.result);
        mReturnUrl = view.findViewById(R.id.return_url);

        return view;
    }

    @Override
    public void onClick(View v) {
        browserSwitch(1, "https://braintree.github.io/popup-bridge-example/" +
                "this_launches_in_popup.html?popupBridgeReturnUrlPrefix=" + getReturnUrlScheme()
                + "://");
    }

    @Override
    public void onBrowserSwitchResult(int requestCode, BrowserSwitchResult result, @Nullable Uri returnUri) {
        String resultText = null;
        String returnUrl = "";

        int statusCode = result.getStatus();
        switch (statusCode) {
            case BrowserSwitchResult.STATUS_OK:
                resultText = getString(R.string.browser_switch_success_text);
                if (returnUri != null) {
                    returnUrl = getString(R.string.browser_switch_uri_text, returnUri.toString());
                }
                break;
            case BrowserSwitchResult.STATUS_ERROR:
                resultText = getString(R.string.browser_switch_error_text, result.getErrorMessage());
                break;
            case BrowserSwitchResult.STATUS_CANCELED:
                resultText = getString(R.string.browser_switch_cancel_text);
                break;
        }
        mResult.setText(resultText);
        mReturnUrl.setText(returnUrl);
    }
}
