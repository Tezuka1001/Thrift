package org.lyh.transports;

import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;

import java.nio.charset.StandardCharsets;

/**
 * @author lyh
 * @version 2019-10-17 17:30
 */
public class SockTransDemo {

    public static void main(String[] args) throws TTransportException {
        TTransport trans = new TSocket("thrift.apache.org", 80);
        final String msg = "GET / \n";
        trans.open();
        /**
         * 通过TSocket向网络服务器传输数据
         */
        trans.write(msg.getBytes());
        trans.flush();

        /**
         * 读取网络返回结果，并打印
         */
        readTrans(trans);
        trans.close();
    }

    private static void readTrans(TTransport trans) {
        final int bufSize = 1024 * 8;
        byte[] buf = new byte[bufSize];
        while (true) {
            try {
                int bytesRead = trans.read(buf, 0, bufSize);
                if (bytesRead <= 0 || bufSize < bytesRead) {
                    break;
                }
                System.out.print(new String(buf, 0, bytesRead, StandardCharsets.UTF_8));
            } catch (Throwable t) {
                break;
            }
        }
    }
}
