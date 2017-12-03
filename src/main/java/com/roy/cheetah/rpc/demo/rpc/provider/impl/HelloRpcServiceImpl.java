package com.roy.cheetah.rpc.demo.rpc.provider.impl;

import com.roy.cheetah.rpc.RpcContext;
import com.roy.cheetah.rpc.demo.rpc.provider.HelloRpcService;
import org.apache.log4j.Logger;

/**
 * @Author:Roy
 * @Date: Created in 17:11 2017/12/3 0003
 */
public class HelloRpcServiceImpl implements HelloRpcService{
    private Logger logger = Logger.getLogger(HelloRpcServiceImpl.class);

    public void sayHello(String message,int tt) {
        Object attachment = RpcContext.getContext().getAttachment("myattachment");
        System.out.println("my attachment:"+attachment);
        System.out.println("sayHello:"+message+" intValue:"+tt);
    }

    public String getHello() {
        return "this is hello service";
    }

    public int callException(boolean exception) {
        if(exception){
            throw new RuntimeException("happen");
        }
        return 1;
    }
}
