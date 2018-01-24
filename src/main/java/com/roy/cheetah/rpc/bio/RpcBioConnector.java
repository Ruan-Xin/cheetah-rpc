package com.roy.cheetah.rpc.bio;

import com.roy.cheetah.rpc.RpcObject;
import com.roy.cheetah.rpc.net.AbstractRpcConnector;
import com.roy.cheetah.rpc.net.AbstractRpcWriter;
import com.roy.cheetah.rpc.utils.RpcUtils;

import java.io.DataInput;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 * Created by rx on 2017/12/4.
 */
public class RpcBioConnector extends AbstractRpcConnector {

    private Socket socket;
    private DataInputStream dis;
    private DataOutputStream dos;

    public RpcBioConnector(AbstractRpcWriter rpcWriter) {
        super(rpcWriter);

    }

    public RpcBioConnector(AbstractRpcWriter rpcWriter, Socket socket) {
        super(rpcWriter);
        this.socket = socket;
    }

    @Override
    public void startService() {
        super.startService();

        try {
            if (socket == null) {
                //client call
                socket = new Socket();
                socket.connect(new InetSocketAddress(this.getHost(), this.getPort()));
            }
            InetSocketAddress remoteAddress = (InetSocketAddress) socket.getRemoteSocketAddress();
            remoteHost = remoteAddress.getAddress().getHostAddress();
            remotePort = remoteAddress.getPort();
            dis = new DataInputStream(socket.getInputStream());
            dos = new DataOutputStream(socket.getOutputStream());
            getWriter().registerWrite(this);
            getWriter().startService();
            new ClientThreand().start();
            this.fireStartNetListeners();
        } catch (IOException e) {
            this.handleNetException(e);
        }
    }

    private class ClientThreand extends Thread {
        @Override
        public void run() {
            while (!stop) {
                RpcObject rpcObject = RpcUtils.readDataRpc(dis, RpcBioConnector.this);
                rpcObject.setHost(remoteHost);
                rpcObject.setPort(remotePort);
                rpcObject.setRpcContext(rpcContext);
                fireCall(rpcObject);
            }
        }
    }

    @Override
    public void stopService() {
        super.stopService();
        stop = true;
        RpcUtils.close(dis, dos);
        try {
            socket.close();
        } catch (IOException e) {
            //ignore
        }
        rpcContext.clear();
        sendQueueCache.clear();
    }

    public void handleNetException(Exception e) {
        this.getWriter().unRegisterWrite(this);
        this.stopService();

    }
}
