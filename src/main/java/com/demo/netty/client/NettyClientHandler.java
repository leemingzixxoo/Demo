package com.demo.netty.client;

import java.util.Random;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * Netty TCP 粘包处理
 * @author Ming.Li
 *
 */
public class NettyClientHandler extends ChannelInboundHandlerAdapter {

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {

		byte[] temp = new byte[43];
		temp[0]=0x23;
		temp[1]=0x42;
		temp[2]=0x0B;
		temp[3]=0x00;
		temp[4]=0x01;
		temp[5]=0x00;
		temp[6]=0x00;
		temp[7]=(byte) 0x80;
		temp[8]=0x02;
		temp[9]=(byte)0xBB;
		temp[10]=0x00;
		temp[11]=0x00;
		temp[12]=0x01;
		temp[13]=0x67;
		temp[14]=(byte)0x96;
		temp[15]=(byte)0xF1;
		temp[16]=0x00;
		temp[17]=0x28;
		temp[18]=0x11;
		temp[19]=0x11;
		temp[20]=0x11;
		temp[21]=0x22;
		temp[22]=0x22;
		temp[23]=0x22;
		temp[24]=0x33;
		temp[25]=0x33;
		temp[26]=0x44;
		temp[27]=0x44;
		temp[28]=0x00;
		temp[29]=0x00;
		temp[30]=0x00;
		temp[31]=0x00;
		temp[32]=0x00;
		temp[33]=0x00;
		temp[34]=0x00;
		temp[35]=0x00;
		temp[36]=0x00;
		temp[37]=0x00;
		temp[38]=0x00;
		temp[39]=0x0D;
		temp[40]=0x0A;
		
		int arrayIndex = 0;
		while(true) {
						
			int len = new Random().nextInt(5) + 1;
			byte[] send = new byte[len];
			
			for(int i=0; i<len; i++) {
				if(arrayIndex > 40) {
					arrayIndex = 0;
				}
				
				send[i] = temp[arrayIndex++];				
			}
			
			ByteBuf encoded = ctx.alloc().buffer(len);  
            encoded.writeBytes(send);  
            ctx.writeAndFlush(encoded);
            
            Thread.sleep(new Random().nextInt(100));
		}
	}

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		
		System.out.println("client read");
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		
		System.out.println("exceptionCaught");
		System.out.println(cause);
	}

}
