package com.roy.cheetah.rpc.sync;

import com.roy.cheetah.rpc.RpcObject;
import com.roy.cheetah.rpc.exception.RpcException;

/**
 * @Author:Roy
 * @Date: Created in 18:57 2017/12/2 0002
 */
public class SimpleFutureRpcSync implements RpcSync {
    public void waitForResult(int time, RpcCallSync sync) {
        int timeAll = 0;
        while (!sync.isDone()) {
            try {
                Thread.currentThread().sleep(5);
                timeAll += 5;
                if (timeAll > time) {
                    throw new RpcException("request time out");
                }
            } catch (InterruptedException e) {
                throw new RpcException(e);
            }
        }
    }

    public void notifyResult(RpcCallSync sync, RpcObject rpc) {
        if (sync != null) {
            sync.setResponse(rpc);
        }
    }
}
