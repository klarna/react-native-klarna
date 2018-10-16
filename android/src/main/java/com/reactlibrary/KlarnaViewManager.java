package com.reactlibrary;

import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.facebook.react.bridge.ReactApplicationContext;

import android.app.Activity;
import android.view.View;
import android.util.Log;

import com.klarna.checkout.KlarnaCheckout;
import com.klarna.checkout.SignalListener;

import org.json.JSONException;
import org.json.JSONObject;

public class KlarnaViewManager extends SimpleViewManager<View> {

  private final ReactApplicationContext appContext;
  private KlarnaCheckout mCheckout;
  public static final String REACT_CLASS = "RNKlarna";

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
   if (activity != null) {
      String name = this.appContext.getPackageName();
      mCheckout = new KlarnaCheckout(activity, name);
      mCheckout.setSnippet("snippet");
      mCheckout.setSignalListener(new SignalListener() {
            @Override
            public void onSignal(String eventName, JSONObject jsonObject) {
                if (eventName.equals("complete")) {
                    try {
                        String url = jsonObject.getString("uri");
                        
                    } catch (JSONException e) {
                        Log.e(e.getMessage(), e.toString());
                    }
                }
            }
      });
      View klarnaView = mCheckout.getView();
      if (klarnaView != null) {
        return mCheckout.getView();
      } else {
        return new View(this.appContext);
      }
   } else {
     return new View(this.appContext);
   }
  }

  @ReactProp(name = "snippet")
  public void setSnippet(View view, String snippet) {
      mCheckout.setSnippet(snippet);
  }
}