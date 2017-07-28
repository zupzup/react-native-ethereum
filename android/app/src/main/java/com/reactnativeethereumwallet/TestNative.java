package com.reactnativeethereumwallet;

import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.Callback;

public class TestNative extends ReactContextBaseJavaModule {
    public TestNative(ReactApplicationContext reactContext) {
        super(reactContext);
    }

    @Override
    public String getName() {
        return "TestNative";
    }

    @ReactMethod
    public void test(String message, Callback cb) {
        cb.invoke(message + " added from java!");
    }
}
