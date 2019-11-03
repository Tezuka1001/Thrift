package org.lyh.server;


import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TSimpleServer;
import org.apache.thrift.transport.TServerSocket;
import org.lyh.service.HelloWorldService;
import org.lyh.service.impl.HelloWorldServiceImpl;

import java.net.ServerSocket;

public class SimpleServer {

    public static void main(String[] args) throws Exception {
        ServerSocket serverSocket = new ServerSocket(8000);//新建一个ServerSocket对象，用于监听连接
        TServerSocket tServerSocket = new TServerSocket(serverSocket);//创建thrift传输层socket，选择阻塞同步IO模型
        HelloWorldService.Processor processor = new HelloWorldService.Processor<HelloWorldService.Iface>(new HelloWorldServiceImpl());//注册一个处理器
        TBinaryProtocol.Factory factory = new TBinaryProtocol.Factory();//创建一个协议工厂，选择二进制序列化协议

        //设置服务端的参数
        TSimpleServer.Args tArgs = new TSimpleServer.Args(tServerSocket);//选择简单的IO模型服务器
        tArgs.processor(processor);
        tArgs.protocolFactory(factory);

        //启动服务
        TServer tServer = new TSimpleServer(tArgs);
        System.out.println("simple org.lyh.server started");
        tServer.serve();
    }
}
