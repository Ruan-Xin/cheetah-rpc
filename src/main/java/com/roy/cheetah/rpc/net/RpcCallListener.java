package com.roy.cheetah.rpc.net;

import com.roy.cheetah.rpc.RpcObject;
import com.roy.cheetah.rpc.RpcSender;

/**
 * @Author:Roy
 * @Date: Created in 0:27 2017/10/15 0015
 */
public interface RpcCallListener {

    public void onRpcMessage(RpcObject rpc, RpcSender sender);
}
