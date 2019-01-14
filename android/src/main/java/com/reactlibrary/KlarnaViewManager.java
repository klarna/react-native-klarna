package com.reactlibrary;

import com.facebook.react.uimanager.ViewGroupManager;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.facebook.react.common.MapBuilder;

import android.support.annotation.Nullable;

import android.view.ViewGroup;
import android.view.View;
import android.widget.LinearLayout;

import java.util.Map;

public class KlarnaViewManager extends ViewGroupManager<LinearLayout> {

  private KlarnaView klarnaView;
  public static final String REACT_CLASS = "RNKlarna";

  @Override
  public String getName() {
    return REACT_CLASS;
  }

  @Override
  protected LinearLayout createViewInstance(ThemedReactContext themedReactContext) {
    klarnaView = new KlarnaView(themedReactContext);
    // creating a wrapper instead of a view is needed for correct work of KLarnaCheckout.destroy()
    LinearLayout wrapper = new LinearLayout(themedReactContext);
    wrapper.addView(klarnaView.getmView(), new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
    return wrapper;
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