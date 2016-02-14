package com.demo.proto.send;

import com.demo.proto.model.PersonProto;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class SendHandler extends ChannelInboundHandlerAdapter {
	
	private ChannelHandlerContext ctx;
	
	private volatile boolean stop = false;

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		this.ctx = ctx;
		new Thread(new SendMsg()).start();
	}

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		System.out.println(msg);
		PersonProto.Person person = (PersonProto.Person) msg;
		System.out.println(person.getName());
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		System.out.println("**********exception*************");
		cause.printStackTrace();
		stop = true;
		ctx.channel().close();
	}
	
	final class SendMsg implements Runnable {
		
		public void run() {
			PersonProto.Person.Builder personBuilder = PersonProto.Person.newBuilder();
			personBuilder.setName("ming.li");
			personBuilder.setAge(30);
			personBuilder.addWife("hong");
			personBuilder.addWife("meng");
			personBuilder.addWife("yu");
			
			int i = 10000;
			while(!stop) {
				personBuilder.setSalary(i++);
				ctx.writeAndFlush(personBuilder.build());
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			
		}
		
	}

}


