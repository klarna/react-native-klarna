package com.reactlibrary;

import android.view.View;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.bridge.ReactApplicationContext;

import com.klarna.checkout.KlarnaCheckout;
import com.klarna.checkout.SignalListener;

import android.app.Activity;

public class KlarnaView extends View {

    private KlarnaCheckout mCheckout;

    public KlarnaView(ThemedReactContext reactContext, ReactApplicationContext appContext) {
        super(reactContext, null);
        Activity activity = appContext.getCurrentActivity();
        mCheckout = new KlarnaCheckout(activity, "returnUrl");
        mCheckout.getView();
    }

    public void setSnippet(String snippet) {
        mCheckout.setSnippet(snippet);
    }
}
