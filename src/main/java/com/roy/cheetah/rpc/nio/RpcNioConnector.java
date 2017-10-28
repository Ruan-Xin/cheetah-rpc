package com.roy.cheetah.rpc.nio;

import com.roy.cheetah.rpc.exception.RpcException;
import com.roy.cheetah.rpc.net.AbstractRpcConnector;
import com.roy.cheetah.rpc.net.AbstractRpcWriter;
import com.roy.cheetah.rpc.net.RpcNetListener;
import com.roy.cheetah.rpc.utils.RpcUtils;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.net.InetSocketAddress;
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

    private RpcNioBuffer rpcNioReadBuffer;
    private RpcNioBuffer rpcNioWriteBuffer;

    private RpcNioAcceptor acceptor;

    private Logger logger = Logger.getLogger(RpcNioConnector.class);

    public RpcNioConnector(SocketChannel socketChannel, AbstractRpcNioSelector selection) {
        this(selection);
        this.channel = socketChannel;
    }
    public RpcNioConnector(AbstractRpcNioSelector selector) {
        super(null);
        if (selector == null) {
            this.selector = new SimpleRpcNioSelector();
        } else {
            this.selector = selector;
        }
        this.initBuf();
    }


    public void startService() {
        super.startService();
        try {
            if (channel == null) {
                channel = SocketChannel.open();
                channel.connect(new InetSocketAddress(this.getHost(), this.getPort()));
                channel.configureBlocking(false);
                while (!channel.isConnected());
                logger.info("connect to "+this.getHost()+":"+this.getPort()+" success");
                selector.startService();
                selector.register(this);
            }
            InetSocketAddress remoteAddress = (InetSocketAddress) channel.socket().getRemoteSocketAddress();
            InetSocketAddress localAddress = (InetSocketAddress) channel.socket().getLocalSocketAddress();
            String remote = RpcUtils.genAddressString("remoteAddress->", remoteAddress);
            String local = RpcUtils.genAddressString("localAddress->", localAddress);
            logger.info(local + " " + remote);
            remoteP
        } catch(IOException e){
            logger.error("connect to host "+this.getHost()+" port "+this.getPort()+" failed", e);
            throw new RpcException("connect to host error");
        }
    }

    public void stopService() {

    }

    public void handleNetException(Exception e) {

    }

    @Override
    public void addRpcNetListener(RpcNetListener listener) {
        super.addRpcNetListener(listener);
        this.selector.addRpcNetListener(listener);
    }

    private void initBuf(){
        channelWriteBuffer = ByteBuffer.allocate(RpcUtils.MEM_512KB);
        channelReadBuffer = ByteBuffer.allocate(RpcUtils.MEM_512KB);
        rpcNioReadBuffer = new RpcNioBuffer(RpcUtils.MEM_512KB);
        rpcNioWriteBuffer = new RpcNioBuffer(RpcUtils.MEM_512KB);
    }
}
