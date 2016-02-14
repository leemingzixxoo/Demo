package com.demo.proto.main;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import com.demo.proto.model.PersonProto;

public class Main {

	public static void main(String[] args) throws IOException {
		PersonProto.Person.Builder personBuilder = PersonProto.Person.newBuilder();
		personBuilder.setName("ming.li");
		personBuilder.setAge(30);
		personBuilder.setSalary(10000);
		personBuilder.addWife("hong");
		personBuilder.addWife("meng");
		personBuilder.addWife("yu");
		
		PersonProto.Person person = personBuilder.build();
		
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		person.writeTo(out);
		
		byte[] byteArray = out.toByteArray();
		
		ByteArrayInputStream in = new ByteArrayInputStream(byteArray);
		PersonProto.Person after = PersonProto.Person.parseFrom(in);
		System.out.println(after.getName());
		System.out.println(after.getAge());
		System.out.println(after.getSalary());
		for(String wife : after.getWifeList()) {
			System.out.println(wife);
		}
		
	}
}
