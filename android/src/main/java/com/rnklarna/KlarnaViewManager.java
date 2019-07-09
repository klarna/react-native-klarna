package com.rnklarna;

import com.facebook.react.uimanager.ViewGroupManager;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.facebook.react.common.MapBuilder;

import androidx.annotation.Nullable;

import android.view.ViewGroup;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ScrollView;

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

    // Klarna WebView is not scrollable by itself we need to place it within scrollview wrapper
    /*
     <LinearLayout> <-so that scrollview is contained
        <ScrollView> <-so that klarna view is scrollable
          <LinearLayout> <- otherwise klarna view has height of 0
            <FrameLayout> <-with a minimum height to accommodate expanding klarna view
              <KlarnaView>
     */

    LinearLayout container = new LinearLayout(themedReactContext);

    ScrollView wrapper = new ScrollView(themedReactContext);

    FrameLayout frameLayout = new FrameLayout(themedReactContext);
    frameLayout.setLayoutParams(new ViewGroup.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT));
    final View view = klarnaView.getmView();

    view.setLayoutParams(new ViewGroup.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT));
    frameLayout.addView(view);


    float scale = themedReactContext.getResources().getConfiguration().fontScale;
    float density = themedReactContext.getResources().getDisplayMetrics().density;
    // Magic numbers needed to accommodate the biggest possible size of the view - it does not always auto-scale otherwise
    // Numbers depend on font size and pixel density of a device
    frameLayout.setMinimumHeight(Math.round(4700 * scale * density / 3));

    LinearLayout linearLayout = new LinearLayout(themedReactContext);
    linearLayout.setOrientation(LinearLayout.VERTICAL);
    linearLayout.setLayoutParams(new ViewGroup.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));


    linearLayout.addView(frameLayout);

    wrapper.addView(linearLayout);

    container.addView(wrapper);

    return container;
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