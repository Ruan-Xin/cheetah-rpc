package com.roy.cheetah.rpc.nio;

import com.roy.cheetah.rpc.net.AbstractRpcConnector;
import com.roy.cheetah.rpc.net.AbstractRpcWriter;
import org.apache.log4j.Logger;

import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;

/**
 * @Author:Roy
 * @Date: Created in 22:48 2017/10/14 0014
 */
public class RpcNioConnector extends AbstractRpcConnector{

    private SocketChannel channel;
    private AbstractRpcNioSelector selector;
    private ByteBuffer channelWriteBuffer;
    private ByteBuffer channelReadBuffer;
    private SelectionKey selectionKey;

    private RpcNioConnector
    private Logger logger = Logger.getLogger(RpcNioConnector.class);

    public RpcNioConnector(AbstractRpcWriter rpcWriter) {
        super(rpcWriter);
    }


    public void startService() {

    }

    public void stopService() {

    }

    public void handleNetException(Exception e) {

    }
}
