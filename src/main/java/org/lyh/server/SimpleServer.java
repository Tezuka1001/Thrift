package org.lyh.server;


import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TSimpleServer;
import org.apache.thrift.transport.TServerSocket;
import org.lyh.service.HelloWorldService;
import org.lyh.service.HelloWorldServiceImpl;

import java.net.ServerSocket;

public class SimpleServer {

    public static void main(String[] args) throws Exception {
        ServerSocket serverSocket = new ServerSocket(8000);//新建一个socket对象
        TServerSocket tServerSocket = new TServerSocket(serverSocket);//阻塞同步IO模型
        HelloWorldService.Processor processor = new HelloWorldService.Processor<HelloWorldService.Iface>(new HelloWorldServiceImpl());
        TBinaryProtocol.Factory factory = new TBinaryProtocol.Factory();//创建一个协议工厂

        //设置服务端的参数
        TSimpleServer.Args tArgs = new TSimpleServer.Args(tServerSocket);//IO模型
        tArgs.processor(processor);//处理逻辑
        tArgs.protocolFactory(factory);//使用的协议

        //启动服务
        TServer tServer = new TSimpleServer(tArgs);
        System.out.println("simple org.lyh.server started");
        tServer.serve();
    }
}
