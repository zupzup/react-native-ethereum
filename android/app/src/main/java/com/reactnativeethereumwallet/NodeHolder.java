package com.reactnativeethereumwallet;

import org.ethereum.geth.Node;

public class NodeHolder {
    private Node node;
    private static NodeHolder instance = null;
    private NodeHolder(){}
    public static NodeHolder getInstance() {
        if (instance == null) {
            instance = new NodeHolder();
        }
        return instance;
    }

    public Node getNode() {
        return node;
    }

    public void setNode(Node node) {
        this.node = node;
    }
}
