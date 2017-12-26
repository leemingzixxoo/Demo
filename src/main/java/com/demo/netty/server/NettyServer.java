package com.demo.netty.server;


import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.string.StringEncoder;

public class NettyServer {

	public static void main(String[] args) {

		EventLoopGroup bossGroup = new NioEventLoopGroup();
		EventLoopGroup workerGroup = new NioEventLoopGroup();
		
		try {
			ServerBootstrap sb = new ServerBootstrap();
			sb.group(bossGroup, workerGroup)
			.option(ChannelOption.SO_BACKLOG, 1024)
			.channel(NioServerSocketChannel.class)			
			.childOption(ChannelOption.SO_KEEPALIVE, true)
			.childHandler(new ChannelInitializer<SocketChannel>(){

				@Override
				protected void initChannel(SocketChannel sc) throws Exception {
					ChannelPipeline pipeline = sc.pipeline();
					//pipeline.addLast(new ByteArrayDecoder());
					//pipeline.addLast(new ByteArrayEncoder());
					
					//pipeline.addLast(new StringDecoder());
					//pipeline.addLast(new StringEncoder());
					
					byte[] temp = new byte[2];
					temp[0]=0x0D;
					temp[1]=0x0A;
					
					ByteBuf bf = Unpooled.wrappedBuffer(temp);
			        
					pipeline.addLast(new DelimiterBasedFrameDecoder(1024, false, bf));
					pipeline.addLast(new StringEncoder());
					
					pipeline.addLast(new NettyServerHandler());
					
				}});
			
			ChannelFuture cf = sb.bind(8888).sync();
			
			cf.channel().closeFuture().sync();			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			bossGroup.shutdownGracefully();
			workerGroup.shutdownGracefully();
		}
	}

}
