package com.roy.cheetah.rpc.demo.nio;

import com.roy.cheetah.rpc.net.AbstractRpcConnector;
import com.roy.cheetah.rpc.nio.RpcNioConnector;
import org.apache.log4j.Logger;

/**
 * @Author:Roy
 * @Date: Created in 17:27 2017/12/2 0002
 */
public class RpcNioClient {
    private static Logger logger = Logger.getLogger(RpcNioClient.class);

    public static void main(String args[]) {
        String host = "127.0.0.1";
        int port = 4332;
        AbstractRpcConnector connector = new RpcNioConnector(null);
        connector.setHost(host);
        connector.setPort(port);


    }
}
