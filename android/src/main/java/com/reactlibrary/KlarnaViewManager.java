package com.reactlibrary;

import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.facebook.react.bridge.ReactApplicationContext;

import android.app.Activity;
import android.view.View;

import com.klarna.checkout.KlarnaCheckout;
import com.klarna.checkout.SignalListener;

public class KlarnaViewManager extends SimpleViewManager<View> {

  private final ReactApplicationContext appContext;
  private KlarnaCheckout mCheckout;
  public static final String REACT_CLASS = "Klarna";

  public KlarnaViewManager(ReactApplicationContext context) {
    this.appContext = context;
  }

  @Override
  public String getName() {
    return REACT_CLASS;
  }

  @Override
  protected View createViewInstance(ThemedReactContext themedReactContext) {
    Activity activity = this.appContext.getCurrentActivity();
    mCheckout = new KlarnaCheckout(activity, "returnUrl");
    return mCheckout.getView();
  }

  @ReactProp(name = "snippet")
  public void setSnippet(String snippet) {
    mCheckout.setSnippet(snippet);
  }
}