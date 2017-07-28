package com.reactnativeethereumwallet;

import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.Callback;
import org.ethereum.geth.*;

import android.util.*;
import android.widget.Toast;

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
        try {
            android.util.Log.d("before", "yay");
            NodeHolder nh = NodeHolder.getInstance();
            Node node = nh.getNode();
            if (node != null) {
                NodeInfo info = node.getNodeInfo();
                cb.invoke(node.getPeersInfo().size() + " node created, added from java ");
                return;
            }
            cb.invoke("node was null");
        } catch (Exception e) {
            android.util.Log.d("", e.getMessage());
            e.printStackTrace();
        }
    }
}
