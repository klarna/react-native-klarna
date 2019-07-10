package com.rnklarna;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.util.Log;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.WritableArray;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.events.RCTEventEmitter;

import com.klarna.checkout.KlarnaCheckout;
import com.klarna.checkout.SignalListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Iterator;


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
          onReceiveNativeEvent(jsonObject, eventName);
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
    if (this.getmView().getParent() != null
            && this.getmView().getParent().getParent() != null
            && this.getmView().getParent().getParent().getParent() != null
            && this.getmView().getParent().getParent().getParent().getParent() != null ) {

      int id = ((ViewGroup)this.getmView().getParent().getParent().getParent().getParent()).getId();
      this.appContext.getJSModule(RCTEventEmitter.class).receiveEvent(id, "onComplete", serializeEventData(jsonObject, eventName, id));
    }
  }

  public void setSnippet(String snippet) {
    if (snippet.equals("error")) {
       mCheckout.destroy();
    } else {
      if (mCheckout != null) {
        mCheckout.setSnippet(snippet);
      }
    }
  }

  public static WritableMap serializeEventData(JSONObject jsonObject, String eventName, int id) {
    WritableMap event = Arguments.createMap();
    WritableMap data = Arguments.createMap();
    try {
      data = jsonToReact(jsonObject);
    } catch (JSONException e) {
      Log.e(e.getMessage(), e.toString());
    }
    event.putString("type", "onComplete");
    event.putMap("data", data);
    event.putInt("target", id);
    event.putString("signalType", eventName);
    return event;
  }

  private static WritableMap jsonToReact(JSONObject jsonObject) throws JSONException {
    WritableMap writableMap = Arguments.createMap();
    Iterator iterator = jsonObject.keys();
    while(iterator.hasNext()) {
      String key = (String) iterator.next();
      Object value = jsonObject.get(key);
      if (value instanceof Float || value instanceof Double) {
        writableMap.putDouble(key, jsonObject.getDouble(key));
      } else if (value instanceof Number) {
        writableMap.putInt(key, jsonObject.getInt(key));
      } else if (value instanceof String) {
        writableMap.putString(key, jsonObject.getString(key));
      } else if (value instanceof JSONObject) {
        writableMap.putMap(key,jsonToReact(jsonObject.getJSONObject(key)));
      } else if (value instanceof JSONArray){
        writableMap.putArray(key, jsonToReact(jsonObject.getJSONArray(key)));
      } else if (value == JSONObject.NULL){
        writableMap.putNull(key);
      }
    }

    return writableMap;
  }
  private static WritableArray jsonToReact(JSONArray jsonArray) throws JSONException {
    WritableArray writableArray = Arguments.createArray();
    for(int i=0; i < jsonArray.length(); i++) {
      Object value = jsonArray.get(i);
      if (value instanceof Float || value instanceof Double) {
        writableArray.pushDouble(jsonArray.getDouble(i));
      } else if (value instanceof Number) {
        writableArray.pushInt(jsonArray.getInt(i));
      } else if (value instanceof String) {
        writableArray.pushString(jsonArray.getString(i));
      } else if (value instanceof JSONObject) {
        writableArray.pushMap(jsonToReact(jsonArray.getJSONObject(i)));
      } else if (value instanceof JSONArray){
        writableArray.pushArray(jsonToReact(jsonArray.getJSONArray(i)));
      } else if (value == JSONObject.NULL){
        writableArray.pushNull();
      }
    }
    return writableArray;
  }

}
