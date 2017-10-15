package com.roy.cheetah.rpc.exception;

import com.roy.cheetah.rpc.RemoteCall;
import com.roy.cheetah.rpc.RpcObject;

/**
 * @Author:Roy
 * @Date: Created in 14:59 2017/10/15 0015
 */
public interface RpcExceptionHandler {

    public void handleException(RpcObject rpc, RemoteCall call, Throwable e);
}
