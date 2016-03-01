package com.demo.mongo;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.mapreduce.MapReduceOptions;
import org.springframework.data.mongodb.core.mapreduce.MapReduceResults;
import org.springframework.data.mongodb.core.query.Query;

public class MapReduce {

	public static void main(String[] args) throws InterruptedException {
		ClassPathXmlApplicationContext c = new ClassPathXmlApplicationContext("classpath:mongo/spring/applicationContext.xml");
		final MongoTemplate mt = (MongoTemplate) c.getBean("mongoTemplate");

		//final Criteria criteria = Criteria.where("timestamp").lt(System.currentTimeMillis());

		//final Query query = new Query().addCriteria(criteria).with(new Sort(Sort.Direction.ASC, "timestamp"));

		/*DBObject o = new BasicDBObject();
		o.put("mapreduce", "NetMonitorLog");
		o.put("map", "function() {var key={timestamp:NumberLong(this.timestamp), protocalType:this.protocalType}; var value={input:this.receiveSize, output:this.sentSize, total:NumberLong(this.receiveSize + this.sentSize), count:1}; emit(key, value);}");
		o.put("reduce", "function(key, values){ var rtn = {input:0, output:0, total:0, count:0}; for(var i=0; i<values.length; i++) { rtn.input += values[i].input; rtn.output += values[i].output; rtn.total += values[i].total; rtn.count += values[i].count; } rtn.input = NumberLong(rtn.input); rtn.output = NumberLong(rtn.output); rtn.total = NumberLong(rtn.total); rtn.count = NumberLong(rtn.count); return rtn;}");
		o.put("out", "NML_PROTOCOL_MINUTE");
		DBObject sort = new BasicDBObject();
		sort.put("timestamp", 1);
		o.put("sort", sort);
		o.put("jsMode", true);*/
		
		
		//DB db = mt.getDb();
		long start = System.currentTimeMillis();
		//CommandResult cr = db.command(o);	
		
		MapReduceResults<ValueObject> results = mt.mapReduce(new Query(), "NetMonitorLog", "classpath:mongo/js/srcIp.js", "classpath:mongo/js/reduce.js", new MapReduceOptions().outputTypeMerge().outputCollection("NML_PROTOCOL_MINUTE"), ValueObject.class);
//		for (ValueObject valueObject : results) {
//			System.out.println(valueObject);
//		}
		long end = System.currentTimeMillis();
		System.out.println(end -start);
		c.close();
	}

}
