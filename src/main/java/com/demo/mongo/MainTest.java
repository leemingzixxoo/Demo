package com.demo.mongo;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;

import com.mongodb.WriteResult;

public class MainTest {

	public static void main(String[] args) {
		ClassPathXmlApplicationContext c = new ClassPathXmlApplicationContext("classpath:mongo/spring/applicationContext.xml");
		MongoTemplate mt = (MongoTemplate) c.getBean("mongoTemplate");
		long s = System.currentTimeMillis();
		WriteResult r = mt.remove(new Query(), NetMonitorLog.class);
		r.getN();
		long e = System.currentTimeMillis();
		System.out.println((e-s)/1000);
		c.close();
	}

}
