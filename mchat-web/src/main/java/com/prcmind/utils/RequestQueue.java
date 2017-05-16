package com.prcmind.utils;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RequestQueue {
	Logger log = LoggerFactory.getLogger(RequestQueue.class);
	// 请求队列唯一实例
	private static RequestQueue instance = new RequestQueue();
	// 请求线程池
	private ThreadPoolExecutor requestThreadPool;
	// 运行中的指令ID集合
	private List<String> orderIdRunning;
	
	public static RequestQueue getInstance() {
		return instance;
	}
	
	/**
	 * 构造一个请求队列
	 * 初始化线程池、计时器、指令ID集合
	 */
	private RequestQueue() {
		// 创建线程池：
		// 线程池维护线程的最少数量: 2
		// 线程池维护线程的最大数量: 10
		// 线程池维护线程所允许的空闲时间: 10秒
		// 每个线程任务队列为：3个
		// 超过线程池承受能力时抛出异常：AbortPolicy
		requestThreadPool = new ThreadPoolExecutor(2, 10, 10, 
			TimeUnit.SECONDS,
			new ArrayBlockingQueue<Runnable>(100000),
			new ThreadPoolExecutor.AbortPolicy()
		);
		orderIdRunning = new ArrayList<String>();
	}
	
	/**
	 * 增加指令到请求队列中执行
	 * @param order 业务指令
	 */
	public void addOrder(final String orderId, Runnable task) {
		synchronized (orderIdRunning) {
			// 判断当前执行队列中是否包含该指令，包含则不加入队列运行
			if (!orderIdRunning.contains(orderId)) {
				log.info("{addOrder}订单添加到队列");
				orderIdRunning.add(orderId);
				requestThreadPool.execute(task);
			} else {
				log.info("{addOrder}订单已经存在");
			}
		}
	}
	
	/**
	 * 从请求队列中移除该指令（请求任务执行完毕）
	 * @param order 业务指令
	 */
	public void removeOrder(String orderId) {
		synchronized (orderIdRunning) {
			orderIdRunning.remove(orderId);
		}
	}

}
