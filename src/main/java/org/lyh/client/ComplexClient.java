package org.lyh.client;

import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.lyh.service.HelloWorldService;

/**
 * @author lyh
 * @version 2019-10-25 11:36
 */
public class ComplexClient {

    public static void main(String[] args) {
        TSocket transport = null;
        try {
            transport = new TSocket("localhost", 8000, 500);//BIO
            TProtocol protocol = new TBinaryProtocol(transport);
            HelloWorldService.Client client = new HelloWorldService.Client(protocol);//创建一个同步客户端对象
            transport.open();
            System.out.println(client.say("lyh"));//rpc
        } catch (TException e) {
            e.printStackTrace();
        } finally {
            if (null != transport) {
                transport.close();
            }
        }
    }
}
