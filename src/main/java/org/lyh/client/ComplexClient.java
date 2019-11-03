package org.lyh.client;

import org.apache.thrift.TException;
import org.apache.thrift.protocol.TJSONProtocol;
import org.apache.thrift.protocol.TMultiplexedProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.lyh.service.ComplextService;

/**
 * @author lyh
 * @version 2019-10-25 11:36
 */
public class ComplexClient {

    public static void main(String[] args) {
        TTransport transport = null;
        try {
            transport = new TFramedTransport(new TSocket("localhost", 8000, 500));
            TProtocol protocol = new TMultiplexedProtocol(new TJSONProtocol(transport), "complex_server");
            ComplextService.Client client = new ComplextService.Client(protocol);
            transport.open();
            System.out.println(client.getFullName("Tezuka", "Kunimitsu"));
        } catch (TException e) {
            e.printStackTrace();
        } finally {
            if (null != transport) {
                transport.close();
            }
        }
    }
}
