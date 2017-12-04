package com.roy.cheetah.rpc.bio;

import com.roy.cheetah.rpc.net.AbstractRpcConnector;
import com.roy.cheetah.rpc.net.AbstractRpcWriter;

/**
 * Created by rx on 2017/12/4.
 */
public class RpcBioConnector extends AbstractRpcConnector {

    public RpcBioConnector(AbstractRpcWriter rpcWriter) {
        super(rpcWriter);

    }

    public void handleNetException(Exception e) {

    }
}
