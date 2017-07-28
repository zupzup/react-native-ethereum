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
            Context ctx = new Context();
            if (node != null) {
                NodeInfo info = node.getNodeInfo();
                EthereumClient ethereumClient = node.getEthereumClient();
                Account newAcc = nh.getAcc();
                BigInt balanceAt = ethereumClient.getBalanceAt(ctx, new Address("0x22B84d5FFeA8b801C0422AFe752377A64Aa738c2"), -1);
                cb.invoke(balanceAt.toString() + " ether found address:" + newAcc.getAddress().getHex());
                return;
            }
            cb.invoke("node was null");
        } catch (Exception e) {
            android.util.Log.d("", e.getMessage());
            e.printStackTrace();
        }
    }
}
