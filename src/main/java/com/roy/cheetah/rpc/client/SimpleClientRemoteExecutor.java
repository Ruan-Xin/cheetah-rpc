package com.roy.cheetah.rpc.client;

import com.roy.cheetah.rpc.RemoteCall;
import com.roy.cheetah.rpc.RemoteExecutor;
import com.roy.cheetah.rpc.RpcService;
import com.roy.cheetah.rpc.net.AbstractRpcConnector;
import com.roy.cheetah.rpc.net.RpcCallListener;

/**
 * @Author:Roy
 * @Date: Created in 14:51 2017/12/3 0003
 */
public class SimpleClientRemoteExecutor extends AbstractClientRemoteExecutor implements RemoteExecutor, RpcCallListener, RpcService {

    private AbstractRpcConnector connector;

    public SimpleClientRemoteExecutor(AbstractRpcConnector connector) {
        super();
        connector.addRpcCallListener(this);
        this.connector = connector;
    }

    public AbstractRpcConnector getConnector() {
        return connector;
    }

    public AbstractRpcConnector getRpcConnector(RemoteCall call) {
        return connector;
    }

    public void startService() {
        connector.startService();
    }

    public void stopService() {
        connector.stopService();
    }
}
