package org.lyh.transports;

import org.apache.thrift.transport.TSimpleFileTransport;
import org.apache.thrift.transport.TTransportException;

import java.io.*;

/**
 * @author lyh
 * @version 2019-10-10 21:33
 */
public class FileTransDemo {

    static private class Trade implements Serializable {
        String symbol;
        double price;
        int size;

        private Trade(String symbol, double price, int size) {
            this.symbol = symbol;
            this.price = price;
            this.size = size;
        }
    }

    public static void main(String[] args) throws TTransportException, IOException, ClassNotFoundException {
        Trade trade = new Trade("F", 13.10, 2500);

        /**
         * 通过TSimpleFileTransport向文件中写数据
         */
        TSimpleFileTransport transOut = new TSimpleFileTransport("data", false, true);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(trade);
        transOut.write(baos.toByteArray());
        transOut.close();

        /**
         * 通过TSimpleFileTransport在从文件中读取数据
         */
        TSimpleFileTransport transIn = new TSimpleFileTransport("data", true, false);
        byte[] buf = new byte[128];
        int iBytesRead = transIn.read(buf, 0, buf.length);
        ByteArrayInputStream bais = new ByteArrayInputStream(buf);
        ObjectInputStream ois = new ObjectInputStream(bais);
        trade = (Trade) ois.readObject();

        System.out.println("Trade(" + iBytesRead + "): " +
                trade.symbol + " " + trade.size + " @ " + trade.price);
    }

}
