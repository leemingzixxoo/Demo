package com.demo.mongo;

import java.util.Random;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.mongodb.core.MongoTemplate;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

public class PrepareData {

	private static final Random r = new Random();
	
	public static void main(String[] args) throws InterruptedException {
		ClassPathXmlApplicationContext c = new ClassPathXmlApplicationContext("classpath:mongo/spring/applicationContext.xml");
		MongoTemplate mt = (MongoTemplate) c.getBean("mongoTemplate");
		
		for(int i=0; i<5000000; i++) {
			DBObject o = new BasicDBObject();
			o.put("xx", r.nextInt(100000));
			mt.getDb().getCollection("XXOO").insert(o);
		}	
		c.close();
	}

}
