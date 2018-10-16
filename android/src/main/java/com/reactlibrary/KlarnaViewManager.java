import com.facebook.react.uimanager.ViewGroupManager;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.annotations.ReactProp;

import com.klarna.checkout.KlarnaCheckout;
import com.klarna.checkout.SignalListener;

public class KlarnaViewManager extends ViewGroupManager<ReactImageView> {

  public static final String REACT_CLASS = "Klarna";
  private KlarnaCheckout mCheckout;

  @Override
  public String getName() {
    return REACT_CLASS;
  }

  @Override
  protected RNCameraView createViewInstance(ThemedReactContext themedReactContext) {
    Activity activity = themedReactContext.getCurrentActivity();
    // if (activity != null) {
      mCheckout = new KlarnaCheckout(activity);
      View view = mCheckout.getView()
    // }
    return view;
  }

  @ReactProp(name = "snippet")
  public void setSnippet(String snippet) {
    mCheckout.setSnippet(snippet);
  }