package org.mos.tools.thread;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.apache.felix.ipojo.annotations.Instantiate;
import org.apache.felix.ipojo.annotations.Provides;
import org.mos.tools.thread.exception.ThreadPoolAlreadyExistsException;
import org.mos.tools.thread.exception.ThreadPoolNotExistsException;

import lombok.extern.slf4j.Slf4j;
import onight.osgi.annotation.NActorProvider;
import onight.tfw.ntrans.api.ActorService;
import onight.tfw.outils.conf.PropHelper;

@NActorProvider
@Provides(specifications = { ActorService.class }, strategy = "SINGLETON")
@Instantiate(name = "bc_thread_manager")
@Slf4j
public class ThreadExecutorManager implements ActorService {
	PropHelper prop = new PropHelper(null);
	ForkJoinPool pools = new ForkJoinPool();
	
	private ConcurrentHashMap<String, ForkJoinPool> executorMap = new ConcurrentHashMap<>();
	ThreadMonitor monitor = null;

	public ThreadExecutorManager() {
//		monitor = new ThreadMonitor();
//		monitor.addExecutor("default_thread", pools);
//		Thread monitorThread = new Thread(monitor);
//		monitorThread.start();
	}

	public ExecutorService getOrCreateForkJoinPool() throws ThreadPoolAlreadyExistsException {
		return pools;
	}

	// private void check(String name) throws ThreadPoolAlreadyExistsException {
	// if (executorMap.containsKey(name)) {
	// // 如果已经创建了对象的线程池，抛出异常
	// throw new ThreadPoolAlreadyExistsException(
	// "线程[" + name + "]已经创建了类型为" + executorMap.get(name).getExecutorType() +
	// "的线程池");
	// }
	// }
}
