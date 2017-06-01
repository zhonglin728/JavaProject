package com.pingan.util;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * 多线程执行任务 每个任务模拟1秒的耗时操作！
 * @author ZhongLin728
 *
 */
public class ExecutorServiceThread {
	
	public static void main(String[] args) {
		 long timeStart = System.currentTimeMillis(); 
		 
		  int taskSize = 4;//线程数量
		  ExecutorService pool = Executors.newFixedThreadPool(taskSize);
		  Set<Callable<String>> callables = new HashSet<Callable<String>>(); 
		  
			callables.add(new Callable<String>() {
				@Override
				public String call() throws Exception {
					JsoupHttp.login();
					return UUID.randomUUID().toString()+"线程1";
				}
			});
			callables.add(new Callable<String>() {
				@Override
				public String call() throws Exception {
					JsoupHttp.login();
					return UUID.randomUUID().toString()+"线程2";
				}
			});
			callables.add(new Callable<String>() {
				@Override
				public String call() throws Exception {
					JsoupHttp.login();
					return UUID.randomUUID().toString()+"线程3";
				}
			});
			callables.add(new Callable<String>() {
				@Override
				public String call() throws Exception {
					JsoupHttp.login();
					return UUID.randomUUID().toString()+"线程4";
				}
			});
			try {
				List<Future<String>> invokeAll = pool.invokeAll(callables);
				for (Future<String> future : invokeAll) {
					try {
						System.out.println(future.get());
					} catch (ExecutionException e) {
						e.printStackTrace();
					}
				}
				long timeEnd = System.currentTimeMillis();
				System.out.println("方法运行时间" + (timeEnd-timeStart) + " ms");  
			} catch (InterruptedException e) {
				e.printStackTrace();
			}finally{
				pool.shutdown();
			}
			
			
			
	}
	
	


}
