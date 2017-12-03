package com.roy.cheetah.rpc.filter;

import com.roy.cheetah.rpc.RemoteCall;
import com.roy.cheetah.rpc.RpcObject;
import com.roy.cheetah.rpc.RpcSender;

/**
 * 过滤器责任链
 * @Author:Roy
 * @Date: Created in 17:20 2017/12/3 0003
 */
public interface RpcFilterChain {
    /**
     * 过滤
     */
    public void nextFilter(RpcObject rpc, RemoteCall call, RpcSender sender);

    /**
     * 按顺序添加过滤器
     */
    public void addRpcFilter(RpcFilter filter);

    /**
     * 启动chain
     */
    public void startFilter(RpcObject rpc, RemoteCall call, RpcSender sender);
}
