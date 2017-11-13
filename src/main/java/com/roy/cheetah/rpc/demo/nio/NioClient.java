package com.roy.cheetah.rpc.demo.nio;

import com.roy.cheetah.rpc.RpcObject;
import com.roy.cheetah.rpc.RpcSender;
import com.roy.cheetah.rpc.constants.RpcType;
import com.roy.cheetah.rpc.net.AbstractRpcConnector;
import com.roy.cheetah.rpc.net.RpcCallListener;
import com.roy.cheetah.rpc.nio.RpcNioConnector;
import com.roy.cheetah.rpc.nio.SimpleRpcNioSelector;
import org.apache.log4j.Logger;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author:Roy
 * @Date: Created in 15:57 2017/11/11 0011
 */
public class NioClient implements RpcCallListener {
    public static Logger logger = Logger.getLogger(NioClient.class);
    private SimpleRpcNioSelector selection;
    private RpcNioConnector connector;
    private String host = "127.0.0.1";
    private int port = 4332;
    private int threadCount;
    private AtomicInteger send = new AtomicInteger(0);
    private AtomicInteger receive = new AtomicInteger(0);
    private List<Thread> threads;
    private AtomicBoolean started = new AtomicBoolean(false);
    private AtomicInteger cccc = new AtomicInteger(0);

    public NioClient(SimpleRpcNioSelector selection) {
        this.selection = selection;
    }
    public NioClient clone() {
        NioClient client = new NioClient(selection);
        client.host = host;
        client.port = port;
        client.threadCount = threadCount;
        return client;
    }
    public static void startService(List<NioClient> clients) {
        int i = 0;
        for (NioClient client : clients) {
            client
        }
    }
    public void onRpcMessage(RpcObject rpc, RpcSender sender) {

    }

    public class SendThread extends Thread {
        private AbstractRpcConnector connector;
        private int interval;
        private int index;

        public SendThread(AbstractRpcConnector connector, int interval, int startIndex) {
            this.connector = connector;
            this.interval = interval;
            this.index = startIndex;
        }

        @Override
        public void run() {
            String prefix = "rpc test index";
            long threadId = Thread.currentThread().getId();
            logger.info("send thread:"+ threadId + " start" + host + ":" + port);
            while (true) {
                RpcObject rpc = crea
            }
        }
    }

    public static RpcObject createRpc(String str, long id, int index) {
        RpcObject rpc = new RpcObject();
        rpc.setType(RpcType.INVOKE);
        rpc.setIndex(index);
        rpc.setThreadId(id);
        rpc.setData(str.getBytes());
        rpc.setLength(rpc.getData().length);
        return rpc;
    }

    public void startService() {
        if (!started.get()) {
            started.set(true);
            connector = new RpcNioConnector(selection);
            connector.setHost(host);
            connector.setPort(port);
            connector.addRpcCallListener(this);
            connector.startService();
            threads =
        }
    }

    private List<Thread> startThread(AbstractRpcConnector connector, int count) {
        LinkedList<Thread> linkedList = new LinkedList<Thread>();
        int c = 0;
        Random random = new Random();
        while (c < count) {
            int intervel = random.nextInt(200);
            int index = random.nextInt(20000);
            Send
        }
    }
}
