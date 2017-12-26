package com.demo.mongo;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.mapreduce.MapReduceOptions;
import org.springframework.data.mongodb.core.query.Query;

public class MapReduce {

	public static void main(String[] args) throws InterruptedException {
		ClassPathXmlApplicationContext c = new ClassPathXmlApplicationContext("classpath:mongo/spring/applicationContext.xml");
		final MongoTemplate mt = (MongoTemplate) c.getBean("mongoTemplate");

		//final Criteria criteria = Criteria.where("timestamp").lt(System.currentTimeMillis());

		//final Query query = new Query().addCriteria(criteria).with(new Sort(Sort.Direction.ASC, "timestamp"));
		
		long total = 0;
		for(int i=1; i<=10; i++) {
			long start = System.currentTimeMillis();
			mt.mapReduce(new Query(), "NetMonitorLog", "classpath:mongo/js/srcIp.js", "classpath:mongo/js/reduce.js", new MapReduceOptions().outputTypeMerge().outputCollection("NML_PROTOCOL_MINUTE"), ValueObject.class);
			long end = System.currentTimeMillis();
			total += (end -start);
			System.out.println(i);
		}
		System.out.println("10次平均使用时间：" + (total/1000/10) + " 秒！");
		c.close();
	}

}
