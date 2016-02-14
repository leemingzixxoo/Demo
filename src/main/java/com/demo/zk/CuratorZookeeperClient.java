package com.demo.zk;

import java.net.URLEncoder;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.CuratorFrameworkFactory.Builder;
import org.apache.curator.retry.RetryNTimes;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException.NodeExistsException;

public class CuratorZookeeperClient {
	
	private final CuratorFramework client;
	
	public CuratorZookeeperClient() {
		Builder builder = CuratorFrameworkFactory.builder()
				.connectString("127.0.0.1:2181")
		        .retryPolicy(new RetryNTimes(Integer.MAX_VALUE, 1000))  
		        .connectionTimeoutMs(5000)
		        .sessionTimeoutMs(60 * 1000);
		
		client = builder.build();
		client.start();
	}

	public static void main(String[] args) throws Exception {
		CuratorZookeeperClient obj = new CuratorZookeeperClient();
		obj.createPersistent("/zktest");
		long start = System.currentTimeMillis();
		for(int i=0; i<7; i++) {
			obj.createEphemeral("/zktest/node-" + i);			
		}
		long end = System.currentTimeMillis();
		System.out.println(obj.getChildren("/zktest"));
		System.out.println(end - start);
		
		
		start = System.currentTimeMillis();
		for(int i=0; i<7; i++) {
			obj.delete("/zktest/node-" + i);
			obj.createEphemeral("/zktest/node-" + i);			
		}
		end = System.currentTimeMillis();
		System.out.println(obj.getChildren("/zktest"));
		System.out.println(end - start);
		
		
		String service = "com.dmall.push.api.notification.wechat.WechatNotificationService";
		String provider = URLEncoder.encode("dubbo://192.168.8.57:20880/com.dmall.dubbed.demo.DemoService?acl=false&anyhost=true&application=dsp_demoProvider&delay=-1&dubbo=2.0.0&generic=false&interface=com.dmall.dubbed.demo.DemoService&loadbalance=random&methods=methodAAA,uptonExcetion,methodBBB,sayHello&owner=ming.li&pid=6408&side=provider&timestamp=1453427781259", "UTF-8");
		for(int i=0; i<500; i++) {
			for(int j=0; j<50; j++) {
				obj.create("/testdubbo/" + service + i + "/providers/" + provider + j, false);
			}
		}
		
	}
	
	private void create(String path, boolean ephemeral) {
		int i = path.lastIndexOf('/');
		if (i > 0) {
			create(path.substring(0, i), false);
		}
		if (ephemeral) {
			createEphemeral(path);
		} else {
			createPersistent(path);
		}
	}
	
	private void createPersistent(String path) {
		try {
			client.create().forPath(path);
		} catch (NodeExistsException e) {
		} catch (Exception e) {
			throw new IllegalStateException(e.getMessage(), e);
		}
	}

	private void createEphemeral(String path) {
		try {
			client.create().withMode(CreateMode.EPHEMERAL).forPath(path);
		} catch (NodeExistsException e) {
		} catch (Exception e) {
			throw new IllegalStateException(e.getMessage(), e);
		}
	}
	
	private void delete(String path) {
		try {
			client.delete().forPath(path);
		} catch (NodeExistsException e) {
		} catch (Exception e) {
			throw new IllegalStateException(e.getMessage(), e);
		}
	}
	
	private int getChildren(String path) {
		int size = 0;
		try {
			size = client.getChildren().forPath(path).size();
		} catch (NodeExistsException e) {
		} catch (Exception e) {
			throw new IllegalStateException(e.getMessage(), e);
		}
		return size;
	}

}
