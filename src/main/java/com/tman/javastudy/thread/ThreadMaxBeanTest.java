package com.tman.javastudy.thread;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;
import java.util.concurrent.*;

/**
 * Copyright 2010 MacroSAN, Co.Ltd. All rights reserved.
 * 文件名称  ：AnnotationBeandefinitionDemo
 * 描述    ：
 * 作者    ：haowanjin
 * 创建时间：2020/4/9 20:22
 * 审核人:
 * 审核时间:
 */
public class ThreadMaxBeanTest {
	private static volatile int count = 1;

	public static void main(String[] args) {
		/*DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
		AnnotatedBeanDefinitionReader reader = new AnnotatedBeanDefinitionReader(beanFactory);
		reader.register();*/
		ExecutorService threadPool = new ThreadPoolExecutor(2, 10,
				20, TimeUnit.SECONDS, new ArrayBlockingQueue<>(100),
				new ThreadPoolExecutor.AbortPolicy());

		ExecutorService threadPool1 = Executors.newSingleThreadExecutor();

		ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
		ThreadInfo[] threadInfos = threadMXBean.dumpAllThreads(false, false);
		for (ThreadInfo threadInfo : threadInfos) {
			System.out.println("[" + threadInfo.getThreadId() + " " + threadInfo.getThreadName()+"]");
		}

		count += 2;
		System.out.println(count);
	}
}
