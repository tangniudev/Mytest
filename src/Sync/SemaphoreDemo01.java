package Sync;

import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * Description
 *
 * @author tyw
 * @since 2022/4/20
 * 6辆车，3个停车位
 */
public class SemaphoreDemo01 {
    public static void main(String[] args) {
        //设置许可
        Semaphore semaphore = new Semaphore(3);

        for (int i = 0; i < 6; i++){
            new Thread(()->{
                try {
                    //抢占资源
                    semaphore.acquire();
                    System.out.println(Thread.currentThread().getName()+"占据了车位");
                    TimeUnit.SECONDS.sleep(new Random(5).nextInt());

                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    //释放资源
                    System.out.println(Thread.currentThread().getName()+"----离开了车位");
                    semaphore.release();
                }
            },String.valueOf(i)).start();

        }
    }
}
