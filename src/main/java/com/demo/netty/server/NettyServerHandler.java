package com.demo.netty.server;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class NettyServerHandler extends ChannelInboundHandlerAdapter {

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {

		System.out.println("channelActive");
	}

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

		ByteBuf bf = (ByteBuf) msg;
		byte[] b = new byte[bf.readableBytes()];
		bf.readBytes(b);
		String resultStr = new String(b);  
        System.out.println("Client said:" + resultStr);    
		
        bf.release();
        
		System.out.println("channelRead");
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {

		System.out.println("exceptionCaught");
		System.out.println(cause);
	}

}
