package com.roy.cheetah.rpc.bio;

import com.roy.cheetah.rpc.net.AbstractRpcConnector;
import com.roy.cheetah.rpc.net.AbstractRpcWriter;

/**
 * Created by rx on 2017/12/4.
 */
public class RpcBioWriter extends AbstractRpcWriter {
    public RpcBioWriter() {

    }

    public boolean doSend(AbstractRpcConnector connector) {
        return false;
    }
}
