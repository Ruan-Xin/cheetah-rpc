package com.roy.cheetah.rpc.nio;

import com.roy.cheetah.rpc.RpcService;
import com.roy.cheetah.rpc.exception.RpcNetExceptionHandler;
import com.roy.cheetah.rpc.net.RpcNetBase;
import com.roy.cheetah.rpc.net.RpcNetListener;
import com.roy.cheetah.rpc.net.RpcOutputNofity;

import java.util.LinkedList;
import java.util.List;

/**
 * @Author:Roy
 * @Date: Created in 0:12 2017/10/15 0015
 */
public abstract class AbstractRpcNioSelector implements RpcService, RpcOutputNofity, RpcNetExceptionHandler {

    protected List<RpcNetListener> netListeners;

    public abstract void register(RpcNioAcceptor acceptor);

    public abstract void unRegister(RpcNioAcceptor acceptor);

    public abstract void register(RpcNioConnector connector);

    public abstract void unRegister(RpcNioConnector connector);

    public AbstractRpcNioSelector() {
        netListeners = new LinkedList<RpcNetListener>();
    }

    public void addRpcNetListener(RpcNetListener listener) {
        netListeners.add(listener);
    }

    public void fireNetListeners(RpcNetBase netWork, Exception e) {
        for (RpcNetListener listener : netListeners) {
            listener.onClose(netWork, e);
        }
    }
}
