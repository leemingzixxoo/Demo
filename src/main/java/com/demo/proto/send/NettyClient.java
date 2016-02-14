package com.demo.proto.send;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufEncoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32FrameDecoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32LengthFieldPrepender;

import com.demo.proto.model.PersonProto;

public class NettyClient {

	public static void main(String[] args) {
		NettyClient client = new NettyClient();
		client.startClient();
	}

	private void startClient() {
		EventLoopGroup workerGroup = new NioEventLoopGroup();  
		try {
			Bootstrap b = new Bootstrap();
			b.group(workerGroup)
			.channel(NioSocketChannel.class)
			.option(ChannelOption.SO_KEEPALIVE, true)
			.handler(new ChannelInitializer<SocketChannel>() {

				@Override
				protected void initChannel(SocketChannel sc) throws Exception {
					ChannelPipeline pipeline = sc.pipeline();
					pipeline.addLast("frameDecoder", new ProtobufVarint32FrameDecoder());   
					pipeline.addLast("protobufDecoder", new ProtobufDecoder(PersonProto.Person.getDefaultInstance()));   
					pipeline.addLast("frameEncoder", new ProtobufVarint32LengthFieldPrepender());  
					pipeline.addLast("protobufEncoder", new ProtobufEncoder());
					pipeline.addLast("handler", new SendHandler());
					
				}});
			
			ChannelFuture f = b.connect("192.168.8.57", 9999).sync();
			f.channel().closeFuture().sync();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			workerGroup.shutdownGracefully();
		}
	}
}
