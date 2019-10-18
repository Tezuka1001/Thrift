package org.lyh.transports;

import org.apache.thrift.transport.TMemoryBuffer;
import org.apache.thrift.transport.TTransportException;
import org.lyh.model.Trade;

import java.io.*;

/**
 * @author lyh
 * @version 2019-10-10 21:22
 * Thrift memory transport Demo
 */
public class MemTransDemo {

    public static void main(String[] args) throws IOException, TTransportException, ClassNotFoundException {
        Trade trade = new Trade("F", 13.10, 2500);

        /**
         * 创建一个4K的内存缓存区
         */
        TMemoryBuffer buffer = new TMemoryBuffer(4096);

        /**
         * 通过ObjectOutputStream写入buffer
         */
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(trade);
        buffer.write(baos.toByteArray());

        /**
         * 通过ObjectInputStream读取buffer字节流，并转化为对象
         */
        byte[] buf = new byte[4096];
        int bytes_read = buffer.read(buf, 0, buf.length);
        ByteArrayInputStream bais = new ByteArrayInputStream(buf);
        ObjectInputStream ois = new ObjectInputStream(bais);
        Trade tradeRead = (Trade) ois.readObject();

        System.out.println("Trade(" + bytes_read + "): " +
                tradeRead.getSymbol() + " " + tradeRead.getSize() + " @ " + tradeRead.getPrice());
    }
}
