package com.roy.cheetah.rpc.client;

import com.roy.cheetah.rpc.RemoteCall;
import com.roy.cheetah.rpc.RemoteExecutor;
import com.roy.cheetah.rpc.RpcService;
import com.roy.cheetah.rpc.utils.RpcUtils;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author:Roy
 * @Date: Created in 14:55 2017/12/3 0003
 */
public class SimpleClientRemoteProxy implements InvocationHandler, RpcService {

    private RemoteExecutor remoteExecutor;

    private Map<Class, String> versionCache = new ConcurrentHashMap<Class, String>();

    private Map<Class, String> groupCache = new ConcurrentHashMap<Class, String>();

    /**
     * 应用
     */
    private String application;

    public void startService() {

    }

    public void stopService() {

    }

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Class<?> service = method.getDeclaringClass();

        String name = method.getName();
        RemoteCall call = new RemoteCall(service.getName(), name);
        call.setArgs(args);
        String version = versionCache.get(service);
        if (version != null) {
            call.setVersion(version);
        } else {
            call.setVersion(RpcUtils.DEFAULT_VERSION);
        }

        String group = groupCache.get(service);
        if (group == null) {
            group = RpcUtils.DEFAULT_GROUP;
        }
        call.setGroup(group);


        return null;
    }
}
