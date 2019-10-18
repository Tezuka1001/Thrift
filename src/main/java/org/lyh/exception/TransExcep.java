package org.lyh.exception;

import org.apache.thrift.transport.TSimpleFileTransport;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;
import org.lyh.model.Trade;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * @author lyh
 * @version 2019-10-18 10:40
 */
public class TransExcep {

    public static void main(String[] args) {
        try {
            TTransport trans = new TSimpleFileTransport("data", false, true);
            Trade trade = new Trade("F", 13.10, 2500);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(trade);
            trans.write(baos.toByteArray());
            trans.close();

            trans = new TSimpleFileTransport("data", (args.length == 0), true);
            byte[] buf = new byte[128];
            int iBytesRead = trans.read(buf, 0, buf.length);
            ByteArrayInputStream bais = new ByteArrayInputStream(buf);
            ObjectInputStream ois = new ObjectInputStream(bais);
            trade = (Trade) ois.readObject();
            System.out.println("Trade(" + iBytesRead + "): " + trade.getSymbol()
                    + " " + trade.getSize() + " @ " + trade.getPrice());
            /**
             * 在原先简单的文件传输的代码中加入异常处理块，使代码更加健壮
             */
        } catch (TTransportException tte) {
            System.out.println("TTransportException(" + tte.getType() + "): " + tte);
        } catch (Exception e) {
            System.out.println("Exception: " + e);
        } catch (Throwable t) {
            System.out.println("Throwable: " + t);
        }
    }
}
