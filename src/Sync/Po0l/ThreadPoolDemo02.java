package Sync.Po0l;

import com.sun.corba.se.spi.orbutil.threadpool.ThreadPool;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Description
 *
 * @author tyw
 * @since 2022/4/21
 */
public class ThreadPoolDemo02 {
    public static void main(String[] args) {
        ThreadPoolExecutor pool1 = new ThreadPoolExecutor(3, 5, 3, TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(4), Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy());
        try {
            for (int i = 0; i < 10; i++) {
                pool1.execute(() -> {
                    System.out.println(Thread.currentThread().getName() + "办理业务");
                });
            }
        }finally {
            pool1.shutdown();
        }
    }
}
