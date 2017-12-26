package com.demo.netty.server;

import org.apache.commons.codec.binary.Hex;

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
        System.out.println(Hex.encodeHex(b));
        bf.release();
        
        ctx.writeAndFlush("received");
    }

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {

		System.out.println("exceptionCaught");
		System.out.println(cause);
	}

}
