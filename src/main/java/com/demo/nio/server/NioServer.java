package com.demo.nio.server;


import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

public class NioServer {

	private Selector selector;
	private ServerSocketChannel server;
	
	public static void main(String[] args) {
		NioServer server = new NioServer();
		server.startServer();
	}
	
	private void startServer() {
		try {
			selector = Selector.open();
			server = ServerSocketChannel.open();
			server.bind(new InetSocketAddress(9999));
			server.configureBlocking(false);
			server.register(selector, SelectionKey.OP_ACCEPT);
			
			SocketChannel client;
			SelectionKey key;
			Iterator<SelectionKey> iKeys;
			while(true) {
				selector.select();
				iKeys = selector.selectedKeys().iterator();
				while(iKeys.hasNext()) {
					key = iKeys.next();	
					iKeys.remove();
					if(key.isAcceptable()) {
						client = server.accept();
						client.configureBlocking(false);
						client.register(selector, SelectionKey.OP_READ);
					} else if(key.isReadable()) {
						client = (SocketChannel) key.channel();
						processClient(client);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void processClient(SocketChannel client) {
		ByteBuffer buffer = ByteBuffer.allocate(1024);
		buffer.clear();
		try {
			while(true) {
				int count = client.read(buffer);
				if(count == -1) {					
				}
				if(count == 0) {
					buffer.flip();
					while(buffer.remaining() > 0) { //防止client发送比server处理快，导致数据丢失的问题
						int length = buffer.getInt();
						byte[] data = new byte[length];
						buffer.get(data);
						System.out.println("Received --> " + new String(data, "UTF-8"));	
						Thread.sleep(1000);
					}
					break;
				}
			}
		} catch(Exception e) {
			try {
				client.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}

}
