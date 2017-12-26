package com.demo.netty.client;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

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
		temp[41]=0x23;
		temp[42]=0x42;
		
        ByteBuf encoded = ctx.alloc().buffer(43);  
        encoded.writeBytes(temp);  
        ctx.writeAndFlush(encoded);
        
        byte[] temp2 = new byte[43];
		temp2[0]=0x0B;
		temp2[1]=0x00;
		temp2[2]=0x01;
		temp2[3]=0x00;
		temp2[4]=0x00;
		temp2[5]=(byte) 0x80;
		temp2[6]=0x02;
		temp2[7]=(byte)0xBB;
		temp2[8]=0x00;
		temp2[9]=0x00;
		temp2[10]=0x01;
		temp2[11]=0x67;
		temp2[12]=(byte)0x96;
		temp2[13]=(byte)0xF1;
		temp2[14]=0x00;
		temp2[15]=0x28;
		temp2[16]=0x11;
		temp2[17]=0x11;
		temp2[18]=0x11;
		temp2[19]=0x22;
		temp2[20]=0x22;
		temp2[21]=0x22;
		temp2[22]=0x33;
		temp2[23]=0x33;
		temp2[24]=0x44;
		temp2[25]=0x44;
		temp2[26]=0x00;
		temp2[27]=0x00;
		temp2[28]=0x00;
		temp2[29]=0x00;
		temp2[30]=0x00;
		temp2[31]=0x00;
		temp2[32]=0x00;
		temp2[33]=0x00;
		temp2[34]=0x00;
		temp2[35]=0x00;
		temp2[36]=0x00;
		temp2[37]=0x0D;
		temp2[38]=0x0A;
		temp2[39]=0x0B;
		temp2[40]=0x00;
		temp2[41]=0x01;
		temp2[42]=0x00;
		
		ByteBuf encoded2 = ctx.alloc().buffer(43);  
        encoded2.writeBytes(temp2);  
        ctx.writeAndFlush(encoded2);
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
