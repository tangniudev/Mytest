package Sync.Po0l;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Description
 *
 * @author tyw
 * @since 2022/4/21
 */
public class ThreadPoolDemo01 {
    public static void main(String[] args) {
        //一池5线程
//        ExecutorService pool1 = Executors.newFixedThreadPool(5);
        //一池一线程
//        ExecutorService pool1 = Executors.newSingleThreadExecutor();
        //可伸缩容量
        ExecutorService pool1 = Executors.newCachedThreadPool();
        //10个客户
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
