package com.demo.mongo;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.mongodb.core.MongoTemplate;

public class NMLogGenerator {

	private static String[] inout = {"IN","OUT"};
	private static String[] protocal = {"HTTP","HTTPS"};
	private static String[] operation = {"GET","POST"};
	private static String[] protocalL4 = {"TCP","UDP"};
	private static final Random r = new Random();
	
	public static void main(String[] args) {
		ClassPathXmlApplicationContext c = new ClassPathXmlApplicationContext("classpath:mongo/spring/applicationContext.xml");
		MongoTemplate mt = (MongoTemplate) c.getBean("mongoTemplate");
		List<NetMonitorLog> list = new ArrayList<>();
		for(int i=0; i<1000000; i++){
			NetMonitorLog n = new NetMonitorLog();
			n.setTimestamp(System.currentTimeMillis());
			n.setProtocalType(protocal[r.nextInt(2)]);
			n.setSrcIP("192.168.1."+r.nextInt(244));
			n.setDstIP("192.168.10."+r.nextInt(244));
			n.setInOrOut(inout[r.nextInt(2)]);
			n.setSentSize(r.nextInt(500000));
			n.setReceiveSize(r.nextInt(1000000));
			n.setGuid("54a05087-39c2-0000-0000-54a0508739c1");
			n.setDateStr("2016-01-13");
			n.setHourStr("2016-01-13 09");
			n.setMinStr("2016-01-13 09:11");
			n.setProtocalL4(protocalL4[r.nextInt(2)]);
			n.setDuration(0);
			n.setUser("");
			n.setOperation(operation[r.nextInt(2)]);
			n.setClientIP("192.168.1."+r.nextInt(244));
			n.setSrcPort(r.nextInt(65535));
			n.setDstPort(r.nextInt(65535));
			n.setApplicationName("");
			n.setSendPacketNum(0);
			n.setRecvPacketNum(0);
			n.setUrl("http://dl.ijinshan.com/kphone/config/findPhoneCfgEx.json");
			n.setHost("dl.ijinshan.com ");
			n.setIshttp(0);
			list.add(n);
			if(i % 1000 == 0){
				mt.insert(list,NetMonitorLog.class);
				list = new ArrayList<>();
				System.out.println(i);
			}
		}
		mt.insert(list,NetMonitorLog.class);
		c.close();
	}
	
}
