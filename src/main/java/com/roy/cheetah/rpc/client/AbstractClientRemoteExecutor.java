package com.roy.cheetah.rpc.client;

import com.roy.cheetah.rpc.*;
import com.roy.cheetah.rpc.net.AbstractRpcConnector;
import com.roy.cheetah.rpc.net.RpcCallListener;
import com.roy.cheetah.rpc.serializer.JdkSerializer;
import com.roy.cheetah.rpc.serializer.RpcSerializer;
import com.roy.cheetah.rpc.sync.RpcCallSync;
import com.roy.cheetah.rpc.sync.RpcSync;
import com.roy.cheetah.rpc.sync.SimpleFutureRpcSync;
import org.apache.log4j.Logger;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author:Roy
 * @Date: Created in 18:47 2017/12/2 0002
 */
public abstract class AbstractClientRemoteExecutor implements RemoteExecutor, RpcService, RpcCallListener {
    protected int timeout = 10000;
    private AtomicInteger index = new AtomicInteger(10000);
    private RpcSync clientRpcSync;
    private RpcSerializer serializer;

    private Logger logger = Logger.getLogger(AbstractClientRemoteExecutor.class);

    private Map<String, RpcCallSync> rpcCache = new ConcurrentHashMap<String, RpcCallSync>();

    public AbstractClientRemoteExecutor() {
        clientRpcSync = new SimpleFutureRpcSync();
        serializer = new JdkSerializer();
    }
    public void oneWay(RemoteCall remoteCall) {
        AbstractRpcConnector connector = getRpcConnector(remoteCall);
        byte[] buffer = serializer.serialize(remoteCall);
        int length = buffer.length;
        RpcObject rpc = new RpcObject(ONEWAY, this.getIndex(), length, buffer);
        connector.sendRpcObject(rpc, timeout);
    }

    public Object invoke(RemoteCall call) {
        AbstractRpcConnector connector = getRpcConnector(call);
        byte[] buffer = serializer.serialize(call);
        int length = buffer.length;
        RpcObject request = new RpcObject(INVOKE, this.getIndex(), length, buffer);
        RpcCallSync sync = new RpcCallSync()
        return null;
    }

    public void startService() {

    }

    public void stopService() {

    }

    public void onRpcMessage(RpcObject rpc, RpcSender sender) {
        RpcCallSync sync = rpcCache.get(this.makeRpcCallCacheKey(rpc.getThreadId(), rpc.getIndex()));
        if (sync != null && sync.getRequest().getThreadId() == rpc.getThreadId()) {
            clientRpcSync.notifyResult(sync, rpc);
        }
    }

    private String makeRpcCallCacheKey(long threadId, int index) {
        return "rpc_" + threadId + "_" + index;
    }

    public int getIndex() {
        return index.getAndIncrement();
    }

    public abstract AbstractRpcConnector getRpcConnector(RemoteCall call);

    public RpcSerializer getSerializer() {
        return serializer;
    }

    public void setSerializer(RpcSerializer serializer) {
        this.serializer = serializer;
    }
}
