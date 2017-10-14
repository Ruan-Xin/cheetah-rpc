package com.roy.cheetah.rpc.nio;

import com.roy.cheetah.rpc.RpcService;
import com.roy.cheetah.rpc.exception.RpcNetExceptionHandler;
import com.roy.cheetah.rpc.net.RpcOutputNofity;

/**
 * @Author:Roy
 * @Date: Created in 0:12 2017/10/15 0015
 */
public abstract class AbstractRpcNioSelector implements RpcService, RpcOutputNofity, RpcNetExceptionHandler {
    public abstract void register(RpcNio)
}
