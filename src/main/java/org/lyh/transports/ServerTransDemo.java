package org.lyh.transports;

import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TServerTransport;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;

import java.nio.charset.StandardCharsets;

/**
 * @author lyh
 * @version 2019-10-17 17:49
 */
public class ServerTransDemo {

    public static void main(String[] args) throws TTransportException {
        final String msg = "Hello Thrift!\n";
        final String stopCmd = "STOP";
        final int bufSize = 1024 * 8;
        byte[] buf = new byte[bufSize];

        /**
         * 通过TServerSocket来创建一个server，监听在8585端口
         */
        TServerTransport acceptor = new TServerSocket(8585);
        acceptor.listen();
        System.out.println("[Server] listening on port 8585");

        /**
         * accept阻塞等待客户端连接，收到请求接受，然后返回msg
         */
        while (true) {
            TTransport trans = acceptor.accept();
            System.out.println("[Server] handling request");
            trans.read(buf, 0, bufSize);
            if (stopCmd.regionMatches(0, new String(buf, 0, buf.length, StandardCharsets.UTF_8), 0, 4)) {
                break;
            }
            System.out.println(new String(buf));
            trans.write(msg.getBytes());
            trans.flush();
            trans.close();
        }
        System.out.println("[Server] exiting");
        acceptor.close();
    }
}
