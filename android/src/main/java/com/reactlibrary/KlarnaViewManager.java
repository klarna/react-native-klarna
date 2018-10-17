package com.reactlibrary;

import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.facebook.react.common.MapBuilder;

import android.support.annotation.Nullable;

import android.view.View;
import java.util.Map;

public class KlarnaViewManager extends SimpleViewManager<View> {

  private KlarnaView klarnaView;
  public static final String REACT_CLASS = "RNKlarna";

  @Override
  public String getName() {
    return REACT_CLASS;
  }

  @Override
  protected View createViewInstance(ThemedReactContext themedReactContext) {
    klarnaView = new KlarnaView(themedReactContext);
    return klarnaView.getmView();
  }

  @ReactProp(name = "snippet")
  public void setSnippet(View view, String snippet) {
    klarnaView.setSnippet(snippet);
  }

  public enum Events {
    EVENT_ON_COMPLETED("onComplete");

    private final String mName;

    Events(final String name) {
      mName = name;
    }

    @Override
    public String toString() {
      return mName;
    }
  }

  @Override
  @Nullable
  public Map<String, Object> getExportedCustomDirectEventTypeConstants() {
    MapBuilder.Builder<String, Object> builder = MapBuilder.builder();
    for (Events event : Events.values()) {
      builder.put(event.toString(), MapBuilder.of("registrationName", event.toString()));
    }
    return builder.build();
  }
}