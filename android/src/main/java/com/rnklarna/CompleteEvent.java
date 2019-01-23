package com.rnklarna;
import com.facebook.react.uimanager.events.Event;
import android.support.v4.util.Pools;
import android.util.Log;

import com.facebook.react.uimanager.events.RCTEventEmitter;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.WritableArray;
import com.facebook.react.bridge.WritableMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Iterator;

public class CompleteEvent extends Event<CompleteEvent> {
  private static final Pools.SynchronizedPool<CompleteEvent> EVENTS_POOL =
          new Pools.SynchronizedPool<>(3);

  private JSONObject mJsonObject;
  private String mEventName;

  private CompleteEvent() {}

  public static CompleteEvent obtain(
          int viewTag,
          JSONObject jsonObject,
          String eventName) {
    CompleteEvent event = EVENTS_POOL.acquire();
    if (event == null) {
      event = new CompleteEvent();
    }
    event.init(viewTag, jsonObject, eventName);
    return event;
  }

  private void init(
          int viewTag,
          JSONObject jsonObject,
          String eventName) {
    super.init(viewTag);
    mJsonObject = jsonObject;
    mEventName = eventName;
  }

  @Override
  public String getEventName() {
    return KlarnaViewManager.Events.EVENT_ON_COMPLETED.toString();
  }

  @Override
  public void dispatch(RCTEventEmitter rctEventEmitter) {
    rctEventEmitter.receiveEvent(getViewTag(), getEventName(), serializeEventData());
  }

  private WritableMap serializeEventData() {
    WritableMap event = Arguments.createMap();
    WritableMap data = Arguments.createMap();
    try {
      data = jsonToReact(mJsonObject);
    } catch (JSONException e) {
      Log.e(e.getMessage(), e.toString());
    }
    event.putString("type", "onComplete");
    event.putMap("data", data);
    event.putInt("target", getViewTag());
    event.putString("signalType", mEventName);
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
