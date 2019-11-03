package org.lyh.transports.factory;

import org.apache.thrift.transport.*;
import org.lyh.transports.TTeeTransport;

/**
 * @author lyh
 * @version 2019-11-03 15:29
 * 自定义传输工厂，实现将服务器返回结果记录一个日志
 */
public class TWritelogTransportFactory extends TTransportFactory {

    private int clientId = 0;

    public TWritelogTransportFactory(int clientId) {
        this.clientId = clientId;
    }

    @Override
    public TTransport getTransport(TTransport trans) {
        TSimpleFileTransport log;
        try {
            log = new TSimpleFileTransport("server_log_" + clientId, false, true);
            log.open();
        } catch (TTransportException e) {
            log = null;
            e.printStackTrace();
        }
        return new TTeeTransport(new TFramedTransport(trans), log);
    }
}
