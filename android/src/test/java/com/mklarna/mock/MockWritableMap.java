package com.mklarna.mock;

import com.facebook.react.bridge.Dynamic;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.ReadableMapKeySetIterator;
import com.facebook.react.bridge.ReadableType;
import com.facebook.react.bridge.WritableArray;
import com.facebook.react.bridge.WritableMap;

import java.util.HashMap;
import java.util.Iterator;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class MockWritableMap extends HashMap<String, Object> implements WritableMap {
    @Override
    public void putNull(@Nonnull String key) {
        put(key, null);
    }

    @Override
    public void putBoolean(@Nonnull String key, boolean value) {
        put(key, value);
    }

    @Override
    public void putDouble(@Nonnull String key, double value) {
        put(key, value);
    }

    @Override
    public void putInt(@Nonnull String key, int value) {
        put(key, value);
    }

    @Override
    public void putString(@Nonnull String key, @Nullable String value) {
        put(key, value);
    }

    @Override
    public void putArray(@Nonnull String key, @Nullable WritableArray value) {
        put(key, value);
    }

    @Override
    public void putMap(@Nonnull String key, @Nullable WritableMap value) {
        put(key, value);
    }

    @Override
    public void merge(@Nonnull ReadableMap source) {

    }

    @Override
    public boolean hasKey(@Nonnull String name) {
        return containsKey(name);
    }

    @Override
    public boolean isNull(@Nonnull String name) {
        return get(name) == null;
    }

    @Override
    public boolean getBoolean(@Nonnull String name) {
        return (boolean) get(name);
    }

    @Override
    public double getDouble(@Nonnull String name) {
        return (double) get(name);
    }

    @Override
    public int getInt(@Nonnull String name) {
        return (int) get(name);
    }

    @Nullable
    @Override
    public String getString(@Nonnull String name) {
        return (String) get(name);
    }

    @Nullable
    @Override
    public ReadableArray getArray(@Nonnull String name) {
        return (ReadableArray) get(name);
    }

    @Nullable
    @Override
    public ReadableMap getMap(@Nonnull String name) {
        return (ReadableMap) get(name);
    }

    @Nonnull
    @Override
    public Dynamic getDynamic(@Nonnull String name) {
        return (Dynamic) get(name);
    }

    @Nonnull
    @Override
    public ReadableType getType(@Nonnull String name) {
        return null;
    }

    @Nonnull
    @Override
    public Iterator<Entry<String, Object>> getEntryIterator() {
        return entrySet().iterator();
    }

    @Nonnull
    @Override
    public ReadableMapKeySetIterator keySetIterator() {
        return null;
    }

    @Nonnull
    @Override
    public HashMap<String, Object> toHashMap() {
        return this;
    }
}
