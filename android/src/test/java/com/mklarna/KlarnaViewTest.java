package com.mklarna;

import android.app.Activity;
import android.util.Log;
import android.view.View;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.uimanager.ThemedReactContext;
import com.klarna.checkout.KlarnaCheckout;
import com.mklarna.mock.MockWritableMap;
import com.rnklarna.KlarnaView;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.Map;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.powermock.api.mockito.PowerMockito.mock;
import static org.powermock.api.mockito.PowerMockito.mockStatic;
import static org.powermock.api.mockito.PowerMockito.verifyNew;
import static org.powermock.api.mockito.PowerMockito.when;

@PrepareForTest({KlarnaView.class, Log.class, Arguments.class})
@RunWith(PowerMockRunner.class)
public class KlarnaViewTest {

    private ThemedReactContext themedReactContext = mock(ThemedReactContext.class);
    private KlarnaCheckout klarnaCheckout = mock(KlarnaCheckout.class);

    private Activity activity = mock(Activity.class);
    private View view = mock(View.class);

    @Before
    public void setup() throws Exception {
        mockStatic(Log.class);
        mockStatic(Arguments.class, new Answer<WritableMap>() {
            @Override
            public WritableMap answer(InvocationOnMock invocation) throws Throwable {
                return new MockWritableMap();
            }
        });

//        BDDMockito.given(Arguments.createMap()).willReturn(new MockWritableMap());
//        when(Arguments.createMap()).thenReturn(new MockWritableMap());

        PowerMockito.whenNew(KlarnaCheckout.class).withAnyArguments().thenReturn(klarnaCheckout);

        when(klarnaCheckout.getView()).thenReturn(view);

        when(themedReactContext.getPackageName()).thenReturn("packageName");
        when(themedReactContext.getCurrentActivity()).thenReturn(activity);
    }

    @Test
    public void testInitialize() throws Exception {
        KlarnaView klarnaView = new KlarnaView(themedReactContext);
        verifyNew(KlarnaCheckout.class).withArguments(activity, "packageName");
        verify(klarnaCheckout).setSnippet("snippet");
        verify(klarnaCheckout).setSignalListener(any());
        Assert.assertEquals(view, klarnaView.getmView());
    }

    @Test
    public void testSetSnippet() {
        KlarnaView klarnaView = new KlarnaView(themedReactContext);
        klarnaView.setSnippet("snip");
        verify(klarnaCheckout).setSnippet("snip");
    }

    @Test
    public void testSetSnippetError() {
        KlarnaView klarnaView = new KlarnaView(themedReactContext);
        klarnaView.setSnippet("error");
        verify(klarnaCheckout).destroy();
    }

    @Test
    public void testSerializeEventData() throws JSONException {
        JSONObject json = new JSONObject();
        json.put("int", 1);
        json.put("string", "str");
        WritableMap map = KlarnaView.serializeEventData(json, "eventName", 123);
        Assert.assertEquals("onComplete", map.getString("type"));
        Assert.assertEquals("eventName", map.getString("signalType"));
        Assert.assertEquals(123, map.getInt("target"));
        Assert.assertNotNull(map.getMap("data"));
        Assert.assertTrue(map.getMap("data") instanceof Map);
        Map dataMap = (Map) map.getMap("data");
        Assert.assertEquals("str", dataMap.get("string"));
        Assert.assertEquals(1, dataMap.get("int"));
    }
}
