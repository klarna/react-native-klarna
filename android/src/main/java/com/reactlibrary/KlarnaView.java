package com.reactlibrary;

import android.util.Log;
import android.view.View;

import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.UIManagerModule;

import com.klarna.checkout.KlarnaCheckout;
import com.klarna.checkout.SignalListener;

import android.app.Activity;

import org.json.JSONObject;
import org.json.JSONException;


public class KlarnaView extends View {

  private final ThemedReactContext appContext;
  private KlarnaCheckout mCheckout;
  private View mView;

  public KlarnaView(ThemedReactContext themedReactContext) {
    super(themedReactContext, null);
    this.appContext = themedReactContext;
    Activity activity = themedReactContext.getCurrentActivity();
    if (activity != null) {
      String name = themedReactContext.getPackageName();
      mCheckout = new KlarnaCheckout(activity, name);
      mCheckout.setSnippet("snippet");
      mCheckout.setSignalListener(new SignalListener() {
        @Override
        public void onSignal(String eventName, JSONObject jsonObject) {
          // if (eventName.equals("complete")) {
          try {
            Log.v("KLARNA in View", jsonObject.toString(4));
          } catch (JSONException e) {
            Log.e(e.getMessage(), e.toString());
          }
          onReceiveNativeEvent(jsonObject, eventName);
          // }
        }
      });
      View klarnaView = mCheckout.getView();
      if (klarnaView != null) {
        View view = mCheckout.getView();
        mView =  view;
      } else {
        mView = new View(themedReactContext);
      }
    } else {
      mView = new View(themedReactContext);
    }
  }

  public View getmView() {
    return mView;
  }

  public void onReceiveNativeEvent(JSONObject jsonObject, String eventName) {

    CompleteEvent event = CompleteEvent.obtain(
            this.getmView().getId(),
            jsonObject,
            eventName
    );

    this.appContext.getNativeModule(UIManagerModule.class).getEventDispatcher().dispatchEvent(event);
  }

  public void setSnippet(String snippet) {
    mCheckout.setSnippet(snippet);
  }

}
