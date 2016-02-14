package com.demo.thrift.impl;
import org.apache.thrift.TException;

import com.demo.thrift.iface.DemoService;
import com.demo.thrift.model.HelloMan;

public class DemoServiceImpl implements DemoService.Iface {

	@Override
	public String sayHello(HelloMan man) throws TException {

		System.out.println(man.getName());
		System.out.println(man.getAge());
		return man.getName() + " ----> " + man.getAge();
	}

}
