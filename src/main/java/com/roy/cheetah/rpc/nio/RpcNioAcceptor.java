package com.roy.cheetah.rpc.nio;

import com.roy.cheetah.rpc.exception.RpcException;
import com.roy.cheetah.rpc.net.AbstractRpcAcceptor;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.net.ServerSocket;
import java.nio.channels.ServerSocketChannel;

/**
 * @Author:Roy
 * @Date: Created in 0:55 2017/10/15 0015
 */
public class RpcNioAcceptor extends AbstractRpcAcceptor {
    private ServerSocketChannel serverSocketChannel;
    private AbstractRpcNioSelector selector;
    private Logger logger = Logger.getLogger(RpcNioAcceptor.class);

    public RpcNioAcceptor(AbstractRpcNioSelector selector) {
        try {
            serverSocketChannel = ServerSocketChannel.open();
            serverSocketChannel.configureBlocking(false);
            this.selector = selector;
        } catch (IOException e) {
            this.handleNetException(e);
        }
    }

    public RpcNioAcceptor() {
        this(null);
    }

    public AbstractRpcNioSelector getSelector() {
        return selector;
    }

    public void setSelector(AbstractRpcNioSelector selector) {
        this.selector = selector;
    }

    @Override
    public void startService() {
        super.startService();
        try {
            if (selector == null) {
                selector = new
            }
        }
    }

    public void handleNetException(Exception e) {
        logger.error("nio acceptor io exception, start to shutdown service!");
        this.stopService();
        throw new RpcException(e);
    }
}
