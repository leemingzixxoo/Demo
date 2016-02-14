package com.demo.proto.receive;

import com.demo.proto.model.PersonProto;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class ReceiveHandler extends ChannelInboundHandlerAdapter {

	@Override
	public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
		System.out.println("handlerAdded -- " + ctx.channel().remoteAddress());
	}

	@Override
	public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
		System.out.println("handlerRemoved -- " + ctx.channel().remoteAddress());
	}

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		PersonProto.Person person = (PersonProto.Person) msg;
		System.out.println(person.getName());
		System.out.println(person.getAge());
		System.out.println(person.getSalary());
		for(String wife : person.getWifeList()) {
			System.out.println(wife);
		}
		System.out.println("-------------------------------");
		
		ctx.write(person);
		ctx.flush();
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		cause.printStackTrace();
		ctx.channel().close();
	}
}
