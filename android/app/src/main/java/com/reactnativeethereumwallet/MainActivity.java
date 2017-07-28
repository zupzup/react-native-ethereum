package com.reactnativeethereumwallet;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import com.facebook.react.ReactActivity;

import org.ethereum.geth.Geth;
import org.ethereum.geth.Node;
import org.ethereum.geth.NodeConfig;

public class MainActivity extends ReactActivity {

    /**
     * Returns the name of the main component registered from JavaScript.
     * This is used to schedule rendering of the component.
     */
    @Override
    protected String getMainComponentName() {
        return "reactnativeethereumwallet";
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            android.util.Log.d("before", "yay");
            Node node = Geth.newNode(getFilesDir() + "/.ethereum", new NodeConfig());
            node.start();
            NodeHolder nh = NodeHolder.getInstance();
            nh.setNode(node);
            Log.d("success", "this worked");
        } catch (Exception e) {
            Log.d("fail", "what happened?" + e.getMessage());
            e.printStackTrace();
        }
    }
}
