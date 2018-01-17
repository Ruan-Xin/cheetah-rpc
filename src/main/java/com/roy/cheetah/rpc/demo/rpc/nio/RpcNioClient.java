package com.roy.cheetah.rpc.demo.rpc.nio;

import com.roy.cheetah.rpc.client.SimpleClientRemoteExecutor;
import com.roy.cheetah.rpc.client.SimpleClientRemoteProxy;
import com.roy.cheetah.rpc.demo.rpc.provider.HelloRpcService;
import com.roy.cheetah.rpc.demo.rpc.provider.impl.HelloRpcServiceImpl;
import com.roy.cheetah.rpc.net.AbstractRpcConnector;
import com.roy.cheetah.rpc.nio.RpcNioConnector;
import org.apache.log4j.Logger;

/**
 * @Author:Roy
 * @Date: Created in 17:03 2017/12/3 0003
 */
public class RpcNioClient {
    private static Logger logger = Logger.getLogger(RpcNioClient.class);

    public static void main(String[] args) {
        String host = "127.0.0.1";
        int port = 4332;

        AbstractRpcConnector connector = new RpcNioConnector(null);
        connector.setHost(host);
        connector.setPort(port);

        SimpleClientRemoteExecutor executor = new SimpleClientRemoteExecutor(connector);

        SimpleClientRemoteProxy proxy = new SimpleClientRemoteProxy();
        proxy.setRemoteExecutor(executor);

        proxy.startService();

//        HelloRpcService htest = new HelloRpcServiceImpl();
//        htest.sayHello("this is test",564);
        HelloRpcService helloRpcService = proxy.registerRemote(HelloRpcService.class);
        logger.info("start client");

        helloRpcService.sayHello("this is HelloRpcService",564);

        helloRpcService.sayHello("this is HelloRpcService tttttttt",3333);

        String hello = helloRpcService.getHello();

        int ex = helloRpcService.callException(false);

        logger.info("hello result:"+hello);

        logger.info("exResult:"+ex);

//        long start = System.currentTimeMillis();
//        int total = 10000;
//        for(int i=0;i<total;i++){
//            helloRpcService.sayHello("this is HelloRpcService",564);
//        }
//        long end = System.currentTimeMillis();
//        System.out.println("cost:"+(end-start));
    }
}
