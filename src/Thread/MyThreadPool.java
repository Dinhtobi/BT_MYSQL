package Thread;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class MyThreadPool {
	public static void ThreadPool() {
		ArrayBlockingQueue<Runnable> hangdoi = new ArrayBlockingQueue<Runnable>(100);
		
		ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(5, 7, 2, TimeUnit.SECONDS, hangdoi);
		
		for(int i =0 ; i < 10; i ++) {
			threadPoolExecutor.execute(new RunPool(i));
		}
	}
	public static void ThreadExcutorService() {
		ExecutorService pool = Executors.newScheduledThreadPool(4);
		for(int i = 0 ; i< 10 ; i++) {
			pool.submit(new RunPool(i));
		}
		try {
			pool.awaitTermination(1, TimeUnit.DAYS);
		} catch (Exception e) {
			// TODO: handle exception
		}
		pool.shutdown();
	}
	public static void main(String []args) {
		ThreadExcutorService();
	}
}
