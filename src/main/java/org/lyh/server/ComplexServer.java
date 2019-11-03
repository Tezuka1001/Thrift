package org.lyh.server;

import org.apache.thrift.TMultiplexedProcessor;
import org.apache.thrift.protocol.TJSONProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.server.ServerContext;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TServerEventHandler;
import org.apache.thrift.server.TThreadedSelectorServer;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TNonblockingServerSocket;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;
import org.lyh.service.ComplextService;
import org.lyh.service.HelloWorldService;
import org.lyh.service.impl.ComplexServiceImpl;
import org.lyh.service.impl.HelloWorldServiceImpl;
import org.lyh.transports.factory.TWritelogTransportFactory;

import java.io.IOException;

/**
 * @author lyh
 * @version 2019-10-27 15:38
 */
public class ComplexServer {

    public static void main(String[] args) throws IOException, TTransportException {
        /**
         * 创建一个Server对应的Args对象，并创建一个Server Socket
         */
        TThreadedSelectorServer.Args tArgs = new TThreadedSelectorServer.Args(
                new TNonblockingServerSocket(8000)
        );
        /**
         * 使用更加复杂的多路复用Processor TMultiplexedProcessor
         * 注册两个我们的Processor，用于处理业务逻辑
         * 然后设置到Args里，用于配置服务器
         */
        TMultiplexedProcessor multiplexedProcessor = new TMultiplexedProcessor();
        multiplexedProcessor.registerProcessor("hello_world", new HelloWorldService.Processor<>(new HelloWorldServiceImpl()));
        multiplexedProcessor.registerProcessor("complex_server", new ComplextService.Processor<>(new ComplexServiceImpl()));
        tArgs.processor(multiplexedProcessor);

        /**
         * 设置协议层的输入和输出工厂都为：TBinaryProtocol.Factory
         * 设置传输层的输入和输出工厂都为：TFramedTransport.Factory
         */
        //tArgs.protocolFactory(new TBinaryProtocol.Factory())
        //        .transportFactory(new TFramedTransport.Factory());

        /**
         * 设置协议层的输入工厂为：TJSONProtocol.Factory 输出工厂为：TJSONProtocol.Factory
         * 设置传输层的输入工厂为：TFramedTransport.Factory 输出工厂为：TWritelogTransportFactory
         * 一般input 和 output都设置为相同的，并且要与客户端相同，否则无法正常通信
         * 指定selector 和 worker线程数量
         */
        tArgs.inputProtocolFactory(new TJSONProtocol.Factory())
                .outputProtocolFactory(new TJSONProtocol.Factory())
                .inputTransportFactory(new TFramedTransport.Factory())
                .outputTransportFactory(new TWritelogTransportFactory(100))
                .selectorThreads(3)
                .workerThreads(6)
        ;

        /**
         * 创建Server对象，可以选择合适的IO模型Server，比如非阻塞的IO复用模型-TThreadedSelectorServer
         */
        TServer server = new TThreadedSelectorServer(tArgs);

        /**
         * thrift开放了接口TServerEventHandler，可以让开发者在一些Server event触发的时候，执行想要的逻辑
         * 这个处理器是非必选的，在需要的时候可以加上扩展的逻辑
         */
        server.setServerEventHandler(new TServerEventHandler() {
            @Override
            public void preServe() {
                System.out.println("server启动之前调用一次");
            }

            @Override
            public ServerContext createContext(TProtocol tProtocol, TProtocol tProtocol1) {
                System.out.println("在每个客户端连接到达之后立刻调用");
                return null;
            }

            @Override
            public void deleteContext(ServerContext serverContext, TProtocol tProtocol, TProtocol tProtocol1) {
                System.out.println("在每个客户端连接断开之前调用");
            }

            @Override
            public void processContext(ServerContext serverContext, TTransport tTransport, TTransport tTransport1) {
                System.out.println("在一次RPC的processor和接口逻辑之间调用");
            }
        });

        /**
         * 最后，启动Server即可
         */
        server.serve();
    }
}
