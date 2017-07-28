package com.reactnativeethereumwallet;

import android.os.Bundle;
import android.util.Log;

import com.facebook.react.ReactActivity;

import org.ethereum.geth.Account;
import org.ethereum.geth.Geth;
import org.ethereum.geth.KeyStore;
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

    private String keyfile = "{\"address\":\"c4beb45d0240c8d18b0589db6abad9b135e6612c\",\"crypto\":{\"cipher\":\"aes-128-ctr\",\"ciphertext\":\"9111c04efc353eff245ccb7033bbbd01c5ee5da96b657505c8d68677067af044\",\"cipherparams\":{\"iv\":\"6282b3bf1b1d077cd84c853a8861189d\"},\"kdf\":\"scrypt\",\"kdfparams\":{\"dklen\":32,\"n\":4096,\"p\":6,\"r\":8,\"salt\":\"20d323a2b276a660888e378ecb5f81b01bdbe902def6575c57c0c4db4fd064f9\"},\"mac\":\"7fda57c3954594ddd90d8b1e45a70012a3834fe47c38dee114abd531a3e1ad48\"},\"id\":\"348714cf-9457-4fb9-83e8-cffa259a55d8\",\"version\":3}";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            android.util.Log.d("before", "yay");
            NodeConfig nc = new NodeConfig();
//            nc.setEthereumNetworkID(3); //ropsten
//            nc.setEthereumGenesis(Geth.testnetGenesis());

            Node node = Geth.newNode(getFilesDir() + "/.eth1", nc);
            node.start();
            NodeHolder nh = NodeHolder.getInstance();
            nh.setNode(node);
            KeyStore ks = new KeyStore(getFilesDir() + "/keystore", Geth.LightScryptN, Geth.LightScryptP);
//            Account newAcc = ks.newAccount("reallyhardpassword");
            Account newAcc = ks.importKey(keyfile.getBytes(), "alsohardpassword", "reallyhardpassword");
            byte[] bytes = ks.exportKey(newAcc, "reallyhardpassword", "alsohardpassword");
            android.util.Log.d("keyfile", newAcc.getAddress().getHex());
            nh.setAcc(newAcc);
            Log.d("success", "this worked");
        } catch (Exception e) {
            Log.d("fail", "what happened?" + e.getMessage());
            e.printStackTrace();
        }
    }
}
