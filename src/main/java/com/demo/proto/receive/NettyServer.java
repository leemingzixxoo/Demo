package com.demo.proto.receive;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufEncoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32FrameDecoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32LengthFieldPrepender;

import com.demo.proto.model.PersonProto;

public class NettyServer {
	
	public static void main(String[] args) {
		NettyServer server = new NettyServer();
		server.startNettyServer();
	}
	
	private void startNettyServer() {

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
					pipeline.addLast("frameDecoder", new ProtobufVarint32FrameDecoder());   
					pipeline.addLast("protobufDecoder", new ProtobufDecoder(PersonProto.Person.getDefaultInstance()));   
					pipeline.addLast("frameEncoder", new ProtobufVarint32LengthFieldPrepender());  
					pipeline.addLast("protobufEncoder", new ProtobufEncoder());
					pipeline.addLast("handler", new ReceiveHandler());
					
				}});
			
			ChannelFuture cf = sb.bind(9999).sync();
			
			cf.channel().closeFuture().sync();			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			bossGroup.shutdownGracefully();
			workerGroup.shutdownGracefully();
		}
	}
}
