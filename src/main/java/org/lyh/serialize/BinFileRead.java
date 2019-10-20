package org.lyh.serialize;

import org.apache.thrift.TException;
import org.apache.thrift.protocol.*;
import org.apache.thrift.transport.TSimpleFileTransport;
import org.apache.thrift.transport.TTransport;
import org.lyh.model.Trade;

/**
 * @author lyh
 * @version 2019-10-20 12:07
 * @see BinFileWrite 需要先写文件
 */
public class BinFileRead {

    public static void main(String[] args) throws TException {
        TTransport trans = new TSimpleFileTransport("data", true, false);
        TProtocol proto = new TBinaryProtocol(trans);
        Trade trade = new Trade();

        /**
         * 使用binary协议来反序列化数据，只要是用TBinaryProtocol序列化的数据都可以反序列化
         */
        proto.readStructBegin();
        while (true) {
            TField field = proto.readFieldBegin();
            if (field.id == TType.STOP) {
                break;
            }
            switch (field.id) {
                case 1:
                    trade.setSymbol(proto.readString());
                    break;
                case 2:
                    trade.setPrice(proto.readDouble());
                    break;
                case 3:
                    trade.setSize(proto.readI32());
                    break;
                default:
                    TProtocolUtil.skip(proto, field.type);
                    break;
            }
        }
        proto.readFieldEnd();
        proto.readStructEnd();

        System.out.println("Trade: " + trade.getSymbol() + " " + trade.getSize() + " @ " + trade.getPrice());
    }
}
