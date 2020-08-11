package com.mklarna;

import android.content.res.Configuration;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.touch.JSResponderHandler;
import com.facebook.react.uimanager.ReactStylesDiffMap;
import com.facebook.react.uimanager.ThemedReactContext;
import com.rnklarna.KlarnaView;
import com.rnklarna.KlarnaViewManager;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.powermock.api.mockito.PowerMockito.mock;
import static org.powermock.api.mockito.PowerMockito.mockStatic;
import static org.powermock.api.mockito.PowerMockito.verifyNew;

@PrepareForTest({KlarnaViewManager.class, Log.class})
@RunWith(PowerMockRunner.class)
public class KlarnaViewManagerTest {

    private KlarnaView mockKlarnaView = mock(KlarnaView.class);
    private ThemedReactContext mockThemedReactContext = mock(ThemedReactContext.class);
    private ReadableMap mockProps = mock(ReadableMap.class);
    private ReactStylesDiffMap mockReactStylesDiffMap = new ReactStylesDiffMap(mockProps);
    private JSResponderHandler mockJSResponderHandler = mock(JSResponderHandler.class);

    @Before
    public void setup() throws Exception {
        mockStatic(Log.class);

        PowerMockito.whenNew(KlarnaView.class).withAnyArguments().thenReturn(mockKlarnaView);
        PowerMockito.whenNew(FrameLayout.class).withAnyArguments().thenReturn(mock(FrameLayout.class));
        PowerMockito.whenNew(LinearLayout.class).withAnyArguments().thenReturn(mock(LinearLayout.class));
        PowerMockito.whenNew(ScrollView.class).withAnyArguments().thenReturn(mock(ScrollView.class));

        when(mockKlarnaView.getmView()).thenReturn(mock(View.class));

        Resources mockResources = mock(Resources.class);
        when(mockThemedReactContext.getResources()).thenReturn(mockResources);
        when(mockResources.getConfiguration()).thenReturn(mock(Configuration.class));
        when(mockResources.getDisplayMetrics()).thenReturn(mock(DisplayMetrics.class));

        when(mockProps.getEntryIterator()).thenReturn(mock(Iterator.class));
    }

    @Test
    public void testCreateView() throws Exception {
        KlarnaViewManager viewManager = new KlarnaViewManager();
        LinearLayout linearLayout = viewManager.createViewWithProps(mockThemedReactContext, mockReactStylesDiffMap, mockJSResponderHandler);
        verifyNew(KlarnaView.class).withArguments(mockThemedReactContext);
        verify(mockKlarnaView).getmView();
        Assert.assertNotNull(linearLayout);
    }

    @Test
    public void testSetSnippet() {
        KlarnaViewManager viewManager = new KlarnaViewManager();
        viewManager.createViewWithProps(mockThemedReactContext, mockReactStylesDiffMap, mockJSResponderHandler);
        viewManager.setSnippet(mock(View.class), "snippet");
        verify(mockKlarnaView).setSnippet("snippet");
    }

    @Test
    public void testGetExportedCustomBubblingEventTypeConstants() {
        KlarnaViewManager viewManager = new KlarnaViewManager();
        Map<String, Object> map = viewManager.getExportedCustomBubblingEventTypeConstants();
        Assert.assertNotNull(map);
        Assert.assertTrue(map.containsKey("onComplete"));

        Object onComplete = map.get("onComplete");
        Assert.assertTrue(onComplete instanceof HashMap);
        HashMap hashMapOnComplete = (HashMap) onComplete;
        Assert.assertTrue(hashMapOnComplete.containsKey("phasedRegistrationNames"));

        Object phasedRegistrationNames = hashMapOnComplete.get("phasedRegistrationNames");
        Assert.assertTrue(phasedRegistrationNames instanceof HashMap);
        HashMap hashMapPhasedRegistrationNames = (HashMap) phasedRegistrationNames;
        Assert.assertTrue(hashMapPhasedRegistrationNames.containsKey("bubbled"));
        Assert.assertEquals("onComplete", hashMapPhasedRegistrationNames.get("bubbled"));
    }
}
