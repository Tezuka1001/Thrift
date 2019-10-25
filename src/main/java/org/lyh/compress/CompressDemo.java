package org.lyh.compress;

import org.apache.thrift.TException;
import org.apache.thrift.transport.TSimpleFileTransport;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TZlibTransport;
import org.lyh.model.Trade;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

/**
 * @author lyh
 * @version 2019-10-23 10:35
 */
public class CompressDemo {

    public static void main(String[] args) throws TException, IOException {
        Trade trade = new Trade("F", 13.10, 2500);
        TTransport fileTransport = new TSimpleFileTransport("data", false, true);
        TTransport transport = new TZlibTransport(fileTransport);

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(trade);
        transport.write(baos.toByteArray());
        transport.close();

        System.out.println("Wrote CompressDemo trade to file");
    }
}
