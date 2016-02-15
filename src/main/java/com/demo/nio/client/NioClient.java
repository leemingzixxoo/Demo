package com.demo.nio.client;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.Random;

public class NioClient {

	public static void main(String[] args) throws IOException {
		NioClient client = new NioClient();
		client.startClient();
	}

	private void startClient() {
		SocketChannel channel = null;
		ByteBuffer buffer = ByteBuffer.allocate(1024);
		Random r = new Random();
		try {
			channel = SocketChannel.open();
			channel.configureBlocking(false);
			channel.connect(new InetSocketAddress("localhost", 9999));
			while(!channel.finishConnect()){};
			for(int i=0; i<10; i++) {
				buffer.clear();
				int item = r.nextInt(10000);
				String val = "测试文本内容-" + item;
				buffer.putInt(val.getBytes().length);
				buffer.put(val.getBytes());
				buffer.flip();
				channel.write(buffer);	
				System.out.println("Send --> " + val);
			}
			System.in.read();
		} catch (Exception e) {
			try {
				channel.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}
}
