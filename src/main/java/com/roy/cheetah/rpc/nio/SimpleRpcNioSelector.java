package com.roy.cheetah.rpc.nio;

import com.roy.cheetah.rpc.exception.RpcException;
import com.roy.cheetah.rpc.net.AbstractRpcConnector;
import org.apache.log4j.Logger;
import org.omg.CORBA.PRIVATE_MEMBER;

import java.io.IOException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @Author:Roy
 * @Date: Created in 1:02 2017/10/15 0015
 */
public class SimpleRpcNioSelector extends AbstractRpcNioSelector {
    private Selector selector;
    private boolean stop = false;
    private boolean started = false;
    private ConcurrentHashMap<SocketChannel, RpcNioConnector> connectorCache;
    private List<RpcNioConnector> connectors;
    private ConcurrentHashMap<ServerSocketChannel, RpcNioAcceptor> acceptorCache;
    private List<RpcNioAcceptor> acceptors;
    private final static int READ_OP = SelectionKey.OP_READ;
    private final static int READ_WRITE_OP = SelectionKey.OP_READ | SelectionKey.OP_WRITE;
    private LinkedList<Runnable> selectTasks = new LinkedList<Runnable>();

    private AbstractRpcNioSelector delegageSelector;

    private Logger logger = Logger.getLogger(SimpleRpcNioSelector.class);

    public SimpleRpcNioSelector() {
        super();
        try {
            selector = Selector.open();
            connectorCache = new ConcurrentHashMap<SocketChannel, RpcNioConnector>();
            connectors = new CopyOnWriteArrayList<RpcNioConnector>();
            acceptorCache = new ConcurrentHashMap<ServerSocketChannel, RpcNioAcceptor>();
            acceptors = new CopyOnWriteArrayList<RpcNioAcceptor>();
        } catch (IOException e) {
            throw new RpcException(e);
        }
    }

    public void register(RpcNioAcceptor acceptor) {
        final ServerSocketChannel channel = acceptor.getServerSocketChannel();

    }

    public void unRegister(RpcNioAcceptor acceptor) {

    }

    public void register(RpcNioConnector connector) {

    }

    public void unRegister(RpcNioConnector connector) {

    }

    public void startService() {

    }

    public void stopService() {

    }

    public void handleNetException(Exception e) {
        logger.error("selector exception:" + e.getMessage());
    }

    public void notifySend(AbstractRpcConnector connector) {
        selector.wakeup();
    }

    private boolean checkSend() {
        boolean needSend = false;
        for (RpcNioConnector connector : connectors) {
            if (connector.isNeedToSend()) {
                SelectionKey selectionKey = connector.getc
            }
        }
    }

    private void addSelectTask(Runnable task) {
        selectTasks.offer(task);
    }

    private boolean hasTask() {
        Runnable peek = selectTasks.peek();
        return peek != null;
    }

    private void runSelectTasks() {
        Runnable peek = selectTasks.peek();
        while (peek != null) {
            peek = selectTasks.poll();
            peek.run();
            peek = selectTasks.peek();
        }
    }

    public void setDelegageSelector(AbstractRpcNioSelector delegageSelector) {
        this.delegageSelector = delegageSelector;
    }
}
