package org.lyh.serialize;

import org.apache.thrift.TException;
import org.apache.thrift.protocol.*;
import org.apache.thrift.transport.TSimpleFileTransport;
import org.apache.thrift.transport.TTransport;
import org.lyh.model.Trade;

/**
 * @author lyh
 * @version 2019-10-20 12:18
 */
public class BinFileWrite {

    /**
     * 这里使用C++ Python序列化写文件也是一样的，thrift的跨语言性
     * @param args 无
     */
    public static void main(String[] args) throws TException {
        TTransport trans = new TSimpleFileTransport("data", false, true);
        TProtocol proto = new TBinaryProtocol(trans);
        Trade trade = new Trade("F", 13.10, 2500);

        /**
         * 通过TProtocol序列化，然后用TSimpleFileTransport写入文件
         */
        proto.writeStructBegin(new TStruct());
        proto.writeFieldBegin(new TField("symbol", TType.STRING, (short) 1));
        proto.writeString(trade.getSymbol());
        proto.writeFieldEnd();
        proto.writeFieldBegin(new TField("price", TType.DOUBLE, (short) 2));
        proto.writeDouble(trade.getPrice());
        proto.writeFieldEnd();
        proto.writeFieldBegin(new TField("size", TType.I32, (short) 3));
        proto.writeI32(trade.getSize());
        proto.writeFieldEnd();
        proto.writeFieldStop();
        proto.writeStructEnd();

        System.out.println("Wrote trade to file");
    }
}
