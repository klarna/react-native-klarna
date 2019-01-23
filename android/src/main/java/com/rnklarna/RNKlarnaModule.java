
package com.rnklarna;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
public class RNKlarnaModule extends ReactContextBaseJavaModule {

  private final ReactApplicationContext reactContext;

  public RNKlarnaModule(ReactApplicationContext reactContext) {
    super(reactContext);
    this.reactContext = reactContext;
  }

  @Override
  public String getName() {
    return "RNKlarna";
  }
}