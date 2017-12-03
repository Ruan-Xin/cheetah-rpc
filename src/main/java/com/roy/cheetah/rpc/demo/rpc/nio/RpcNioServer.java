package com.roy.cheetah.rpc.demo.rpc.nio;

import com.roy.cheetah.rpc.demo.rpc.provider.HelloRpcService;
import com.roy.cheetah.rpc.demo.rpc.provider.impl.HelloRpcServiceImpl;
import com.roy.cheetah.rpc.net.AbstractRpcAcceptor;
import com.roy.cheetah.rpc.nio.RpcNioAcceptor;
import com.roy.cheetah.rpc.server.RpcServiceProvider;
import org.apache.log4j.Logger;

/**
 * @Author:Roy
 * @Date: Created in 17:17 2017/12/3 0003
 */
public class RpcNioServer {

    private static Logger logger = 	Logger.getLogger(RpcNioServer.class);

    public static void main(String[] args) throws InterruptedException {
        String host = "127.0.0.1";
        int port = 4332;

        AbstractRpcAcceptor acceptor = new RpcNioAcceptor();
        acceptor.setHost(host);
        acceptor.setPort(port);
        RpcServiceProvider provider = new RpcServiceProvider();

        SimpleServerRemoteExecutor proxy = new SimpleServerRemoteExecutor();

        HelloRpcServiceImpl obj = new HelloRpcServiceImpl();

        proxy.registerRemote(HelloRpcService.class, obj);

        HelloRpcTestServiceImpl obj2 = new HelloRpcTestServiceImpl();

        proxy.registerRemote(HelloRpcTestService.class, obj2);

        LoginRpcService loginService = new LoginRpcServiceImpl();

        proxy.registerRemote(LoginRpcService.class, loginService);

        provider.setExecutor(proxy);

//		provider.addRpcFilter(new MyTestRpcFilter());
//
//		provider.addRpcFilter(new RpcLoginCheckFilter());

        acceptor.addRpcCallListener(provider);

        acceptor.startService();

        logger.info("service started");
}
