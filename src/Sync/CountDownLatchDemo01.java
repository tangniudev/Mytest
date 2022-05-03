package Sync;

import java.util.concurrent.CountDownLatch;

/**
 * Description
 *
 * @author tyw
 * @since 2022/4/20
 */
public class CountDownLatchDemo01 {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(6);
        for (int i = 0 ; i < 6 ; i++){
            new Thread(()->{
                System.out.println(Thread.currentThread().getName()+"号同学离开了教室");
                countDownLatch.countDown();
            },String.valueOf(i)).start();
        }
        countDownLatch.await();
        System.out.println(Thread.currentThread().getName()+"班长锁门");
    }
}
