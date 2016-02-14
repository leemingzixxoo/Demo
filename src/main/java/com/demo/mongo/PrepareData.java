package com.demo.mongo;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.mongodb.core.MongoTemplate;

public class PrepareData {

	private static String[] inout = {"IN", "OUT"};
	private static String[] protocol = {"HTTP", "HTTPS", "FTP"};
	private static final Random r = new Random();
	
	public static void main(String[] args) throws InterruptedException {
		ClassPathXmlApplicationContext c = new ClassPathXmlApplicationContext("classpath:spring/applicationContext.xml");
		MongoTemplate mt = (MongoTemplate) c.getBean("mongoTemplate");
		List<NML> list = new ArrayList<>();
		long start = System.currentTimeMillis();
		for(int i=0; i<10000000; i++) {
			NML n = new NML();
			start = start + r.nextInt(100000000);
			n.setTimestamp(start);
			n.setProtocalType(protocol[r.nextInt(3)]);
			n.setSrcIp("192.168.1.10" + r.nextInt(244));
			n.setDestIp("192.168.1.10" + r.nextInt(244));
			n.setInOrOut(inout[r.nextInt(2)]);
			n.setSentSize(r.nextInt(500000));
			n.setReceiveSize(r.nextInt(1000000));
			n.setContent("我们的祖国刷元，华阳的花朵真鲜艳，阳光照耀着我们每个人脸上都笑开颜，哇哈哈啊哇哈哈，每个人脸上都笑开颜");
			list.add(n);
			if(i % 1000 == 0) {
				mt.insert(list, NML.class);
				list = new ArrayList<>();
				System.out.println(i);
			}
		}
		mt.insert(list, NML.class);
		c.close();
	}

}
