package com.mklarna;

import android.os.SystemClock;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.JavaScriptModule;
import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.modules.core.ReactChoreographer;
import com.facebook.react.uimanager.ViewManager;
import com.rnklarna.KlarnaViewManager;
import com.rnklarna.RNKlarnaPackage;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.List;

@PrepareForTest({ReactChoreographer.class, Arguments.class, SystemClock.class})
@RunWith(PowerMockRunner.class)
public class RNKlarnaPackageTest {

    private ReactApplicationContext mockReactContext;

    @Before
    public void setup() {
        mockReactContext = Mockito.mock(ReactApplicationContext.class);
    }

    @Test
    public void testNativeModules() {
        RNKlarnaPackage klarnaPackage = new RNKlarnaPackage();
        List<NativeModule> nativeModules = klarnaPackage.createNativeModules(mockReactContext);
        Assert.assertNotNull(nativeModules);
        Assert.assertTrue(nativeModules.isEmpty());
    }

    @Test
    public void testJSModules() {
        RNKlarnaPackage klarnaPackage = new RNKlarnaPackage();
        List<Class<? extends JavaScriptModule>> jsModules = klarnaPackage.createJSModules();
        Assert.assertNotNull(jsModules);
        Assert.assertTrue(jsModules.isEmpty());
    }

    @Test
    public void testViewManagers() {
        RNKlarnaPackage klarnaPackage = new RNKlarnaPackage();
        List<ViewManager> viewManagers = klarnaPackage.createViewManagers(mockReactContext);
        Assert.assertNotNull(viewManagers);
        Assert.assertFalse(viewManagers.isEmpty());
        Assert.assertTrue(containsInstance(viewManagers, KlarnaViewManager.class));
    }

    public static <E> boolean containsInstance(List<E> list, Class<? extends E> clazz) {
        for (E e : list) {
            if (clazz.isInstance(e)) {
                return true;
            }
        }
        return false;
    }
}
