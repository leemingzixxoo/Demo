package com.demo.thrift.server;

import org.apache.thrift.TProcessor;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.server.TNonblockingServer;
import org.apache.thrift.server.TServer;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TNonblockingServerSocket;
import org.apache.thrift.transport.TTransportException;

import com.demo.thrift.iface.DemoService;
import com.demo.thrift.iface.DemoService.Iface;
import com.demo.thrift.impl.DemoServiceImpl;

public class ThriftServer {

	public static void main(String[] args) throws TTransportException {
		TProcessor processor = new DemoService.Processor<Iface>(new DemoServiceImpl());
		TNonblockingServerSocket transport = new TNonblockingServerSocket(9999);
		TNonblockingServer.Args tArgs = new TNonblockingServer.Args(transport);
		tArgs.processor(processor);
		tArgs.transportFactory(new TFramedTransport.Factory());
		tArgs.protocolFactory(new TBinaryProtocol.Factory());
		TServer server = new TNonblockingServer(tArgs);
		server.serve();
		System.out.println("Server Started.....");
	}

}
