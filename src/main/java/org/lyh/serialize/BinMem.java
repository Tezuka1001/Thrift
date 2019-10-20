package org.lyh.serialize;

import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TMemoryBuffer;

/**
 * @author lyh
 * @version 2019-10-18 11:48
 */
public class BinMem {

    public static void main(String[] args) throws TException {
        TMemoryBuffer trans = new TMemoryBuffer(4096);
        /**
         * 使用Binary序列化协议
         */
        TProtocol proto = new TBinaryProtocol(trans);
        proto.writeString("Hello Thrift Serialization");
        System.out.println("Wrote " + trans.length() + " bytes to the TMemoryBuffer");
        String strMsg = proto.readString();
        System.out.println("Recovered string: " + strMsg);
    }
}
