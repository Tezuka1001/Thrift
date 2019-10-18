package org.lyh.client;

import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;

/**
 * @author lyh
 * @version 2019-10-17 18:09
 */
public class SockClient {

    public static void main(String[] args) throws TTransportException {
        TTransport transport = new TSocket("localhost", 8585);
        transport.open();
        transport.write("hello".getBytes());
        transport.flush();
        transport.close();
    }
}
