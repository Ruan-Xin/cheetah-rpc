package com.roy.cheetah.rpc.bio;

import com.roy.cheetah.rpc.exception.RpcException;
import com.roy.cheetah.rpc.net.AbstractRpcAcceptor;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;

/**
 * Created by rx on 2017/12/4.
 */
public class RpcBioAcceptor extends AbstractRpcAcceptor {

    private final static Logger logger = Logger.getLogger(RpcBioAcceptor.class);

    private ServerSocket serverSocket;

    @Override
    public void startService() {
        super.startService();
        try {
            serverSocket.bind(new InetSocketAddress(this.getHost(), this.getPort()));
            this.startListeners();
            new BioAcceptorThread().start();
            this.fireStartNetListeners();
        } catch (IOException e) {
            throw new RpcException(e);
        }
    }

    @Override
    public void stopService() {
        super.stopService();
    }

    private class BioAcceptorThread extends Thread {

        @Override
        public void run() {
            while (!stop) {
                try {
                    serverSocket.accept();
                } catch (IOException e) {
                    RpcBioAcceptor.this.handleNetException(e);
                }
            }
        }
    }

    public void handleNetException(Exception e) {
        logger.error("bio acceptor io exception, start to shutdown service!");
        this.stopService();
        throw new RpcException(e);
    }
}
