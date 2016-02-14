package com.demo.thrift.client;

import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;

import com.demo.thrift.iface.DemoService;
import com.demo.thrift.model.HelloMan;

public class ThriftClient {

	public static void main(String[] args) {

		HelloMan man = new HelloMan();
		man.setName("xx");
		man.setAge(20);
		TTransport transport = null;
		try {
			transport = new TFramedTransport(new TSocket("192.168.8.57", 9999, 60000));
			TProtocol protocol = new TBinaryProtocol(transport);
			DemoService.Client client = new DemoService.Client(protocol);
			transport.open();
			String rtn = client.sayHello(man);
			System.out.println(rtn);
			rtn = client.sayHello(man);
		} catch(Throwable e) {
			e.printStackTrace();
		} finally {
			if(transport != null) {
				transport.close();
			}
		}
	}

}
