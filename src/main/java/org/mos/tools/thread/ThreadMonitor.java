package org.mos.tools.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ThreadPoolExecutor;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
public class ThreadMonitor implements Runnable {
	private ConcurrentHashMap<String, ForkJoinPool> executors = new ConcurrentHashMap<>();
	private boolean run = true;
	private int delayMs = 30000;

	public void addExecutor(String threadName, ForkJoinPool executor) {
		while (run) {
			run = false;
			executors.put(threadName, executor);
			run = true;
			break;
		}
	}

	@Override
	public void run() {
		while (run) {
			run = false;
			
			executors.forEach((threadName, executor)->{
				log.info(String.format(
						"[monitor] [%-20s] [%d/%d] Active: %d, Running: %d, Queue: %d, Queue Submission: %d, Steal: %d, isShutdown: %s, isTerminated: %s",
						threadName,
						executor.getPoolSize(), executor.getActiveThreadCount(),
						executor.getActiveThreadCount(), executor.getRunningThreadCount(),
						executor.getQueuedTaskCount(), executor.getQueuedSubmissionCount(), executor.getStealCount(),
						executor.isShutdown(), executor.isTerminated()));
			});
			
			run = true;
			try {
				Thread.sleep(delayMs);
			} catch (InterruptedException e) {
				log.error("" + e);
			}
		}
	}

}
