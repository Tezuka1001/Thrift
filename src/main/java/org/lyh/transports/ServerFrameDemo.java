package org.lyh.transports;

import org.apache.thrift.transport.*;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;

/**
 * @author lyh
 * @version 2019-10-17 17:55
 */
public class ServerFrameDemo {

    public static void main(String[] args) throws TTransportException, UnsupportedEncodingException{
        final String msg = "Hello Thrift!\n";
        final String stop_cmd = "STOP";
        final int buf_size = 1024 * 8;
        byte[] buf = new byte[buf_size];
        TServerTransport acceptor = new TServerSocket(8585);
        acceptor.listen();
        System.out.println("[Server] listening on port 8585");
        while (true) {
            TTransport ep_trans = acceptor.accept();
            /**
             * 分层传输，使用TFramedTransport
             */
            TTransport trans = new TFramedTransport(ep_trans);
            System.out.println("[Server] handling request");
            trans.read(buf, 0, buf_size);
            if (stop_cmd.regionMatches(0, new String(buf, 0, buf.length, StandardCharsets.UTF_8), 0, 4)) {
                break;
            }
            trans.write(msg.getBytes());
            trans.flush();
            trans.close();
        }
        System.out.println("[Server] exiting");
        acceptor.close();
    }
}
