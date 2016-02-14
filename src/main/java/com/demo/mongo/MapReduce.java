package com.demo.mongo;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.mapreduce.MapReduceOptions;
import org.springframework.data.mongodb.core.mapreduce.MapReduceResults;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

public class MapReduce {

	public static void main(String[] args) throws InterruptedException {
		ClassPathXmlApplicationContext c = new ClassPathXmlApplicationContext("classpath:mongo/spring/applicationContext.xml");
		final MongoTemplate mt = (MongoTemplate) c.getBean("mongoTemplate");

		final Criteria criteria = Criteria.where("timestamp").lt(System.currentTimeMillis());

		final Query query = new Query().addCriteria(criteria).with(new Sort(Sort.Direction.ASC, "timestamp"));
		Thread t1 = new Thread(new Runnable() {

			@Override
			public void run() {
				MapReduceResults<ValueObject> results = mt.mapReduce(query, "NML", "classpath:mongo/js/map.js", "classpath:mongo/js/reduce.js", new MapReduceOptions().outputTypeMerge().outputCollection("NML_PROTOCOL_MINUTE"), ValueObject.class);
				for (ValueObject valueObject : results) {
					System.out.println(valueObject);
				}

			}
		});
		Thread t2 = new Thread(new Runnable() {

			@Override
			public void run() {
				MapReduceResults<ValueObject> results = mt.mapReduce(query, "NML", "classpath:mongo/js/srcIp.js", "classpath:mongo/js/reduce.js", new MapReduceOptions().outputTypeMerge().outputCollection("NML_SRCIP_MINUTE"), ValueObject.class);
				for (ValueObject valueObject : results) {
					System.out.println(valueObject);
				}

			}
		});
		Thread t3 = new Thread(new Runnable() {

			@Override
			public void run() {
				MapReduceResults<ValueObject> results = mt.mapReduce(query, "NML", "classpath:mongo/js/destIp.js", "classpath:mongo/js/reduce.js", new MapReduceOptions().outputTypeMerge().outputCollection("NML_DESTIP_MINUTE"), ValueObject.class);
				for (ValueObject valueObject : results) {
					System.out.println(valueObject);
				}

			}
		});

		
		t1.start();
		t2.start();
		t3.start();
		
		t1.join();
		t2.join();
		t3.join();
		c.close();
	}

}
