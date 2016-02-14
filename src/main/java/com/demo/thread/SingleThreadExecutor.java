package com.demo.thread;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 单线程池任务队列实现
 * @author Ming.Li
 *
 */
public class SingleThreadExecutor {

	private static SingleThreadExecutor instance = new SingleThreadExecutor();
	
	private BlockingQueue<Runnable> queue = new LinkedBlockingQueue<Runnable>();
	
	private Executor thread = null;
	
	private ReentrantLock lock = new ReentrantLock();

	private SingleThreadExecutor(){	
		thread = new Executor(queue, lock);
		thread.start();
	}
	
	public static SingleThreadExecutor getInstance() {
		return instance;
	}
	
	public void addTask(Runnable task) {
		
		queue.offer(task);
		try {
			lock.lock();
			if(thread.isStoped()) {
				thread = new Executor(queue, lock);
				thread.start();
			}	
		} finally {
			lock.unlock();
		}
	}
	
}

/**
 * 任务执行线程
 * @author Ming.Li
 *
 */
class Executor extends Thread {
	
	private volatile boolean stop = false;
	
	private BlockingQueue<Runnable> queue = null;
	
	private ReentrantLock lock = null;
	
	public Executor(BlockingQueue<Runnable> queue, ReentrantLock lock) {
		super("ZkRegisterSubscribe");
		this.queue = queue;
		this.lock = lock;
	}
	
	@Override
	public void run() {

		while(!stop) {
			Runnable task = null;
			try {
				lock.lock();
				task = queue.poll(60, TimeUnit.SECONDS);
				if(task == null) {
					stop = true;
				}
			} catch (InterruptedException e) {
			} finally {
				lock.unlock();
			}
			if(task != null) {
				task.run();
			}
		}

	}
	
	public boolean isStoped() {
		return stop;
	}
}
